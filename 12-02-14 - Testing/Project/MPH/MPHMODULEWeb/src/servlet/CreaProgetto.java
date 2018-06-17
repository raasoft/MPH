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

import entityBean.Professore;
import entityBean.Progetto;
import exception.CampiNonCompletiException;
import exception.ProgettoEsistenteException;
import exception.TuplaNonTrovataException;

import sessionBean.CreaProgettoRemote;
import sessionBean.ManagerProfessoreRemote;
import util.ContextUtil;

public class CreaProgetto extends HttpServlet {

	private static final long serialVersionUID = -7678304799654875594L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CreaProgettoRemote manager = (CreaProgettoRemote) req.getSession().getAttribute("CreaProgettoBean");
		if(req.getParameter("conferma").compareTo("Ok") == 0)
			manager.finalizzaProgetto();
		else
			manager.annulla();
		RequestDispatcher disp = req.getRequestDispatcher("HomeProfessore.jsp");
		req.getSession().removeAttribute("listaRelease");
		req.getSession().removeAttribute("progetto");
		req.getSession().removeAttribute("CreaProgettoBean");
		req.getSession().setAttribute("step2", 0);
		Object obj2;
		try {
			obj2 = ContextUtil.getInitialContext().lookup("ManagerProfessore/remote");
			ManagerProfessoreRemote manager2 = (ManagerProfessoreRemote)
					PortableRemoteObject.narrow(obj2, ManagerProfessoreRemote.class);
			Professore prof = (Professore) req.getSession().getAttribute("professore");
			List<Progetto> lista = (List<Progetto>) manager2.getProgetti(prof);
			req.getSession().setAttribute("ListaProgetti", lista);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		disp.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		String nome = req.getParameter("labelNome");
		String descrizione = req.getParameter("labelDescrizione");
		String corso = req.getParameter("labelCorso");
		String username = req.getParameter("username");
		String ctrlStep = req.getParameter("controlloStep");
		RequestDispatcher disp;
		if (ctrlStep.compareTo("Step1") == 0)
			disp = req.getRequestDispatcher("CreaProgettoStep2.jsp");
		else
			disp = req.getRequestDispatcher("HomeProfessore.jsp");
		try {
			Object obj = ContextUtil.getInitialContext().lookup("CreaProgetto/remote");
			CreaProgettoRemote manager = (CreaProgettoRemote)
					PortableRemoteObject.narrow(obj, CreaProgettoRemote.class);
			Progetto p = manager.creaProgetto(nome, descrizione, corso, username);
			req.getSession().setAttribute("progetto", p);
			obj = ContextUtil.getInitialContext().lookup("ManagerProfessore/remote");
			ManagerProfessoreRemote manager2 = (ManagerProfessoreRemote)
					PortableRemoteObject.narrow(obj, ManagerProfessoreRemote.class);
			Professore prof = manager2.getProfessoreByUsername(username);
			req.getSession().setAttribute("step2", 1);
			req.getSession().setAttribute("professore", prof);
			req.getSession().setAttribute("CreaProgettoBean", manager);
		}
		catch (NamingException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			req.getSession().invalidate();
			disp = req.getRequestDispatcher("Index.jsp");
		}
		catch (TuplaNonTrovataException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Professore non trovato, riprovare!',0,0,0,0)</script>");
			req.getSession().invalidate();
			disp = req.getRequestDispatcher("Index.jsp");
		}
		catch (ProgettoEsistenteException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Progetto già presente nel database, scegliere un altro nome!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("CreaProgettoStep1.jsp");
		}
		catch (CampiNonCompletiException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Completare correttamente tutti i campi per procedere alla creazione del progetto!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("CreaProgettoStep1.jsp");
		}
		finally {
        	disp.forward(req, resp);
        }
	}
}
