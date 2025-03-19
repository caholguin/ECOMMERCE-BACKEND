package com.ecommerce.ecommerce.config.security.handler;

import com.ecommerce.ecommerce.dto.ApiErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException{

        int httpStatus = HttpStatus.FORBIDDEN.value();

        ApiErrorDTO apiError = new ApiErrorDTO();
        apiError.setBackendMessage(accessDeniedException.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setHttpMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Acceso denegado. No tienes los permisos necesarios para acceder a este recurso.");
        apiError.setHttpCode(httpStatus);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(httpStatus);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String apiErrorAsJson = objectMapper.writeValueAsString(apiError);

        response.getWriter().write(apiErrorAsJson);
    }
}