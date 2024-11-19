<%@ page contentType="text/html;charset=UTF-8" %>
<div class="topdiv">
    <p>${param.title}</p> <!-- Can add the username here -->
    <div class="topMenuContainer">

        <%
            if(pageContext.getSession().getAttribute("user") != null){
        %>

            <img src="${pageContext.request.contextPath}/pictures/HomeDark.png" alt="Home icon" class="icon" id="home" onclick="window.location.href='${pageContext.request.contextPath}/home'">
            <img src="${pageContext.request.contextPath}/pictures/LogoutDark.png" alt="Logout icon" class="icon" id="logout">

        <%
            }
        %>


    </div>
</div>
