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
    <jsp:param name="title" value="Login"/>
</jsp:include>
<div class="centerdiv">
    <c:if test='${pageContext.request.getAttribute("error") != null}'>
        <label class="error">${pageContext.request.getAttribute("error")}</label>
    </c:if>
    <form name="loginForm" method="post" action="${pageContext.request.contextPath}/login">
        <input type="hidden" name="redirect" value="${pageContext.request.getParameter("redirect")}">
        <label for="email">Username : </label>
        <input class="inputarea" type="text" id="email" name="email"/> <br/>
        <br>
        <label for="password">Password : </label>
        <input class="inputarea" type="password" id="password" name="password"/> <br/>
        <br>
        <input type="submit" value="Submit" />
    </form>
    <br>

</div>


</body>
</html>