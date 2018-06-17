package servlet;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.Gruppo;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
import exception.CampiNonCompletiException;
import exception.GruppoEsistenteException;
import exception.TuplaNonTrovataException;

import sessionBean.ManagerStudenteRemote;
import sessionBean.RegistrazioneStudenteRemote;
import util.ContextUtil;

public class CreaGruppoRegistrazione extends HttpServlet {

	private static final long serialVersionUID = 8498943665762225303L;

	public CreaGruppoRegistrazione() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("labelNome");
        RequestDispatcher disp = request.getRequestDispatcher("SelezionaProgettoRegistrazione.jsp");
        try {
        	//Integer cond = (Integer) request.getSession().getAttribute("registrazione");
        	Object obj = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
        	ManagerStudenteRemote manager = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj, ManagerStudenteRemote.class);
			List<Progetto> proj = manager.getProgetti();
			request.getSession().setAttribute("listaProgetti", proj);
			RegistrazioneStudenteRemote manager2 = (RegistrazioneStudenteRemote) request.getSession().getAttribute("RegistrazioneBean");
			String active = request.getParameter("active");
			boolean c;
			if("ON".equals(active))
				c = true;
			else
				c = false;
			Studente s = (Studente) request.getSession().getAttribute("studente");
			Gruppo gruppo = manager2.creaGruppo(nome, c, s);
			request.getSession().setAttribute("SetGruppiAppartenente", s.getGruppi());
			request.getSession().setAttribute("gruppotemp", gruppo);
			List<Gruppo> lista = (List<Gruppo>) manager.getGruppi(s);
			request.getSession().setAttribute("ListaGruppi", lista);
			List<ProgettoGruppo> lista2 = manager.getProgetti(s);
			request.getSession().setAttribute("listaProgettiGruppo", lista2);
			List<Progetto> lista3 = manager.getProgetti();
			request.getSession().setAttribute("listaProgetti", lista3);
        }
		catch (NamingException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("Index.jsp");
		}
        catch (GruppoEsistenteException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Gruppo già presente nel database, scegliere un altro nome!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("AmministraGruppiRegistrazione.jsp");	
		}
        catch (CampiNonCompletiException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Completare correttamente tutti i campi per procedere alla creazione del gruppo!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("AmministraGruppiRegistrazione.jsp");	
		}
        catch (TuplaNonTrovataException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Progetto non trovato, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("AmministraGruppiRegistrazione.jsp");
		}
        finally {
        	disp.forward(request, response);
        }
	}
}
