package com.accenture.controller.advice;

import com.accenture.exception.ClientException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ErreurResponse> gestionClientException(ClientException exception) {
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(), "Erreur functionnelle", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErreurResponse> entityNotFoundException(EntityNotFoundException ex) {
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(), "Mauvaise Requete", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErreurResponse> problemeValidation(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.joining(","));
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(),"Validation erreur", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
    }
}
