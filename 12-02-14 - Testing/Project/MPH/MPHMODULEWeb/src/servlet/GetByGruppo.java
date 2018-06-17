package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.Gruppo;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;

public class GetByGruppo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetByGruppo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        Progetto p = (Progetto) request.getSession().getAttribute("progettotemp");
        for(ProgettoGruppo pg : p.getProgettigruppo())
        	for(ProgettoRelease pr : pg.getListaRelease())
        		if(pr.getId().compareTo(Long.parseLong(nome)) == 0)
        		{
        			request.getSession().setAttribute("progettoreleasetemp", pr);
        			request.getSession().setAttribute("gruppotemp", pg.getGruppo());
        		}
        RequestDispatcher disp = request.getRequestDispatcher("ElencoPerGruppo.jsp");
        disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("radioButtonSelectGruppo");
        Progetto p = (Progetto) request.getSession().getAttribute("progettotemp");
        for(ProgettoGruppo pg : p.getProgettigruppo())
        	if(pg.getGruppo().getNome().compareTo(nome) == 0)
        	{
        		request.getSession().setAttribute("gruppotemp", pg.getGruppo());
        		Set<ProgettoRelease> release =  (Set<ProgettoRelease>) pg.getListaRelease();
        		request.getSession().setAttribute("listareleasetemp", release);
        	}
        RequestDispatcher disp = request.getRequestDispatcher("ElencoPerGruppo.jsp");
        disp.forward(request, response);
	}
}
