package com.workshop.filmsApi.exception;

public class GenreAlreadyExistException extends EntityConflictException{
    public GenreAlreadyExistException(String message){
        super(message);
    }
}
