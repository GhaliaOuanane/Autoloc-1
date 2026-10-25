package tn.esprit.autoloc.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("statut", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("horodatage", LocalDateTime.now());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(RuntimeException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("introuvable")) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("statut", HttpStatus.NOT_FOUND.value());
            errorResponse.put("message", ex.getMessage());
            errorResponse.put("horodatage", LocalDateTime.now());
            
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("statut", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("horodatage", LocalDateTime.now());
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
