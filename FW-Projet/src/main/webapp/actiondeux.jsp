<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire 2</title>
</head>
<body>

	<form action="actiondeux.frm" method="post">
		<input type="text" name="prenom" /><span><c:out
				value="${erreurs['prenom'] }"></c:out></span><input type="submit"
			value="go">
	</form>

</body>
</html>