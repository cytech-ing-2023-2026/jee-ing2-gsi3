package fr.cyu.jee;

import fr.cyu.jee.middleware.AdminInterceptor;
import fr.cyu.jee.middleware.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //TODO Add other endpoints
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns(
                "/home"
        );

        //TODO Add admin endpoints
        //registry.addInterceptor(new AdminInterceptor());
    }
}
