<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" uri="/WEB-INF/carrierTag.tld"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<link href="css/table.css" rel="stylesheet">
<title>Carrier</title>
</head>
<body>
	<div class="error-message">
		<c:out value="${message}" />
	</div>
	<form:form id="toAdmin" method="get" action="admin" />
	<button onclick="document.forms['toAdmin'].submit()">На
		главную</button>
	<button onclick="document.forms['createForm'].submit()">Добавить
		перевозчика</button>

	<form action="carrierManagement" method="get">
		Найти пеервозчика <input placeholder="Название перевозчика" id="title"
			name="title" type="text"></input> <input type="submit" value="поиск">
	</form>

	<my:carrierTag carriers="${carriers}" />
	<p />
	<form:form id="createForm" method="get" action="createCarrier" />


	<script>
		function removeCarrier(id) {
			if (confirm("Вы действительно хотите удалить перевозчика? Все связанные данные будут утеряны")) {
				var form = document.createElement("form");
				form.setAttribute("method", "post");
				form.setAttribute("action", "deleteCarrier");
				document.body.appendChild(form);
				var idField = document.createElement("input");
				idField.setAttribute("type", "hidden");
				idField.setAttribute("name", "id");
				idField.setAttribute("value", id);
				form.appendChild(idField);
				form.submit();
			}
		}

		function updateCarrier(id) {
			var form = document.createElement("form");
			form.setAttribute("method", "get");
			form.setAttribute("action", "updateCarrier");
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