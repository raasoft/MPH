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
import sessionBean.ManagerReleaseRemote;
import util.ContextUtil;
import util.InvioEmail;

import entityBean.Gruppo;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Studente;
import exception.TuplaNonTrovataException;

/**
 * Servlet implementation class Condividi
 */
public class Condividi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Condividi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String nomeGruppo = request.getParameter("nome");
		String [] nomeGruppo = request.getParameterValues("nome");
		ProgettoRelease pr = (ProgettoRelease)request.getSession().getAttribute("releaseDaCond");
        RequestDispatcher disp = request.getRequestDispatcher("HomeProfessore.jsp");
		try
		{
			Object obj = ContextUtil.getInitialContext().lookup("ManagerRelease/remote");
			ManagerReleaseRemote manager = (ManagerReleaseRemote)
					PortableRemoteObject.narrow(obj, ManagerReleaseRemote.class);
			for (int i=0; i<nomeGruppo.length; i++)
				manager.CondividiRelease(pr.getId(), nomeGruppo[i]);
			InvioEmail Mail = new InvioEmail();
			String contenutoEmail = "";
			contenutoEmail = "La avvisiamo che è stata messa a disposizione dal professore la release condivisa: ".concat(pr.getIdRelease().getTipo());
			for(Studente s : pr.getIdProgettoGruppo().getGruppo().getStudenti())
				Mail.test(s.getEmail(), "Release condivisa", contenutoEmail);
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>OK</h1></center>Email inviata con successo agli studenti del gruppo!',0,0,0,0)</script>");
		}
		catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (TuplaNonTrovataException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Email di condivsione release non inviata allo studente<br><h5>Nota: questo può essere dovuto al fatto che siete connessi attraverso un server proxy</h5>',0,0,0,0)</script>");

		}
		finally {
        	disp.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Object obj = null;
	    RequestDispatcher disp = request.getRequestDispatcher("CondividiRelease.jsp");
		try {
			obj = ContextUtil.getInitialContext().lookup("ManagerAmministratore/remote");
			ManagerAmministratoreRemote manager = (ManagerAmministratoreRemote)
					PortableRemoteObject.narrow(obj, ManagerAmministratoreRemote.class);
			List<Gruppo> listaGruppi = manager.getListaGruppi();
			request.getSession().setAttribute("listaGruppiDaCond", listaGruppi);
			String idRelease = request.getParameter("nome");
		    ProgettoGruppo prgr = (ProgettoGruppo)request.getSession().getAttribute("progettoGruppo");
		    if (prgr == null){
		    	Object obj2 = ContextUtil.getInitialContext().lookup("ManagerRelease/remote");
				ManagerReleaseRemote manager2 = (ManagerReleaseRemote)
						PortableRemoteObject.narrow(obj2, ManagerReleaseRemote.class);
				ProgettoRelease x =  manager2.getProgettoRelease(Long.valueOf(idRelease));
				request.getSession().setAttribute("gruppoDaEscludere", x.getIdProgettoGruppo().getGruppo());
				for(ProgettoRelease pr : x.getIdProgettoGruppo().getListaRelease())
			      	if(pr.getId().compareTo(Long.valueOf(idRelease)) == 0)
			      		request.getSession().setAttribute("releaseDaCond", pr);
		    }
		    else{
		    	request.getSession().setAttribute("gruppoDaEscludere", prgr.getGruppo());
		    	 for(ProgettoRelease pr : prgr.getListaRelease())
				      	if(pr.getId().compareTo(Long.valueOf(idRelease)) == 0)
				      		request.getSession().setAttribute("releaseDaCond", pr);
		    }
		     
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNonTrovataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        disp.forward(request, response);
		
	}

}
