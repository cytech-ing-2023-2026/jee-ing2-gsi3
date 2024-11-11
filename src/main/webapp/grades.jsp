<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.gradesystem.model.Student" %>
<%@ page import="com.example.gradesystem.model.Grade" %>
<html>
<head>
    <title>Student Grades</title>
</head>
<body>
<%
    Student student = (Student) request.getAttribute("student");
    if (student != null) {
%>
<h1>Grades for <%= student.getFirstName() %> <%= student.getLastName() %></h1>
<table border="1">
    <tr>
        <th>Subject</th>
        <th>Grade</th>
        <th>Assigned by</th>
    </tr>
    <%
        for (Grade grade : student.getGrades()) {
    %>
    <tr>
        <td><%= grade.getSubject() %></td>
        <td><%= grade.getValue() %></td>
        <td><%= grade.getTeacher() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>Student not found or no grades available.</p>
<%
    }
%>
</body>
</html>
