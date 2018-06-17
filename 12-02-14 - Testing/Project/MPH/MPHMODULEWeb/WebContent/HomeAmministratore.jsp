<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home amministratore</title>
<% Amministratore admin = (Amministratore)request.getSession().getAttribute("amministratore");%>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function StatoIniziale(){
	document.getElementById("funzioniUtente").style.display="inline";
	document.getElementById("registraProf").style.display="none";
}
function MostraDivInserisci(){
	document.getElementById("registraProf").style.display="inline";
	document.getElementById("funzioniUtente").style.display="none";
}
var keylist = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890";
function Genera(){
	temp = ''; 
		for (i=0; i<10; i++) 
			temp += keylist.charAt(Math.floor(Math.random()*keylist.length)); 
	 document.getElementById('labelPsw').value = temp;
}
</script>

</head>
<body onload=<% Integer step = (Integer)request.getSession().getAttribute("step");
				if (step == 1) {
					out.print("MostraDivInserisci()");} %>>
					
<% if(request.getAttribute("messaggio") != null)
	out.print(request.getAttribute("messaggio")); %>
	
<div id="pagina">
	<div class="header">
		<a href="HomeAmministratore.jsp"><img src="Images/MPHLogo.jpg"></img></a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div class="headerLink">
		<a href="Logout">Logout</a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div style:"margin-top:25px; width:100%;">
		<img src="Images/IconAdmin.png" alt="immagine utente" alt="immagine profilo utente" style="float:left; margin-left:110px; margin-top:20px; margin-bottom:15px; width:210px; height:250px;"></img>
		<div style="float:left; margin-left:50px; line-height:30px; margin-top:70px;">
			<p>
				<b>Username:</b> <% out.print(admin.getUsername()); %><br>
				<b>Nome:</b> <% out.print(admin.getNome()); %><br>
				<b>Cognome:</b> <% out.print(admin.getCognome()); %><br>
				<b>Email:</b> <% out.print(admin.getEmail()); %><br>
			</p>
		</div>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="funzioniUtente">
		Per inserire un nuovo professore nel portale:
		<input type="button" class="button xs" name="submit" onclick="MostraDivInserisci()" id="insProf" value="Inserisci professore" />
		<br />
		<br />
		Per visualizzare i profili di tutti gli utenti:
		<a href="SelezionaUtente.jsp"><input type="button" class="button xs" name="submit" id="elProf" value="Visualizza profili" /></a> 
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="registraProf" style="width:100%; float:left; margin-left:20px; padding-top:50px;">
		<form action="AggiungiProfessore" method="post">
			<fieldset>
				<label for="username">Username*:</label>
				<br />
				<input type="text" name="labelUser" id="labelUser" value=<% if (request.getSession().getAttribute("username") != null)  out.print(request.getSession().getAttribute("username")); %> >				
				<br />
				<br />
				<label for="password">Password*:</label>
				<br />
				<input type="text" name="labelPsw" id="labelPsw" value=<% if (request.getSession().getAttribute("password") != null)  out.print(request.getSession().getAttribute("password")); %> >
				<input type="button" value="Genera password" onclick="Genera()" style="margin-left:25px;" class="button xss">
				<br />
				<br />
				<label for="nome">Nome*:</label>
				<br />
				<input type="text" name="labelNome" id="labelNome" value=<% if (request.getSession().getAttribute("nome") != null)  out.print(request.getSession().getAttribute("nome")); %> >				
				<br />
				<br />
				<label for="cognome">Cognome*:</label>
				<br />
				<input type="text" name="labelCognome" id="labelCognome" value=<% if (request.getSession().getAttribute("cognome") != null)  out.print(request.getSession().getAttribute("cognome")); %> >				
				<br />
				<br />
				<label for="email">Email*:</label>
				<br />
				<input type="text" name="labelEmail" id="labelEmail" value=<% if (request.getSession().getAttribute("email") != null)  out.print(request.getSession().getAttribute("email")); %>>				
				<br />
				<br />
				<label for="telefono">Telefono°:</label>
				<br />
				<input type="text" name="labelTelefono" id="labelTelefono" value=<% if (request.getSession().getAttribute("telefono") != null)  out.print(request.getSession().getAttribute("telefono")); %>>				
				<br />
				<br />
				(* campo obbligatorio)			
				<br />
				<br />
				(° scrivere SOLO il numero, senza caratteri)
				<br />
				<br />
				<br />
				<div style="margin-left:-5px; padding-top:10px;">
					<input type="submit" class="button xss" name="submit" value="Registra" />
					<input type="reset" class="button xss" name="buttonAnnulla" onclick="StatoIniziale()" value="Annulla registrazione" />
				</div>
			</fieldset>
		</form>
	</div>

</div>
</body>
</html>