<%@page import="com.gestionecorsi.businesscomponent.model.Corsista"%>
<%@page import="com.gestionecorsi.businesscomponent.AdminFacade"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<%@ include file="cdn.html" %>
	<link rel="stylesheet" href="css/general.css">
	<link rel="stylesheet" href="css/footer.css">
	<link rel="stylesheet" href="css/navbar.css">
	<title>Riepilogo iscrizioni</title>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container">
		<div class="page-header">
			<h3>Studenti</h3>
		</div>
		<table class="table">
			<tr>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Precedenti formativi</th>	
			</tr>
			<%
				Corsista[] corsisti = AdminFacade.getInstance().getCorsisti();
			
				for(int i=0; i<corsisti.length; i++) {
			%>
			<tr>
				<td><%= corsisti[i].getNomeCorsista() %></td>
				<td><%= corsisti[i].getCognomeCorsista() %></td>
				<td>
					<%
						if(corsisti[i].getPrecedentiFormativi()) {
					%>
					<i class="glyphicon glyphicon-ok"></i>
					<%
						} else {
					%>
					<i class="glyphicon glyphicon-remove"></i>
					<%
						}
					%>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
		
	<footer>
		<%@ include file="footer.html" %>
	</footer>
</body>
</html>