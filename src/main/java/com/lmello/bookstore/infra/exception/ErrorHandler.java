package com.lmello.bookstore.infra.exception;

import com.lmello.bookstore.dto.exception.ExceptionResponseDTO;
import com.lmello.bookstore.exception.DuplicateEntryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<?> handleErrorDuplicateEntry(DuplicateEntryException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponseDTO(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(
                errors
                        .stream()
                        .map(DataValidationErrors::new)
                        .toList()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.internalServerError().body(new ExceptionResponseDTO(ex.getMessage()));
    }

    private record DataValidationErrors(String field, String message) {
        public DataValidationErrors(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
