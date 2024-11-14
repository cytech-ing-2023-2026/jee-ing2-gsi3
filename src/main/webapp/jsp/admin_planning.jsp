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
    <div class="buttonDiv" id="createCourse">
      <p>Create a course</p>
    </div>
    <div class="buttonDiv" id="modifyCourse">
      <p>Modify a course</p>
    </div>
    <div class="buttonDiv" id="assignCourse">
      <p>Assign a course to a user</p>
    </div>
    <div class="buttonDiv" id="displayCourse">
      <p>Display the list of courses</p>
    </div>
    <div class="buttonDiv" id="deleteCourse">
      <p>Delete a course</p>
    </div>


  </div>
</div>


</body>
</html>

