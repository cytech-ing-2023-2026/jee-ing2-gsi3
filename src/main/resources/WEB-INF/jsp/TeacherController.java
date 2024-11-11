package com.example.gradesystem.controller;

import com.example.gradesystem.model.Teacher;
import com.example.gradesystem.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher")
public class TeacherController extends HttpServlet {
    private Teacher teacher = new Teacher("Mrs. Dupont");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentLastName = request.getParameter("studentLastName");
        String studentFirstName = request.getParameter("studentFirstName");
        String subject = request.getParameter("subject");
        double value = Double.parseDouble(request.getParameter("value"));

        Student student = new Student(studentLastName, studentFirstName);
        teacher.addGrade(student, subject, value);

        request.setAttribute("message", "Grade added successfully");
        request.getRequestDispatcher("views/teacher_grades.jsp").forward(request, response);
    }
}
