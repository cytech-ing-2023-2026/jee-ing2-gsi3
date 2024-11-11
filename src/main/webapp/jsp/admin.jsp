<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Management - Administrator</title>
</head>
<body>
<h1>List of Students</h1>
<table border="1">
    <tr>
        <th>Last Name</th>
        <th>First Name</th>
        <th>Actions</th>
    </tr>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        for (Student student : students) {
    %>
    <tr>
        <td><%= student.getLastName() %></td>
        <td><%= student.getFirstName() %></td>
        <td>
            <a href="${pageContext.request.contextPath}/admin?action=viewGrades&lastName=<%= student.getLastName() %>">View Grades</a> |
            <a href="${pageContext.request.contextPath}/admin?action=viewDetails&lastName=<%= student.getLastName() %>">Details</a>

        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
