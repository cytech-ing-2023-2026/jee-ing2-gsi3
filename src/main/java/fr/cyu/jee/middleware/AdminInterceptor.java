package fr.cyu.jee.middleware;

import fr.cyu.jee.model.User;
import fr.cyu.jee.model.UserType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            response.sendRedirect("/login");
            return false;
        } else if(user.getUserType() == UserType.ADMIN) return true;
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}
