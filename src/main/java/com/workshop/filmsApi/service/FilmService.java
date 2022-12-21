package com.workshop.filmsApi.service;

import com.workshop.filmsApi.dto.FilmDirectorDto;
import com.workshop.filmsApi.dto.FilmGenreDirectorDto;
import com.workshop.filmsApi.entity.DirectorEntity;
import com.workshop.filmsApi.entity.FilmEntity;
import com.workshop.filmsApi.entity.GenreEntity;
import com.workshop.filmsApi.exception.DirectorNotFoundException;
import com.workshop.filmsApi.exception.FilmNameAlreadyExistException;
import com.workshop.filmsApi.exception.FilmNotFoundException;
import com.workshop.filmsApi.exception.GenreNotFoundException;
import com.workshop.filmsApi.mapper.FilmMapper;
import com.workshop.filmsApi.repository.DirectorRepository;
import com.workshop.filmsApi.repository.FilmRepository;
import com.workshop.filmsApi.repository.GenreRepository;
import com.workshop.filmsApi.request.FilmRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final DirectorRepository directorRepository;
    private final GenreRepository genreRepository;

    public Page<FilmGenreDirectorDto> getAll(Pageable pagination) {
        return filmRepository.findAll(pagination)
                .map(FilmMapper::toFilmDirectorGenreDto);
    }

//    public FilmEntity getById(Long id) throws FilmNotFoundException {
//        Optional<FilmEntity> film = filmRepository.findById(id);
//
//        if(film.isPresent()){
//            return film.get();
//        }else {
//            throw new FilmNotFoundException((new BaseResponseError("NOT_FOUND",
//                    HttpStatus.NOT_FOUND.value(),
//                    "Film with id " + id + " not found!")));
//        }
//    }

    public FilmEntity getFilmById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("Film with id " + id + " not found"));
    }

    @Transactional
    public FilmDirectorDto createFilm(FilmRequest request) {

        Optional<FilmEntity> filmName = filmRepository.findByFilmName(request.getFilmName());

        if (filmName.isPresent()) {
            throw new FilmNameAlreadyExistException("The film " + request.getFilmName() + " already exists");
        }

        DirectorEntity director = directorRepository.findById(request.getDirectorId())
                .orElseThrow(() -> new DirectorNotFoundException("Director id " + request.getDirectorId() + " not found"));

        FilmEntity film = filmRepository.save(FilmMapper.toFilm(request));

        film.addDirector(director);
        return FilmMapper.toFilmDirectorDto(film);
    }

//    public FilmEntity addFilm(Long filmId, Long genreId) throws Exception {
//        GenreEntity genreEntity = genreService.getGenreById(genreId);
//        FilmEntity filmEntity = getFilmById(filmId);
//        genreRepository.save(filmEntity.addGenre(genreEntity));
//        return filmRepository.save(filmEntity);
//    }

    public FilmEntity update(FilmRequest request, Long Id) {
        Optional<FilmEntity> filmEntity = filmRepository.findById(Id);

        if (filmEntity.isEmpty()) {
            throw new FilmNotFoundException("Film id " + Id + " not found!");
        }
        FilmEntity editedFilm = filmEntity.get();

        editedFilm.setBannerURL(request.getBannerURL());
        editedFilm.setRating(request.getRating());

        return filmRepository.save(editedFilm);
    }

    public FilmEntity updateAll(FilmRequest request, Long Id) {
        Optional<FilmEntity> filmEntity = filmRepository.findById(Id);

        if (filmEntity.isEmpty()) {
            throw new FilmNotFoundException("Film id " + Id + " not found!");
        }
        FilmEntity editedFilm = filmEntity.get();

        editedFilm.setBannerURL(request.getBannerURL());
        editedFilm.setRating(request.getRating());
        editedFilm.setStatus(request.getStatus());

        return filmRepository.save(editedFilm);
    }

    public FilmEntity updateStatus(FilmRequest request, Long Id) {
        Optional<FilmEntity> filmEntity = filmRepository.findById(Id);

        if (filmEntity.isEmpty()) {
            throw new FilmNotFoundException("Film id " + Id + " not found!");
        }
        FilmEntity editedFilm = filmEntity.get();

        editedFilm.setStatus(request.getStatus());

        return filmRepository.save(editedFilm);
    }

    public FilmEntity updateRating(FilmRequest request, Long Id) {
        Optional<FilmEntity> filmEntity = filmRepository.findById(Id);

        if (filmEntity.isEmpty()) {
            throw new FilmNotFoundException("Film id " + Id + " not found!");
        }
        FilmEntity editedFilm = filmEntity.get();

        editedFilm.setRating(request.getRating());

        return filmRepository.save(editedFilm);
    }

    public FilmEntity addGenreToFilm(Long genreId, Long filmId){
        Optional<GenreEntity> genreEntity = Optional.ofNullable(genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException("Genre with id " + genreId + " not found")));
        Optional<FilmEntity> filmEntity = Optional.ofNullable(filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException("Film with id " + filmId + " not found!")));

        if(genreEntity.isPresent() && filmEntity.isPresent()){
            filmEntity.get().addGenre(genreEntity.get());
        }

        return filmRepository.save(filmEntity.get());
    }

    public void deleteById(Long id) {
        filmRepository.deleteById(id);
    }
}
