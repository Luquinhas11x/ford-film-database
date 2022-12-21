package com.workshop.filmsApi.mapper;

import com.workshop.filmsApi.dto.*;
import com.workshop.filmsApi.entity.FilmEntity;
import com.workshop.filmsApi.request.FilmRequest;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilmMapper {
    public FilmMapper(){}

    public static FilmEntity toFilm(FilmRequest request){
        return FilmEntity.builder()
                .filmName(request.getFilmName())
                .bannerURL(request.getBannerURL())
                .releaseDate(request.getReleaseDate())
                .description(request.getDescription())
                .rating(request.getRating())
                .status(request.getStatus())
                .genres(new ArrayList<>())
                .build();
    }

    public static FilmDto toDTOFilm(FilmEntity film){
        return FilmDto.builder()
                .id(film.getId())
                .filmName(film.getFilmName())
                .bannerURL(film.getBannerURL())
                .releaseDate(film.getReleaseDate())
                .description(film.getDescription())
                .rating(film.getRating())
                .status(film.getStatus())
                .build();
    }

    public static FilmDirectorDto toFilmDirectorDto(FilmEntity film){
        return FilmDirectorDto.builder()
                .id(film.getId())
                .filmName(film.getFilmName())
                .bannerURL(film.getBannerURL())
                .releaseDate(film.getReleaseDate())
                .description(film.getDescription())
                .rating(film.getRating())
                .status(film.getStatus())
                .director(DirectorMapper.toDTODirector(film.getDirector()))
                .build();
    }

    public static FilmGenreDirectorDto toFilmDirectorGenreDto(FilmEntity film){
        return FilmGenreDirectorDto.builder()
                .id(film.getId())
                .filmName(film.getFilmName())
                .bannerURL(film.getBannerURL())
                .releaseDate(film.getReleaseDate())
                .description(film.getDescription())
                .rating(film.getRating())
                .status(film.getStatus())
                .director(DirectorMapper.toDTODirector(film.getDirector()))
                .genres(film.getGenres().stream().map(GenreMapper::toGenreDto).collect(Collectors.toList()))
                .build();
    }

    public static FilmGenreDto toFilmGenreDto(FilmEntity film){
        return FilmGenreDto.builder()
                .id(film.getId())
                .filmName(film.getFilmName())
                .bannerURL(film.getBannerURL())
                .releaseDate(film.getReleaseDate())
                .description(film.getDescription())
                .rating(film.getRating())
                .status(film.getStatus())
                .genres(film.getGenres().stream().map(GenreMapper::toGenreDto).collect(Collectors.toList()))
                .build();
    }
}
