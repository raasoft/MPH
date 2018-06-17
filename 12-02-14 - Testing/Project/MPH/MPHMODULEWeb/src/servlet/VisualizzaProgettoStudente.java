package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessionBean.ManagerStudenteRemote;
import util.ContextUtil;

import entityBean.Gruppo;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;
import entityBean.Studente;
import exception.TuplaNonTrovataException;

public class VisualizzaProgettoStudente extends HttpServlet {

	private static final long serialVersionUID = 7045919012703097645L;

	public VisualizzaProgettoStudente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("radioButtonSelectProgetto");
        RequestDispatcher disp = request.getRequestDispatcher("HomeProgettoStudente.jsp");
        ProgettoGruppo p = null;
        Object obj2 = null;
		try 
		{
			obj2 = ContextUtil.getInitialContext().lookup("ManagerStudente/remote");
			ManagerStudenteRemote manager2 = (ManagerStudenteRemote)
					PortableRemoteObject.narrow(obj2, ManagerStudenteRemote.class);
			p = manager2.getProgettoGruppoById(id);
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
		catch (TuplaNonTrovataException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*List<ProgettoGruppo> lista = (List<ProgettoGruppo>) request.getSession().getAttribute("listaProgettiGruppo");
        for(ProgettoGruppo pg : lista)
        	if(pg.getId().compareTo(Long.parseLong(id)) == 0)
        	{
        		request.getSession().setAttribute("progetto", pg);
        		p = pg;
        	}*/
		request.getSession().setAttribute("progetto", p);
        {
	        Set<ProgettoRelease> lista3 = p.getListaRelease();
	        Progetto p1 = p.getProgetto();
	        List<Releas> lista2 = new ArrayList<Releas>();
	        boolean ctrl = false;
	        for (Releas r : p1.getListaRelease()){
	        	for (ProgettoRelease pr : lista3) {
	        		if (r.getId().compareTo(pr.getIdRelease().getId()) == 0)
	        		{
	        			ctrl = true;
	        		}
	        	}
	        	if(!ctrl)
	        	{
	        		if(r.isConsegnabile())
	        			lista2.add(r);
	        	}
	        	else
	        		ctrl = false;
	        }
	        request.getSession().setAttribute("listareleasedacaricare", lista2);
        }
        List<Gruppo> lista2 = (List<Gruppo>) request.getSession().getAttribute("ListaGruppi");
        Set<Studente> stud = new HashSet<Studente>();
        for(Gruppo g : lista2)
        	if(p.getGruppo().getNome().compareTo(g.getNome()) == 0)
        	{
        		request.getSession().setAttribute("gruppo", g);
        		stud.addAll(g.getStudenti());
        		request.getSession().setAttribute("listastud", stud);
        	}
        disp.forward(request, response);
	}
}
