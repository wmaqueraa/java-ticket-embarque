package com.ticket.electronico.infrastructure.adapter.in.web;


import com.ticket.electronico.domain.exception.BusinessException;
import com.ticket.electronico.domain.exception.DomainException;
import com.ticket.electronico.domain.exception.ReservaNoEncontradaException;
import com.ticket.electronico.shared.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

/*
    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePetNotFoundException(PetNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                "PET_NOT_FOUND",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<ErrorResponse> handleAppointmentConflictException(AppointmentConflictException ex) {
        ErrorResponse error = new ErrorResponse(
                "APPOINTMENT_CONFLICT",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(InvalidAppointmentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAppointmentException(InvalidAppointmentException ex) {
        ErrorResponse error = new ErrorResponse(
                "INVALID_APPOINTMENT",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
*/

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException ex) {
        ErrorResponse error = new ErrorResponse(
                "DOMAIN_ERROR",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getErrorCode().name(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ErrorResponse> handleTechnicalException(TechnicalException ex) {
        ErrorResponse error = new ErrorResponse(
                "TECHNICAL_ERROR",
                "Error técnico interno",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse error = new ErrorResponse(
                "VALIDATION_ERROR",
                "Errores de validación en los datos de entrada",
                LocalDateTime.now(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {

        log.error("Error interno", ex);
        ErrorResponse error = new ErrorResponse(
                "INTERNAL_ERROR",
                "Error interno del servidor",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(ReservaNoEncontradaException.class)
    public ResponseEntity<Object> handleReservaNoEncontrada(ReservaNoEncontradaException ex) {

        ErrorResponse error = new ErrorResponse(
                "RESERVA_NOT_FOUND",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    public static class ErrorResponse {
        private String code;
        private String message;
        private LocalDateTime timestamp;
        private Map<String, String> details;

        public ErrorResponse(String code, String message, LocalDateTime timestamp) {
            this.code = code;
            this.message = message;
            this.timestamp = timestamp;
        }

        public ErrorResponse(String code, String message, LocalDateTime timestamp, Map<String, String> details) {
            this.code = code;
            this.message = message;
            this.timestamp = timestamp;
            this.details = details;
        }

        // Getters y Setters
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

        public Map<String, String> getDetails() { return details; }
        public void setDetails(Map<String, String> details) { this.details = details; }
    }
}