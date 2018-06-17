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
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
import exception.TuplaNonTrovataException;

public class VisualizzaGruppo extends HttpServlet {

	private static final long serialVersionUID = 7215054155317609609L;

	public VisualizzaGruppo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nomeGrup = request.getParameter("radioButtonSelectGruppo");
		RequestDispatcher disp = request.getRequestDispatcher("HomeGruppo.jsp");
        try {
			Object obj = ContextUtil.getInitialContext().lookup("RegistrazioneStudente/remote");
			RegistrazioneStudenteRemote manager = (RegistrazioneStudenteRemote)
					PortableRemoteObject.narrow(obj, RegistrazioneStudenteRemote.class);
			Studente studente = (Studente) request.getSession().getAttribute("studente");
			for (Gruppo g : studente.getGruppi())
				if (g.getNome().compareTo(nomeGrup) == 0)
					request.getSession().setAttribute("Gruppo", g);
			manager.scegliProgetto(nomeGrup);
			List<ProgettoGruppo> lista2 = manager.getProgetti(studente);
			request.getSession().setAttribute("listaProgettiGruppo", lista2);
			request.getSession().setAttribute("control", 0);
			Object obj2 = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			ManagerStudenteRemote manager2 = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj2, ManagerStudenteRemote.class);
			List<Gruppo> lista = (List<Gruppo>) manager2.getGruppi(studente);
			request.getSession().setAttribute("ListaGruppi", lista);
			List<ProgettoGruppo> lista3 = manager2.getProgetti(studente);
			request.getSession().setAttribute("listaProgettiGruppo", lista3);
			List<Progetto> lista4 = manager2.getProgetti();
			request.getSession().setAttribute("ListaProgettiCompleta", lista4);
			
         }
        catch (TuplaNonTrovataException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Gruppo non trovato, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("HomeStudente.jsp");
		}
        catch (NamingException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("Index.jsp");
		}
        finally {
        	disp.forward(request, response);
        }
	}
}
