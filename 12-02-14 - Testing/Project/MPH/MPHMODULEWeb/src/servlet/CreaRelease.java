package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessionBean.CreaProgettoRemote;
import entityBean.Releas;
import exception.CampiNonCompletiException;

public class CreaRelease extends HttpServlet {

	private static final long serialVersionUID = 8238073413389922128L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		String tipo = req.getParameter("labelTipo");
		String ctrlStep = req.getParameter("controlloStep");
		RequestDispatcher disp = null;
			try {
				if(ctrlStep.compareTo("Step2") == 0)
				{
					disp = req.getRequestDispatcher("CreaProgettoStep2.jsp");
					String data = req.getParameter("data");
					//Object obj = ContextUtil.getInitialContext().lookup("ManagerProfessore/remote");
					//ManagerProfessoreRemote manager2 = (ManagerProfessoreRemote)
							//PortableRemoteObject.narrow(obj, ManagerProfessoreRemote.class);
					//Professore p = manager2.getProfessoreByUsername(username);
					//req.getSession().setAttribute("professore", p);
					CreaProgettoRemote manager = (CreaProgettoRemote) req.getSession().getAttribute("CreaProgettoBean");
					List<Releas> release = manager.aggiungiRelease(tipo, data);
					req.getSession().setAttribute("listaRelease", release);
				}
				else 
				{
					disp = req.getRequestDispatcher("CreaProgettoStep3.jsp");
					String data2 = req.getParameter("data2");
					String tipo2 = req.getParameter("Tipo2");
					CreaProgettoRemote manager = (CreaProgettoRemote) req.getSession().getAttribute("CreaProgettoBean");
					List<Releas> release = manager.aggiungiRelease(tipo2, data2);
					req.getSession().setAttribute("listaRelease", release);
				}
			}
			/*catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			catch (CampiNonCompletiException e) {
				req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Completare correttamente tutti i campi per procedere alla creazione della release!',0,0,0,0)</script>");
				disp = req.getRequestDispatcher("CreaProgettoStep2.jsp");
				disp.forward(req, resp);
				return;
			}
			catch (Exception e) {
				req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
				disp = req.getRequestDispatcher("HomeProfessore.jsp");
				disp.forward(req, resp);
				return;
			}
			disp.forward(req, resp);
			return;
	}
}
