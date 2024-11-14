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

  <div class="buttonDivContainer">
    <div class="buttonDiv" id="createCourse" onclick="window.location.href='${pageContext.request.contextPath}/'">
      <p>Create a course</p>
    </div>
    <div class="buttonDiv" id="modifyCourse" onclick="window.location.href='${pageContext.request.contextPath}/'">
      <p>Modify a course</p>
    </div>
    <div class="buttonDiv" id="assignCourse" onclick="window.location.href='${pageContext.request.contextPath}/'">
      <p>Assign a course to a user</p>
    </div>
    <div class="buttonDiv" id="displayCourse" onclick="window.location.href='${pageContext.request.contextPath}/'">
      <p>Display the list of courses</p>
    </div>
    <div class="buttonDiv" id="deleteCourse" onclick="window.location.href='${pageContext.request.contextPath}/'">
      <p>Delete a course</p>
    </div>


  </div>
</div>


</body>
</html>

