<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.cyu.jee.model.Course" %>
<%@ page import="java.util.Map, java.util.List, java.util.stream.Collectors" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin - All Courses</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/course/admin.css" rel="stylesheet">
</head>
<body>

<jsp:include page="banner.jsp">
    <jsp:param name="title" value="Admin - All Courses"/>
</jsp:include>

<%
    List<Course> courses = (List<Course>) request.getAttribute("courses");

    if (courses == null) {
        courses = new ArrayList<>();
    }

    // Grouper les cours par matière si la liste n'est pas vide
    Map<String, List<Course>> coursesBySubject = courses.isEmpty() ? Map.of() :
            courses.stream().collect(Collectors.groupingBy(course -> course.getSubject().getName()));

    // Formatter pour l'affichage des horaires
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
%>

<div class="course-list">
    <h1>All Courses</h1>

    <% if (coursesBySubject != null && !coursesBySubject.isEmpty()) { %>
    <!-- Parcourir les matières -->
    <% for (Map.Entry<String, List<Course>> entry : coursesBySubject.entrySet()) { %>
    <div class="subject-section">
        <h2 class="subject-title"><%= entry.getKey() %></h2> <!-- Nom de la matière -->

        <!-- Parcourir les cours de la matière -->
        <table class="course-table">
            <thead>
            <tr>
                <th>Course Date</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Professor</th>
                <th>Duration</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% for (Course course : entry.getValue()) { %>
            <tr>
                <td><%= course.getBeginDate().toLocalDate().format(dateFormatter) %></td>
                <td><%= course.getBeginDate().toLocalTime().format(timeFormatter) %></td>
                <td><%= course.getEndDate().toLocalTime().format(timeFormatter) %></td>
                <td>
                    <%= course.getTeacher().getFirstName() %> <%= course.getTeacher().getLastName().toUpperCase() %>
                </td>
                <td><%= course.getDuration().toHours() %>h <%= course.getDuration().toMinutesPart() %>min</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/course/delete" method="post" style="display: inline;">
                        <input type="hidden" name="id" value="<%= course.getId() %>">
                        <button type="submit" class="btn-delete">Delete</button>
                    </form>

                    <form action="${pageContext.request.contextPath}/admin/course/edit" method="get" style="display: inline;">
                        <input type="hidden" name="courseId" value="<%= course.getId() %>">
                        <button type="submit" class="btn-edit">Edit</button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <% } %>
    <% } else { %>
    <p>No courses available.</p>
    <% } %>
</div>
</body>
</html>

