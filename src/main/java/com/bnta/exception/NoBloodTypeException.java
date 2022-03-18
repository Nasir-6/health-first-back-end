package com.bnta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoBloodTypeException extends NullPointerException {

    public NoBloodTypeException(String message){
        super(message);
    }
}
