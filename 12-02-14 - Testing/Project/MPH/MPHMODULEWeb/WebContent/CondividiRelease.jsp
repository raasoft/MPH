<%@ page language="java" import="java.util.*, java.net.URL, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Professore prof = (Professore)request.getSession().getAttribute("professore");%>
<% Gruppo gruppoEsc = (Gruppo)request.getSession().getAttribute("gruppoDaEscludere");%>
<% ProgettoRelease rel = (ProgettoRelease)request.getSession().getAttribute("releaseDaCond"); %>
<% List<Gruppo> grup = (List<Gruppo>)request.getSession().getAttribute("listaGruppiDaCond");%>
<% boolean gruppoOk = false; %>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<title>Condividi release</title>
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
	<!-- Aggiunto alla fine g.isCondivisa per sapere se è già condivisa la release per quel determinato gruppo -->
		<% if ( rel != null) { %>
			<label>Seleziona uno o più gruppi a cui condividere la release selezionata:</label>
			<br />
			<br />
			<form action="Condividi" method="get" name="formScelta" id="formScelta">
			<fieldset>
				<% for (Gruppo g: grup)
					if ((!g.isCondivisa(rel)) && (gruppoEsc.getNome().compareTo(g.getNome()) != 0 ) && (rel.getIdProgettoGruppo().getProgetto().controllaProgSet(g.getProgettigruppo())))
						{
							gruppoOk = true;
							%>
							<input type="CHECKBOX" name="nome" value=<%out.print(g.getNome()); %>><label> <%out.print(g.getNome()); %></label>
							<br/>
							<br/>
						<%}
					%>
				<% if (gruppoOk) {%>
					<input type="submit" class="button xss" value="Conferma">
					<% } else {
					%>	<label style="width:90%; margin-left:14px;">Non ci sono gruppi disponibili a cui condividere la release selezionata</label>
						<br>
						<br>
					<%
					}
					%>
					<a href="HomeProgettoProfessore.jsp"><input type="button" class="button xss" value="Back" ></a>
			</fieldset>
			</form>
		<% } 
		else
		{
			%>
				<label>Errore nella selezione della release da condividere </label>
			<%
		   } %>
	</div>
			
</div>



</body>
</html>