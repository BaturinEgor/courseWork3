<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" uri="/WEB-INF/stationTag.tld"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<link href="css/table.css" rel="stylesheet">
<title>Управление санциями</title>
</head>
<body>
	<form:form id="toAdmin" method="get" action="admin" />
	<div class="error-message">
		<c:out value="${message}" />
	</div>
	<p />
	<button onclick="document.forms['toAdmin'].submit()">На
		главную</button>
	<button onclick="document.forms['createForm'].submit()">Добавить
		станцию</button>
		
	<form action="stationManagement" method="get">
		Поиск по названию станции <input id="title" name="title"
			placeholder="Название станции" type="text">
			</input> <input
			type="submit" value="поиск">
	</form>
	<my:stationTag stations="${stations}" />
	<p />
	<form:form id="createForm" method="get" action="createStation" />
	<script>
		function removeStation(id) {
			if (confirm("Вы действительно хотите удалить станцию? Все связанные данные будут утеряны")) {
				var form = document.createElement("form");
				form.setAttribute("method", "post");
				form.setAttribute("action", "deleteStation");
				document.body.appendChild(form);
				var idField = document.createElement("input");
				idField.setAttribute("type", "hidden");
				idField.setAttribute("name", "id");
				idField.setAttribute("value", id);
				form.appendChild(idField);
				form.submit();
			}
		}

		function updateStation(id) {
			var form = document.createElement("form");
			form.setAttribute("method", "get");
			form.setAttribute("action", "updateStation");
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