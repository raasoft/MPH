<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<% ProgettoGruppo prgr = (ProgettoGruppo)request.getSession().getAttribute("progetto");%>
<% Gruppo grup = (Gruppo)request.getSession().getAttribute("gruppo");%>
<% List<Releas> release = (List<Releas>)request.getSession().getAttribute("listareleasedacaricare");%>
<% Set<Releas> elencoRel = prgr.getProgetto().getListaRelease();%>

<title>Upload file</title>
<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function inizializza(){
	document.getElementById('labelTipoRelease').value = document.getElementById("TipoRelease").options[0].value;
}
function scelta(stringa){
	document.getElementById('labelTipoRelease').value = stringa;
}
</script>

</head>

<body onload="inizializza()">

<% if(request.getAttribute("messaggio") != null)
	out.print(request.getAttribute("messaggio")); %>
	
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
	
	<div style="width:100%; float:left; margin-top:50px; margin-left:20px">
		<div id="uploadRelease" class="Upload">
			<form action="UploadRelease" method="post" enctype="multipart/form-data">
				<fieldset>
					<label id="titoli" for="nome">UPLOAD RELEASE</label>
					<br />
					<br />
					<br />
					<label for="TipoRelease">Tipo release:</label>
					<label>&nbsp</label>
					<SELECT name="TipoRelease" id="TipoRelease" onchange="scelta(this.value)" >
						<%
						if (release != null) {
						   	for(Releas rel : release)
						   		if (rel != null) {
						%>
									<option value= <% out.print(rel.getIdAsString()); %>> <% out.print(rel.getTipo()); %> 
									</option>
						<% 		
								}
						}
						%>
					</SELECT>
				    <input type="text" style="display:none" name="labelTipoRelease" value="" id="labelTipoRelease">
					<br />
					<br />
					<label for="Commento">Commento:</label>
					<br />
					<textarea rows="15" cols="50" name="Commento"></textarea>
					<br />
					<br />
					<label for="File">File:</label>
					<label>&nbsp</label>
					<input type="file" name="Nomefile" />
					<br />
					<br />
					<input type="hidden" name="idprog" value=<%if(prgr != null) out.print(prgr.getId());%> />
					<input type="submit" class="button xss" name="submit" value="Carica e termina" />
					<input type="submit" class="button xss" name="submit" value="Carica" />
				</fieldset>
			</form>
		</div>
		<div style="width:100%; float:left; padding-top:30px; padding-left:25px; margin-left:25px;">
			<a href="HomeStudente.jsp"><input type="button" class="button xss" name="buttonAnnulla" value="Annulla" /></a>
		</div>
	</div>
</div>
</body>
</html>