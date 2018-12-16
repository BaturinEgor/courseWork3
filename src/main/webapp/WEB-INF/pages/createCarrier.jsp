<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Добавить перевозчика</title>
</head>
<body>
	<div class="container">
		<form:form id="cancelForm" method="get" action="carrierManagement" />
		<form:form method="post" action="createCarrier"
			modelAttribute="carrier">
			<h2>Добавить перевозчика</h2>
			<div class="container-block">
				<form:input path="title" type="text" placeholder="название"
					required="required" />
			</div>
			<button type="submit">Добавить</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Отменить</button>
		</form:form>
	</div>
</body>