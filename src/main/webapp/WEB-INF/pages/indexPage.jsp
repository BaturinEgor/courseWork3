<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<title>Log in</title>
<link href="css/index.css" rel="stylesheet">
</head>
<body>
	<div class="container-header">
		<h2>Добро пожаловать</h2>
		<h3>Для поиска и покупки автобусных билетов войдите или
			зарегистрируйтесь</h3>
		<p>${message}</p>
	</div>
	<div class="container">
		<form:form method="get" action="login">
			<button type="submit">Войти</button>
		</form:form>
		<form:form method="get" action="registration">
			<button type="submit">Зарегистрироваться</button>
		</form:form>
	</div>
</body>