<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Studente stud = (Studente)request.getSession().getAttribute("studente");%>
<% List<Gruppo> lista = (List<Gruppo>) request.getSession().getAttribute("ListaGruppi"); %>
<% Set<Progetto> Listaprog = new HashSet<Progetto>();%>
<% Set<ProgettoRelease> lista2 = new HashSet<ProgettoRelease>(); %>
<% int cont = 0; %>

<title>Release condivise</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function StatoIniziale(){
	formSelectRelease.radioButtonSelectRelease[0].checked = true;
}
function MostraDivDettaglioRelease(indice){
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
		Lista delle release condivise, suddivise per progetto:
		<br />
		<%
		for(Gruppo g : lista) {
			lista2.addAll(g.getReleaseCondivise());
			for(ProgettoRelease pr : g.getReleaseCondivise())
				 Listaprog.add(pr.getIdProgettoGruppo().getProgetto());
		}
		if(lista2 != null) {
			if(lista2.size() <= 0) {
		%>
				<br />
				<label style="width:90%; float:left; margin-left:14px;">In questo momento non sei abilitato alla condivisione delle release, attendere l'abilitazione da parte del professore di riferimento!</label>
				<br />
				<br />
		<%
			}
			else {
		%>
				<form id="formSelectRelease" name="formSelectRelease" action="DettaglioRelease" method="get">
					<fieldset>
						<%
						for(Progetto p : Listaprog) {
						%>
							<input type="hidden" name="nome" value=<%out.print(p.getNome());%> />
							<br />
						<%
							for(ProgettoRelease pr : lista2) {
								if(pr.getIdProgettoGruppo().getProgetto().getNome().compareTo(p.getNome()) == 0) {
						%>
							
									<input type="hidden" name="nome" value=<%=pr.getId()%> />
									<input type="radio" checked="checked" name="radioButtonSelectRelease" value=<% out.print("\"" + pr.getId() + "\""); %>><a href="#" id="linkElencoConMenu" onclick="MostraDivDettaglioRelease(<% out.print(cont); %>)"> <%=p.getNome() %> </a>
									<br />
									
									<div id=<% out.print(cont); %> style="width:100%; float:left; margin-left:40px; padding-top:15px; padding-bottom:20px; display:none">
										<label for="text"><b>Tipo release:</b></label>
										<label>&nbsp</label>
										<label> <%=pr.getIdRelease().getTipo() %> </label>
										<br />
										<label for="text"><b>Gruppo:</b></label>
										<label>&nbsp</label>
										<label> <%=pr.getIdProgettoGruppo().getGruppo().getNome() %> </label>
									</div>	
						<%
								}
							}
						}	
						%>
						</fieldset>
					</form>		
				<br />	
				<div style="float:left; margin-left:10px; margin-bottom:20px; margin-top:-5px;">
					<input type="submit" class="button xss" name="submit" onclick="document.forms['formSelectRelease'].submit()" value="Dettaglio" />
				</div>
		<%
			}
		}
		%>
		<div style="float:left; margin-left:10px; margin-bottom:20px; margin-top:-5px;">
			<a href="HomeStudente.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
		</div>
	</div>
	
</div>
</body>
</html>