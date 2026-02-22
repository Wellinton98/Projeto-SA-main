package com.locacao.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlugueisException.imovelNaoEncontrado.class)
    public ResponseEntity<ErrorMessage> handleImovelNaoEncontrado(AlugueisException.imovelNaoEncontrado ex) {

        ErrorMessage error = new ErrorMessage(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    static class ErrorMessage {
        private LocalDateTime timestamp;
        private int status;
        private String message;

        public ErrorMessage(LocalDateTime timestamp, int status, String message) {
            this.timestamp = timestamp;
            this.status = status;
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
    }

