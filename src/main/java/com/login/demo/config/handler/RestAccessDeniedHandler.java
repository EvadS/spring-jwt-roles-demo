package com.login.demo.config.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.demo.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * handle an AccessDeniedException.
 * The AccessDeniedHandler only applies to authenticated users.
 * * @author Evgeniy Skiba
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    public RestAccessDeniedHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(httpServletResponse.getWriter(),
                new ErrorResponse(HttpStatus.UNAUTHORIZED, "User doesn't have permission to this resource. Access Denied"));
    }
}
