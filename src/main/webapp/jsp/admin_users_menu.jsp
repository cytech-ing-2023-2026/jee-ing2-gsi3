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
    <jsp:param name="title" value="Welcome ${sessionScope.user.firstName}"/>
</jsp:include>
<div class="centerdiv">
    <c:if test='${pageContext.request.getAttribute("error") != null}'>
        <label class="error">${pageContext.request.getAttribute("error")}</label>
    </c:if>

    <div class="buttonDivContainer">
        <div class="buttonDiv" id="addUser" onclick="window.location.href='${pageContext.request.contextPath}/admin/add_users'">
            <p>Add a user</p>
        </div>
        <div class="buttonDiv" id="displayUser" onclick="window.location.href='${pageContext.request.contextPath}/admin/display'">
            <p>Modify or delete a user</p>
        </div>
        <div class="buttonDiv" id="searchUser" onclick="window.location.href='${pageContext.request.contextPath}/'">
            <p>Search for a user</p>
        </div>

    </div>
</div>


</body>
</html>
