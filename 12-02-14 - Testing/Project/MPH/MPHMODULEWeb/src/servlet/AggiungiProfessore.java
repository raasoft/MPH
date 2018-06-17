package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessionBean.ManagerAmministratoreRemote;
import sessionBean.ManagerStudenteRemote;
import util.ContextUtil;
import util.InvioEmail;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import entityBean.Professore;
import exception.CampiNonCompletiException;
import exception.EmailErrataException;
import exception.PasswordCortaException;
import exception.PasswordErrataException;
import exception.ProfessoreEsistenteException;
import exception.TelefonoErratoException;

public class AggiungiProfessore extends HttpServlet {

	private static final long serialVersionUID = -3766837580948055586L;
	
	public AggiungiProfessore(){
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("labelUser");
		String password = request.getParameter("labelPsw");
		String nome = request.getParameter("labelNome");
		String cognome = request.getParameter("labelCognome");
		String telefono = request.getParameter("labelTelefono");
		String email = request.getParameter("labelEmail");
		RequestDispatcher disp = request.getRequestDispatcher("HomeAmministratore.jsp");
			try {
				Object obj = ContextUtil.getInitialContext().lookup("ManagerAmministratore/remote");
				ManagerAmministratoreRemote manager = (ManagerAmministratoreRemote)
					PortableRemoteObject.narrow(obj, ManagerAmministratoreRemote.class);					
				manager.aggiungiProfessore(username, password, nome, cognome, telefono, email);
				request.getSession().setAttribute("AggiungiProfessoreBean", manager);
				List<Professore> prof = manager.getListaProfessori();
				request.getSession().setAttribute("ListaProf", prof);
				InvioEmail Mail = new InvioEmail();
				String contenutoEmail = "";
				contenutoEmail = "Siamo lieti di informarla che la registrazione è avvenuta con successo! \nI suoi dati per autenticarsi sono\nUSERNAME:  ".concat(username).concat("\nPASSWORD:  ").concat(password);
				Mail.test(email, "Creazione account", contenutoEmail);
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>OK</h1></center>Email inviata con successo al professore!',0,0,0,0)</script>");
			} 
			catch (NamingException e) {
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
				request.getSession().invalidate();
				disp = request.getRequestDispatcher("Index.jsp");
			}
			catch (ProfessoreEsistenteException e) {
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Professore già presente nel database, scegliere un altro username!',0,0,0,0)</script>");
				disp = request.getRequestDispatcher("HomeAmministratore.jsp");
			}
			catch (CampiNonCompletiException e) {
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Completare correttamente tutti i campi per registrare professore!',0,0,0,0)</script>");
				disp = request.getRequestDispatcher("HomeAmministratore.jsp");
			}
			catch (TelefonoErratoException e) {
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("nome", nome);
				request.getSession().setAttribute("cognome", cognome);
				request.getSession().setAttribute("password", password);
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("step", 1);
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Hai inserito un numero di telefono non valido!',0,0,0,0)</script>");
				disp = request.getRequestDispatcher("HomeAmministratore.jsp");
			}
			catch (EmailErrataException e){
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("nome", nome);
				request.getSession().setAttribute("cognome", cognome);
				request.getSession().setAttribute("password", password);
				request.getSession().setAttribute("telefono", telefono);
				request.getSession().setAttribute("step", 1);
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Hai inserito una mail non valida!',0,0,0,0)</script>");
				disp = request.getRequestDispatcher("HomeAmministratore.jsp");
			}
			catch (PasswordCortaException e){
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("nome", nome);
				request.getSession().setAttribute("cognome", cognome);
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("telefono", telefono);
				request.getSession().setAttribute("step", 1);
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Password troppo corta, minima lunghezza 4 caratteri!',0,0,0,0)</script>");
				disp = request.getRequestDispatcher("HomeAmministratore.jsp");
			}
			catch (Exception e){
				// TODO Auto-generated catch block
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Email di avvenuta registrazione non inviata al professore!<br><h5>Nota: questo può essere dovuto al fatto che siete connessi attraverso un server proxy</h5>',0,0,0,0)</script>");
			}
			finally {
	        	disp.forward(request, response);
	        }
	}
}
