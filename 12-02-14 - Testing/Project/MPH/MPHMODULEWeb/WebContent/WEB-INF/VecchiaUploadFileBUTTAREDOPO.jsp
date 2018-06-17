<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<% ProgettoGruppo prgr = (ProgettoGruppo)request.getSession().getAttribute("progetto");%>
<% Gruppo grup = (Gruppo)request.getSession().getAttribute("gruppo");%>
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
		<img src="Images/Profilo.png" alt="immagine utente" alt="immagine profilo utente"></img>
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
	
	<div id="uploadRelease" class="Upload">
		<form action="CaricaLecture" method="post" enctype="multipart/form-data">
			<fieldset>
				<label for="id">Seleziona i file da caricare:</label>
				<br />
				<label for="TipoRelease">Tipo release:</label>
				<br />
				<SELECT name="TipoRelease" id="TipoRelease" onchange="scelta(this.value)" >
					<%if (prgr != null) {
					   	for(Releas rel : elencoRel)
					   		if (rel != null) {
						%>
							<option value= <% out.print(rel.getIdAsString()); %>> <% out.print(rel.getTipo()); %> </option>
					<% 		}
					   	} %>
				</SELECT>
				<input type="text" name="labelTipoRelease" style="display:hidden" value="" id="labelTipoRelease"/>		
				<br />
				<label for="Commento">Commento:</label>
				<br />
				<textarea rows="20" cols="60" name="Commento"></textarea>
				<br />
				<label for="File">File:</label>
				<br />
				<input type="file" name="Nomefile" />
				<br />
				<input type="hidden" name="idprog" value=<%if(prgr != null) out.print(prgr.getId());%> />
				<input type="submit" name="submit" value="Carica e termina" />
				<input type="submit" name="submit" value="Carica" />
			</fieldset>
		</form>
		<!-- <form action="UploadRelease" method="post" enctype="multipart/form-data">
			<fieldset>
				<!-- non c'è il nome nel database
				<label for="Nome">Nome:</label>
				<br />
				<input type="Text" name="labelNome" id="labelNome" />
				<br />
				<label for="TipoRelease">Tipo release:</label>
				<br />
				<SELECT name="TipoRelease" id="TipoRelease" onchange="scelta(this.value)" >
					<%if (prgr != null) {
					   	for( Releas rel : elencoRel)
					   		if (rel != null) {
						%>
							<option value= <% out.print(rel.getTipo()); %>> <% out.print(rel.getTipo()); %> </option>
					<% 		}
					   	} %>
				</SELECT>
				<input type="text" name="labelTipoRelease" style="display:hidden" value="" id="labelTipoRelease"/>		
				<br />
				<label for="Commento">Commento:</label>
				<br />
				<textarea rows="20" cols="60" name="Commento"></textarea>
				<br />
				<label for="File">File:</label>
				<br />
				<input type="FILE" multiple="multiple" name="Nomefile[]">
				<br />
				<input type="submit" class="button xs" name="submit" value="Carica" />
				<input type="hidden" name="username" value=<%if(stud != null) out.print(stud.getUsername());%> />
			</fieldset>
		</form> -->
	</div>
	
	<div class="pulisci">
	</div>
	
</div>
</body>
</html>