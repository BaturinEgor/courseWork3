<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Добавить автобус</title>
</head>
<body>
	<div class="container">
		<form:form id="cancelForm" method="get" action="admin" />
		<form:form method="post" action="createBus" modelAttribute="bus">
			<h2>Добавить автобус</h2>
			<div class="container-block">
				<form:input path="busNumber" type="text"
					placeholder="номер автобуса" required="required" />
			</div>
			<div class="container-block">
				<form:input path="seats" type="text" placeholder="Количество мест"
					required="required" />
				<form:errors path="seats" cssStyle="color: #f4426e;" />
			</div>
			<select class="custom-select" id="carrier" name="carrier">
				<c:forEach items="${carriers}" var="carrier">
					<option value=${carrier.title}>${carrier.title}</option>
				</c:forEach>
			</select>
			<button type="submit">Добавить</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Отменить</button>
		</form:form>
	</div>
</body>