<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="Css/Style.css" type="text/css" media="screen" title="no title" charset="utf-8">

<script type="text/javascript" src="Js/tinybox.js">
</script>

</head>
<body>

<% if(request.getAttribute("messaggio") != null)
	out.print(request.getAttribute("messaggio")); %>
	
<div id="pagina">
	<div class="imgMph">
		<img src="Images/MPH.jpg" alt="Immagine logo mph"></img>
	</div>

	<div class="formLogin">
		<form action="Login" method="post">
				<fieldset>
					<label for="username" class="titoloLabel">Username:</label>
					<br />
					<input type="text" name="labelUser" id="labelUser" />				
					<br /><br/>
					<label for="password" class="titoloLabel">Password:</label>
					<br />
					<input type="password" name="labelPsw" id="labelPsw" />
					<br />
					<br />
					<input type="submit" name="submit"  class="button xs" value="Login" />
					<br />
					<br />
					<p>
						(Se sei un nuovo studente <a href="Registrazione.jsp">registrati</a>)
					</p>	
				</fieldset>
		</form>
	</div>
</div>
</body>
</html>