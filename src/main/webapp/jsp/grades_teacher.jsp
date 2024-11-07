<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
</head>
<body class="main_body">
<div class="topdiv">
    <p>Add a grade</p>
    <div class="topMenuContainer">
        <img src="${pageContext.request.contextPath}/pictures/HomeDark.png" alt="Home icon" class="icon" id="home">
        <img src="${pageContext.request.contextPath}/pictures/LogoutDark.png" alt="Logout icon" class="icon" id="logout">
    </div>
</div>
<div class="centerdiv">
    <c:if test='${pageContext.request.getAttribute("error") != null}'>
        <label class="error">${pageContext.request.getAttribute("error")}</label>
    </c:if>

    <form name="addGradesForm" method="post" action="${pageContext.request.contextPath}/login">
        <label for="studentFirstName">First name : </label>
        <input class="inputarea" type="text" id="studentFirstName" name="studentFirstName"/> <br/>
        <br>
        <label for="studentLastName">Last name : </label>
        <input class="inputarea" type="text" id="studentLastName" name="studentLastName"/> <br/>
        <br>
        <label for="grade">Grade : </label>
        <input class="inputarea" type="number" step="0.01" id="grade" name="grade"/> <br/>
        <br>
        <input type="submit" value="Submit" />
    </form>

</div>


</body>
</html>
