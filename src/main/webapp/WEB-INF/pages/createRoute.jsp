<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/createRoute.css" rel="stylesheet">
<title>Добавить маршрут</title>:
</head>
<body>
	<div class="header-container">
		<h2>Добавть маршрут</h2>
	</div>
	<form:form id="cancelForm" method="get" action="routeManagement" />
	<form:form id="addDrivingForm" method="post" action="createRoute" />

	<form:form method="get" action="createRoute" modelAttribute="driving">
		<div class="triple-container">
			<div class="route-container">
				<p>&nbsp;Автобус</p>
				<select class="custom-select" id="selectedBus" name="selectedBus">
					<c:forEach items="${busses}" var="bus1">
						<c:choose>
							<c:when test="${bus.equals(bus1.busNumber)}">
								<option selected value=${bus1.busNumber}>${bus1}</option>
							</c:when>
							<c:otherwise>
								<option value=${bus1.busNumber}>${bus1}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<form:input id="routeNumber" path="routeNumber" type="text"
					placeholder="Номер маршрута" required="required"
					value="${routeNumber}" />
				<button type="button"
					onclick="document.forms['addDrivingForm'].submit()">Добавить
					маршрут</button>
				<button type="button"
					onclick="document.forms['cancelForm'].submit()">Отмена</button>
			</div>
		</div>
		<form:errors path="routeNumber" cssStyle="color: #f4426e;" />

		<div class="triple-container">
			<p>&nbsp; Данные отправления &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp; &nbsp;Данные прибытия</p>
			<select class="custom-select" id="departureStation"
				name="departureStation">
				<c:forEach items="${stations}" var="station">
					<option value=${station.title}>${station.title}</option>
				</c:forEach>
			</select> <select class="custom-select" id="arrivalStation"
				name="arrivalStation">
				<c:forEach items="${stations}" var="station">
					<option value=${station.title}>${station.title}</option>
				</c:forEach>
			</select> <select class="custom-select" id="departureDate"
				name="departureDate">
				<c:forEach items="${days}" var="day">
					<option value=${day}>${day}</option>
				</c:forEach>
			</select> </select> <select class="custom-select" id="arrivalDate" name="arrivalDate">
				<c:forEach items="${days}" var="day">
					<option value=${day}>${day}</option>
				</c:forEach>
			</select>
			<form:input path="departureTime" type="text"
				placeholder="Время отправления" required="required" />
			<form:errors path="departureTime" cssStyle="color: #f4426e;" />
			<form:input path="arrivalTime" type="text"
				placeholder="Время прибытия" required="required" />
			<form:errors path="arrivalTime" cssStyle="color: #f4426e;" />
			<form:input path="price" type="text" placeholder="Цена"
				required="required" />
			<form:errors path="price" cssStyle="color: #f4426e;" />
			<button type="submit">Добавить переезд</button>
		</div>
	</form:form>
	<div class="triple-container">
		<c:if test="${drivings.size() != 0}">
			<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp;Переезды</p>
		</c:if>
		<c:forEach items="${drivings}" var="driving1">
			<div class="drivings-container">
				<form:form method="post" action="deleteDriving">
					<input id="uniqueRouteIdentifier" name="uniqueRouteIdentifier"
						type="hidden" value="${driving1.uniqueRouteIdentifier}" />
					<input id="bus" name="bus" type="hidden" value="${bus}" />
					<textarea readonly rows="4" cols="26" name="text">${driving1.departureStation.title} -  ${driving1.arrivalStation.title}&#013;&#010;${driving1.departureDate} - ${driving1.arrivalDate}&#013;&#010;${driving1.departureTime} - ${driving1.arrivalTime}&#013;&#010;Цена: ${driving1.price}</textarea>
					<div class="custom-button">
						<button type="submit">Удалить переезд</button>
					</div>
				</form:form>
			</div>
		</c:forEach>
	</div>
</body>