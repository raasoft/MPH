package servlet;

import java.io.IOException;
import java.util.List;

import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessionBean.CreaProgettoRemote;
import sessionBean.ManagerProfessoreRemote;
import sessionBean.ManagerStudenteRemote;
import util.ContextUtil;
import entityBean.ProgettoRelease;
import entityBean.Releas;
import exception.CampiNonCompletiException;

/**
 * Servlet implementation class DettaglioRelease
 */
public class DettaglioRelease extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettaglioRelease() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("radioButtonSelectRelease");
		RequestDispatcher disp = null;
		try {
			disp = request.getRequestDispatcher("DettaglioReleaseCondivise.jsp");
			Object obj = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			ManagerStudenteRemote manager = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj, ManagerStudenteRemote.class);
			ProgettoRelease pr = manager.getProgettoReleaseById(id);
			request.getSession().setAttribute("progettoRelease", pr);
		}
		/*catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		catch (Exception e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("HomeStudente.jsp");
			disp.forward(request, response);
			return;
		}
		disp.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
