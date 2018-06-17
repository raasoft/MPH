<%@ page language="java" import="java.util.*, entityBean.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Professore prof = (Professore)request.getSession().getAttribute("professore");%>
<% Progetto proj = (Progetto)request.getSession().getAttribute("progetto");%>
<% List<Releas> rel = (List<Releas>)request.getSession().getAttribute("listaRelease"); %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crea progetto: Aggiungi release</title>

<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" language="javascript" src="Js/prototype-1.js"></script>
<script type="text/javascript" language="javascript" src="Js/prototype-base-extensions.js"></script>
<script type="text/javascript" language="javascript" src="Js/prototype-date-extensions.js"></script>
<script type="text/javascript" language="javascript" src="Js/behaviour.js"></script>
<script type="text/javascript" language="javascript" src="Js/datepicker.js"></script>
<script type="text/javascript" language="javascript" src="Js/behaviors.js"></script>

<script type="text/javascript" src="Js/tinybox.js">
</script>

<script type="text/javascript">
function controllaData(){
	if (document.getElementById('data').value != "")
	{
		var dataOdierna = new Date();
		var anno = dataOdierna.getFullYear();
		var mese = dataOdierna.getMonth()+1;
		var giorno = dataOdierna.getDate();
		var stringa = document.getElementById('data').value;
		if (parseInt(stringa.substring(6,10)) < anno){
			document.getElementById('data').value = "";
			TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Hai selezionato una data antecedente alla data odierna!',0,0,0,0);
		}
		if (parseInt(stringa.substring(6,10)) == anno){
			if (parseInt(stringa.substring(3,5), 10) < mese){
				document.getElementById('data').value = "";
				TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Hai selezionato una data antecedente alla data odierna!',0,0,0,0);
			}
			if (parseInt(stringa.substring(3,5), 10) == mese)
				if (parseInt(stringa.substring(0,2), 10) < giorno){
				document.getElementById('data').value = "";
				TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Hai selezionato una data antecedente alla data odierna!',0,0,0,0);
			}
		}
	}
	
}

function copiaCampi(){
	document.getElementById('Tipo2').value = document.getElementById('labelTipo').value;
	document.getElementById('data2').value = document.getElementById('data').value;	
}
</script>

</head>
<body onclick="controllaData()">

<% if(request.getAttribute("messaggio") != null)
	out.print(request.getAttribute("messaggio")); %>
	
<div id="pagina">
	<div class="header">
		<a href="HomeProfessore.jsp"><img src="Images/MPHLogo.jpg"></img></a>
	</div>
	
	<div class="pulisci">
	</div>
	
	<div class="headerLink">
		<a href="ModificaProfiloProfessore.jsp"> Modifica profilo</a>
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
		<form action="CreaRelease" method="post" style="float:left;">
			<fieldset>
				<label id="titoli" for="nome">AGGIUNGI RELEASE</label>
				<br />
				<br />
				<br />
				<label for="release">Tipo Release*:</label>
				<br />
				<input type="text" name="labelTipo" id="labelTipo" value=""/>
				<br />
				<br />
				<label for="deadline">Deadline*:</label><br> <input type="text" value="" readonly class="datepicker" name="data" id="data" onchange="controllaData()"/>
				<br clear="all"/>
				<br />
				(* campo obbligatorio)			
				<br />
				<br />
				<br />
				<input type="submit" class="button xss" name="submit" value="Aggiungi release" style="margin-left:-5px;"/>
				<input type="hidden" name="controlloStep" value="Step2" />
				<input type="hidden" name="username" value=<%if(prof != null) out.print(prof.getUsername());%> />
			</fieldset>
		</form>
		<form action="CreaRelease" method="post" style="float:left; margin-top:252px; margin-left:-75px;">
			<fieldset>
				<input type="submit" class="button xss" id="buttonNext" onclick="copiaCampi()" value="Aggiungi release e termina" />
				<input type="hidden" name="controlloStep" value="Step3" />
				<input type="text" name="Tipo2" style="display:none" id="Tipo2" value=""/>
				<input type="text" value="" style="display:none" name="data2" id="data2" />
			</fieldset>
		</form>
		<form action="" method="post" style="float:left; margin-top:252px; margin-left:-15px;">
			<fieldset>
				<a href="HomeProfessore.jsp"><input type="button" class="button xss" id="buttonNext" value="Annulla creazione" /></a>
				<input type="hidden" name="controlloStep" value="Home" />
			</fieldset>
		</form>
	</div>
</div>
</body>
</html>