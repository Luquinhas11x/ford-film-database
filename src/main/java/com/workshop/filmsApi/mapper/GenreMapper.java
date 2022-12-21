package com.workshop.filmsApi.mapper;

import com.workshop.filmsApi.dto.GenreDto;
import com.workshop.filmsApi.dto.GenreFilmDto;
import com.workshop.filmsApi.entity.GenreEntity;
import com.workshop.filmsApi.request.GenreRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GenreMapper {
    public GenreMapper(){}

    public static GenreEntity toGenre(GenreRequest request){
        return GenreEntity.builder()
                .genreName(request.getGenreName())
                .films(new ArrayList<>())
                .build();
    }

    public static GenreDto toGenreDto(GenreEntity genre){
        return GenreDto.builder()
                .id(genre.getId())
                .genreName(genre.getGenreName())
                .build();
    }

    public static GenreFilmDto toGenreFilmDto(GenreEntity genre){
        return GenreFilmDto.builder()
                .id(genre.getId())
                .genreName(genre.getGenreName())
                .films(genre.getFilms().stream().map(FilmMapper::toFilmDirectorDto).collect(Collectors.toList()))
                .build();
    }
}
