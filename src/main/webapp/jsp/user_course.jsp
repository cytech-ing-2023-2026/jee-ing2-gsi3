<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page
        import="fr.cyu.jee.model.Course, fr.cyu.jee.model.Subject, fr.cyu.jee.model.Teacher, fr.cyu.jee.model.Student" %>
<%@ page import="java.util.ArrayList, java.util.List, java.util.HashSet" %>
<%@ page
        import="java.time.LocalDateTime, java.time.LocalDate, java.time.Duration, java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Set" %>

<!DOCTYPE html>
<html>
<head>
    <title>Emploi du Temps</title>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    <!-- Lien vers le fichier CSS principal -->
    <link href="${pageContext.request.contextPath}/css/course/user.css" rel="stylesheet">
    <!-- Lien vers le fichier CSS spécifique aux cours -->
</head>
<body>

<jsp:include page="banner.jsp">
    <jsp:param name="title" value="Emploi du temps"/>
</jsp:include>

<%
    // Récupération et formatage de la date sélectionnée
    String dateParam = request.getParameter("date"); // Récupère la date sélectionnée dans les paramètres de requête
    LocalDate selectedDate = (dateParam != null) ? LocalDate.parse(dateParam) : LocalDate.now(); // Utilise la date du jour si aucun paramètre n'est fourni

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // Format pour afficher la date
    String formattedDate = selectedDate.format(formatter); // Formatage de la date sélectionnée

    // Initialisation de la liste des cours
    List<Course> courses = new ArrayList<>(); // Liste de cours pour l'affichage
    Teacher teacher1 = new Teacher("dupont@example.com", "password123", "Jean", "Dupont", LocalDate.of(1980, 5, 20)); // Exemple d'enseignant
    Subject math = new Subject("Mathématiques"); // Exemple de matière
    Set<Student> students = new HashSet<>(); // Ensemble d'étudiants (vide pour cet exemple)

    // Ajout des cours (exemple de test)
    if (selectedDate.equals(LocalDate.of(2024, 11, 15))) { // Si la date sélectionnée est le 15 novembre 2024
        courses.add(new Course(LocalDateTime.of(2024, 11, 15, 8, 0), Duration.ofMinutes(60), math, teacher1, students)); // Ajoute un cours de mathématiques
        courses.add(new Course(LocalDateTime.of(2024, 11, 15, 9, 15), Duration.ofMinutes(90), new Subject("Physique"), teacher1, students)); // Ajoute un cours de physique
    } else if (selectedDate.equals(LocalDate.of(2024, 11, 16))) { // Si la date sélectionnée est le 16 novembre 2024
        courses.add(new Course(LocalDateTime.of(2024, 11, 16, 10, 0), Duration.ofMinutes(60), new Subject("Chimie"), teacher1, students)); // Ajoute un cours de chimie
        courses.add(new Course(LocalDateTime.of(2024, 11, 16, 11, 30), Duration.ofMinutes(60), new Subject("Histoire"), teacher1, students)); // Ajoute un cours d'histoire
    }
%>

<div class="timetable">
    <!-- En-têtes des jours de la semaine -->
    <div></div> <!-- Cellule vide pour l'angle en haut à gauche -->
    <div class="day-header">Lundi</div>
    <div class="day-header">Mardi</div>
    <div class="day-header">Mercredi</div>
    <div class="day-header">Jeudi</div>
    <div class="day-header">Vendredi</div>
    <div class="day-header">Samedi</div>
    <div class="day-header">Dimanche</div>

    <!-- Créneaux horaires statiques dans la première colonne et cellules vides pour chaque jour -->
    <%
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), java.time.LocalTime.of(8, 0)); // Heure de début à 8h00
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");
        for (int i = 0; i < (20 - 8) * 4; i++) { // Boucle pour créer 48 créneaux de 15 minutes (de 8h00 à 20h00)
            if (i % 2 == 0) {
                String timeLabel = startTime.plusMinutes(i * 15).format(hourFormatter); // Formatage de chaque créneau horaire
                String timeStyle = "grid-row-start: " + (i + 2) + "; grid-row-end: " + (i + 4) + ";";
    %>
    <div class="time-slot" style="<%= timeStyle%>"><%= timeLabel %>
    </div> <!-- Affichage du créneau horaire dans la première colonne -->
    <!-- Cellules vides avec bordure pour chaque jour de la semaine -->
    <%
        }
        String cellRow = "quarter-" + i % 4;
        for (int j = 0; j < 7; j++) {
            String cellStyle = "grid-row: " + (i + 2) + ";grid-column: " + (j + 2) + ";";
    %>
    <div class="empty-cell <%= cellRow %>" style="<%= cellStyle %>"></div>
    <% } %>
    <% } %>

    <!-- Affichage des cours -->
    <% for (Course course : courses) {
        int dayColumn = course.getBeginDate().getDayOfWeek().getValue(); // Colonne correspondant au jour de la semaine (Lundi = 1, Mardi = 2, etc.)
        int beginHour = (course.getBeginDate().getHour() - 8) * 4 + course.getBeginDate().getMinute() / 15; // Calcul de la position de début du cours dans la grille
        int durationSlots = (int) course.getDuration().toMinutes() / 15; // Calcul de la durée du cours en créneaux de 15 minutes
        String cellStyle = "grid-column: " + (dayColumn + 1) + "; grid-row: " + (beginHour + 2) + " / span " + durationSlots + ";"; // Style CSS dynamique pour positionner le cours dans la grille
    %>
    <div class="course" style="<%= cellStyle %>">
        <label class="subject"><%= course.getSubject().getName() %></label>
        <label class="hour"><%= course.getBeginDate().format(hourFormatter) %> - <%= course.getEndDate().format(hourFormatter) %></label>
        <label class="teacher"><%= course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName().toUpperCase() %></label>
    </div> <!-- Affichage du cours avec son style positionné dans la grille -->
    <% } %>
</div>

</body>
</html>
