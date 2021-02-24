package com.login.demo.config.handler;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * implements Spring Security’s AuthenticationEntryPoint interface
 * This class is used to return a 401 unauthorized error to clients that try to access a protected resource without proper authentication.
 *
 * @author Evgeniy Skiba
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    private final HandlerExceptionResolver resolver;

    private final ObjectMapper mapper;

    public CustomAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, ObjectMapper mapper) {
        this.resolver = resolver;
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse httpServletResponse, AuthenticationException ex) throws IOException {
        logger.error("User is unauthorised. Routing from the entry point");

        if (request.getAttribute("javax.servlet.error.exception") != null) {
            Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
            resolver.resolveException(request, httpServletResponse, null, (Exception) throwable);
        }
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    }

}