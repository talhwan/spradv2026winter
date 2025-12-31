package com.thc.sprbasic2025fall.Interceptor;

import com.thc.sprbasic2025fall.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Enumeration;

public class DefaultInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    final TokenFactory tokenFactory;
    public DefaultInterceptor(TokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    //컨트롤러 진입 전에 호출되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle / request [{}]", request);
        String accessToken = request.getHeader("Authorization");
        System.out.println("accessToken = " + accessToken);
        Long userId = null;
        if(accessToken != null && accessToken.startsWith("Bearer ")) {
            accessToken = accessToken.substring(7);
            userId = tokenFactory.validateAccessToken(accessToken);
        }
        request.setAttribute("userId", userId);

        return true;
    }

    //컨트롤러 실행 후에 호출되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle / request [{}]", request);

        Collection<String> resHeaderNames = response.getHeaderNames();
        //logger.info("[2HEADER RES] " + resHeaderNames);
        for (String each : resHeaderNames) {
            String resHeaderValue = response.getHeader(each);
            //logger.info("[HEADER RES] " + each + " : " + resHeaderValue);
        }
    }

    //모든것을 마친 후 실행되는 메서드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion / request [{}]", request);

        Collection<String> resHeaderNames = response.getHeaderNames();
        //logger.info("[3HEADER RES] " + resHeaderNames);
        for (String each : resHeaderNames) {
            String resHeaderValue = response.getHeader(each);
            //logger.info("[HEADER RES] " + each + " : " + resHeaderValue);
        }
    }

}
