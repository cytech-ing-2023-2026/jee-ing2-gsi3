<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgotten password</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
</head>
<body class="main_body">
<div class="topdiv">
    <p>Forgot your password?</p>
</div>
<div class="centerdiv">

    <form name='forgottenPasswordForm' method='post' action='${pageContext.request.contextPath}/forgotten_password'>
        <label for=email>Enter the email of the account : </label>
        <input class="inputarea" type='text' id='email'/> <br/>
        <br>
        <input type='submit' value='Submit' />
    </form>
    <br>

    <a href="${pageContext.request.contextPath}/login">Back to the login page</a>


</div>


</body>
</html>