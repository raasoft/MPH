<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<% List<Gruppo> grup = (List<Gruppo>)request.getSession().getAttribute("ListaGruppi");%>
<% List<ProgettoGruppo> proj = (List<ProgettoGruppo>)request.getSession().getAttribute("listaProgettiGruppo"); %>
<% ProgettoGruppo prgr = (ProgettoGruppo)request.getSession().getAttribute("progetto");%>
<% Set<Releas> elencoRel = prgr.getProgetto().getListaRelease();%>
<% int cont = 0; %>
<% int ind = 0; %>

<title>Home progetto studente</title>

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
		Il progetto prevede le seguenti release:
		<br />
		<br />
		<%
		if (prgr != null) {
		%>
			<ul class="elencoPuntatoUL">
		<%
				for(Releas rel : elencoRel) {
					if (rel != null) {
		%>
						<li><% out.print(rel.getTipo()); %>  <%if(!rel.isConsegnabile())out.print("(consegna chiusa)"); %></li>
		<% 		
					}
				}
		%>
			</ul>
		<%
		}
		%>
	</div>
	
	<div id="divGenerale">
		Le release che hai caricato sono:
		<br />
		<br />
		<%
		if (prgr.getListaRelease() != null)
			if (prgr.getListaRelease().size() <= 0) {
		%>
				<label style="width:90%; margin-left:14px;">Non hai ancora effettuato l'upload di release associate a questo progetto!</label>
				<br />
				<br />
		<%
		}
		else {
		%>
			<ul class="elencoPuntatoUL">
				<%
				for(ProgettoRelease pr : prgr.getListaRelease()) {
				%>
					<li><label for="nome"><b>Tipo release:</b></label>
					<label>&nbsp</label>
					<label><% out.print(pr.getIdRelease().getTipo()); %></label></li>
					<label for="votoFin" style="margin-left:-4px;"><b>Voto:</b></label>
					<label>&nbsp</label>
					<label><%if(pr.getFiles().size() == 0) out.print(pr.getVotoParziale() + " (Non consegnato)");else if(pr.getVotoParziale() != 0) out.print(pr.getVotoParziale()); else out.print("Voto non ancora assegnato");%></label>
					<br />
					<br />
				<%
				}
				%>
			</ul>
		<%
		}
		%>
	</div>
	
	<div style="width:90%; float:left; padding-top:40px; margin-left:63px;">
		<label style="width:90%;">E' da precisare che quando appare la scritta "consegna chiusa" di fianco al tipo di release significa che lo studente non è più abilitato al caricamento di release di quel genere.
		Per ogni evenienza contattare il professore di riferimento. </label>
	</div>
	
	<div style="width:100%; float:left; padding-top:30px; padding-left:25px; margin-left:25px;">
		<form action="" method="post">
			<fieldset>
				Per caricare una release al progetto:
				<a href="ControlloUpload"><input type="button" class="button xss" name="upload" value="Upload release"></a>
				<a href="SelezionaProgettoProfilo.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
			</fieldset>
		</form>
	</div>

</div>
</body>
</html>