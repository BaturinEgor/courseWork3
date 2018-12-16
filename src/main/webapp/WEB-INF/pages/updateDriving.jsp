<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Изменить перегон</title>
</head>
<body>
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
			<div class="container-block">
				<form:input path="departureTime" type="text"
					placeholder="Время отправления" required="required"
					value="${driving.departureTime}" />
				<form:errors path="departureTime" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="departureDate" type="text"
					placeholder="Дата отправления" required="required"
					value="${driving.departureDate}" />
				<form:errors path="departureDate" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="arrivalTime" type="text"
					placeholder="Время прибытия" required="required"
					value="${driving.arrivalTime}" />
				<form:errors path="arrivalTime" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="arrivalDate" type="text"
					placeholder="Дата прибытия" required="required"
					value="${driving.arrivalDate}" />
				<form:errors path="arrivalDate" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="price" type="text" placeholder="Цена"
					required="required" value="${driving.price}" />
				<form:errors path="price" cssStyle="color: #f4426e;" />
			</div>
		</form:form>
		<button type="button"
			onclick="document.forms['updateDriving'].submit()">Изменить
			перегон</button>
		<button type="button" onclick="document.forms['cancelForm'].submit()">Отмена</button>
	</div>
</body>