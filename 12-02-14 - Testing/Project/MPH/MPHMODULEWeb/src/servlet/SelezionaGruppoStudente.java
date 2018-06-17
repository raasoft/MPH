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
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
import exception.GruppoCompletoException;
import exception.GruppoNonTrovatoException;
import exception.ProgettoGiaSeguitoException;
import exception.TuplaNonTrovataException;

public class SelezionaGruppoStudente extends HttpServlet {

	private static final long serialVersionUID = 5329412594215270329L;

	public SelezionaGruppoStudente() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Studente studente = (Studente) request.getSession().getAttribute("studente");
        String nomeGruppo = request.getParameter("radioButtonSelectGruppo");
        RequestDispatcher disp = request.getRequestDispatcher("HomeStudente.jsp");
        try {
        	Object obj2 = ContextUtil.getInitialContext().lookup("RegistrazioneStudente/remote");
			RegistrazioneStudenteRemote manager2 = (RegistrazioneStudenteRemote)
					PortableRemoteObject.narrow(obj2, RegistrazioneStudenteRemote.class);
			/*Gruppo g = */manager2.aggiungiAGruppo(studente, nomeGruppo);
			Object obj = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			ManagerStudenteRemote manager = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj, ManagerStudenteRemote.class);
            List<Gruppo> lista = (List<Gruppo>) manager.getGruppi(studente);
    		request.getSession().setAttribute("ListaGruppi", lista);
    		List<Gruppo> listaCompleta = (List<Gruppo>) manager.getListaGruppiConPosti(studente);
			request.getSession().setAttribute("ListaGruppiCompleta", listaCompleta);
    		List<ProgettoGruppo> lista2 = manager2.getProgetti(studente);
    		request.getSession().setAttribute("listaProgettiGruppo", lista2);

			manager2.remove();
        }
        catch (NamingException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
        	request.getSession().invalidate();
        	disp = request.getRequestDispatcher("Index.jsp");
        } 
        catch (GruppoCompletoException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Gruppo completo, selezionare un altro gruppo!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("AmministraGruppiStudente.jsp");
		} 
        catch (GruppoNonTrovatoException e) {
        	request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Gruppo non trovato, riprovare!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("AmministraGruppiStudente.jsp");
		}
        catch (TuplaNonTrovataException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Progetto non trovato, riprovare!',0,0,0,0)</script>");
			request.getSession().invalidate();
			disp = request.getRequestDispatcher("AmministraGruppiStudente.jsp");
		}
        catch (ProgettoGiaSeguitoException e) {
			request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Il gruppo selezionato segue un progetto che tu stai già seguendo!',0,0,0,0)</script>");
			disp = request.getRequestDispatcher("AmministraGruppiStudente.jsp");
		}
        finally {
        	disp.forward(request, response);
        }		
	}
}
