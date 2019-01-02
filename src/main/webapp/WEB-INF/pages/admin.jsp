<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="my" uri="/WEB-INF/routeTag.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/admin.css" rel="stylesheet">
<script src="https://www.google.com/recaptcha/api.js"></script>
<title>Create user</title>
</head>
<body>
	<div class="button">
		<button type="button" onclick="document.forms['logout'].submit()">Выйти</button>
	</div>
	<div class="container-block">
		<h2>Добро пожаловать, ${user.getFirstName()}</h2>
		<div class="container">
			<form:form id="logout" method="get" action="logout" />
			<form:form id="carrierManagement" method="get"
				action="carrierManagement" />
			<form:form id="busManagement" method="get" action="busManagement" />
			<form:form id="createStation" method="get" action="stationManagement" />
			<form:form id="createRoute" method="get" action="routeManagement" />
			<form:form id="createUser" method="get" action="usersManagement" />
			<div class="button2">
				<button type="button"
					onclick="document.forms['carrierManagement'].submit()">Управление
					перевозчиками</button>
			</div>
			<div class="button2">
				<button type="button"
					onclick="document.forms['busManagement'].submit()">Управление
					автобусами</button>
			</div>
			<div class="button2">
				<button type="button"
					onclick="document.forms['createStation'].submit()">Управление
					станциями</button>
			</div>
			<div class="button2">
				<button type="button"
					onclick="document.forms['createRoute'].submit()">Управление
					маршрутами</button>
			</div>
			<div class="button2">
				<button type="button"
					onclick="document.forms['createUser'].submit()">Управление
					пользователями</button>
			</div>
			<form:errors path="message" cssStyle="color: #f4426e;" />
			<div class="error-message">
				<p>${message}</p>
			</div>
		</div>
	</div>
</body>