package com.workshop.filmsApi.exception;

public class FilmNameAlreadyExistException extends EntityNotFoundException{
    public FilmNameAlreadyExistException(String message){
        super(message);
    }
}
