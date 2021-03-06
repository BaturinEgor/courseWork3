<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>Log in</title>
<link href="css/login.css" rel="stylesheet">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="error" value="${requestScope.error}" />
	<div class="container">
		<form id="cancelForm" method="get" action="index"></form>
		<form:form method="post" action="j_spring_security_check"
			modelAttribute="user">
			<h2>Вход в систему</h2>
			<input name="j_username" type="text" placeholder="логин"
				title="login" autofocus="true" />
			<input name="j_password" type="password" placeholder="пароль"
				title="password" />
			<button type="submit">Вход</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Отмена</button>
			<div class="error-message">
				<span><p />${error}</span>
			</div>
		</form:form>
	</div>
</body>