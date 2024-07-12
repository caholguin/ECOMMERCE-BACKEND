package com.ecommerce.ecommerce.exception;

import com.ecommerce.ecommerce.dto.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(HttpServletRequest request, Exception exception) {

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error interno del servidor vuelva a intertarlo");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> handleObjectNotFoundException(HttpServletRequest request, ObjectNotFoundException exception) {
        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error interno del servidor vuelva a intentarlo");

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {

        ApiErrorDto apiError = new ApiErrorDto();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMethod(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error al guardar el registro");

        List<Map<String, String>> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of("field",error.getField(), "message",error.getDefaultMessage()))
                .collect(Collectors.toList());
        apiError.setValidationErrors(validationErrors);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
