<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Connexion</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1>Connexion</h1>
		<form action="connexion.frm" method="post">
			<div class="form-group">
				<label class="col-md-2">Login : </label> <input type="text"
					name="login" value="${login}" />&#160;<span><c:out
						value="${erreurs['login'] }"></c:out></span>
			</div>
			<div class="form-group">
				<label class="col-md-2">Mot de passe : </label> <input
					type="password" name="password" />&#160;<span><c:out
						value="${erreurs['password'] }"></c:out></span>
			</div>
			<div class="form-group">
				<input type="submit" value="Connexion" class="btn btn-primary">&#160;<span><c:out
						value="${erreurs['authentification'] }"></c:out></span>
			</div>
		</form>
	</div>
</body>
</html>