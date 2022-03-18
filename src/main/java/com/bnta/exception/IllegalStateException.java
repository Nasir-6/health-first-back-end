package com.bnta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class IllegalStateException extends RuntimeException {

        public IllegalStateException(String message){
            super(message);
        }
    }

