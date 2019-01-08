<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/updateDriving.css" rel="stylesheet">
<title>Изменить перегон</title>
</head>
<body>
	<div class="header-container">
		<h2>Изменить переезд</h2>
	</div>
	<div class="container">
		<form:form id="cancelForm" method="get" action="updateRoute" />
		<form:form id="updateDriving" method="post" action="updateDriving"
			modelAttribute="driving">
			<input id="uniqueRouteIdentifier" name="uniqueRouteIdentifier"
				type="hidden" value="${driving.uniqueRouteIdentifier}" />
			<select class="custom-select" id="departureStation"
				name="departureStation">
				<c:forEach items="${stations}" var="station1">
					<c:choose>
						<c:when test="${station1.equals(driving.departureStation)}">
							<option selected value="${station1.title}">${station1.title}</option>
						</c:when>
						<c:otherwise>
							<option value="${station1.title}">${station1.title}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<select class="custom-select" id="arrivalStation"
				name="arrivalStation">
				<c:forEach items="${stations}" var="station1">
					<c:choose>
						<c:when test="${station1.equals(driving.arrivalStation)}">
							<option selected value="${station1.title}">${station1.title}</option>
						</c:when>
						<c:otherwise>
							<option value="${station1.title}">${station1.title}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<select class="custom-select" id="departureDate" name="departureDate">
				<c:forEach items="${days}" var="day">
					<option value=${day}>${day}</option>
				</c:forEach>
			</select>
			</select>
			<select class="custom-select" id="arrivalDate" name="arrivalDate">
				<c:forEach items="${days}" var="day">
					<option value="${day}">${day}</option>
				</c:forEach>
			</select>
			<form:input path="departureTime" type="text"
				placeholder="Время отправления" required="required"
				value="${driving.departureTime}" />
			<form:input path="arrivalTime" type="text"
				placeholder="Время прибытия" required="required"
				value="${driving.arrivalTime}" />
			&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;Цена:&nbsp; &nbsp;<form:input
				path="price" type="text" placeholder="Цена" required="required"
				value="${driving.price}" />
			<form:errors path="price" cssStyle="color: #f4426e;" />
		</form:form>
	</div>
	<div class="header-container">
		<button type="button"
			onclick="document.forms['updateDriving'].submit()">Изменить
			переезд</button>
		<button type="button" onclick="document.forms['cancelForm'].submit()">Отмена</button>
	</div>
</body>