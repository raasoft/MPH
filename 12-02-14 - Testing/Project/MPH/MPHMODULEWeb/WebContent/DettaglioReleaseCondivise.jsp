<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<% List<Gruppo> lista = (List<Gruppo>) request.getSession().getAttribute("ListaGruppi"); %>
<% ProgettoRelease pr = (ProgettoRelease) request.getSession().getAttribute("progettoRelease"); %>

<title>Download release condivise</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

</head>
<body>
<div id="pagina">
	<div class="header">
		<a href="HomeStudente.jsp"><img src="Images/MPHLogo.jpg"></img></a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div class="headerLink">
		<a href="ModificaProfiloStudente.jsp"> Modifica profilo</a>
		|   
		<a href="Logout">Logout</a>
	</div>
	
	<div class="pulisci">
	</div>
		
	<div class="divInfoUtente">
		<img src="Images/IconStud.png" alt="immagine utente" alt="immagine profilo utente"></img>
		<div class="infoUtente">
			<p>
				<b>Username:</b> <% out.print(stud.getUsername()); %><br>
				<b>Nome:</b> <% out.print(stud.getNome()); %><br>
				<b>Cognome:</b> <% out.print(stud.getCognome()); %><br>
				<b>Email:</b> <% out.print(stud.getEmail()); %><br>
				<b>Matricola:</b> <% out.print(stud.getMatricola()); %><br>
			</p>
		</div>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="divGenerale">			
	Elenco dei file associati alla release condivisa:
			<br />
			<br />
			<%
			if(pr != null) {
				if(pr.getFiles().size() <= 0) {
			%>
					<br />
					<label style="width:90%; margin-left:14px;">Non sono presenti file associati alla release condivisa!</label>
					<br />
					<br />
			<%
				}
				else {
					for(FileRelease file : pr.getFiles()) {
			%>
						<ul>
							<li>
								<form action="Download" name="formSelectFile" id="formSelectFile" method="get">
									<fieldset>	
										<input type="hidden" name="nome" value=<%out.print(file.getId());%> />
										<label for="tipo"><b>Tipo release:</b></label>
										<label>&nbsp</label>
										<label>  <%=pr.getIdRelease().getTipo() %></label>
										<br />
										<br />
										<label for="file" style="float:left;"><b>File:</b></label>
										<label style="float:left; margin-left:10px;">  <%=file.getFile().split("/")[file.getFile().split("/").length - 1] %> </label> 
										<a href="#" onclick="document.forms['formSelectFile'].submit()"><img src="Images/IconDownload.png" style="float:left; widht:40px; height:40px; margin-left:10px; margin-top:-10px;"></img></a> 
									</fieldset>
								</form>
							</li>
						</ul>
							<br />
							<br />
			<%
					}
				}
			}
			%>
	</div>
	
</div>
</body>
</html>