<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Изменить автобус</title>
</head>
<body>
	<div class="container">
		<form:form id="cancelForm" method="get" action="busManagement" />
		<form:form method="post" action="updateBus" modelAttribute="bus">
			<h2>Изменить автобус</h2>
			Номер автобуса:
			<form:hidden path="id" required="required" value="${bus.id}" />
			<div class="container-block">
				<form:input path="busNumber" type="text"
					placeholder="номер автобуса" required="required"
					value="${bus.busNumber}" />
			</div>
			Количество мест:
			<div class="container-block">
				<form:input path="seats" type="text" placeholder="Количество мест"
					required="required" value="${bus.seats}" />
				<form:errors path="seats" cssStyle="color: #f4426e;" />
			</div>
			Перевозчик:
			<select class="custom-select" id="carrier" name="carrier">
				<c:forEach items="${carriers}" var="carrier">
					<c:choose>
						<c:when test="${bus.carrier.title.equals(carrier.title)}">
							<option selected value=${carrier.title}>${carrier.title}</option>
						</c:when>
						<c:otherwise>
							<option value=${carrier.title}>${carrier.title}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<button type="submit">Изменить</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Отменить</button>
		</form:form>
	</div>
</body>