<%@ page language="java" import="java.util.*, java.net.URL, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Professore prof = (Professore)request.getSession().getAttribute("professore");%>
<% Gruppo g = (Gruppo) request.getSession().getAttribute("gruppotemp"); %>
<% Progetto p = (Progetto) request.getSession().getAttribute("progettotemp"); %>

<title>Release per gruppo</title>
<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function scelta(stringa){
	document.getElementById('voto').value = stringa;
}
</script>

</head>
<body>

<% if(request.getAttribute("messaggio") != null)
	out.print(request.getAttribute("messaggio")); %>
	
<div id="pagina">
	<div class="header">
		<a href="HomeProfessore.jsp"><img src="Images/MPHLogo.jpg"></img></a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div class="headerLink">
		<a href="ModificaProfiloProfessore.jsp">Modifica profilo</a>
		|   
		<a href="Logout">Logout</a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div class="divInfoUtente">
		<img src="Images/IconProf.png" alt="immagine utente" alt="immagine profilo utente"></img>
		<div class="infoUtente">
			<p>
				<b>Username:</b> <% out.print(prof.getUsername()); %><br>
				<b>Nome:</b> <% out.print(prof.getNome()); %><br>
				<b>Cognome:</b> <% out.print(prof.getCognome()); %><br>
				<b>Email:</b> <% out.print(prof.getEmail()); %><br>
				<b>Telefono:</b> <% out.print(prof.getTelefono()); %><br>
			</p>
		</div>
	</div>
	
	<div id="divGenerale">
		Lista delle release relative al gruppo &nbsp <b><%=g.getNome()%></b>:
		<br />
		<%
		if(g != null && p != null) {
			for(ProgettoGruppo pg : g.getProgettigruppo()) 
			if(pg.getProgetto().getNome().compareTo(p.getNome()) == 0)
			{
				if(pg.getListaRelease().size() <= 0) {
		%>
					<br />
					<label style="width:90%; margin-left:14px;">Il gruppo non ha ancora effettuato l'upload delle release!</label>
					<br />
					<br />
		<%
				break;
				}
				else {
					/*if(pg.getProgetto().getNome().compareTo(p.getNome()) == 0)*/ {
						request.getSession().setAttribute("progettoGruppo",pg);
						ProgettoRelease[] pr1 = new ProgettoRelease[pg.getListaRelease().size()];
						int i = 0;
						for (ProgettoRelease pr : pg.getListaRelease()) {
							pr1[i] = pr;
							i++;
						}
						for (int y = 0; y < pg.getListaRelease().size(); y++) {
						%>
							<br />
							<label style="float:left;"> &nbsp &nbsp &nbsp &#149</label>
							<label style="float:left; padding-left:20px; margin-bottom:8px;"><b>Release:</b> &nbsp <%=pr1[y].getIdRelease().getTipo() %></label>
							
						<%
							if(pr1[y].getFiles().size() != 0) {
						%>
							<form action="Condividi" method="post" style="float:left; margin-top:-13px;">
								<fieldset>
									<input type="hidden" name="nome" value=<%=pr1[y].getId()%> />
									<input type="submit" class="button xss" name="submit" value="Condividi" />
								</fieldset>
							</form>
						<%
							}
						%>
							<form style="margin-left:33px; clear:both;">
				  				<fieldset>
				  				<label style="margin-top:30px;"><b>Voto: </b><% if(pr1[y].getVotoParziale() == 0) out.print("voto non assegnato"); else out.print(pr1[y].getVotoParziale()); %> <%if(pr1[y].getFiles().size() == 0) out.print("(Non consegnato)");%></label>
				  				</fieldset>
				  			</form>
							<%
							if(pr1[y].getFiles() != null) {
								for(FileRelease f : pr1[y].getFiles()) {
							%>		
									<form name="formSelectFile" id="formSelectFile" action="Download" method="get" style="margin-left:33px; margin-top:20px; clear:both;">
										<fieldset>
											<input type="hidden" name="nome" value=<% out.print(f.getId()); %> />
											<label for="descrizione" style="width:90%; float:left; clear:both;"><b>Descrizione: </b> &nbsp <%=f.getDescrizione() %></label>
											<br />
											<label for="file" style="float:left; margin-top:10px; clear:both;"><b>File:</b> &nbsp   <%=f.getFile().split("/")[f.getFile().split("/").length - 1]%> </label> 
											<a href="#" onclick="document.forms['formSelectFile'].submit()"><img src="Images/IconDownload.png" style="float:left; widht:40px; height:40px; margin-left:10px;"></img></a>
										</fieldset>
									</form>		
							<%
								}	
							}
				  		}
				 	}
				}
		  	}
		}
		%>
	</div>
	
	<br/>
	<div style="width:100%; float:left; padding-top:0px; padding-left:25px; margin-left:40px;">
		<a href="HomeProgettoProfessore.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
	</div>	
</div>
</body>
</html>