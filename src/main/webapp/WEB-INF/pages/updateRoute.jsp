<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/createRoute.css" rel="stylesheet">
<title>Изменить маршрут</title>
</head>
<body>
	<div class="error-message">
		<c:out value="${message}" />
	</div>
	<div class="header-container">
		<h2>Изменить маршрут</h2>
	</div>
	<div class="triple-container">
		<div class="route-container">
			Автобус:
			<form:form id="updateRoute2" method="post" action="updateRoute"
				modelAttribute="driving">
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
				Номер маршрута:
				<div class="container-block">
					<form:input id="routeNumber" path="routeNumber" type="text"
						placeholder="например 192А5" required="required"
						value="${routeNumber}" />
					<form:errors path="routeNumber" cssStyle="color: #f4426e;" />
				</div>
				<button type="button"
					onclick="document.forms['updateRoute2'].submit()">Изменить
					маршрут</button>
				<button type="button"
					onclick="document.forms['cancelForm'].submit()">Отмена</button>
			</form:form>
			<form:form id="cancelForm" method="get" action="routeManagement" />
		</div>
	</div>
	<div class="triple-container">
		<p>&nbsp; Данные отправления &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			&nbsp; &nbsp; &nbsp;Данные прибытия</p>
		<form:form id="updateRoute" method="get" action="updateRoute"
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
			<form:errors path="departureTime" cssStyle="color: #f4426e;" />
			<select class="custom-select" id="departureDate" name="departureDate">
				<c:forEach items="${days}" var="day">
					<option value=${day}>${day}</option>
				</c:forEach>
			</select>
			<select class="custom-select" id="arrivalDate" name="arrivalDate">
				<c:forEach items="${days}" var="day">
					<option value=${day}>${day}</option>
				</c:forEach>
			</select>
			<form:input path="departureTime" type="text"
				placeholder="например 13:30:00" required="true" />
			<form:input path="arrivalTime" type="text"
				placeholder="например 09:30:00" required="true" />
			<form:input path="price" type="text" placeholder="Цена"
				required="required" />
			<button type="submit">Добавить переезд</button>
		</form:form>
	</div>
	<div class="triple-container">
		<c:if test="${drivings.size() != 0}">
			<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp;Переезды</p>
		</c:if>
		<c:forEach items="${drivings}" var="driving1">
			<div class="drivings-container">
				<form:form method="post" action="deleteDrivingUpdate">
					<input id="uniqueRouteIdentifier" name="uniqueRouteIdentifier"
						type="hidden" value="${driving1.uniqueRouteIdentifier}" />
					<textarea readonly rows="4" cols="35" name="text">${driving1.departureStation.title} -  ${driving1.arrivalStation.title}&#013;&#010;${driving1.departureDate} - ${driving1.arrivalDate}&#013;&#010;${driving1.departureTime} - ${driving1.arrivalTime}&#013;&#010;${driving1.price}</textarea>
					<button type="submit">Удалить переезд</button>
				</form:form>
				<form:form method="get" action="updateDriving">
					<input id="uniqueRouteIdentifier" name="uniqueRouteIdentifier"
						type="hidden" value="${driving1.uniqueRouteIdentifier}" />
					<button type="submit">Изменить переезд</button>
				</form:form>
			</div>
		</c:forEach>
	</div>
</body>