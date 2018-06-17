<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% List<Progetto> lista = (List<Progetto>) request.getSession().getAttribute("ListaProgetti"); %>
<% List<ProgettoGruppo> proj = (List<ProgettoGruppo>)request.getSession().getAttribute("listaProgettiGruppo"); %>
<% Gruppo grup = (Gruppo)request.getSession().getAttribute("Gruppo"); %>
<% List<Progetto> lista4 = (List<Progetto>) request.getSession().getAttribute("ListaProgettiCompleta"); %>
<% Set<Studente> st = new HashSet<Studente>(); %>
<% int cont = 0; %>
<% int ind = 0;%>

<title>Home gruppo</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript" >
function MostraDivDettaglioProgetto(indice){
	if (document.getElementById(indice).style.display == "none")
		document.getElementById(indice).style.display="inline";
	else
		document.getElementById(indice).style.display="none";
}
</script>
</head>
<body>

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
		<img src="Images/IconGroup.png" alt="immagine utente" alt="immagine profilo utente"></img>
		<div class="infoUtente">
			<p>
				<b>Nome:</b> <% out.print(grup.getNome()); %><br>
				<% 
					st = grup.getStudenti();
 					if(st != null) {
			  			for(Studente s: st) {
			  		 		if (s != null) {
			  	%>
								<b>Membro:</b> <% out.print(s.getNome()); %>
								<br />
				<%
							}
						}
			  		 }
				%>
			</p>
		</div>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div id="divGenerale">
			Elenco dei progetti associati al gruppo:
			<br />
			<br />
			<%
			if(proj != null) {
				if(proj.size() <= 0) {
			%>
					<br />
					<label style="width:90%; margin-left:14px;">Non sono presenti progetti legati al gruppo, contattare il professore responsabile!</label>
					<br />
					<br />
			<%
				}
				else {
			%>
					<form action="VisualizzaProgettoStudente" name="formSelectProgetto" id="formSelectProgetto" method="post">
						<fieldset>
								<%
								for(ProgettoGruppo prgr : proj)
									if (prgr.getGruppo().getNome().compareTo(grup.getNome()) ==0)
									{
									cont = cont + 1;
								%>
									<input type="hidden" name="nome" value=<%out.print(prgr.getProgetto().getNome());%> />
									<label style="float:left;">&#149</label>
									<label style="float:left; margin-left:10px; margin-bottom:8px;" value=<% out.print(prgr.getProgetto().getNome()); %>><a href="#" id="linkElencoConUl" onclick="MostraDivDettaglioProgetto(<% out.print(cont); %>)"> <%=prgr.getProgetto().getNome() %></a></label>
									<br />
								
									<div class="pulisci">
									</div>	
									
									<div id=<% out.print(cont); %> style="float:left; margin-left:16px; padding-top:5px; display:none;">
										<label for="nome"><b>Nome progetto:</b></label>
										<label>&nbsp</label>
										<label><%=prgr.getProgetto().getNome()%></label>
										<br />
										<br />
										<label for="descr"><b>Descrizione:</b></label>
										<label>&nbsp</label>
										<label><%=prgr.getProgetto().getDescrizione()%></label>
										<br />
										<br />
										<label for="corso"><b>Corso:</b></label>
										<label>&nbsp</label>
										<label name="corsoProj"><%=prgr.getProgetto().getMateria()%></label>
										<br />
										<br />
										<label for="prof"><b>Professore di riferimento:</b></label>
										<label>&nbsp</label>
										<label><%=prgr.getProgetto().getProfessore().getNome()%></label>
										<label>&nbsp</label>
										<label><%=prgr.getProgetto().getProfessore().getCognome()%></label>
										<br />
										<br />
										<label for="votoFin"><b>Voto:</b></label>
										<label>&nbsp</label>
										<label><%if(prgr.getVotoFinale() != 0) out.print(prgr.getVotoFinale()); else out.print("Voto non ancora assegnato");%></label>
										<br />
										<br />
										<input type="hidden" name="nome" value=<%if(prgr != null) out.print(prgr.getId());%> />
									</div> 	
									
									<div class="pulisci">
									</div>			
							<%
							}
							%>
						</fieldset>
					</form>
			<%
				}
			}
			%>
	</div>
	
	<div style="width:100%; float:left; padding-top:0px; padding-left:25px; margin-left:25px;">
		<form action="" method="post">
			<fieldset>
				Per aggiungere un nuovo progetto al gruppo:
				<a href="VisualizzaProgetti.jsp"><input type="button" class="button xss" name="submit" value="Aggiungi progetto" /></a>
				<a href="SelezionaGruppoProfilo.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
			</fieldset>
		</form>
	</div>
	
</div>
</body>
</html>