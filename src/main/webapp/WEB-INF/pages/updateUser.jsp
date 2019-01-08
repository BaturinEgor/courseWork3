<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="currentRole" value="${role}" />
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Update user</title>
</head>
<body>
	<div class="container">
		<form:form id="cancelForm" method="get" action="admin" />
		<form:form method="post" action="updateUser" modelAttribute="user">
			<form:hidden path="id" />
			<h2>Update user</h2>
			<div class="container-block">
				<form:input path="login" type="text" placeholder="login"
					required="required" readonly="true" />
				<form:errors path="login" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="password" type="password" placeholder="пароль"
					required="required" />
				<form:errors path="password" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="firstName" type="text" placeholder="имя"
					required="required" />
				<form:errors path="firstName" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="lastName" type="text" placeholder="фамилия"
					required="required" />
				<form:errors path="lastName" cssStyle="color: #f4426e;" />
			</div>
			<select class="custom-select" id="role" name="role.name">
				<c:forEach items="${roles}" var="role">
					<c:choose>
						<c:when test="${currentRole.equals(role.name)}">
							<option selected value=${role.name}>${role.name}</option>
						</c:when>
						<c:otherwise>
							<option value=${role.name}>${role.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<button type="submit">Confirm</button>
			<button type="button" onclick="document.forms['cancelForm'].submit()">Cancel</button>
		</form:form>
	</div>
</body>