package com.accenture.controller.advice;

import com.accenture.exception.ApplicatifException;
import com.accenture.exception.ClientException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ApplicationControllerAdvice {
    @ExceptionHandler(ApplicatifException.class)
    public ResponseEntity<ErreurResponse> gestionApplicatifException(ApplicatifException exception) {
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(), "Erreur functionnelle", exception.getMessage());
        log.error("Erreur functionnelle");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErreurResponse> entityNotFoundException(EntityNotFoundException ex) {
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(), "Mauvaise Requête", ex.getMessage());
        log.error("Mauvaise requête");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErreurResponse> problemeValidation(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.joining(","));
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(),"Validation erreur", message);
        log.error("Erreur de validation");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
    }
}
