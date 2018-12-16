<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Билет</title>:
</head>
<body>
	<div class="container">
		<h2>Билет куплен</h2>
		Маршрут № ${ticket.route.routeNumber}
		<p />
		${ticket.departureStation.title} - ${ticket.arrivalStation.title}
		<p />
		Дата и время отправления: ${ticket.departureDate } /
		${ticket.departureTime}
		<p />
		Дата и время прибытия: ${ticket.arrivalDate} / ${ticket.arrivalTime}
		<p />
		Цена: ${ticket.price}
		<p />
		Номер автобуса: ${ticket.route.bus.busNumber}
		<p />
		Место: ${ticket.seat}
		<p />
		Имя пассажира: ${ticket.user.firstName}
		<p />
		Фамилия пассажира: ${ticket.user.lastName}
		<form:form id="cancelForm" method="get" action="user" />
		<%-- <form:form method="get" action="createRoute" modelAttribute="ticket">
			<div class="container-block">
				<form:input id="route" path="route" type="text"
					placeholder="Номер маршрута" readonly="true" required="required"
					value="${ticket.route.routeNumber}" />
			</div>
			<div class="container-block">
				<form:input id="departureStation" path="departureStation"
					type="text" placeholder="Номер маршрута" required="required"
					readonly="true" value="${ticket.departureStation.title}" />
			</div> --%>
		<button type="button" onclick="document.forms['cancelForm'].submit()">На
			главную</button>
	</div>
</body>