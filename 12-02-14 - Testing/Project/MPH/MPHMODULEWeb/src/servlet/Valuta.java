package servlet;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entityBean.ProgettoRelease;
import entityBean.Releas;
import entityBean.Studente;
import exception.TuplaNonTrovataException;

import sessionBean.ManagerProfessoreRemote;
import util.ContextUtil;
import util.InvioEmail;

/**
 * Servlet implementation class Valuta
 */
public class Valuta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Valuta() {
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
        response.setCharacterEncoding("UTF-8");
        String voto = request.getParameter("voto");
        String id = request.getParameter("nome");
        Releas rel = (Releas) request.getSession().getAttribute("releasetemp");
        RequestDispatcher disp = request.getRequestDispatcher("ElencoPerTipo.jsp");
        Set <Studente> stud = new HashSet<Studente>();
        if(!rel.isConsegnabile())
        {
        	try {
    	        Object obj= ContextUtil.getInitialContext().lookup("ManagerProfessore/remote");
    			ManagerProfessoreRemote manager = (ManagerProfessoreRemote)
    					PortableRemoteObject.narrow(obj, ManagerProfessoreRemote.class);
    			ProgettoRelease proRel = manager.Valuta(Integer.valueOf(voto), Long.valueOf(id));
    			stud.addAll(proRel.getIdProgettoGruppo().getGruppo().getStudenti());
    			Long id2 = rel.getId();
    			rel = null;
    			request.getSession().removeAttribute("releasetemp");
    			rel = manager.getReleaseById(id2);
    			request.getSession().setAttribute("releasetemp", rel);
    			InvioEmail Mail = new InvioEmail();
				String contenutoEmail = "";
				contenutoEmail = "Siamo lieti di informarla che è stata corretta la sua release:  ".concat(rel.getTipo()).concat(" \ncon valutazione pari a:  ").concat(voto);
				for(Studente s : stud)
					Mail.test(s.getEmail(), "Valutazione release", contenutoEmail);
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>OK</h1></center>Email inviata con successo agli studenti del gruppo!',0,0,0,0)</script>");
    		}
    		catch (NamingException e)
    		{
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		catch (NumberFormatException e) 
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
				e.printStackTrace();
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Email di valutazione non inviata allo studente<br><h5>Nota: questo può essere dovuto al fatto che siete connessi attraverso un server proxy</h5>',0,0,0,0)</script>");
			}
    		finally
    		{
    			disp.forward(request, response);
    		}
        }
        else
        {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Devi sospendere la consegna di queste release se vuoi dare i voti!',0,0,0,0)</script>");
        	disp.forward(request, response);
        }
		
	}

}
