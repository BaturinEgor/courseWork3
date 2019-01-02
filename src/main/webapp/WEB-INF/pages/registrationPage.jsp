<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Create user</title>
</head>
<body>
	<div class="container">
		<form:form id="cancelForm" method="get" action="index" />
		<form:form method="post" action="registration" modelAttribute="user">
			<h2>Регистрация</h2>
			<div class="container-block">
				<form:input path="login" type="text" placeholder="логин"
					required="required" />
				<form:errors path="login" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="password" type="password" placeholder="пароль"
					required="required" />
				<form:errors path="password" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="confirmPassword" type="password"
					placeholder="подтвердите пароль" required="required" />
				<form:errors path="confirmPassword" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="firstName" type="text" placeholder="имя"
					required="required" />
				<form:errors path="firstName" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="lastName" type="text" placeholder="фамилия"
					required="required" />
				<form:errors path="lastName" cssStyle="color: #f4426e;" />
			</div>
			<form:hidden path="role" value="USER" />
			<button type="submit">Подтвердить</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Отменить</button>
			<form:errors path="message" cssStyle="color: #f4426e;" />
			<div class="error-message">
				<p>${message}</p>
			</div>
		</form:form>
	</div>
</body>