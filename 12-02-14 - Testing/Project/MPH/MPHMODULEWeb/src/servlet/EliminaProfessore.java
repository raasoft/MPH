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
import entityBean.Professore;
import entityBean.Studente;
import exception.TuplaNonTrovataException;

import sessionBean.ManagerAmministratoreRemote;
import sessionBean.ManagerProfessoreRemote;
import util.ContextUtil;

public class EliminaProfessore extends HttpServlet {

	private static final long serialVersionUID = 5545477936654082423L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		String usernameProf = req.getParameter("radioButtonSelectProf");
		String controllo = req.getParameter("controllo");
		RequestDispatcher disp = req.getRequestDispatcher("HomeAmministratore.jsp");
		if (controllo.compareTo("Visualizza") == 0) 
			disp = req.getRequestDispatcher("VisualizzaProfilo.jsp");
		try {
			if (controllo.compareTo("Visualizza") == 0){
				if (usernameProf != null) {
					Object obj2 = ContextUtil.getInitialContext().lookup("ManagerProfessore/remote");
					ManagerProfessoreRemote manager2 = (ManagerProfessoreRemote)
							PortableRemoteObject.narrow(obj2, ManagerProfessoreRemote.class);
					Professore professore = manager2.getProfessoreByUsername(usernameProf);
					req.setAttribute("professore", professore);
				}
			}
			else {	
				/*Object obj = ContextUtil.getInitialContext().lookup("ManagerAmministratore/remote");
				ManagerAmministratoreRemote manager = (ManagerAmministratoreRemote)
						PortableRemoteObject.narrow(obj, ManagerAmministratoreRemote.class);
				if (usernameProf != null){
					manager.eliminaProfessore(usernameProf);
					//List<Professore> prof = manager.getListaProfessori();
					//req.getSession().setAttribute("ListaProf", prof);
					List<Professore> lista = (List<Professore>) manager.getListaProfessori();
					req.getSession().setAttribute("ListaProf", lista);
					List<Studente> stud = manager.getListaStudenti();
					req.getSession().setAttribute("ListaStud", stud);
					List<Gruppo> grup = manager.getListaGruppi();
					req.getSession().setAttribute("ListaGruppi", grup);
				}*/
				req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La funzionalità non è stata implementata, modificare direttamente il database.',0,0,0,0)</script>");
				disp = req.getRequestDispatcher("SelezionaUtente.jsp");
			}
		}
		catch (NamingException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			req.getSession().invalidate();
			disp = req.getRequestDispatcher("Index.jsp");
		}
		catch (TuplaNonTrovataException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Professore non trovato, riprovare!',0,0,0,0)</script>");
			req.getSession().invalidate();
			disp = req.getRequestDispatcher("HomeAmministratore.jsp");
		}
		finally {
        	disp.forward(req, resp);
        }
	}
}
