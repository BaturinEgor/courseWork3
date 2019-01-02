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
	<c:out value="${message}" />
	<p />
	<button onclick="document.forms['toAdmin'].submit()">На
		главную</button>
	<my:routeTag2 ticketToBuyForm="${routes}"
		departureStation="${departureStation}"
		arrivalStation="${arrivalStation }" />
	<p />
	<script>
		function bookTicket(id) {
			var form = document.createElement("form");
			form.setAttribute("method", "get");
			form.setAttribute("action", "bookTicket");
			document.body.appendChild(form);
			var idField = document.createElement("input");
			idField.setAttribute("type", "hidden");
			idField.setAttribute("name", "id");
			idField.setAttribute("value", id);
			form.appendChild(idField);
			form.submit();
		}
	</script>
</body>