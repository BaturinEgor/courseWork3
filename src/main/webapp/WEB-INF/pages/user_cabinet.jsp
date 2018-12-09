<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" uri="/WEB-INF/customTag.tld"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form id="logoutForm" method="get" action="logout"></form>
    <a onclick="document.forms['logoutForm'].submit()">Logout</a>
    <center>
        <h2>Welcome ${user.getFirstName()}</h2>
    </center>
</body>
</html>