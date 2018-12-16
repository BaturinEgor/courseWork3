<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Добавить станцию</title>
</head>
<body>
	<div class="container">
		<form:form id="cancelForm" method="get" action="stationManagement" />
		<form:form method="post" action="createStation"
			modelAttribute="station">
			<h2>Добавить станцию</h2>
			<div class="container-block">
				<form:input path="title" type="text" placeholder="название"
					required="required" />
			</div>
			<button type="submit">Добавить</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Отменить</button>
		</form:form>
	</div>
</body>