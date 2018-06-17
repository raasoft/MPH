<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<% List<Gruppo> grup = (List<Gruppo>)request.getSession().getAttribute("ListaGruppi");%>
<% Set<Studente> st = new HashSet<Studente>(); %>

<title>Home studente</title>

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
		Per accedere ai gruppi a cui appartieni:
		<a href="SelezionaGruppoProfilo.jsp"><input type="button" class="button xs" name="submit" value="Visualizza gruppi" /></a>
		<br />
		<br />
		Per accedere ai progetti a cui stai attualmente lavorando:
		<a href="SelezionaProgettoProfilo.jsp"><input type="button" class="button xs" name="submit" value="Visualizza progetti" /></a>
		<br />
		<br />
		Per definire un nuovo team di lavoro:
		<a href="AmministraGruppiStudente.jsp"><input type="button" class="button xs" name="submit" value="Formazione gruppi" /></a>
		<br />
		<br />
		Per visualizzare le release condivise:
		<a href="VisualizzaReleaseCondivise.jsp"><input type="button" class="button xs" name="submit" value="Visualizza release condivise" /></a>
	</div>
	
</div>
</body>
</html>