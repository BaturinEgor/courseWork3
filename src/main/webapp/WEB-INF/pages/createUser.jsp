<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/update.css" rel="stylesheet">
<title>Create user</title>
</head>
<body>
    <div class="container">
        <form:form id="cancelForm" method="get" action="admin" />
        <form:form method="post" action="create" modelAttribute="user">
            <h2>Create user</h2>
            <div class="container-block">
                <form:input path="login" type="text" placeholder="login" required="required" />
                <form:errors path="login" cssStyle="color: #f4426e;" />
            </div>
            <div class="container-block">
                <form:input path="password" type="password" placeholder="password"
                    required="required"
                />
                <form:errors path="password" cssStyle="color: #f4426e;" />
            </div>
            <div class="container-block">
                <form:input path="confirmPassword" type="password" placeholder="password"
                    required="required"
                />
                <form:errors path="confirmPassword" cssStyle="color: #f4426e;" />
            </div>
            <div class="container-block">
                <form:input path="email" type="text" placeholder="email" required="required" />
                <form:errors path="email" cssStyle="color: #f4426e;" />
            </div>
            <div class="container-block">
                <form:input path="firstName" type="text" placeholder="first name"
                    required="required"
                />
                <form:errors path="firstName" cssStyle="color: #f4426e;" />
            </div>
            <div class="container-block">
                <form:input path="lastName" type="text" placeholder="last name" required="required" />
                <form:errors path="lastName" cssStyle="color: #f4426e;" />
            </div>
            <div class="container-block">
                <form:input path="birthday" type="text" placeholder="birthday" required="required" />
                <form:errors path="birthday" cssStyle="color: #f4426e;" />
            </div>
            <form:select class="custom-select" path="role">
                <form:option value="ADMIN">admin</form:option>
                <form:option value="USER">user</form:option>
            </form:select>
            <button type="submit">Confirm</button>
            <button type="button" onclick="document.forms['cancelForm'].submit()">Cancel</button>
            <form:errors path="message" cssStyle="color: #f4426e;" />
        </form:form>
    </div>
</body>