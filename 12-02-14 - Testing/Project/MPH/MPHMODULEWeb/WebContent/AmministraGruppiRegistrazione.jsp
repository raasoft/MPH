<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% List<Gruppo> grup = (List<Gruppo>)request.getSession().getAttribute("listaGruppi");%>
<title>Registrazione: scelta gruppo</title>

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
		<a href="Logout"><img src="Images/MPHLogo.jpg"></img></a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="divGenerale">
		<br />
		<label id="titoli" for="nome">SCELTA GRUPPO</label>
		<br />
		<br />
		<br />
		In questo step hai a disposizione due modalità per scegliere un team di lavoro:
		<br />
		<br/>

		<a id="linkElenco" href="#" onclick="MostraDivUnioneGruppo()">Unione ad un gruppo esistente</a>
		
		<div id="unioneGruppo">
			<%
			if(grup != null) {
				if(grup.size() <= 0) {
			%>
					<br />
					<label style="width:90%; margin-left:14px;">Non sono presenti gruppi nel sistema, procedere con la creazione di un nuovo gruppo!</label>
					<br />
					<br />
				<%
				}
				else {
				%>
					<form id="formSelectGruppo" name="formSelectGruppo" action="SelezionaGruppoRegistrazione" method="post">
						<fieldset>
							<%
							for(Gruppo g : grup) {
								if (g != null) {
							%>
									<input type="hidden" name="nome" value=<%out.print(g.getNome());%> />
									<input type="radio" checked="checked" name="radioButtonSelectGruppo" value=<%="\"" + g.getNome() + "\""%>> <%=g.getNome() %> </input>
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
						<a href="Logout"><input type="button" class="button xss" value="Annulla registrazione" /></a>
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
			<form action="CreaGruppoRegistrazione" method="post">
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
						<a href="Logout"><input type="button" class="button xss" name="submit" value="Annulla registrazione" style="padding-left:10px;" /></a>
					</div>
				</fieldset>
			</form>
		</div>
		<div id="buttonBackElenco" style="margin-left:33px; margin-top:20px;">
			<a href="VisualizzaProgettiProfessore.jsp"><input type="button" class="button xss" name="submit" value="Annulla registrazione" /></a>
		</div>
	</div>
	
</div>
</body>
</html>