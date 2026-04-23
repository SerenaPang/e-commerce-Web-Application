package com.cakefactory.auth;

import com.cakefactory.auth.CsrfTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final CsrfTokenInterceptor csrfTokenInterceptor;

    public WebMvcConfig(CsrfTokenInterceptor csrfTokenInterceptor) {
        this.csrfTokenInterceptor = csrfTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(csrfTokenInterceptor);
    }
}