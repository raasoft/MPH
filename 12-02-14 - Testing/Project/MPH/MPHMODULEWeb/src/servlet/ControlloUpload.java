package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.Releas;

/**
 * Servlet implementation class ControlloUpload
 */
public class ControlloUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlloUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Releas> release = (List<Releas>)request.getSession().getAttribute("listareleasedacaricare");
		RequestDispatcher disp = request.getRequestDispatcher("UploadFile.jsp");
		if (release != null) {
			if (release.size() == 0) {
				 disp = request.getRequestDispatcher("HomeProgettoStudente.jsp");
				 request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Info</h1></center>Non ci sono release da caricare!',0,0,0,0)</script>");

			}
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
