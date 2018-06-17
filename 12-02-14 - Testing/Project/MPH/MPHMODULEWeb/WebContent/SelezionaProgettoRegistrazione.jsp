<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% List<Progetto> proj = (List<Progetto>)request.getSession().getAttribute("listaProgetti");%>
<% int cont = 0; %>

<title>Registrazione: scelta progetto</title>

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
		<a href="Logout"><img src="Images/MPHLogo.jpg"></img></a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="divGenerale">
			<br />
			<label id="titoli" for="nome">SCELTA PROGETTO </label>
			<br />
			<br />
			<br />
			Elenco dei progetti disponibili:
			<br />
			<br />
			<%
			if(proj != null) {
				if(proj.size() <= 0) {
			%>
					<br />
					<label style="width:90%; margin-left:14px;">Non sono presenti progetti nel sistema, contattare il professore responsabile!</label>
					<br />
					<br />
					<a href="Logout"><input type="button" class="button xss" value="Annulla Registrazione" /></a>
			<%
				}
				else {
			%>
					<form action="SelezionaProgettoRegistrazione" name="formSelectProgetto" id="formSelectProgetto" method="post">
						<fieldset>
							<%
							for(Progetto pr : proj) {
								if (pr != null) {
									cont = cont + 1;
							%>
									
									<input type="hidden" name="nome" value=<%out.print(pr.getNome());%> />
									<input type="radio" checked="checked" name="radioButtonSelectProgetto" value=<% out.print("\"" + pr.getNome() + "\""); %>><a href="#" id="linkElencoConMenu" onclick="MostraDivDettaglioProgetto(<% out.print(cont); %>)"> <%=pr.getNome() %> </a>
									<br />
										
									<div id=<% out.print(cont); %> style="width:100%; float:left; margin-left:40px; padding-top:15px; padding-bottom:20px; display:none;">
										<label for="nome"><b>Nome progetto:</b></label>
										<label>&nbsp</label>
										<label name="nomeProj"><%=pr.getNome()%></label>
										<br />
										<br />
										<label for="descr"><b>Descrizione:</b></label>
										<label>&nbsp</label>
										<label name="descrProj"><%=pr.getDescrizione()%></label>
										<br />
										<br />
										<label for="corso"><b>Corso:</b></label>
										<label>&nbsp</label>
										<label name="corsoProj"><%=pr.getMateria()%></label>
										<br />
										<br />
										<label for="prof"><b>Professore di riferimento:</b></label>
										<label>&nbsp</label>
										<label><%=pr.getProfessore().getNome()%></label>
										<label>&nbsp</label>
										<label><%=pr.getProfessore().getCognome()%></label>	
									</div>
							<%
								}
							}
							%>
							</fieldset>
						</form>
						<br />
						<div style="margin-left:12px;">
							<input type="submit" class="button xss" name="submit" onclick="document.forms['formSelectProgetto'].submit()" value="Next" />
							<a href="Logout"><input type="button" class="button xss" value="Annulla registrazione" /></a>
						</div>
			<%
				}
			}
			%>
			<br />
	</div>
	
</div>
</body>
</html>