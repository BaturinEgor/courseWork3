<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
</head>
<body>
    <h1>Access denied or requested page does not exist</h1>
    <h4>
        <spring:url value="home" var="userUrl" />
        <button onclick="location.href='${userUrl}'">Click to get home page</button>
    </h4>
</body>
</html>
