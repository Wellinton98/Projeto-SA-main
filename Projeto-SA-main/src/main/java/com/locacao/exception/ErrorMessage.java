package com.locacao.exception;

import java.time.LocalDateTime;

public class ErrorMessage {

        private LocalDateTime timestamp;
        private int status;
        private String message;

        public ErrorMessage(LocalDateTime timestamp, int status, String message) {
            this.timestamp = timestamp;
            this.status = status;
            this.message = message;
        
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
