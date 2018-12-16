<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Добавить маршрут</title>:
</head>
<body>
	<div class="container">
		<h2>Добавть маршрут</h2>
		<form:form id="cancelForm" method="get" action="routeManagement" />
		<form:form id="addDrivingForm" method="post" action="createRoute" />
		<c:forEach items="${drivings}" var="driving1">
			<form:form method="post" action="deleteDriving">
				<input id="uniqueRouteIdentifier" name="uniqueRouteIdentifier"
					type="hidden" value="${driving1.uniqueRouteIdentifier}" />
				<textarea readonly rows="4" cols="40" name="text">${driving1.departureStation.title} -  ${driving1.arrivalStation.title}&#013;&#010;${driving1.departureDate} - ${driving1.arrivalDate}&#013;&#010;${driving1.departureTime} - ${driving1.arrivalTime}&#013;&#010;${driving1.price}</textarea>
				<button type="submit">Удалить перегон</button>
			</form:form>
		</c:forEach>
		<form:form method="get" action="createRoute" modelAttribute="driving">
			<div class="container-block">
				<form:input id="routeNumber" path="routeNumber" type="text"
					placeholder="Номер маршрута" required="required"
					value="${routeNumber}" />
				<form:errors path="routeNumber" cssStyle="color: #f4426e;" />
			</div>
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
				<form:input path="departureTime" type="text"
					placeholder="Время отправления" required="required" />
				<form:errors path="departureTime" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="departureDate" type="text"
					placeholder="Дата отправления" required="required" />
				<form:errors path="departureDate" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="arrivalTime" type="text"
					placeholder="Время прибытия" required="required" />
				<form:errors path="arrivalTime" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="arrivalDate" type="text"
					placeholder="Дата прибытия" required="required" />
				<form:errors path="arrivalDate" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="price" type="text" placeholder="Цена"
					required="required" />
				<form:errors path="price" cssStyle="color: #f4426e;" />
			</div>
			<select class="custom-select" id="selectedBus" name="selectedBus">
				<c:forEach items="${busses}" var="bus1">
					<c:choose>
						<c:when test="${bus.equals(bus1.busNumber)}">
							<option selected value=${bus1.busNumber}>${bus1.busNumber}</option>
						</c:when>
						<c:otherwise>
							<option value=${bus1.busNumber}>${bus1.busNumber}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<button type="submit">Добавить перегон</button>
			<button type="button"
				onclick="document.forms['addDrivingForm'].submit()">Добавить
				маршрут</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Отмена</button>
		</form:form>
	</div>
</body>