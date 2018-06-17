package servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import sessionBean.LoginRemote;
import sessionBean.ManagerAmministratoreRemote;
import sessionBean.ManagerProfessoreRemote;
import sessionBean.ManagerStudenteRemote;
import util.ContextUtil;
import entityBean.Amministratore;
import entityBean.Gruppo;
import entityBean.Professore;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
import exception.ErroreLoginException;
import exception.TuplaNonTrovataException;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("labelUser");
		String password = request.getParameter("labelPsw");
		//request.getSession().invalidate();
		RequestDispatcher disp = request.getRequestDispatcher("Index.jsp");
		try {
			Object obj = ContextUtil.getInitialContext().lookup("Login/remote");
			LoginRemote manager = (LoginRemote)
					PortableRemoteObject.narrow(obj, LoginRemote.class);
			Object Persona = manager.login(username, password);
			if (Persona instanceof Studente){
				Object obj2 = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
				ManagerStudenteRemote manager2 = (ManagerStudenteRemote)
						PortableRemoteObject.narrow(obj2, ManagerStudenteRemote.class);
				Studente studente = (Studente) Persona;
				request.getSession().setAttribute("studente", Persona);
				List<Gruppo> lista = (List<Gruppo>) manager2.getGruppi(studente);
				request.getSession().setAttribute("ListaGruppi", lista);
				List<Gruppo> listaCompleta = (List<Gruppo>) manager2.getListaGruppiConPosti((Studente)Persona);
				request.getSession().setAttribute("ListaGruppiCompleta", listaCompleta);
				List<ProgettoGruppo> lista2 = manager2.getProgetti(studente);
				request.getSession().setAttribute("listaProgettiGruppo", lista2);
				disp = request.getRequestDispatcher("HomeStudente.jsp");
			}else
			if (Persona instanceof Amministratore){
				Object obj2 = ContextUtil.getInitialContext().lookup("ManagerAmministratore/remote");
				ManagerAmministratoreRemote manager2 = (ManagerAmministratoreRemote)
						PortableRemoteObject.narrow(obj2, ManagerAmministratoreRemote.class);
				List<Professore> lista = (List<Professore>) manager2.getListaProfessori();
				request.getSession().setAttribute("ListaProf", lista);
				List<Studente> stud = manager2.getListaStudenti();
				request.getSession().setAttribute("ListaStud", stud);
				List<Gruppo> grup = manager2.getListaGruppi();
				request.getSession().setAttribute("ListaGruppi", grup);
				request.getSession().setAttribute("step", 0);
				request.getSession().setAttribute("amministratore", Persona);
				disp = request.getRequestDispatcher("HomeAmministratore.jsp");
			}else
			if (Persona instanceof Professore){
				Object obj2 = ContextUtil.getInitialContext().lookup("ManagerProfessore/remote");
				ManagerProfessoreRemote manager2 = (ManagerProfessoreRemote)
						PortableRemoteObject.narrow(obj2, ManagerProfessoreRemote.class);
				Professore prof = (Professore) Persona;
				request.getSession().setAttribute("professore", Persona);
				request.getSession().setAttribute("step1", 1);
				request.getSession().setAttribute("step2", 0);
				List<Progetto> lista = (List<Progetto>) manager2.getProgetti(prof);
				request.getSession().setAttribute("ListaProgetti", lista);
				disp = request.getRequestDispatcher("HomeProfessore.jsp");
			}
		}
		catch (ErroreLoginException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Username e/o password potrebbero non essere corretti, riprovare!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("Index.jsp");
		}
		catch(NamingException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("Index.jsp");
		}
		catch (TuplaNonTrovataException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema,riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("Index.jsp");
		}
		finally {
			disp.forward(request, response);
		}
	}
}
