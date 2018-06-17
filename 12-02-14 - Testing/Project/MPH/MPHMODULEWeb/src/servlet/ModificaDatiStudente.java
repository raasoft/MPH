package servlet;

import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.Studente;
import exception.CambioPswFallitoException;
import exception.CampiNonCompletiException;
import exception.ConfrontoPasswordException;
import exception.EmailErrataException;
import exception.EmailVuotaException;
import exception.MatricolaUnicaException;
import exception.PasswordCortaException;
import exception.PasswordErrataException;
import exception.TuplaNonTrovataException;
import exception.MatricolaNonConformeException;

import sessionBean.ManagerStudenteRemote;
import util.ContextUtil;

public class ModificaDatiStudente extends HttpServlet {

	private static final long serialVersionUID = -7465308434202092239L;

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
		String matricola = req.getParameter("labelMatricola");
		String username = req.getParameter("username");
		String nome = req.getParameter("labelNome");
		String cognome = req.getParameter("labelCognome");
		RequestDispatcher disp = req.getRequestDispatcher("HomeStudente.jsp");
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			ManagerStudenteRemote manager = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj, ManagerStudenteRemote.class);
			Studente s = manager.modificaDati(username, oldpsw, newpsw, confnewpsw, nome, cognome, email, matricola);
			req.getSession().setAttribute("studente", s);
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
			disp = req.getRequestDispatcher("HomeStudente.jsp");
		}
		catch (PasswordErrataException e) {
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confnewpsw", confnewpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("matricola", matricola);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La vecchia password inserita è errata!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloStudente.jsp");
		}
		catch (PasswordCortaException e) {
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("matricola", matricola);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La nuova password deve avere una lunghezza minima di 4 caratteri!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloStudente.jsp");
		}
		catch (CampiNonCompletiException e) {
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confnewpsw", confnewpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("matricola", matricola);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Possono rimanere vuoti solo i campi riguardanti le password, riempire correttamente gli altri prima di procedere!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloStudente.jsp");
		}
		catch (EmailErrataException e) {
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confnewpsw", confnewpsw);
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("matricola", matricola);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Indirizzo email inserito non valido, riprovare!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloStudente.jsp");
		}
		catch (ConfrontoPasswordException e) {
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("matricola", matricola);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La password confermata non corrisponde a quella inserita, riprovare!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloStudente.jsp");
		}
		catch (MatricolaNonConformeException e) {
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confnewpsw", confnewpsw);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("oldpsw", oldpsw);
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La matricola deve essere un numero, modificare prima di procedere!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloStudente.jsp");
		}
		catch (MatricolaUnicaException e) {
			req.getSession().setAttribute("username",username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("password", oldpsw);
			req.getSession().setAttribute("newpsw", newpsw);
			req.getSession().setAttribute("confirmPsw", confnewpsw);
			req.getSession().setAttribute("email", email);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Matricola errata, riprovare!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloStudente.jsp");
		}
		catch (CambioPswFallitoException e) {
			req.getSession().setAttribute("username",username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("email", email);
			req.getSession().setAttribute("matricola", matricola);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Per cambiare la password di accesso compilare correttamente i 3 campi relativi!!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("ModificaProfiloStudente.jsp");
		}
		finally {
        	disp.forward(req, resp);
        }
	}
}
