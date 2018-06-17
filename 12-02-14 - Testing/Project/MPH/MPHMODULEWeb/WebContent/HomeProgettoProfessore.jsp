<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Professore prof = (Professore)request.getSession().getAttribute("professore");%>
<% List<Releas> rel = (List<Releas>)request.getSession().getAttribute("listaRelease");%>
<% Progetto proj = (Progetto)request.getSession().getAttribute("progettotemp");%>

<title>Home progetto professore</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function StatoIniziale(){
	document.getElementById("releaseGruppo").style.display="none";
	document.getElementById("releaseTipo").style.display="none";
	document.getElementById("buttonBackElenco").style.display="block";
	formSelectGruppo.radioButtonSelectGruppo[0].checked = true;
	formSelectTipo.radioButtonSelectTipo[0].checked = true;
}
function MostraDivReleaseGruppo(){
	if (document.getElementById("releaseGruppo").style.display == "inline") {
		document.getElementById("releaseGruppo").style.display="none";
		document.getElementById("buttonBackElenco").style.display="block";
	}
	else {
		document.getElementById("releaseGruppo").style.display="inline";
		document.getElementById("buttonBackElenco").style.display="none";
	}
	document.getElementById("releaseTipo").style.display="none";
}
function MostraDivReleaseTipo(){
	if (document.getElementById("releaseTipo").style.display == "inline") {
		document.getElementById("releaseTipo").style.display="none";
		document.getElementById("buttonBackElenco").style.display="block";
	}
	else {
		document.getElementById("releaseTipo").style.display="inline";
		document.getElementById("buttonBackElenco").style.display="none";
	}
	document.getElementById("releaseGruppo").style.display="none";
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
	
	<div class="pulisci">
	</div>
	
	<div id="divGenerale">
		<br />
		<label style="width:90%;">Ti ricordiamo che puoi effettuare la valutazione delle release SOLO tramite la visualizzazione "In base al tipo di release".</label>
		<br />
		<br />
		Puoi visualizzare le release relative a questo progetto secondo due modalità:
		<br />
		<br/>

		<a id="linkElenco" href="#" onclick="MostraDivReleaseGruppo()">In base al gruppo che segue il progetto</a>
		
		<div id="releaseGruppo" style="width:100%; float:left; padding-left:20px; margin-top:10px; margin-left:20px;">
			<%
			if(proj.getProgettigruppo() != null) {
				if(proj.getProgettigruppo().size() <= 0) {
			%>
					<br />
					<label style="width:90%; margin-left:14px;">Nessun gruppo sta seguendo il tuo progetto!</label>
					<br />
					<br />
					<a href="VisualizzaProgettiProfessore.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
					<br />
			<%
				}
				else {
			%>
					<form id="formSelectGruppo" name="formSelectGruppo" action="GetByGruppo" method="post">
						<fieldset>
							<% 
							if (proj.getProgettigruppo() != null) {
								for (ProgettoGruppo p: proj.getProgettigruppo()) {
									Gruppo g = p.getGruppo();
							%>
										<input type="hidden" name="nome" value=<%out.print(g.getNome());%> />
										<input type="radio" checked="checked" name="radioButtonSelectGruppo" value=<% out.print("\"" + g.getNome() + "\""); %>> <%=g.getNome() %> </input>
										<br>
							<%
								}
							}
							%>
						</fieldset>
					</form>
					<br />
					<div style="margin-left:10px; margin-bottom:15px; margin-top:-5px;">
						<input type="submit" class="button xss" name="submit" onclick="document.forms['formSelectGruppo'].submit()" value="Next" />
						<a href="VisualizzaProgettiProfessore.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
					</div>
			<%
				}
			}
			%>
		</div>
		
		<br/>
		<br/>
		<a id="linkElenco" href="#" onclick="MostraDivReleaseTipo()">In base al tipo di release</a>
		
		<div id="releaseTipo" style="width:100%; float:left; padding-left:20px; margin-top:10px; margin-left:20px;">
			<form id="formSelectTipo" name="formSelectTipo" action="GetByTipo" method="post">
				<fieldset>
					<% 
					for (Releas r: proj.getListaRelease()) {
					%>
						<input type="hidden" name="id" value=<%out.print(r.getId());%> />
						<input type="radio" checked="checked" name="radioButtonSelectTipo" value=<% out.print("\"" + r.getId() + "\""); %>> <%=r.getTipo() %> </input>
						<br>
					<%
					}
					%>
				</fieldset>
			</form>
			<br />
			<div style="margin-left:10px; margin-top:-5px;">
				<input type="submit" class="button xss" name="submit" onclick="document.forms['formSelectTipo'].submit()" value="Next" />
				<a href="VisualizzaProgettiProfessore.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
			</div>
		</div>
		<div id="buttonBackElenco" style="margin-left:33px; margin-top:20px;">
			<a href="VisualizzaProgettiProfessore.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
		</div>
	</div>
</div>
</body>
</html>