<%@ page language="java"  import="java.util.*, entityBean.*"  contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% List<Progetto> lista = (List<Progetto>) request.getSession().getAttribute("ListaProgettiCompleta"); %>
<% List<ProgettoGruppo> proj = (List<ProgettoGruppo>)request.getSession().getAttribute("listaProgettiGruppo"); %>
<% Gruppo grup = (Gruppo)request.getSession().getAttribute("Gruppo"); %>
<% Set<Studente> st = new HashSet<Studente>(); %>
<% int cont = 0; %>
<% List<Progetto> controlla = new ArrayList<Progetto>();
	boolean ctrl = false;
	for (Progetto p: lista){
		for(ProgettoGruppo pg : proj)
			if(p.getNome().compareTo(pg.getProgetto().getNome()) == 0)
				ctrl = true;
		if(ctrl)
			ctrl = false;
		else
			controlla.add(p);
	}
%>
<title>Visualizza progetti</title>

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
		Elenco dei progetti disponibili da aggiungere al gruppo:
		<br />
		<br />
		<%
		if(controlla != null) {
			if(controlla.size() <= 0) {
		%>
				<label style="width:90%; margin-left:14px;">Non sono presenti progetti da aggiungere al gruppo!</label>
				<div style="margin-left:-6px; padding-top:20px;">
					<a href="HomeStudente.jsp"><input type="button" class="button xs" value="Back" /></a>
				</div>
		<%
			}
			else {
		%>	
				<form action="AggiungiProgetto" name="formSelectProgetto" id="formSelectProgetto" method="post">
					<fieldset>
						<%
						for (Progetto pg : lista) {
							if (pg.controllaProg(proj) == false) {
								cont = cont + 1;
						%>
								<input type="hidden" name="nome" value=<%out.print(pg.getNome());%> />
								<input type="radio" checked="checked" name="radioButtonSelectProgetto" value=<% out.print("\"" + pg.getNome() + "\""); %>><a href="#" id="linkElencoConMenu" onclick="MostraDivDettaglioProgetto(<% out.print(cont); %>)"> <%=pg.getNome() %> </a>
								<br />
							
								<div id=<% out.print(cont); %> style="width:100%; float:left; margin-left:40px; padding-top:15px; padding-bottom:20px; display:none;">
									<label for="nome"><b>Nome progetto:</b></label>
									<label>&nbsp</label>
									<label name="nomeProj"><%=pg.getNome()%></label>
									<br />
									<br />
									<label for="descr" style="width:90%;"><b>Descrizione:</b></label>
									<label>&nbsp</label>
									<label name="descrProj"><%=pg.getDescrizione()%></label>
									<br />
									<br />
									<label for="corso"><b>Corso:</b></label>
									<label>&nbsp</label>
									<label name="corsoProj"><%=pg.getMateria()%></label>
									<br />
									<br />
									<label for="prof"><b>Professore di riferimento:</b></label>
									<label>&nbsp</label>
									<label><%=pg.getProfessore().getNome()%></label>
									<label>&nbsp</label>
									<label><%=pg.getProfessore().getCognome()%></label>	
								</div>		
						<%
							}
						}
						%>
						<br>
						<div style="width:100%;	float:left;	margin-left:12px; margin-top:-10px;	padding-bottom:20px;">
							<input type="submit" class="button xss" value="Conferma">
							<a href="HomeGruppo.jsp"><input type="button" class="button xss" value="Back"></a>
						</div>
					</fieldset>					
				</form>
				<br />
		<%
			}
		}
		%>
	</div>
</div>
</body>
</html>