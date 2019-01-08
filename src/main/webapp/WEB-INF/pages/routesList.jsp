<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" uri="/WEB-INF/routesList2.tld"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<link href="css/showRouts.css" rel="stylesheet">
<title>Список доступных маршрутов</title>
</head>
<body>
	<form:form id="toAdmin" method="get" action="user" />
	<div class="error-message">
		<c:out value="${message}" />
	</div>
	<p />
	<button onclick="document.forms['toAdmin'].submit()">На
		главную</button>
	<my:routeTag2 ticketToBuyForm="${routes}"
		departureStation="${departureStation}"
		arrivalStation="${arrivalStation }" date="${date2}" />
	<p />
	<script>
		function bookTicket(id, date) {
			console.log(id)
			console.log(date)
			console.log('some')
			var form = document.createElement("form");
			form.setAttribute("method", "get");
			form.setAttribute("action", "bookTicket");
			document.body.appendChild(form);
			var idField = document.createElement("input");
			idField.setAttribute("type", "hidden");
			idField.setAttribute("name", "id");
			idField.setAttribute("value", id);
			var id2Field = document.createElement("input");
			id2Field.setAttribute("type", "hidden");
			id2Field.setAttribute("name", "date");
			id2Field.setAttribute("value", date);
			form.appendChild(idField);
			form.appendChild(id2Field);
			form.submit();
		}
	</script>
</body>