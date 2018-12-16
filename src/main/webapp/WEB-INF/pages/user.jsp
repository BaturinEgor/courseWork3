<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="my" uri="/WEB-INF/routeTag.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<script src="https://www.google.com/recaptcha/api.js"></script>
<title>Create user</title>
</head>
<body>
	<div class="container">
		<form:form id="logout" method="get" action="logout" />
		<form:form id="findTicket" method="get" action="findTicket"
			modelAttribute="driving">
			<select class="custom-select" id="departureStation"
				name="departureStation">
				<c:forEach items="${stations}" var="station">
					<option value=${station.title}>${station.title}</option>
				</c:forEach>
			</select>
			<select class="custom-select" id="arrivalStation"
				name="arrivalStation">
				<c:forEach items="${stations}" var="station">
					<option value=${station.title}>${station.title}</option>
				</c:forEach>
			</select>
			<div class="container-block">
				<form:input path="departureDate" type="text"
					placeholder="Дата отправления" required="required" />
				<form:errors path="departureDate" cssStyle="color: #f4426e;" />
			</div>
		</form:form>
		<button type="button" onclick="document.forms['findTicket'].submit()">Найти
			билет</button>
		<button type="button"
			onclick="document.forms['busManagement'].submit()">Личный
			кабинет</button>
		<button type="button" onclick="document.forms['logout'].submit()">Выйти</button>
		<form:errors path="message" cssStyle="color: #f4426e;" />
		<div class="error-message">
			<p>${message}</p>
		</div>
	</div>
</body>