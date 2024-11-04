<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
    <link rel="stylesheet" href="css/main.css" />
</head>
<body class="main_body">
<div class="topdiv">
    <p>Login</p>
</div>
<div class="centerdiv">

    <form name='loginForm' method='post' action='FormController'>
        <label for="username">Username : </label>
        <input class="inputarea" type='text' id='username'/> <br/>
        <br>
        <label for="password">Password : </label>
        <input class="inputarea" type='password' id='password'/> <br/>
        <br>
        <input type='submit' value='Submit' />
    </form>
    <br>
    <a>Forgotten password</a>
    <p>No account yet? Create one <a href="/register">here</a></p>


</div>


</body>
</html>