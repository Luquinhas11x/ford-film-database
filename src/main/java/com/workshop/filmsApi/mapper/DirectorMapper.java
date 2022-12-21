package com.workshop.filmsApi.mapper;

import com.workshop.filmsApi.dto.DirectorDto;
import com.workshop.filmsApi.dto.DirectorFilmDto;
import com.workshop.filmsApi.entity.DirectorEntity;
import com.workshop.filmsApi.request.DirectorRequest;

import java.util.stream.Collectors;

public class DirectorMapper {
    public DirectorMapper(){}

    public static DirectorEntity toDirector(DirectorRequest request){
        return DirectorEntity.builder()
                .directorName(request.getDirectorName())
                .build();
    }

    public static DirectorDto toDTODirector(DirectorEntity director){
        return DirectorDto.builder()
                .id(director.getId())
                .directorName(director.getDirectorName())
                .build();
    }

    public static DirectorFilmDto toDirectorFilmDto(DirectorEntity director){
        return DirectorFilmDto.builder()
                .id(director.getId())
                .directorName(director.getDirectorName())
                .films(director.getFilms().stream().map(FilmMapper::toFilmGenreDto).collect(Collectors.toList()))
                .build();
    }
}
