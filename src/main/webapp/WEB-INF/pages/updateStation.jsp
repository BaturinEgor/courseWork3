<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Изменить станцию</title>
</head>
<body>
	<div class="container">
		<form:form id="cancelForm" method="get" action="stationManagement" />
		<form:form method="post" action="updateStation"
			modelAttribute="station">
			<h2>Изменить станцию</h2>
			<form:hidden path="id" value="${station.id}" />
			<div class="container-block">
				<form:input path="title" type="text" placeholder="название"
					required="required" value="${station.title}" />
			</div>
			<button type="submit">Изменить</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Отменить</button>
		</form:form>
	</div>
</body>