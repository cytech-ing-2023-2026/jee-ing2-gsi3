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
  <div class="topMenuContainer">
    <img src="${pageContext.request.contextPath}/pictures/HomeDark.png" alt="Home icon" class="icon" id="home" onclick="window.location.href='${pageContext.request.contextPath}/home'">
    <img src="${pageContext.request.contextPath}/pictures/LogoutDark.png" alt="Logout icon" class="icon" id="logout">
  </div>
</div>
<div class="centerdiv">
  <c:if test='${pageContext.request.getAttribute("error") != null}'>
    <label class="error">${pageContext.request.getAttribute("error")}</label>
  </c:if>




  </div>
</div>


</body>
</html>

