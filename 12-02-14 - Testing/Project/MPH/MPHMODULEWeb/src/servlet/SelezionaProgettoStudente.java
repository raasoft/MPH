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

import sessionBean.ManagerStudenteRemote;
import sessionBean.RegistrazioneStudenteRemote;
import util.ContextUtil;

import entityBean.Gruppo;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
import exception.ProgettoGiaSeguitoException;
import exception.TuplaNonTrovataException;

public class SelezionaProgettoStudente extends HttpServlet {

	private static final long serialVersionUID = -4943413385909109550L;

	public SelezionaProgettoStudente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nomeProj = request.getParameter("radioButtonSelectProgetto");
		RequestDispatcher disp = request.getRequestDispatcher("HomeStudente.jsp");
        try {
        	Object obj2 = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			ManagerStudenteRemote manager2 = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj2, ManagerStudenteRemote.class);
        	Studente studente = (Studente) request.getSession().getAttribute("studente");
        	manager2.verificaSeSegueProgetto(studente, nomeProj);
			RegistrazioneStudenteRemote manager = (RegistrazioneStudenteRemote) request.getSession().getAttribute("RegistrazioneBean");
			manager.scegliProgetto(nomeProj);
			List<ProgettoGruppo> lista2 = manager.getProgetti(studente);
			request.getSession().setAttribute("listaProgettiGruppo", lista2);
			request.getSession().setAttribute("control", 0);
			List<Gruppo> lista = (List<Gruppo>) manager2.getGruppi(studente);
			request.getSession().setAttribute("ListaGruppi", lista);
			List<ProgettoGruppo> lista3 = manager2.getProgetti(studente);
			request.getSession().setAttribute("listaProgettiGruppo", lista3);
			manager.remove();
			
        }
        catch (TuplaNonTrovataException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Progetto non trovato, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("SelezionaProgettoStudente.jsp");
		}
        catch (NamingException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("Index.jsp");
		}
        catch (ProgettoGiaSeguitoException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Stai già seguendo questo progetto con un altro gruppo, selezionare un altro progetto!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("SelezionaProgettoStudente.jsp");
		}
        finally {
        	disp.forward(request, response);
        }
	}
}
