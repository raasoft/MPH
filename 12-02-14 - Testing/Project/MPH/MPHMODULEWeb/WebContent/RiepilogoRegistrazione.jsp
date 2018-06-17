<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<% List<Gruppo> grup = (List<Gruppo>)request.getSession().getAttribute("ListaGruppi");%>
<% Gruppo nomeGrup = (Gruppo)request.getSession().getAttribute("gruppotemp"); %>
<% Set<ProgettoGruppo> proj = nomeGrup.getProgettigruppo(); %>
<% ProgettoGruppo nomeProj = (ProgettoGruppo)request.getSession().getAttribute("progettotemp"); %>

<title>Registrazione: riepilogo</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

</head>
<body>

<% if(request.getAttribute("messaggio") != null)
	out.print(request.getAttribute("messaggio")); %>
	
<div id="pagina">
	<div class="header">
		<a href="Index.jsp"><img src="Images/MPHLogo.jpg"></img></a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="divGenerale">
		<form action="RegistraStudente" method="get" name="formRiepilogo">
			<fieldset>
				<br />
				<label id="titoli" for="nome">RIEPILOGO REGISTRAZIONE </label>
				<br />
				<br />
				<br />
				<label for="username"><b>Username:</b></label>
				<label>&nbsp</label>
				<label for="username"><%if(stud != null) out.print(stud.getUsername());%></label>
				<br />
				<br />
				<label for="password"><b>Password:</b></label>
				<label>&nbsp</label>
				<label for="password"><%if(stud != null) out.print(stud.getPassword());%></label>
				<br />
				<br />
				<label for="nome"><b>Nome:</b></label>
				<label>&nbsp</label>
				<label for="nome"><%if(stud != null) out.print(stud.getNome());%></label>
				<br />
				<br />
				<label for="cognome"><b>Cognome:</b></label>
				<label>&nbsp</label>
				<label for="cognome"><%if(stud != null) out.print(stud.getCognome());%></label>
				<br />
				<br />
				<label for="email"><b>Email:</b></label>
				<label>&nbsp</label>
				<label for="email"><%if(stud != null) out.print(stud.getEmail());%></label>
				<br />
				<br />
				<label for="matricola"><b>Matricola:</b></label>
				<label>&nbsp</label>
				<label for="matricola"><%if(stud != null) out.print(stud.getMatricola());%></label>
				<br />
				<br />
				<%
				if (nomeGrup != null) {
				%>
					<label for="nomeGrup"><b>Gruppo scelto/creato:</b></label>
					<label>&nbsp</label>
					<label for="nomeGrup"><%if(stud != null) out.print(nomeGrup.getNome());%></label>
					<br />
					<br />
				<%
				}
				if (nomeProj != null) {
				%>
					<label for="nomeProj"><b>Progetto scelto:</b></label>
					<label>&nbsp</label>
					<label for="nomeProj"><%if(stud != null) out.print(nomeProj.getProgetto().getNome());%></label>
					<br />
					<br />
				<%
				}
				if (proj != null) {
				%>
					<label for="proj"><b>Elenco progetti del gruppo selezionato:</b></label>
					<%
					for (ProgettoGruppo p: proj)
						if (p != null) {
					%>
				     		<label for="proj"> <% out.print(p.getProgetto().getNome()); %></label>
				     		<label>&nbsp</label>
					<%	
						}
				}
				%>
				<div style="margin-left:-5px; margin-top:15px;">
					<input type="submit" class="button xss" name="submit" value="Conferma" />
					<a href="Logout"><input type="button" class="button xss" value="Annulla registrazione" /></a>
				</div>
			</fieldset>
		</form>
	</div>

</div>
</body>
</html>