package com.workshop.filmsApi.exception;

public class DirectorNotFoundException extends EntityNotFoundException{
    public DirectorNotFoundException(String message){
        super(message);
    }
}
