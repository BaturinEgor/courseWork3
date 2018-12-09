<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" uri="/WEB-INF/customTag.tld"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
<link href="css/table.css" rel="stylesheet">
<title>Welcome</title>
</head>
<body>
    <form:form id="logoutForm" method="get" action="logout" />
    <h2>${message}</h2>
    <a onclick="document.forms['logoutForm'].submit()">Logout</a>
    <center>
        <h2>Welcome ${user.getFirstName()}</h2>
    </center>
    <my:customTag users="${users}" roles="${roles}" />
    <p />
    <form:form id="createForm" method="get" action="create" />
    <a onclick="document.forms['createForm'].submit()">Create new user</a>


    <script>
					function removeUser(id) {
						if (confirm("Are you sure?")) {
							var form = document.createElement("form");
							form.setAttribute("method", "post");
							form.setAttribute("action", "delete");
							document.body.appendChild(form);
							var idField = document.createElement("input");
							idField.setAttribute("type", "hidden");
							idField.setAttribute("name", "id");
							idField.setAttribute("value", id);
							form.appendChild(idField);
							form.submit();
						}
					}

					function updateUser(id) {
						var form = document.createElement("form");
						form.setAttribute("method", "get");
						form.setAttribute("action", "update");
						document.body.appendChild(form);
						var idField = document.createElement("input");
						idField.setAttribute("type", "hidden");
						idField.setAttribute("name", "id");
						idField.setAttribute("value", id);
						form.appendChild(idField);
						form.submit();
					}
				</script>

</body>