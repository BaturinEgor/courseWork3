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
		<form:form method="post" action="update" modelAttribute="user">
			<form:hidden path="id" />
			<h2>Update user</h2>
			<div class="container-block">
				<form:input path="login" type="text" placeholder="login"
					required="required" readonly="true" />
				<form:errors path="login" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="password" type="password" placeholder="password"
					required="required" />
				<form:errors path="password" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="confirmPassword" type="password"
					placeholder="password" required="required" />
				<form:errors path="confirmPassword" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="email" type="text" placeholder="email"
					required="required" />
				<form:errors path="email" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="firstName" type="text" placeholder="first name"
					required="required" />
				<form:errors path="firstName" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="lastName" type="text" placeholder="last name"
					required="required" />
				<form:errors path="lastName" cssStyle="color: #f4426e;" />
			</div>
			<div class="container-block">
				<form:input path="birthday" type="text" placeholder="birthday"
					required="required" />
				<form:errors path="birthday" cssStyle="color: #f4426e;" />
			</div>
			<select class="custom-select" id="role" name="role">
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
			<form:errors path="message" cssStyle="color: #f4426e;" />
		</form:form>
	</div>
</body>