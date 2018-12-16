<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Забронировать билет</title>:
</head>
<body>
	<div class="container">
		<h2>Забронировать билет</h2>
		<form:form id="cancelForm" method="get" action="user" />
		<c:out value="Выберите место" />
		<c:forEach items="${seats}" var="seat">
			<form:form id="addDrivingForm" method="post" action="bookTicket">
				<input id="routeId" name="routeId" type="hidden" value="${routeId}" />
				<input id="seat" name="seat" type="hidden" value="${seat}" />
				<button type="submit">${seat}</button>
			</form:form>
		</c:forEach>
		<button type="button" onclick="document.forms['cancelForm'].submit()">Отмена</button>
	</div>
</body>