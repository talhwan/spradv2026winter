package com.thc.sprbasic2025fall.config;

import com.thc.sprbasic2025fall.Interceptor.DefaultInterceptor;
import com.thc.sprbasic2025fall.util.TokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final TokenFactory tokenFactory;

    //인터셉터 설정을 위함
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultInterceptor(tokenFactory))
                .addPathPatterns("/api/**") //인터셉터가 실행되야 하는 url 패턴 설정
                .excludePathPatterns("/resources/**", "/api/auth", "/api/user/signup", "/api/user/login"); //인터셉터가 실행되지 않아야 하는 url 패턴
    }
}