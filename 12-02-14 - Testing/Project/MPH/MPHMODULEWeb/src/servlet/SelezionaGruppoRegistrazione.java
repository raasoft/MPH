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

import sessionBean.ManagerStudenteRemote;
import sessionBean.RegistrazioneStudenteRemote;
import util.ContextUtil;

import entityBean.Gruppo;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
import exception.GruppoCompletoException;
import exception.GruppoNonTrovatoException;
import exception.TuplaNonTrovataException;

public class SelezionaGruppoRegistrazione extends HttpServlet {

	private static final long serialVersionUID = -279002021671999798L;

	public SelezionaGruppoRegistrazione() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Studente studente = (Studente) request.getSession().getAttribute("studente");
        String nomeGruppo = request.getParameter("radioButtonSelectGruppo");
        RequestDispatcher disp = request.getRequestDispatcher("RiepilogoRegistrazione.jsp");
        try {
        	Object obj = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			ManagerStudenteRemote manager = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj, ManagerStudenteRemote.class);
			List<Gruppo> grp = manager.getListaGruppi();
			Gruppo gruppo = null;
			for (Gruppo g: grp)
				if (g.getNome().compareTo(nomeGruppo) == 0)
					gruppo = g;
        	Integer ctrl = (Integer) request.getSession().getAttribute("registrazione");
        	request.getSession().setAttribute("gruppotemp", gruppo);
        	if(ctrl != null)
        	{
        		RegistrazioneStudenteRemote manager2 = (RegistrazioneStudenteRemote) request.getSession().getAttribute("RegistrazioneBean");
    			manager2.aggiungiAGruppoRegistrazione(studente, nomeGruppo);
    			/*Object obj = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
    			ManagerStudenteRemote manager = (ManagerStudenteRemote)
    					PortableRemoteObject.narrow(obj, ManagerStudenteRemote.class);*/
        		if(ctrl == 1)
        		{
                	List<Gruppo> lista = (List<Gruppo>) manager.getGruppi(studente);
        			request.getSession().setAttribute("ListaGruppi", lista);
        			List<ProgettoGruppo> lista2 = manager2.getProgetti(studente);
        			request.getSession().setAttribute("listaProgettiGruppo", lista2);
        		}
        		/*else
        		{
        			
        			Studente s = manager.aggiungiAGruppo(studente, nomeGruppo);
                	List<Gruppo> lista = (List<Gruppo>) manager.getGruppi(studente);
        			request.getSession().setAttribute("ListaGruppi", lista);
        			List<ProgettoGruppo> lista2 = manager.getProgetti(studente);
        			request.getSession().setAttribute("listaProgettiGruppo", lista2);
        		}*/
        	}
        	
        }
        catch (NamingException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
        	request.getSession().invalidate();
        	disp = request.getRequestDispatcher("Index.jsp");
        } 
        catch (GruppoCompletoException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Gruppo completo, selezionare un altro gruppo!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("AmministraGruppiRegistrazione.jsp");
		} 
        catch (GruppoNonTrovatoException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Gruppo non trovato, riprovare!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("AmministraGruppiRegistrazione.jsp");
		}
        catch (TuplaNonTrovataException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Progetto non trovato, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("AmministraGruppiRegistrazione.jsp");
		}
        finally {
        	disp.forward(request, response);
        }		
	}
}
