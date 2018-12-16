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
	<c:out value="${message}" />
	<p />
	<a onclick="document.forms['toAdmin'].submit()">На главную</a>
	<a onclick="document.forms['createForm'].submit()">Добавить станцию</a>
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