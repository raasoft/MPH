package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entityBean.FileRelease;
import exception.TuplaNonTrovataException;

import sessionBean.ManagerReleaseRemote;
import sessionBean.ManagerProfessoreRemote;
import util.ContextUtil;

/**
 * Servlet implementation class Download
 */
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final int DEFAULT_BUFFER_SIZE = 100*1024;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("nome");
		BufferedInputStream input = null;
        BufferedOutputStream output = null;
        //RequestDispatcher disp = request.getRequestDispatcher("HomeRelease.jsp");
		try{
			Object obj= ContextUtil.getInitialContext().lookup("ManagerRelease/remote");
			ManagerReleaseRemote manager = (ManagerReleaseRemote)
					PortableRemoteObject.narrow(obj, ManagerReleaseRemote.class);
			FileRelease file = manager.getById(Long.valueOf(id));
            File f = new File(file.getFile());
			String contentType = getServletContext().getMimeType(f.getName());
            if (contentType == null) 
                contentType = "application/octet-stream";
            response.reset();
            response.setBufferSize(DEFAULT_BUFFER_SIZE);
            response.setContentType(contentType);
            response.setContentLength((int) f.length());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getFile() + "\"");
            FileInputStream fi = new FileInputStream(file.getFile());				              	
			input = new BufferedInputStream(fi, DEFAULT_BUFFER_SIZE);				            
			output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);                    	
			}
		}
		catch(NamingException e)
		{
			e.printStackTrace();
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} 
		catch (TuplaNonTrovataException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			
		}
		finally {
			close(output);
			close(input);
			//disp.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	private static void close(Closeable resource) {
	    if (resource != null) {
	        try {
	            resource.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
