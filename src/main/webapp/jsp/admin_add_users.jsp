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
  <jsp:param name="title" value="Add a user"/>
</jsp:include>
<div class="centerdiv">
  <c:if test='${pageContext.request.getAttribute("error") != null}'>
    <label class="error">${pageContext.request.getAttribute("error")}</label>
  </c:if>

  <form name="registerForm" method="post" action="${pageContext.request.contextPath}/admin/register">
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
    <label for="userType">User type:</label>
    <select name="userType" id="userType">
      <option value="ADMIN">Admin</option>
      <option value="TEACHER">Teacher</option>
      <option value="STUDENT">Student</option>
    </select>
    <br>
    <br>
    <input type="submit" value="Submit"/>
  </form>





</div>
</div>


</body>
</html>
