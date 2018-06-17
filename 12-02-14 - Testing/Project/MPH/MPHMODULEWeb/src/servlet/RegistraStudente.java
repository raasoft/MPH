package servlet;

import java.util.List;
import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.Gruppo;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
import exception.CampiNonCompletiException;
import exception.ConfrontoPasswordException;
import exception.EmailErrataException;
import exception.MatricolaNonConformeException;
import exception.MatricolaUnicaException;
import exception.PasswordCortaException;
import exception.StudenteEsistenteException;
import exception.TuplaNonTrovataException;

import sessionBean.ManagerStudenteRemote;
import sessionBean.RegistrazioneStudenteRemote;
import util.ContextUtil;

public class RegistraStudente extends HttpServlet {


	private static final long serialVersionUID = 3430086052311857838L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RegistrazioneStudenteRemote manager = (RegistrazioneStudenteRemote) req.getSession().getAttribute("RegistrazioneBean");
		manager.finalizza();
		manager.remove();
		//Studente studente = (Studente) req.getSession().getAttribute("studente");
		//Object obj2;
		//try {
			//obj2 = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			//ManagerStudenteRemote manager2 = (ManagerStudenteRemote)
				//	PortableRemoteObject.narrow(obj2, ManagerStudenteRemote.class);
			//List<ProgettoGruppo> lista2 = manager2.getProgetti(studente);
			//req.getSession().setAttribute("listaProgettiGruppo", lista2);
			

		//}
		//catch (NamingException e) {
		//	e.printStackTrace();
		//}
		req.getSession().invalidate();
		req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Ok</h1></center>Registrazione avvenuta con successo!',0,0,0,0)</script>");
		RequestDispatcher disp = req.getRequestDispatcher("Index.jsp");
		disp.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		String username = req.getParameter("labelUser");
		String password = req.getParameter("labelPsw");
		String confirmPsw = req.getParameter("labelConfirmPsw");
		String nome = req.getParameter("labelNome");
		String cognome = req.getParameter("labelCognome");
		String matricola = req.getParameter("labelMatricola");
		String email = req.getParameter("labelEmail");
		RequestDispatcher disp = req.getRequestDispatcher("AmministraGruppiRegistrazione.jsp");
		try {
			/*Object obj = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			ManagerStudenteRemote manager = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj, ManagerStudenteRemote.class);*/
			Object obj2 = ContextUtil.getInitialContext().lookup("RegistrazioneStudente/remote");
			RegistrazioneStudenteRemote manager2 = (RegistrazioneStudenteRemote)
					PortableRemoteObject.narrow(obj2, RegistrazioneStudenteRemote.class);
			Studente s = manager2.creaStudente(username, password, confirmPsw, nome, cognome, email, matricola);
			req.getSession().setAttribute("studente", s);
			List<Gruppo> lista = manager2.getListaGruppiConPosti();
			req.getSession().setAttribute("listaGruppi", lista);
			req.getSession().setAttribute("RegistrazioneBean", manager2);
			req.getSession().setAttribute("registrazione", 1);
		}
		catch (NamingException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			req.getSession().invalidate();
			disp = req.getRequestDispatcher("Index.jsp");;
		}
		catch (StudenteEsistenteException e) {
			req.getSession().setAttribute("password", password);
			req.getSession().setAttribute("confirmPsw", confirmPsw);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("matricola", matricola);
			req.getSession().setAttribute("email", email);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Studente già presente nel database, scegliere un altro username!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("Registrazione.jsp");
		}
		catch (CampiNonCompletiException e) {
			req.getSession().setAttribute("username",username);
			req.getSession().setAttribute("password", password);
			req.getSession().setAttribute("confirmPsw", confirmPsw);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("matricola", matricola);
			req.getSession().setAttribute("email", email);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Completa correttamente tutti i campi per procedere alla registrazione!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("Registrazione.jsp");
		}
		catch (PasswordCortaException e) {
			req.getSession().setAttribute("username",username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("matricola", matricola);
			req.getSession().setAttribute("email", email);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La password deve avere una lunghezza minima di 4 caratteri!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("Registrazione.jsp");
		}
		catch (EmailErrataException e) {
			req.getSession().setAttribute("username",username);
			req.getSession().setAttribute("password", password);
			req.getSession().setAttribute("confirmPsw", confirmPsw);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("matricola", matricola);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Indirizzo email inserito non valido, riprovare!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("Registrazione.jsp");
		}
		catch (ConfrontoPasswordException e) {
			req.getSession().setAttribute("username",username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("matricola", matricola);
			req.getSession().setAttribute("email", email);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La password confermata non corrisponde a quella inserita, riprovare!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("Registrazione.jsp");
		}
		catch (MatricolaNonConformeException e){
			req.getSession().setAttribute("username",username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("password", password);
			req.getSession().setAttribute("confirmPsw", confirmPsw);
			req.getSession().setAttribute("email", email);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>La matricola deve contenere soli numeri!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("Registrazione.jsp");
		}
		catch (MatricolaUnicaException e) {
			req.getSession().setAttribute("username",username);
			req.getSession().setAttribute("nome", nome);
			req.getSession().setAttribute("cognome", cognome);
			req.getSession().setAttribute("password", password);
			req.getSession().setAttribute("confirmPsw", confirmPsw);
			req.getSession().setAttribute("email", email);
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Matricola errata, riprovare!',0,0,0,0)</script>");
			disp = req.getRequestDispatcher("Registrazione.jsp");
		}
		finally {
			disp.forward(req, resp);
		}
	}
}
