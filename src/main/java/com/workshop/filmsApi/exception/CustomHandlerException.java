package com.workshop.filmsApi.exception;

import com.workshop.filmsApi.response.BaseResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomHandlerException {
    @ExceptionHandler({FilmNotFoundException.class, DirectorNotFoundException.class, GenreNotFoundException.class})
    public final ResponseEntity handlerNotFoundException(EntityNotFoundException exception) {
        BaseResponseError error = BaseResponseError.builder()
                .httpCode(HttpStatus.NOT_FOUND.value())
                .message("NOT_FOUND")
                .details(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }

    @ExceptionHandler({FilmNameAlreadyExistException.class, GenreAlreadyExistException.class})
    public final ResponseEntity handlerConflictException(EntityConflictException exception) {
        BaseResponseError error = BaseResponseError.builder()
                .httpCode(HttpStatus.CONFLICT.value())
                .message("CONFLICT")
                .details(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(error);
    }
}
