<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Formulaire 2</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>

	<form action="actiondeux.frm" method="post">
		<input type="text" name="prenom" /><span><c:out
				value="${erreurs['prenom'] }"></c:out></span><input type="submit"
			value="go">
	</form>

</body>
</html>