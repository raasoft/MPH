<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<% List<Gruppo> grup = (List<Gruppo>)request.getSession().getAttribute("ListaGruppi");%>
<% Set<Studente> st = new HashSet<Studente>(); %>

<title>Seleziona gruppo profilo</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function StatoIniziale(){
	formSelectGruppo.radioButtonSelectGruppo[0].checked = true;
}
</script>

</head>
<body onload="StatoIniziale()">

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
		Lista dei gruppi a cui appartieni:
		<br />
		<br />
		<%
		if(grup != null) {
			if(grup.size() <= 0) {
		%>
				<br />
				<label style="width:90%; margin-left:14px;">Il tuo profilo non è associato ad alcun gruppo, contattare l'amministratore di sistema!</label>
				<br />
				<br />
		<%
			}
			else {
		%>
					<form action="VisualizzaGruppo" name="formSelectGruppo" id="formSelectGruppo" method="post">
						<fieldset>
							<%
							for(Gruppo g: grup) {
								if (g != null) {
							%>	
									<input type="hidden" name="nome" value=<%out.print("\"" + g.getNome() + "\"");%> />
									<input type="radio" checked="checked" name="radioButtonSelectGruppo" value=<% out.print("\"" + g.getNome() + "\""); %>> <%out.print(g.getNome());%>
									<br />
						<%
								}
							}
						%>
						</fieldset>
					</form>
					<br />	
					<div style="margin-left:10px; margin-bottom:20px; margin-top:-5px;">
						<input type="submit" class="button xss" name="submit" onclick="document.forms['formSelectGruppo'].submit()" value="Dettaglio gruppo" />
						<a href="HomeStudente.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
					</div>	
		<%
			}
		}
		%>	
	</div>	
	
</div>
</body>
</html>