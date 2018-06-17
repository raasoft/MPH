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
import sessionBean.ManagerProfessoreRemote;
import sessionBean.ManagerStudenteRemote;
import util.ContextUtil;
import entityBean.Gruppo;
import entityBean.Professore;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
import exception.TuplaNonTrovataException;

/**
 * Servlet implementation class AggiungiProgetto
 */
public class AggiungiProgetto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiProgetto() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        Gruppo grup = (Gruppo)request.getSession().getAttribute("Gruppo");
        String Nomeproj = request.getParameter("radioButtonSelectProgetto");
		RequestDispatcher disp = request.getRequestDispatcher("HomeStudente.jsp");
		try {
			Object obj = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			ManagerStudenteRemote manager = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj, ManagerStudenteRemote.class);
			Progetto proj = manager.getProgettoByNome(Nomeproj);
			manager.aggiungiProgettoAGruppo(grup, proj);
			Studente s = (Studente) request.getSession().getAttribute("studente");
			List<ProgettoGruppo> listaPj = manager.getProgetti(s);
			request.getSession().setAttribute("listaProgettiGruppo", listaPj);
			}
		catch (NamingException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("Index.jsp");
		} catch (TuplaNonTrovataException e) {
			// TODO Auto-generated catch block
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Errore di sistema nella associazione del progetto al gruppo!',0,0,0,0)</script>");
		}
		finally {
        	disp.forward(request, response);
        }
	}
}

