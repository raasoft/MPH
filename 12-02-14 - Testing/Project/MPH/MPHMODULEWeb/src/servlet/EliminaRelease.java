package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.Releas;
import exception.TuplaNonTrovataException;
import sessionBean.CreaProgettoRemote;

public class EliminaRelease extends HttpServlet {

	private static final long serialVersionUID = 4485357562133061332L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		String idrelease = req.getParameter("idRelease");
		RequestDispatcher disp = req.getRequestDispatcher("HomeProfessore.jsp");
		try {
			CreaProgettoRemote manager = (CreaProgettoRemote) req.getSession().getAttribute("CreaProgettoBean");
			List<Releas> lista = manager.rimuoviRelease(idrelease);
			req.getSession().setAttribute("listaRelease", lista);
		}
		catch (TuplaNonTrovataException e) {
			req.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Release non trovata, riprovare!',0,0,0,0)</script>");
			req.getSession().invalidate();
			disp = req.getRequestDispatcher("HomeProfessore.jsp");
		}
		finally {
			disp.forward(req, resp);
		}
	}
}
