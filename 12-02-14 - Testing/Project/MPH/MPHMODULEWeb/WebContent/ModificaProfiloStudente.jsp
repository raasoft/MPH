<%@ page language="java"  import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica profilo studente</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>
</head>

<body>

<% if(request.getAttribute("messaggio") != null)
	out.print(request.getAttribute("messaggio")); %>

<div id="pagina">
	<div class="header">
		<a href="HomeStudente.jsp"><img src="Images/MPHLogo.jpg"></img></a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div class="headerLink">
		<a href="Logout">Logout</a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="modificaDati" class="ModInfo">
		<form action="ModificaDatiStudente" method="post">
				<fieldset>
					<label for="username">Username*:</label>
					<br />
					<input type="text" name="labelUsername" id="labelUsername" disabled="disabled" value=<%out.print(stud.getUsername()); %> >
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
					<input type="text" name="labelNome" id="labelNome" value=<%out.print(stud.getNome()); %>>
					<br />
					<br/>
					<label for="cognome">Cognome*:</label>
					<br />
					<input type="text" name="labelCognome" id="labelCognome" value=<%out.print(stud.getCognome());%>>
					<br />
					<br />
					<label for="email">Email*:</label>
					<br />
					<input type="text" name="labelEmail" id="labelEmail" value=<%out.print(stud.getEmail()); %>>			
					<br />
					<br />
					<label for="matricola">Matricola*:</label>
					<br />
					<input type="text" name="labelMatricola" id="labelMatricola" value=<%out.print(stud.getMatricola()); %>>
					<br />
					<br />
					(* campo obbligatorio)			
					<br />
					<br />
					(** campo obbligatorio solo se si vuole modificare la password)
					<br />
					<br />
					<br />
					<div style="width:100%; float:left; padding-top:10px; padding-left:-5px; margin-left:-5px;">
						<input type="submit" class="button xss" name="submit" value="Conferma" />
						<a href="HomeStudente.jsp"><input type="button" class="button xss" name="annulla" value="Annulla modifica" /></a>
						<input type="hidden" name="username" value=<%if(stud != null) out.print(stud.getUsername());%> />
					</div>
				</fieldset>
		</form>
	</div>
	
	<div class="pulisci">
	</div>
		
</div>
</body>
</html>