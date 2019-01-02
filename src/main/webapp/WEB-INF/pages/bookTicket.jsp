<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/chooseSeat.css" rel="stylesheet">
<title>Забронировать билет</title>:
</head>
<body>
	<form:form id="cancelForm" method="get" action="user" />
	<div class="button">
		<button type="button" onclick="document.forms['cancelForm'].submit()">Отмена</button>
	</div>
	<c:set var="count" value="0" scope="page" />
	<c:set var="flag" value="false" scope="page" />
	<div class="container">
		<h2>Выберите место</h2>
		<div class="cantainer-flex">
			<c:forEach items="${seats}" var="seat">
				<c:if test="${count % 4 == 0}">
					<c:if test="${flag == 'true'}">
						<c:set var="flag" value="false" scope="page" />
		</div>
		</c:if>
		</c:if>
		<c:if test="${count % 4 == 0}">
			<c:if test="${flag == 'false'}">
				<c:set var="flag" value="true" scope="page" />
				<div class='elem'>
			</c:if>
		</c:if>
		<form:form id="addDrivingForm" method="post" action="bookTicket">
			<input id="routeId" name="routeId" type="hidden" value="${routeId}" />
			<input id="seat" name="seat" type="hidden" value="${seat}" />
			<button tye="submit">${seat}</button>
		</form:form>
		<c:set var="count" value="${count + 1}" scope="page" />
		</c:forEach>
	</div>
	</div>
</body>