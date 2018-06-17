package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUpload;

import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;
import exception.TuplaNonTrovataException;

import sessionBean.ManagerStudenteRemote;
import sessionBean.UploadReleaseBeanRemote;
import util.ContextUtil;

/**
 * Servlet implementation class UploadRelease
 */
public class UploadRelease extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String filePath = "mph/";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ProgettoGruppo prgr = (ProgettoGruppo)request.getSession().getAttribute("progetto");
        String idProg = String.valueOf(prgr.getId());
		RequestDispatcher disp = request.getRequestDispatcher("UploadFile.jsp");
			DiskFileUpload upload = new DiskFileUpload();
			List<FileItem> items;
			if(FileUpload.isMultipartContent(request)) 
			{
				try 
				{
					String submit = null, fileName = null, idRelease = null, descrizione = null;
					File file = null;
					items = upload.parseRequest(request);
					for(FileItem fi: items) 
					{
						if(fi.isFormField()) 
						{
							if(fi.getFieldName().equals("submit"))
							{
								submit = fi.getString();
							}
							if(fi.getFieldName().equals("labelTipoRelease"))
							{
								idRelease = fi.getString();
							}
							if(fi.getFieldName().equals("Commento"))
							{
								descrizione = fi.getString();
							}
							if(fi.getFieldName().equals("idprog"))
							{
								idProg = fi.getString();
							}
						}
						else
						{
							fileName = fi.getName();
							File dir = new File(filePath + "Temporanei/");
			            	if(!dir.exists()) dir.mkdirs();
			            	String id = UUID.randomUUID().toString();
			            	file = new File(dir, id);
			            	fi.write(file);
						}
					}
					if(file != null && !fileName.trim().isEmpty() && !idRelease.trim().isEmpty())
					{
						Object obj = ContextUtil.getInitialContext().lookup("UploadReleaseBean/remote");
						UploadReleaseBeanRemote manager = (UploadReleaseBeanRemote) PortableRemoteObject.narrow(obj, UploadReleaseBeanRemote.class);
						manager.creaRelease(idRelease, idProg);
						manager.uploadFile(file, fileName, descrizione, filePath);
						if(submit != null && !submit.equals("Carica"))
						{
							try
							{
								manager.finalizza();
								manager.remove();
								request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Info</h1></center>Release caricata con successo!',0,0,0,0)</script>");
							} 
							catch (Exception e)
							{
								request.setAttribute("messaggio", "Errore: " + e.getMessage());
							} 
							finally
							{
								disp = request.getRequestDispatcher("HomeStudente.jsp");
							}
						}
						else
						{
							request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Info</h1></center>File caricato con successo!',0,0,0,0)</script>");
						}
					}
					else 
					{
						request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Nessun file selezionato!',0,0,0,0)</script>");
					}
				}
				catch (NamingException e) 
				{
					request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore interno al sistema, riprovare!',0,0,0,0)</script>");
					request.getSession().invalidate();
					disp = request.getRequestDispatcher("Index.jsp");
				} catch (TuplaNonTrovataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else 
			{
				request.setAttribute("messaggio", "<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Si è verificato un errore durante il caricamento del file, riprovare!',0,0,0,0)</script>");
				disp = request.getRequestDispatcher("UploadFile.jsp");
			}
			disp.forward(request, response);
	}
}
