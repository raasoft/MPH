package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.Progetto;

public class VisualizzaProgettoProfessore extends HttpServlet {

	private static final long serialVersionUID = 6530637258722580688L;

	public VisualizzaProgettoProfessore() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Progetto> lista = (List<Progetto>) request.getSession().getAttribute("ListaProgetti");
        String nome = request.getParameter("radioButtonSelectProgetto");
        RequestDispatcher disp = request.getRequestDispatcher("HomeProgettoProfessore.jsp");
        for (Progetto p: lista) {
        	if (p.getNome().compareTo(nome) == 0){
        		request.getSession().setAttribute("progettotemp", p);
        	}
        }
        disp.forward(request, response);
	}

}
