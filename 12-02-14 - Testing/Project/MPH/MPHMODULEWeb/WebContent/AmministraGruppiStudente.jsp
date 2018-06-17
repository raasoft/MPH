<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<% List<Gruppo> grup = (List<Gruppo>)request.getSession().getAttribute("ListaGruppi");%>
<% List<Gruppo> grupCompleto = (List<Gruppo>)request.getSession().getAttribute("ListaGruppiCompleta");%>

<title>Amministra gruppi</title>
<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function StatoIniziale(){
	document.getElementById("unioneGruppo").style.display="none";
	document.getElementById("creaGruppo").style.display="none";
	document.getElementById("buttonBackElenco").style.display="block";
	formSelectGruppo.radioButtonSelectGruppo[0].checked = true;
}
function MostraDivUnioneGruppo(){
	if (document.getElementById("unioneGruppo").style.display == "inline") {
		document.getElementById("unioneGruppo").style.display="none";
		document.getElementById("buttonBackElenco").style.display="block";
	}
	else {
		document.getElementById("unioneGruppo").style.display="inline";
		document.getElementById("buttonBackElenco").style.display="none";
	}
	document.getElementById("creaGruppo").style.display="none";
}
function MostraDivCreaGruppo(){
	if (document.getElementById("creaGruppo").style.display == "inline") {
		document.getElementById("creaGruppo").style.display="none";
		document.getElementById("buttonBackElenco").style.display="block";
	}
	else {
		document.getElementById("creaGruppo").style.display="inline";
		document.getElementById("buttonBackElenco").style.display="none";
	}
	document.getElementById("unioneGruppo").style.display="none";
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
	
	<div id="funzioniUtente">
		<br />
		Puoi formare un nuovo team di lavoro secondo due modalità:
		<br />
		<br/>

		<a id="linkElenco" href="#" onclick="MostraDivUnioneGruppo()">Unione ad un gruppo esistente</a>
		
		<div id="unioneGruppo">
			<%
			if(grupCompleto != null) {
				if(grupCompleto.size() <= 0) {
			%>
					<label style="width:90%; margin-left:14px;">Non sono presenti gruppi nel sistema, procedere con la creazione di un nuovo gruppo!</label>
					<br />
					<br />
				<%
				}
				else {
				%>
					<form id="formSelectGruppo" name="formSelectGruppo" action="SelezionaGruppoStudente" method="post">
						<fieldset>
							<%
							for(Gruppo g: grupCompleto) {
								if (g != null) {
							%>
									<input type="hidden" name="registrazione" value="2">
									<input type="hidden" name="nome" value=<%out.print(g.getNome());%> />
									<input type="radio" checked="checked" name="radioButtonSelectGruppo" value=<%out.print("\"" + g.getNome() + "\""); %>> <%=g.getNome() %> </input>
									<br>
							<%
								}
							}
							%>
						</fieldset>
					</form>
					<br />
					<div style="margin-left:10px; margin-bottom:20px; margin-top:-5px;">
						<input type="submit" class="button xss" name="submit" onclick="document.forms['formSelectGruppo'].submit()" value="Next" />
						<a href="HomeStudente.jsp"><input type="button" class="button xss" value="Back" /></a>
					</div>
				<%
				}
			}
			%>
		</div>
		
		<br/>
		<br/>
		<a href="#" id="linkElenco" onclick="MostraDivCreaGruppo()">Crea nuovo gruppo</a>
		
		<div id="creaGruppo">
			<form action="CreaGruppoStudente" method="post">
				<fieldset>
					<label for="text">Nome*:</label>
					<br />
					<input type="text" name="labelNome" id="labelNome" />
					<br />
					<br />
					(* campo obbligatorio)
					<br />
					<br />
					<input type="checkbox" name="active" value="ON" >Gruppo singolo (solo una persona)
					<br />
					<br />
					<div style="margin-left:-3px;">
						<input type="submit" class="button xss" name="submit" value="Next" style="padding-left:10px;" />
						<a href="HomeStudente.jsp"><input type="button" class="button xss" name="submit" value="Back" style="padding-left:10px;" /></a>
					</div>
				</fieldset>
			</form>
		</div>
		<div id="buttonBackElenco" style="margin-left:33px; margin-top:20px;">
			<a href="HomeStudente.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
		</div>
	</div>
	
</div>
</body>
</html>