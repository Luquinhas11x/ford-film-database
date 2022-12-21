package com.workshop.filmsApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityConflictException extends RuntimeException{
    public EntityConflictException(String message){
        super(message);
    }
}
