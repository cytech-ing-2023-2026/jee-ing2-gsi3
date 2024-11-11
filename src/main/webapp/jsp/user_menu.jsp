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
    <p>Welcome, </p> <!-- Can add the username here -->
</div>
<div class="centerdiv">
    <c:if test='${pageContext.request.getAttribute("error") != null}'>
        <label class="error">${pageContext.request.getAttribute("error")}</label>
    </c:if>

    <div class="buttonDivContainer">
        <div class="buttonDiv" id="planning">
            <p>Planning</p>
        </div>
        <div class="buttonDiv" id="gradesStudent">
            <p>Grades</p>
        </div>
    </div>
</div>


</body>
</html>
