package servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.Professore;
import exception.CambioPswFallitoException;
import exception.CampiNonCompletiException;
import exception.ConfrontoPasswordException;
import exception.EmailErrataException;
import exception.EmailVuotaException;
import exception.PasswordCortaException;
import exception.PasswordErrataException;
import exception.TelefonoErratoException;
import exception.TuplaNonTrovataException;

import sessionBean.ManagerProfessoreRemote;
import util.ContextUtil;

public class ModificaDatiProfessore extends HttpServlet {

	private static final long serialVersionUID = -2322595792896741891L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		String oldpsw = req.getParameter("labelOldPsw");
		String newpsw = req.getParameter("labelNewPsw");
		String confnewpsw = req.getParameter("labelConfirmPsw");
		String email = req.getParameter("labelEmail");
		String telefono = req.getParameter("labelTelefono");
		String username = req.getParameter("username");
		String nome = req.getParameter("labelNome");
		String cognome = req.getParameter("labelCognome");
		RequestDispatcher disp = req.getRequestDispatcher("HomeProfessore.jsp");
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerProfessore/remote");
			ManagerProfessoreRemote manager = (ManagerProfessoreRemote)
					PortableRemoteObject.narrow(obj, ManagerProfessoreRemote.class);
			Professore p = manager.modificaDati(username, oldpsw, newpsw, confnewpsw, nome, cognome, email, telefono);
			req.getSession().setAttribute("professore", p);
			//req.getSession().setAttribute("ModificaProfBean", manager);
		}
		catch (NamingException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			req.getSession().invalidate();
			disp = req.getRequestDispatcher("Index.jsp");
		}
		catch (TuplaNonTrovataException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Professore non trovato, riprovare!',0,0,0,0)</script>");
			req.getSession().invalidate();
			disp = req.getRequestDispatcher("HomeProfessore.jsp");
		}
		catch (PasswordErrataException e) {
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confnewpsw", confnewpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("telefono", telefono);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La vecchia password inserita è errata!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloProfessore.jsp");
		}
		catch (PasswordCortaException e) {
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("telefono", telefono);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La nuova password deve avere una lunghezza minima di 4 caratteri!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloProfessore.jsp");
		}
		catch (CampiNonCompletiException e) {
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confnewpsw", confnewpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("telefono", telefono);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Possono rimanere vuoti i due campi riguardanti la nuova password e/o il campo telefono!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloProfessore.jsp");
		}
		catch (EmailErrataException e) {
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confnewpsw", confnewpsw);
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("telefono", telefono);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Indirizzo email inserito non valido, riprovare!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloProfessore.jsp");
		}
		catch (EmailVuotaException e) {
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confnewpsw", confnewpsw);
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("telefono", telefono);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Campo Email obbligatorio, completare prima di confermare!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloProfessore.jsp");
		}
		catch (ConfrontoPasswordException e) {
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("telefono", telefono);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La password confermata non corrisponde a quella inserita, riprovare!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloProfessore.jsp");
		}
		catch (TelefonoErratoException e) {
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confnewpsw", confnewpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Hai inserito un numero di telefono non valido!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloProfessore.jsp");
		}
		catch (CambioPswFallitoException e) {
			req.getSession().setAttribute("username",username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("telefono", telefono);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Per cambiare la password di accesso compilare correttamente i 3 campi relativi!!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloProfessore.jsp");
		}
		finally {
        	disp.forward(req, resp);
        }
	}
}
