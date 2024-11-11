<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.gradesystem.model.Student" %>
<html>
<head>
    <title>Student Details</title>
</head>
<body>
<%
    Student student = (Student) request.getAttribute("student");
    if (student != null) {
%>
<h1>Details for <%= student.getFirstName() %> <%= student.getLastName() %></h1>
<p><strong>First Name:</strong> <%= student.getFirstName() %></p>
<p><strong>Last Name:</strong> <%= student.getLastName() %></p>
<!-- Placeholder for additional details -->
<p>Additional details or actions can go here.</p>
<%
} else {
%>
<p>Student not found.</p>
<%
    }
%>
</body>
</html>
