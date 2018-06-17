<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% List<Professore> lista = (List<Professore>)request.getSession().getAttribute("ListaProf"); %>
<% List<Studente> lista2 = (List<Studente>)request.getSession().getAttribute("ListaStud"); %>
<% List<Gruppo> lista3 = (List<Gruppo>)request.getSession().getAttribute("ListaGruppi");%>
<% Amministratore admin = (Amministratore)request.getSession().getAttribute("amministratore");%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seleziona utente</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function StatoIniziale(){
	formSelectProf.radioButtonSelectProf[0].checked = true;
	formSelectStud.radioButtonSelectStud[0].checked = true;
	formSelectGruppo.radioButtonSelectGruppo[0].checked = true;
}
</script>

</head>
<body onload="StatoIniziale()">
					
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
	
	<div id="divGenerale">
		<%
		if(lista != null) {
			if(lista.size() <= 0) {
		%>
				<label style="width:90%; margin-left:14px;">Non sono presenti professori nel sistema, procedere con la loro registrazione!</label>
				<br>
				<br>
		<%
			}
			else {
		%>
				Lista dei professori:	
				<form id="formSelectProf" name="formSelectProf" action="EliminaProfessore" method="post">
					<fieldset>
						<%
						 for(Professore p : lista) {
						 	if (p != null) {
						%>				
								<input type="hidden" name="username" value=<%out.print(p.getUsername());%> />
								<input type="radio" checked="checked" name="radioButtonSelectProf" value=<% out.print("\"" + p.getUsername() + "\""); %>> <%out.print(p.getCognome() + " " + p.getNome()); %>
								<br>
						<%
							}
						}
						%>
						<div style="margin-left:-5px; padding-top:10px;">
							<input type="submit" class="button xss" onclick="document.getElementById('controllo').value='Elimina'" value="Elimina" />
							<input type="submit" class="button xss" onclick="document.getElementById('controllo').value='Visualizza'" value="Visualizza" />
							<input type="text" style="display:none" value="" name="controllo" id="controllo">
						</div>
					</fieldset>
				</form>
				<br />
		<%
			}
		}
		%>
		<br />
		<%
		if(lista2 != null) {
			if(lista2.size() <= 0) {
		%>
				<label style="margin-left:14px;">Non sono presenti studenti nel sistema!</label>
				<br>
				<br>
		<%
			}
			else {
		%>
				Lista degli studenti:	
				<form id="formSelectStud" name="formSelectStud" action="EliminaStudente" method="post">
					<fieldset>
						<%
						 for(Studente s : lista2) {
						 	if (s != null) {
						%>		
								<input type="hidden" name="username" value=<%out.print(s.getUsername());%> />
								<input type="radio" checked="checked" name="radioButtonSelectStud" value=<%out.print("\"" + s.getUsername() + "\""); %>><%=s.getCognome()%> <%=s.getNome() %>
								<br>
						<%
							}
						}
						%>
							<div style="margin-left:-5px; padding-top:10px;">
								<input type="submit" class="button xss" name="submit" onclick="document.getElementById('controllo1').value='Elimina'" value="Elimina" />
								<input type="submit" class="button xss" name="submit" onclick="document.getElementById('controllo1').value='Visualizza'" value="Visualizza" />
								<input type="text" style="display:none" value="" name="controllo1" id="controllo1">
							</div>
					</fieldset>
				</form>
				<br />
		<%
			}
		}
		%>
		<br />
		<%
		if(lista3 != null) {
			if(lista3.size() <= 0) {
		%>
				<label style="margin-left:14px;">Non sono presenti gruppi nel sistema!</label>
				<br>
				<br>
		<%
			}
			else {
		%>
				Lista dei gruppi:	
				<form id="formSelectGruppo" name="formSelectGruppo" action="EliminaGruppo" method="post">
					<fieldset>
						<%
						 for(Gruppo g : lista3) {
						 	if (g != null) {
						%>		
								<input type="hidden" name="username" value=<%out.print(g.getNome());%> />
								<input type="radio" checked="checked" name="radioButtonSelectGruppo" value=<%out.print("\"" + g.getNome() + "\""); %>><%=g.getNome() %>
								<br>
						<%
							}
						}
						%>
							<div style="margin-left:-5px; padding-top:10px;">
								<input type="submit" class="button xss" name="submit" onclick="document.getElementById('controllo2').value='Elimina'" value="Elimina" />
								<input type="submit" class="button xss" name="submit" onclick="document.getElementById('controllo2').value='Visualizza'" value="Visualizza" />
								<input type="text" style="display:none" value="" name="controllo2" id="controllo2">
							</div>
					</fieldset>
				</form>
				<br />
		<%
			}
		}
		%>
		<div style="margin-left:-5px; padding-top:10px;">
			<a href="HomeAmministratore.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
		</div>
	</div>
	
</div>
</body>
</html>