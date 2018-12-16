<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Личный кабинет</title>:
</head>
<body>
	<h2>${message}</h2>
	<form:form id="cancelForm" method="get" action="user" />
	<div class="container">
		<h2>Личный кабинет</h2>
		<c:forEach items="${tickets}" var="ticket">
			<h2>Билет</h2>
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
        <form:form id="cancelForm" method="get" action="deleteTicket">
				<input id="id" name="id" type="hidden"
					value="${ticket.id}" />
				<button type="submit">Вернуть билет</button>
			</form:form>
			<p />
			<p />
			<p />
		</c:forEach>
		<button type="button" onclick="document.forms['cancelForm'].submit()">На
			главную</button>
	</div>
</body>