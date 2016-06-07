<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="actionun.frm" method="post">
		<input type="text" name="nom" /><span><c:out
				value="${erreurs['nom'] }"></c:out></span><input type="text" name="age" /><span><c:out
				value="${erreurs['age'] }"></c:out></span><input type="submit" value="go">
	</form>
</body>
</html>