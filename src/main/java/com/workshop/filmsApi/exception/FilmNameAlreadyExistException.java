package com.workshop.filmsApi.exception;

public class FilmNameAlreadyExistException extends EntityConflictException{
    public FilmNameAlreadyExistException(String message){
        super(message);
    }
}
