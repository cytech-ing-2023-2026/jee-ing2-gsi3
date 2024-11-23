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
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns(
                "/home",
                "/course",
                "/grades",
                "/grades/**"
        );

        registry.addInterceptor(new AdminInterceptor()).addPathPatterns(
                "/admin",
                "/admin/**"
        );
    }
}
