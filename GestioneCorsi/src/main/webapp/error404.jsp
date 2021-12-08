<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="cdn.html" %> <!-- importo i cdn -->
<meta name="viewport" content="width=device-width,initial-scale=1"> <!-- lo metto per avere la pagine responsive -->
<link rel="stylesheet" href="css/style.css"><!-- importo il css -->
<meta charset="ISO-8859-1">
<title>Error 404</title>
</head>
<body>

<jsp:include page="navbar.jsp"/> <!-- devo usare la direttiva jsp perchè è una pagina jsp(dinamica) -->

<div class="container">
<div class="page-header"><!-- titolo pagina -->
	
	<h3>Impossibile caricare la risorsa richiesta</h3>
</div>

<div class="panel panel-danger">
	<div class="panel-heading"><!-- titolo pannello -->
	
		<h3>pagina non trovata</h3>
	
	</div>
	
	<div class="panel-body">
		<p>torna alla pagina precedente</p>
		<input type="button" onclick="window.history.back()" class="btn btn-default" value="indietro">
	</div>


</div>


<footer><%@include file="footer.html" %></footer><!-- tag semantico -->


</div>


</body>
</html>