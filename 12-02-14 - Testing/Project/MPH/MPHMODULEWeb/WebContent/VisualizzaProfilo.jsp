<%@ page language="java"  import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Professore prof = (Professore)request.getAttribute("professore"); %>
<% Studente stud = (Studente)request.getAttribute("studente"); %>
<% Gruppo grup = (Gruppo)request.getAttribute("gruppo"); %>
<% Amministratore admin = (Amministratore)request.getSession().getAttribute("amministratore");%>
<title>Insert title here</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script type="text/javascript" src="Js/tinybox.js">
</script>

</head>
<body>
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
		if (stud != null) {
		%>
			<label id="titoli" for="nome">DETTAGLIO PROFILO</label>
			<br />
			<br />
			<br />
			<label for="username"><b>Username:</b></label>
			<label>&nbsp</label>
			<label name="username"><%out.print(stud.getUsername());%></label>
			<br />
			<br />
			<label for="nome"><b>Nome:</b></label>
			<label>&nbsp</label>
			<label name="nome"><%out.print(stud.getNome());%></label>
			<br />
			<br />
			<label for="cognome"><b>Cognome:</b></label>
			<label>&nbsp</label>
			<label name="cognome"><%out.print(stud.getCognome());%></label>
			<br />
			<br />
			<label for="email"><b>Email:</b></label>
			<label>&nbsp</label>
			<label name="email"><%out.print(stud.getEmail());%></label>
			<br />
			<br />	
			<label for="matricola"><b>Matricola:</b></label>
			<label>&nbsp</label>
			<label name="matricola"><%out.print(stud.getMatricola());%></label>
			<br />
			<br />	
		<%
		}
		%>
		
		<%
		if (prof != null){
		%>
			<label id="titoli" for="nome">DETTAGLIO PROFILO</label>
			<br />
			<br />
			<br />
			<label for="username"><b>Username:</b></label>
			<label>&nbsp</label>
			<label name="username"><%out.print(prof.getUsername());%></label>
			<br />
			<br />
			<label for="nome"><b>Nome:</b></label>
			<label>&nbsp</label>
			<label name="nome"><%out.print(prof.getNome());%></label>
			<br />
			<br />
			<label for="cognome"><b>Cognome:</b></label>
			<label>&nbsp</label>
			<label name="cognome"><%out.print(prof.getCognome());%></label>
			<br />
			<br />
			<label for="email"><b>Email:</b></label>
			<label>&nbsp</label>
			<label name="email"><%out.print(prof.getEmail());%></label>
			<br />
			<br />	
			<label for="telefono"><b>Telefono:</b></label>
			<label>&nbsp</label>
			<label name="telefono"><%out.print(prof.getTelefono());%></label>
			<br />
			<br />	
		<%
		}
		%>
		
		<% if (grup != null) { %>
			<label id="titoli" for="nome">DETTAGLIO GRUPPO</label>
			<br />
			<br />
			<br />
			<label for="nome"><b>Nome:</b></label>
			<label>&nbsp</label>
			<label name="nomeGruppo"><%out.print(grup.getNome());%></label>
			<br />
			<br />
			<% if (!grup.getStudenti().isEmpty()) {%>
				<label for="nome"><b>Membri:</b></label>
				<label>&nbsp</label>
			<% for (Studente s: grup.getStudenti()) 
					if ( s != null) {
			%>
			
			<label name="nome"><%out.print(s.getNome());%>  <%out.print(s.getCognome());%></label>
			<br />
			<br />	
		<%			
					}
				} else
				{
				%>
				<label for="testo">Non è associato nessuno studente al gruppo</label>
				<br/>
				<br/>
				<%
				}
		}
		%>
		
		<div style="margin-left:-5px; padding-top:10px;">
			<a href="SelezionaUtente.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
		</div>
	</div>
	
</div>

</body>
</html>