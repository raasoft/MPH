<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<title>Registrazione: Dati personali</title>
<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

</head>
<body>

<% if(request.getAttribute("messaggio") != null)
	out.print(request.getAttribute("messaggio")); %>
	
<div id="pagina">
	<div class="header">
		<a href="Logout"><img src="Images/MPHLogo.jpg"></img></a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="divGenerale">
		<form action="RegistraStudente" method="post">
			<fieldset>
				<br />
				<br />
				<label id="titoli" for="nome">DATI PERSONALI</label>
				<br />
				<br />
				<br />
				<label for="username">Username*:</label>
				<br />
				<input type="text" name="labelUser" id="labelUser" value=<% if (request.getSession().getAttribute("username") != null)
					out.print(request.getSession().getAttribute("username")); %>>				
				<br />
				<br />
				<label for="password">Password*:</label>
				<br />
				<input type="password" name="labelPsw" id="labelPsw" value=<% if (request.getSession().getAttribute("password") != null) out.print(request.getSession().getAttribute("password")); %> >
				<br />
				<br />
				<label for="password">Conferma password*:</label>
				<br />
				<input type="password" name="labelConfirmPsw" id="labelConfirmPsw" value=<% if (request.getSession().getAttribute("confirmPsw") != null) out.print(request.getSession().getAttribute("confirmPsw")); %> >
				<br />
				<br />
				<label for="nome">Nome*:</label>
				<br />
				<input type="text" name="labelNome" id="labelNome" value=<% if (request.getSession().getAttribute("nome") != null) out.print(request.getSession().getAttribute("nome")); %> >				
				<br />
				<br />
				<label for="cognome">Cognome*:</label>
				<br />
				<input type="text" name="labelCognome" id="labelCognome" value=<% if (request.getSession().getAttribute("cognome") != null) out.print(request.getSession().getAttribute("cognome")); %>>				
				<br />
				<br />
				<label for="email">Email*:</label>
				<br />
				<input type="text" name="labelEmail" id="labelEmail" value=<% if (request.getSession().getAttribute("email") != null) out.print(request.getSession().getAttribute("email")); %> >				
				<br />
				<br />
				<label for="matricola">Matricola*:</label>
				<br />
				<input type="text" name="labelMatricola" id="labelMatricola" value=<% if (request.getSession().getAttribute("matricola") != null) out.print(request.getSession().getAttribute("matricola")); %> >	
				<br />
				<br />
				(* campo obbligatorio)			
				<br />
				<br />
				<br />
				<div style="margin-left:0px;">
					<input type="submit" class="button xs" name="submit" value="Next" />
					<a href="Logout"><input type="button" class="button xs" value="Annulla registrazione" /></a>
				</div>
			</fieldset>
		</form>	
	</div>
</div>
</body>
</html>