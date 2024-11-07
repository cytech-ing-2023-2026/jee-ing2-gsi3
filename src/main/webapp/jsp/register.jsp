<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create account</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body class="main_body">
<div class="topdiv">
    <p>Create an account</p>
</div>
<div class="centerdiv">
    <c:if test='${pageContext.request.getAttribute("error") != null}'>
        <label class="error">${pageContext.request.getAttribute("error")}</label>
    </c:if>
    <form name="registerForm" method="post" action="${pageContext.request.contextPath}/register">
        <label for="firstName">First name : </label>
        <input class="inputarea" type="text" id="firstName" name="firstName"/> <br/>
        <br>
        <label for="lastName">Last name : </label>
        <input class="inputarea" type="text" id="lastName" name="lastName"/> <br/>
        <br>
        <label for="dob">Date of birth : </label>
        <input class="inputarea" type="date" id="dob" name="dob"/> <br/>
        <br>
        <label for="email">Email : </label>
        <input class="inputarea" type="email" id="email" name="email"/> <br/>
        <br>
        <label for="password">Password : </label>
        <input class="inputarea" type="password" id="password" name="password"/> <br/>
        <br>
        <input type="submit" value="Submit"/>
    </form>
    <br>
    <p>Have an account already? Login <a href="${pageContext.request.contextPath}/login">here</a></p>
</div>


</body>
</html>