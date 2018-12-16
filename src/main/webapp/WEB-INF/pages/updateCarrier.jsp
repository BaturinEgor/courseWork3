<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Изменить перевозчика</title>
</head>
<body>
	<div class="container">
		<form:form id="cancelForm" method="get" action="carrierManagement" />
		<form:form method="post" action="updateCarrier"
			modelAttribute="carrier">
			<h2>Изменить перевозчика</h2>
			<form:hidden path="id" value="${carrier.id}" />
			<div class="container-block">
				<form:input path="title" type="text" placeholder="название"
					required="required" value="${carrier.title}" />
			</div>
			<button type="submit">Изменить</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Отменить</button>
		</form:form>
	</div>
</body>