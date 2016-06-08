<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Test</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<form action="actionun.frm" method="post">
			<input type="text" name="nom" /><span><c:out
					value="${erreurs['nom'] }"></c:out></span><input type="text" name="age" /><span><c:out
					value="${erreurs['age'] }"></c:out></span><input type="submit" value="go">
		</form>
	</div>
</body>
</html>