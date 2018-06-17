package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;

public class GetByTipo extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public GetByTipo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        Progetto p = (Progetto) request.getSession().getAttribute("progettotemp");
        Releas r = (Releas) request.getSession().getAttribute("releasetemp");
        for(ProgettoGruppo pg : p.getProgettigruppo())
        	if(pg.getGruppo().getNome().compareTo(nome) == 0)
        	{
        		request.getSession().setAttribute("gruppotemp", pg.getGruppo());
        		for(ProgettoRelease pr : pg.getListaRelease())
        			if(pr.getIdRelease().getId().compareTo(r.getId()) == 0)
        				request.getSession().setAttribute("progettoreleasetemp", pr);
        	}
        RequestDispatcher disp = request.getRequestDispatcher("HomeRelease.jsp");
        disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("radioButtonSelectTipo");
        Progetto p = (Progetto) request.getSession().getAttribute("progettotemp");
        for(Releas r : p.getListaRelease())
        	if(r.getId().compareTo(Long.parseLong(nome)) == 0)
        		request.getSession().setAttribute("releasetemp", r);
        //RequestDispatcher disp = request.getRequestDispatcher("SelezionaReleasePerTipo.jsp");
        RequestDispatcher disp = request.getRequestDispatcher("ElencoPerTipo.jsp");
        disp.forward(request, response);
	}
}
