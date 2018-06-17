<%@ page language="java"  import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% Professore prof = (Professore)request.getSession().getAttribute("professore");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica profilo professore</title>

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
		<a href="Logout">Logout</a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="modificaDati" class="ModInfo">
		<form action="ModificaDatiProfessore" method="post">
				<fieldset>
					<label for="username">Username*:</label>
					<br />
					<input type="text" name="labelUsername" id="labelUsername" disabled="disabled" value=<%out.print(prof.getUsername()); %> >
					<br />
					<br />
					<label for="password">Vecchia password**:</label>
					<br />
					<input type="password" name="labelOldPsw" id="labelOldPsw" value=<% %> >
					<br />
					<br />
					<label for="password">Nuova password**:</label>
					<br />
					<input type="password" name="labelNewPsw" id="labelNewPsw" value=<% %>>
					<br />
					<br />
					<label for="password">Conferma nuova password**:</label>
					<br />
					<input type="password" name="labelConfirmPsw" id="labelConfirmPsw" value=<% %>>
					<br />
					<br />
					<label for="nome">Nome*:</label>
					<br />
					<input type="text" name="labelNome" id="labelNome" value=<%out.print(prof.getNome()); %>>
					<br />
					<br/>
					<label for="cognome">Cognome*:</label>
					<br />
					<input type="text" name="labelCognome" id="labelCognome" value=<%out.print(prof.getCognome());%>>
					<br />
					<br />
					<label for="email">Email*:</label>
					<br />
					<input type="text" name="labelEmail" id="labelEmail" value=<%out.print(prof.getEmail()); %>>			
					<br />
					<br />
					<label for="telefono">Telefono°:</label>
					<br />
					<input type="text" name="labelTelefono" id="labelTelefono" value=<%out.print(prof.getTelefono()); %>>
					<br />
					<br />
					(* campo obbligatorio)			
					<br />
					<br />
					(** campo obbligatorio solo se si vuole modificare la password)
					<br />
					<br />
					(° scrivere SOLO il numero, senza caratteri)
					<br />
					<br />
					<br />
					<div style="width:100%; float:left; padding-top:10px; padding-left:-5px; margin-left:-5px;">
						<input type="submit" class="button xss" name="submit" value="Conferma" />
						<a href="HomeProfessore.jsp"><input type="button" class="button xss" name="annulla" value="Annulla modifica" /></a>
						<input type="hidden" name="username" value=<%if(prof != null) out.print(prof.getUsername());%> />
					</div>
				</fieldset>
		</form>
	</div>
	
	<div class="pulisci">
	</div>
		
		
</div>
</body>
</html>