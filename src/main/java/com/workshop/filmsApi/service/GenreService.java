package com.workshop.filmsApi.service;

import com.workshop.filmsApi.dto.GenreFilmDto;
import com.workshop.filmsApi.entity.FilmEntity;
import com.workshop.filmsApi.entity.GenreEntity;
import com.workshop.filmsApi.exception.FilmNotFoundException;
import com.workshop.filmsApi.exception.GenreAlreadyExistException;
import com.workshop.filmsApi.exception.GenreNotFoundException;
import com.workshop.filmsApi.mapper.GenreMapper;
import com.workshop.filmsApi.repository.FilmRepository;
import com.workshop.filmsApi.repository.GenreRepository;
import com.workshop.filmsApi.request.GenreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    private final FilmRepository filmRepository;

    public List<GenreFilmDto> getAll() {
        return genreRepository.findAll().stream()
                .map(GenreMapper::toGenreFilmDto)
                .collect(Collectors.toList());
    }

//    public GenreEntity getById(Long id) throws Exception{
//        Optional<GenreEntity> genre = genreRepository.findById(id);
//
//        if (genre.isEmpty()) {
//            throw new Exception();
//        }
//        return genre.get();
//    }

    public GenreEntity getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException("Genre with id " + id + " not found"));
    }

    public GenreEntity addGenre(GenreRequest request) {
        Optional<GenreEntity> genreName = genreRepository.findByGenreName(request.getGenreName());

        if(genreName.isPresent()){
            throw new GenreAlreadyExistException("Genre " + request.getGenreName() + " already exists");
        }

        return genreRepository.save(GenreMapper.toGenre(request));
    }

//    public GenreEntity addGenreToFilm(Long genreId, Long filmId){
//        Optional<GenreEntity> genreEntity = Optional.ofNullable(genreRepository.findById(genreId)
//                .orElseThrow(() -> new GenreNotFoundException("Genre with id " + genreId + " not found")));
//        Optional<FilmEntity> filmEntity = Optional.ofNullable(filmRepository.findById(filmId)
//                .orElseThrow(() -> new FilmNotFoundException("Film with id " + filmId + " not found!")));
//
//        if(genreEntity.isPresent() && filmEntity.isPresent()){
//            filmEntity.get().addGenre(genreEntity.get());
//        }
//
//        return genreRepository.save(genreEntity.get());
//    }

//    public void deleteById(Long id) {
//        genreRepository.deleteById(id);
//    }
}
