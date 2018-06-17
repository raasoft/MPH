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

import sessionBean.ManagerAmministratoreRemote;
import util.ContextUtil;
import entityBean.Gruppo;
import entityBean.Professore;
import entityBean.Studente;
import exception.TuplaNonTrovataException;

/**
 * Servlet implementation class EliminaGruppo
 */
public class EliminaGruppo extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 4721969929889006775L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaGruppo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		String usernameGruppo = req.getParameter("radioButtonSelectGruppo");
		String controllo = req.getParameter("controllo2");
		RequestDispatcher disp = req.getRequestDispatcher("HomeAmministratore.jsp");
		if (controllo.compareTo("Visualizza") == 0) 
			disp = req.getRequestDispatcher("VisualizzaProfilo.jsp");
		try {
			if (controllo.compareTo("Visualizza") == 0){
				if (usernameGruppo != null) {
					Object obj2 = ContextUtil.getInitialContext().lookup("ManagerAmministratore/remote");
					ManagerAmministratoreRemote manager = (ManagerAmministratoreRemote)
							PortableRemoteObject.narrow(obj2, ManagerAmministratoreRemote.class);
					Gruppo grup = manager.getGruppoByNome(usernameGruppo);
					req.setAttribute("gruppo", grup);
				}
			}
			else {	
				/*Object obj = ContextUtil.getInitialContext().lookup("ManagerAmministratore/remote");
				ManagerAmministratoreRemote manager = (ManagerAmministratoreRemote)
						PortableRemoteObject.narrow(obj, ManagerAmministratoreRemote.class);
				if (usernameGruppo != null){
					manager.eliminaGruppo(usernameGruppo);
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
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Gruppo non trovato, riprovare!',0,0,0,0)</script>");
			req.getSession().invalidate();
			disp = req.getRequestDispatcher("HomeAmministratore.jsp");
		}
		finally {
        	disp.forward(req, resp);
        }
	}

}
