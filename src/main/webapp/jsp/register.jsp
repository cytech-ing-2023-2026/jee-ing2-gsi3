<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create acco</title>
    <link rel="stylesheet" href="css/main.css" />
</head>
<body class="main_body">
<div class="topdiv">
    <p>Create an account</p>
</div>
<div class="centerdiv">

    <form name='loginForm' method='post' action='FormController'>
        <label for="firstName">First name : </label>
        <input class="inputarea" type='text' id='firstName'/> <br/>
        <br>
        <label for="lastName">Last name : </label>
        <input class="inputarea" type='text' id='lastName'/> <br/>
        <br>
        <label for="dob">Date of birth : </label>
        <input class="inputarea" type='date' id='dob'/> <br/>
        <br>
        <label for="email">Email : </label>
        <input class="inputarea" type='text' id='email'/> <br/>
        <br>
        <label for="password">Password : </label>
        <input class="inputarea" type='password' id='password'/> <br/>
        <br>
        <input type='submit' value='Submit' />
    </form>
    <br>
    <a>Forgotten password</a>
    <p>No account yet? Create one <a>here</a></p>


</div>


</body>
</html>