<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
</head>
<body class="main_body">
<jsp:include page="banner.jsp">
    <jsp:param name="title" value="List of users"/>
</jsp:include>
<div class="centerdiv">
    <c:if test='${pageContext.request.getAttribute("error") != null}'>
        <label class="error">${pageContext.request.getAttribute("error")}</label>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>User type</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Date of Birth</th>
                <th>Modify</th>
                <th>Remove</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            for (User user : users) {
        %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getUserType() %></td>
            <td><%= user.getFirstName() %></td>
            <td><%= user.getLastName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getDob() %></td>
            <td>
                <form action="displayModify" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit">Modify</button>
                </form>
            </td>
            <td>
                <form action="remove" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit">Remove</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>





</div>
</div>


</body>
</html>

