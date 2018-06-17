<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Professore prof = (Professore)request.getSession().getAttribute("professore");%>
<% List<Releas> rel = (List<Releas>)request.getSession().getAttribute("listaRelease"); %>
<% List<Progetto> lista = (List<Progetto>) request.getSession().getAttribute("ListaProgetti"); %>
<% int cont = 0; %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizza progetti professore</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function StatoIniziale(){
	formSelectProgetto.radioButtonSelectProgetto[0].checked = true;
}
function MostraDivDettaglioProgetto(indice){
	if (document.getElementById(indice).style.display == "none")
		document.getElementById(indice).style.display="inline";
	else
		document.getElementById(indice).style.display="none";
}
</script>

</head>
<body onload="StatoIniziale()">

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
		<%
		if(lista != null) {
			if(lista.size() <= 0) {
		%>
				<label style="width:90%; margin-left:14px;">Non sono presenti progetti, procedere con la creazione di un nuovo progetto!</label>
				<div style="margin-left:-6px; padding-top:20px;">
					<a href="HomeProfessore.jsp"><input type="button" class="button xs" value="Back" /></a>
				</div>
		<%
			}
			else {
		%>
				Lista dei progetti che hai creato:
				<br />
				<br />
				<form id="formSelectProgetto" name="formSelectProgetto" action="VisualizzaProgettoProfessore" method="post">
					<fieldset>
					<%
					for(Progetto p : lista) {
				 		if (p != null) {
							cont = cont + 1;
					%>
							<input type="hidden" name="nome" value=<%out.print(p.getNome());%> />
							<input type="radio" checked="checked" name="radioButtonSelectProgetto" value=<% out.print("\"" + p.getNome() + "\""); %>><a href="#" id="linkElencoConMenu" onclick="MostraDivDettaglioProgetto(<% out.print(cont); %>)"> <%=p.getNome() %> </a>
							<br />
									
							<div id=<% out.print(cont); %> style="width:100%; float:left; margin-left:40px; padding-top:15px; padding-bottom:10px; display:none;">
								<label for="nome"><b>Nome progetto:</b></label>
								<label>&nbsp</label>
								<label name="nomeProj"><%=p.getNome()%></label>
								<br />
								<br />
								<label for="descr"><b>Descrizione:</b></label>
								<label>&nbsp</label>
								<label name="descrProj"><%=p.getDescrizione()%></label>
								<br />
								<br />
								<label for="corso"><b>Corso:</b></label>
								<label>&nbsp</label>
								<label name="corsoProj"><%=p.getMateria()%></label>
								<br />
								<br />	
							</div>
					<%
						}
					}
					%>
					</fieldset>
				</form>
				<br />
				<div style="width:100%;	float:left;	margin-left:12px; margin-top:-10px;	padding-bottom:20px;">
					<input type="submit" class="button xss" name="submit" onclick="document.forms['formSelectProgetto'].submit()" value="Visualizza release" />
					<a href="HomeProfessore.jsp"><input type="button" class="button xss" value="Back" /></a>
				</div>
		<%
			}
		}
		%>
	</div>
	
</div>
</body>
</html>