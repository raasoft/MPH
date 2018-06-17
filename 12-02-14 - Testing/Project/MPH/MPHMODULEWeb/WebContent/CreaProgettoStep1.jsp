<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Professore prof = (Professore)request.getSession().getAttribute("professore");%>
<% Progetto proj = (Progetto)request.getSession().getAttribute("progetto");%>
<% List<Releas> rel = (List<Releas>)request.getSession().getAttribute("listaRelease"); %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crea progetto: Dati progetto</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
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
		<a href="ModificaProfiloProfessore.jsp"> Modifica profilo</a>
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
	
	<div class="pulisci">
	</div>
	
	<div id="divGenerale">
		<form action="CreaProgetto" method="post">
			<fieldset>
				<label id="titoli" for="nome">DATI PROGETTO </label>
				<br />
				<br />
				<br />
				<label for="nome">Nome progetto*:</label>
				<br />
				<input type="text" name="labelNome" id="labelNome" value=<%if(proj != null) out.print(proj.getNome());%>></input>
				<br />
				<br />
				<label for="descrizione">Descrizione*:</label>
				<br />
				<textarea rows="15" cols="50" name="labelDescrizione" id="labelDescrizione" value==<%if(proj != null) out.print(proj.getDescrizione());%>></textarea>
				<br />
				<br />
				<label for="corso">Corso*:</label>
				<br />
				<input type="text" name="labelCorso" id="labelCorso" value=<%if(proj != null) out.print(proj.getMateria());%>></input>
				<br />
				<br />
				(* campo obbligatorio)			
				<br />
				<br />
				<br />
				<div style="width:100%; float:left; padding-top:0px; padding-left:-5px; margin-left:-5px;">
					<input type="submit" class="button xss" name="submit" value="Next">
					<a href="HomeProfessore.jsp"><input type="button" class="button xss" name="submit" value="Annulla creazione"></a>
					<input type="hidden" name="username" value=<%if(prof != null) out.print(prof.getUsername());%> />
					<input type="hidden" name="controlloStep" value="Step1" />
				</div>
			</fieldset>
		</form>
	</div>
	
</div>
</body>
</html>