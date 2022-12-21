package com.workshop.filmsApi.exception;


public class FilmNotFoundException extends EntityNotFoundException{

    public FilmNotFoundException(String filmError){
        super(filmError);
    }

}
