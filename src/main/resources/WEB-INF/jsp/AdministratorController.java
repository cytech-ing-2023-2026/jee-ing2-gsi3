@WebServlet("/admin")
public class AdministratorController extends HttpServlet {
    // ... existing code ...

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("viewGrades".equals(action)) {
            // Existing view grades code
            String studentLastName = request.getParameter("lastName");
            Student student = findStudentByLastName(studentLastName);

            if (student != null) {
                request.setAttribute("student", student);
                request.getRequestDispatcher("views/grades.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Student not found.");
                request.getRequestDispatcher("views/admin.jsp").forward(request, response);
            }

        } else if ("viewDetails".equals(action)) {
            // New action to view student details
            String studentLastName = request.getParameter("lastName");
            Student student = findStudentByLastName(studentLastName);

            if (student != null) {
                request.setAttribute("student", student);
                request.getRequestDispatcher("views/studentDetails.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Student not found.");
                request.getRequestDispatcher("views/admin.jsp").forward(request, response);
            }

        } else {
            // Default action: list all students
            request.setAttribute("students", students);
            request.getRequestDispatcher("views/admin.jsp").forward(request, response);
        }
    }

    private Student findStudentByLastName(String lastName) {
        for (Student student : students) {
            if (student.getLastName().equalsIgnoreCase(lastName)) {
                return student;
            }
        }
        return null;
    }
}
