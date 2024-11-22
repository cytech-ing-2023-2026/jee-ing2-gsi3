<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modify a user</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
</head>
<body class="main_body">
<jsp:include page="banner.jsp">
    <jsp:param name="title" value="Modify a user"/>
</jsp:include>
<div class="centerdiv">
    <c:if test='${pageContext.request.getAttribute("error") != null}'>
        <label class="error">${pageContext.request.getAttribute("error")}</label>
    </c:if>

    <form name="modifyForm" method="post" action="${pageContext.request.contextPath}/admin/modify">
        <input type="hidden" name="userId" value="${user.id}"/>

        <label for="firstName">First name : </label>
        <input class="inputarea" type="text" id="firstName" name="firstName" value="${user.firstName}"/> <br/>
        <br>
        <label for="lastName">Last name : </label>
        <input class="inputarea" type="text" id="lastName" name="lastName" value="${user.lastName}"/> <br/>
        <br>
        <label for="dob">Date of birth : </label>
        <input class="inputarea" type="date" id="dob" name="dob" value="${user.dob}"/> <br/>
        <br>
        <label for="email">Email : </label>
        <input class="inputarea" type="email" id="email" name="email" value="${user.email}"/> <br/>
        <br>
        <label for="password">Password : </label>
        <input class="inputarea" type="password" id="password" name="password" value="${user.password}"/> <br/>
        <br>
        <br>
        <input type="submit" value="Submit"/>
    </form>





</div>
</div>


</body>
</html>
