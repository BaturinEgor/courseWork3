<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form:form id="cancelForm" method="get" action="usersManagement" />
		<form:form method="post" action="create" modelAttribute="user">
			<h2>Добавить пользователя</h2>
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
				<form:input path="firstName" type="text" placeholder="имя"
					required="required" />
				<form:errors path="firstName" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="lastName" type="text" placeholder="фамилия"
					required="required" />
				<form:errors path="lastName" cssStyle="color: #f4426e;" />
			</div>
			<form:select class="custom-select" path="role.name">
				<form:option value="ADMIN">admin</form:option>
				<form:option value="USER">user</form:option>
			</form:select>
			<button type="submit">добавить</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">отмена</button>
		</form:form>
	</div>
</body>