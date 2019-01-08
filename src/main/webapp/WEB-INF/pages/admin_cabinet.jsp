<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" uri="/WEB-INF/customTag.tld"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<link href="css/table.css" rel="stylesheet">
</head>
<body>
	<form:form id="logoutForm" method="get" action="admin" />
	<h2>${message}</h2>
	<button onclick="document.forms['logoutForm'].submit()">На
		главную</button>
	<button onclick="document.forms['createForm'].submit()">Добавить
		пользователя</button>
	<my:customTag users="${users}" roles="${roles}" />
	<p />
	<form:form id="createForm" method="get" action="create" />


	<script>
		function removeUser(id) {
			if (confirm("Are you sure?")) {
				var form = document.createElement("form");
				form.setAttribute("method", "post");
				form.setAttribute("action", "deleteUser");
				document.body.appendChild(form);
				var idField = document.createElement("input");
				idField.setAttribute("type", "hidden");
				idField.setAttribute("name", "id");
				idField.setAttribute("value", id);
				form.appendChild(idField);
				form.submit();
			}
		}

		function updateUser(id) {
			var form = document.createElement("form");
			form.setAttribute("method", "get");
			form.setAttribute("action", "updateUser");
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