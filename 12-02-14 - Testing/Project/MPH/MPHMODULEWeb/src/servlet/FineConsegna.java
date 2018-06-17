package servlet;

import java.io.IOException;
import java.util.Set;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessionBean.ManagerProfessoreRemote;
import sessionBean.ManagerReleaseRemote;
import util.ContextUtil;

import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;
import exception.TuplaNonTrovataException;

/**
 * Servlet implementation class FineConsegna
 */
public class FineConsegna extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FineConsegna() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Releas r = (Releas) request.getSession().getAttribute("releasetemp");
		RequestDispatcher disp = request.getRequestDispatcher("ElencoPerTipo.jsp");
		try
		{
			Object obj2 = ContextUtil.getInitialContext().lookup("ManagerProfessore/remote");
			ManagerProfessoreRemote manager2 = (ManagerProfessoreRemote)
					PortableRemoteObject.narrow(obj2, ManagerProfessoreRemote.class);
			Object obj = ContextUtil.getInitialContext().lookup("ManagerRelease/remote");
			ManagerReleaseRemote manager = (ManagerReleaseRemote)
					PortableRemoteObject.narrow(obj, ManagerReleaseRemote.class);
			Long id = r.getId();
			Set<ProgettoGruppo> prg = r.getProgetto().getProgettigruppo();
			boolean ctrl = false;
			for(ProgettoGruppo p : prg)
			{
				for(ProgettoRelease pr : p.getListaRelease())
					if(pr.getIdRelease().getId().compareTo(r.getId()) == 0)
						ctrl = true;
				if(ctrl)
					ctrl = false;
				else
				{
					manager.AggiungiReleaseVotoNullo(r.getId(), p.getId());
				}
			}
			r = null;
			request.getSession().removeAttribute("releasetemp");
			r = manager2.fineConsegna(id);
			request.getSession().setAttribute("releasetemp", r);
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Info</h1></center>Chiusura della consegna delle release avvenuta con successo!',0,0,0,0)</script>");
		}
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (TuplaNonTrovataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
