<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Accueil</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1>Accueil</h1>
		<nav>
			<ul class="nav nav-tabs">
				<li role="presentation"><a
					href="${pageContext.request.contextPath}/secured/accueil.jsp">Home</a></li>
				<li role="presentation" class="active"><a
					href="${pageContext.request.contextPath}/secured/profil.frm">Profil</a></li>
			</ul>
		</nav>
		<div class="row">
			Quel artiste voulez écouter : <select>
				<c:forEach items="${artistes }" var="artiste">
					<option>${artiste }</option>
				</c:forEach>
			</select>
		</div>
	</div>
</body>
</html>