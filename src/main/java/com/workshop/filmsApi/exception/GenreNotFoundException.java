package com.workshop.filmsApi.exception;

public class GenreNotFoundException extends EntityNotFoundException{
    public GenreNotFoundException(String message){
        super(message);
    }
}
