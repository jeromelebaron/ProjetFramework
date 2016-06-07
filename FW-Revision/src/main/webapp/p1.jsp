<%@page import="fr.afcepf.atod26.framework.revision.entity.Personne"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Page 1</title>
</head>
<body>
	<jsp:useBean id="personnes"
		type="java.util.List<fr.afcepf.atod26.framework.revision.entity.Personne>"
		scope="request"></jsp:useBean>
	<%
	    for (Personne localPersonne : personnes) {
	        out.println(localPersonne);
	    }
	%>
</body>
</html>