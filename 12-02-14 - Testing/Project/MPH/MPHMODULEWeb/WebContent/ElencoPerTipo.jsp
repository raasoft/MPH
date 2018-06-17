<%@ page language="java" import="java.util.*, java.net.URL, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Professore prof = (Professore)request.getSession().getAttribute("professore");%>
<% Releas r = (Releas) request.getSession().getAttribute("releasetemp");%>

<title>Release per tipo</title>
<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function scelta(stringa){
	document.getElementById('voto').value = stringa;
}
</script>

</head>
<body>

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
	
	<div id="divGenerale">
		<label style="width:90%;">Per poter valutare le release è necessario chiudere definitivamente la consegna delle stesse da parte degli studenti:</label>
		<a href="FineConsegna"><input type="button" class="button xss" name="buttonCrea" value="Fine consegna" /></a>
		<br />
		<br />
		Lista delle release suddivise per tipologia &nbsp <b><% out.print(r.getTipo()); %></b>:
		<br />
		<%
		if(r != null) {
			if(r.getListaRelease().size() <= 0) {
				%>
					<br />
					<label style="width:90%; margin-left:14px;">Nessun gruppo ha ancora consegnato questo tipo di release!</label>
					<br />
					<br />
				<%
			}
			for (ProgettoRelease rel : r.getListaRelease()) {	
			%>
				<br />
				<label style="float:left;"> &nbsp &nbsp &nbsp &#149</label>
				<label style="float:left; padding-left:20px; margin-bottom:8px;"><b>Gruppo:</b> &nbsp <%out.print(rel.getIdProgettoGruppo().getGruppo().getNome());%> </label>
			<%
				if(rel.getFiles().size() != 0) {
			%>
				  	<form action="Condividi" method="post" style="float:left; margin-top:-13px;">
						<fieldset>
							<input type="hidden" name="nome" value=<%=rel.getId()%> />
							<input type="submit" class="button xss" name="submit" value="Condividi" />
						</fieldset>
					</form>
					<label style="clear:both;"></label>
			<%
				}
				if(rel.getVotoParziale() == 0) {
				  	%>
				  	<form action="Valuta" method="post" style="margin-left:33px; clear:both;">
						<fieldset>
							<input type="hidden" name="nome" value=<%=rel.getId()%> />
							<label><b>Voto: </b></label><input name="voto" id="voto" style="display:none" type="text" value="1"/>
							<SELECT name="voto" id="voto" onChange="scelta(this.value)" >
								<option value="1" selected>1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
							</SELECT>
							<input type="submit" class="button xss" name="submit" value="Valuta" />
						</fieldset>
					</form>
					<%
				  	}
				  	else {
				  	%> 
				  		<form style="margin-left:33px; clear:both;">
				  			<fieldset>
				  				<label style="margin-top:30px;"><b>Voto: </b><% out.print(rel.getVotoParziale()); %> <%if(rel.getFiles().size() == 0) out.print("(Non consegnato)");%></label>
				  			</fieldset>
				  		</form>
				  	<%
				  	}
					if(rel.getFiles() != null)
						for(FileRelease f : rel.getFiles()) {
					%>
							<form name="formSelectFile" id="formSelectFile" action="Download" method="get" style="margin-left:33px; clear:both;">
								<fieldset>
									<input type="hidden" name="nome" value=<% out.print(f.getId()); %> />
									<label for="descrizione" style="width:90%; float:left; clear:both;"><b>Descrizione: </b> &nbsp <%=f.getDescrizione() %></label>
									<br />
									<label for="file" style="float:left; margin-top:10px; clear:both;"><b>File:</b> &nbsp   <%=f.getFile().split("/")[f.getFile().split("/").length - 1]%> </label> 
									<a href="#" onclick="document.forms['formSelectFile'].submit()"><img src="Images/IconDownload.png" style="float:left; widht:40px; height:40px; margin-left:10px;"></img></a>
								</fieldset>
							</form>	
					<%
					}
		}
	}
	%>
	</div>
	
	<br/>
	<div style="width:100%; float:left; padding-top:0px; padding-left:25px; margin-left:40px;">
		<a href="HomeProgettoProfessore.jsp"><input type="button" class="button xss" name="submit" value="Back" /></a>
	</div>	
			
</div>
</body>
</html>