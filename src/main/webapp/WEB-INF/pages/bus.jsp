<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" uri="/WEB-INF/busTag.tld"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<link href="css/table.css" rel="stylesheet">
<title>Управление автобусами</title>
</head>
<body>
	<form:form id="toAdmin" method="get" action="admin" />
	<c:out value="${message}" />
	<p />
	<button onclick="document.forms['toAdmin'].submit()">На
		главную</button>
	<button onclick="document.forms['createForm'].submit()">Добавить
		автобус</button>
	<form action="busManagement" method="get">
		<input id="title" name="title" placeholder="Перевозчик" type="text"></input>Фильтр
		по перевозчику<input type="submit" value="Фильтровать">
	</form>
	<form action="busManagement" method="get">
		<input placeholder="Номер автобуса" id="" name="number" type="text"></input>Фильтр
		по номеру автобуса<input type="submit" value="Фильтровать">
	</form>
	<my:busTag busses="${busses}" />
	<p />
	<form:form id="createForm" method="get" action="createBus" />
	<script>
		function change(number) {
			if (confirm(number)) {
				var form = document.createElement("form");
				form.setAttribute("method", "post");
				form.setAttribute("action", "deleteBus");
				document.body.appendChild(form);
				var idField = document.createElement("input");
				idField.setAttribute("type", "hidden");
				idField.setAttribute("name", "id");
				idField.setAttribute("value", id);
				form.appendChild(idField);
				form.submit();
			}
		}

		function removeBus(id) {
			if (confirm("Вы действительно хотите удалить автобус? Все связанные данные будут утеряны")) {
				var form = document.createElement("form");
				form.setAttribute("method", "post");
				form.setAttribute("action", "deleteBus");
				document.body.appendChild(form);
				var idField = document.createElement("input");
				idField.setAttribute("type", "hidden");
				idField.setAttribute("name", "id");
				idField.setAttribute("value", id);
				form.appendChild(idField);
				form.submit();
			}
		}

		function updateBus(id) {
			var form = document.createElement("form");
			form.setAttribute("method", "get");
			form.setAttribute("action", "updateBus");
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