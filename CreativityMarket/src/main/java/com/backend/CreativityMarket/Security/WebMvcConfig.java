package com.backend.CreativityMarket.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AdminInterceptor adminInterceptor;
    private final UserStatusInterceptor userStatusInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // =========================
        // USER STATUS (GLOBAL RULES)
        // =========================
        registry.addInterceptor(userStatusInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/signin",
                        "/signup",
                        "/users/logout",
                        "/error",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/models/**"
                );

        // =========================
        // ADMIN ACCESS ONLY
        // =========================
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**", "/api/admin/**");
    }
}