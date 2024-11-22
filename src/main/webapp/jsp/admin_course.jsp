<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="fr.cyu.jee.model.Course" %>
<%@ page import="java.util.Map, java.util.List, java.util.stream.Collectors" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.cyu.jee.model.Subject" %>
<%@ page import="fr.cyu.jee.model.Teacher" %>
<%@ page import="fr.cyu.jee.model.Student" %>
<%@ page import="fr.cyu.jee.CustomDateTimeFormatter" %>

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

<div style="display: flex;">
    <!-- Formulaire à gauche -->
    <div style="flex: 1; padding: 10px; border-right: 1px solid gray;">
        <form name="add_courses" method="post" action="${pageContext.request.contextPath}/admin/course/add">
            <div class="form-group">
                <label for="begin_date">Begin date:</label>
                <input class="inputarea" type="datetime-local" id="begin_date" name="beginDate" required />
            </div>
            <div class="form-group">
                <label for="duration">Duration:</label>
                <input class="inputarea" type="time" id="duration" name="duration" min="00:00" value="--:--" required>
            </div>
            <div class="form-group">
                <label for="subject_add">Subject:</label>
                <select class="inputarea" id="subject_add" name="subject" required>
                    <%
                        for(Subject subject : (List<Subject>) pageContext.getRequest().getAttribute("subjects")) {
                    %>
                    <option value="<%= subject.getId() %>"><%= subject.getName() %></option>
                    <% } %>
                </select>
            </div>
            <div class="form-group">
                <label for="teacher_add">Teacher:</label>
                <select class="inputarea" id="teacher_add" name="teacher" required>
                    <%
                        for(Teacher teacher : (List<Teacher>) pageContext.getRequest().getAttribute("teachers")) {
                    %>
                    <option value="<%= teacher.getId() %>"><%= teacher.getEmail() %></option>
                    <% } %>
                </select>
            </div>
            <div class="form-group">
                <label for="students_add">Students:</label>
                <div class="inputarea" id="students_add">
                    <%
                        for(Student student : (List<Student>) pageContext.getRequest().getAttribute("students")) {
                    %>
                    <label><%= student.getEmail() %></label><input type="checkbox" name="students" value="<%= student.getId() %>">
                    <% } %>
                </div>
            </div>
            <input type="submit" value="Submit" />
        </form>
    </div>

    <!-- Liste des notes à droite -->
    <div style="flex: 2; padding: 10px;">
        <h2>All courses</h2>
        <table class="courses-table">
            <thead>
            <tr>
                <th>Subject</th>
                <th>Teacher</th>
                <th>Begin date</th>
                <th>Duration</th>
                <th>Students</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                for(Course course : (List<Course>) pageContext.getRequest().getAttribute("courses")) {
            %>
            <tr>
                <form id="delete_form" action="${pageContext.request.contextPath}/admin/course/delete" method="post"></form>
                <form id="update_form" action="${pageContext.request.contextPath}/admin/course/update" method="post"></form>

                <input type="hidden" name="id" value="<%= course.getId() %>" form="delete_form">
                <input type="hidden" name="course" value="<%= course.getId() %>" form="update_form">

                <td>
                    <select class="inputarea" id="subject_update" name="subject" form="update_form" required>
                        <%
                            for(Subject subject : (List<Subject>) pageContext.getRequest().getAttribute("subjects")) {
                                if(subject.getId() == course.getSubject().getId()) {
                        %>
                        <option value="<%= subject.getId() %>" selected><%= subject.getName() %></option>
                        <% } else { %>
                        <option value="<%= subject.getId() %>"><%= subject.getName() %></option>
                        <% }} %>
                    </select>
                </td>
                <td>
                    <select class="inputarea" id="teacher_update" name="teacher" form="update_form" required>
                        <%
                            for(Teacher teacher : (List<Teacher>) pageContext.getRequest().getAttribute("teachers")) {
                                if(teacher.getId() == course.getTeacher().getId()) {
                        %>
                        <option value="<%= teacher.getId() %>" selected><%= teacher.getEmail() %></option>
                        <% } else { %>
                        <option value="<%= teacher.getId() %>"><%= teacher.getEmail() %></option>
                        <% }} %>
                    </select>
                </td>
                <td><input class="inputarea" type="datetime-local" id="begin_date_update" name="beginDate" form="update_form" value="<%= course.getBeginDate().format(CustomDateTimeFormatter.DATE_TIME) %>" required></td>
                <td><input class="inputarea" type="time" id="duration_update" name="duration" form="update_form" min="00:00" value="<%= CustomDateTimeFormatter.formatDuration(course.getDuration()) %>" required></td>
                <td class="students_list">
                    <%
                        for(Student student : (List<Student>) pageContext.getRequest().getAttribute("students")) {
                    %>
                    <label><%= student.getEmail() %></label>
                    <%
                        if(course.getStudents().contains(student)) {
                    %>
                    <input class="inputarea" type="checkbox" name="students" value="<%= student.getId() %>" form="update_form" checked>
                    <% } else { %>
                    <input class="inputarea" type="checkbox" name="students" value="<%= student.getId() %>" form="update_form">
                    <% }} %>
                </td>
                <td>
                    <input type="submit" value="Delete" form="delete_form">
                    <input type="submit" value="Update" form="update_form">
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
