package com.accenture.controller.advice;

import com.accenture.exception.AdminException;
import com.accenture.exception.ApplicatifException;
import com.accenture.exception.ClientException;
import com.accenture.exception.VehiculeException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(), "Erreur fonctionnelle", exception.getMessage());
        log.error("Erreur functionnelle");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
    }
    @ExceptionHandler(VehiculeException.class)
    public ResponseEntity<ErreurResponse> gestionVehiculeException(VehiculeException exception) {
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(), "Erreur vehicule", exception.getMessage());
        log.error("Erreur vehicule");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErreurResponse> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(), "Problème sur le format de l'entrée", ex.getMessage());
        log.error("Problème sur le format de l'entrée");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(er);
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
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErreurResponse> gestionMailPareille(DataIntegrityViolationException exception) {
        ErreurResponse er = new ErreurResponse(LocalDateTime.now(), "Mail déja existant", exception.getMessage());
        log.error("Mail déja existant");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
    }
}
