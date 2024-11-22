<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="fr.cyu.jee.model.Grade" %>
<%@ page import="java.util.List" %>
<%@ page import="fr.cyu.jee.model.User" %>
<%@ page import="fr.cyu.jee.model.UserType" %>
<%@ page import="fr.cyu.jee.model.Subject" %>
<!DOCTYPE html>
<html>
<head>
    <title>Teacher Grades</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
    <style>
        /* Alignement des champs du formulaire */
        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .form-group label {
            flex: 0 0 150px; /* Largeur fixe pour aligner les étiquettes 0 0 150 est le juste milieu ! */
            text-align: right;
            margin-right: 10px;
        }
        .form-group input {
            flex: 1;
        }
        .grades-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .grades-table th, .grades-table td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        .grades-table th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body class="main_body">
<jsp:include page="banner.jsp">
    <jsp:param name="title" value="Add a grade"/>
</jsp:include>
<div class="centerdiv">
    <div style="display: flex;">
        <!-- Liste des notes à droite -->
        <div style="flex: 2; padding: 10px;">
            <h2>Student Grades</h2>
            <table class="grades-table">
                <thead>
                <tr>
                    <th>Subject</th>
                    <th>Grade</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(Grade grade : (List<Grade>) pageContext.getRequest().getAttribute("grades")) {
                %>
                    <tr>
                        <td><%= grade.getSubject().getName() %></td>
                        <td><%= grade.getValue() %></td>
                        <td><%= grade.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE) %></td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>


</body>
</html>
