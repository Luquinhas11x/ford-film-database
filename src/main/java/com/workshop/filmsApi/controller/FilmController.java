package com.workshop.filmsApi.controller;

import com.workshop.filmsApi.dto.*;
import com.workshop.filmsApi.mapper.FilmMapper;
import com.workshop.filmsApi.mapper.GenreMapper;
import com.workshop.filmsApi.request.FilmRequest;
import com.workshop.filmsApi.response.BaseResponse;
import com.workshop.filmsApi.response.BaseResponseError;
import com.workshop.filmsApi.service.FilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@Api(tags = "Film Controller")
@RestController
@AllArgsConstructor
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;

    @ApiOperation(
            value = "Get film by id",
            response = FilmGenreDirectorDto.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully obtain film"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Film not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @GetMapping("/{id}")
    public BaseResponse<FilmGenreDirectorDto>  getFilmById(@PathVariable Long id) {
        return BaseResponse.<FilmGenreDirectorDto>builder()
                .httpCode(200)
                .message("OK")
                .response(FilmMapper.toFilmDirectorGenreDto(filmService.getFilmById(id)))
                .build();
    }

    @ApiOperation(
            value = "Get film list",
            response = FilmGenreDirectorDto.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully obtain film list"),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Film not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @GetMapping()
    public BaseResponse<Page<FilmGenreDirectorDto>> getFilm(@PageableDefault(size = 10, sort = {"rating"}) Pageable pagination){
        return BaseResponse.<Page<FilmGenreDirectorDto>>builder()
                .httpCode(200)
                .message("OK")
                .response(filmService.getAll(pagination))
                .build();
    }

    @ApiOperation(
            value = "Post a new film",
            response = FilmDirectorDto.class,
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Film successfully posted"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Film not found", response = BaseResponseError.class),
            @ApiResponse(code = 409, message = "Film already exists", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @PostMapping()
    public BaseResponse<FilmDirectorDto> addFilm(@RequestBody FilmRequest request){
        return BaseResponse.<FilmDirectorDto>builder()
                .httpCode(201)
                .message("CREATED")
                .response(filmService.createFilm(request))
                .build();

    }

    @ApiOperation(
            value = "Update film banner or rating",
            response = FilmDto.class,
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully edited film"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Film not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @PutMapping("/{id}")
    public BaseResponse<FilmDto> uptadeFilm(@RequestBody FilmRequest request, @PathVariable Long id){
        return BaseResponse.<FilmDto>builder()
                .httpCode(204)
                .message("NO CONTENT")
                .response(FilmMapper.toDTOFilm(filmService.update(request, id)))
                .build();
    }

    @ApiOperation(
            value = "Update film status at store or your list",
            response = FilmDto.class,
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully edited film"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Film not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @PutMapping("/status/{id}")
    public BaseResponse<FilmDto> uptadeFilmStatus(@RequestBody FilmRequest request, @PathVariable Long id){
        return BaseResponse.<FilmDto>builder()
                .httpCode(204)
                .message("NO CONTENT")
                .response(FilmMapper.toDTOFilm(filmService.updateStatus(request, id)))
                .build();
    }

    @ApiOperation(
            value = "Update film rating",
            response = FilmDto.class,
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully edited film"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Film not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @PutMapping("/rating/{id}")
    public BaseResponse<FilmDto> uptadeFilmRating(@RequestBody FilmRequest request, @PathVariable Long id){
        return BaseResponse.<FilmDto>builder()
                .httpCode(204)
                .message("NO CONTENT")
                .response(FilmMapper.toDTOFilm(filmService.updateRating(request, id)))
                .build();
    }

    @ApiOperation(
            value = "Update all film",
            response = FilmDto.class,
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully edited film"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Film not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @PutMapping("/editAll/{id}")
    public BaseResponse<FilmDto> uptadeAll(@RequestBody FilmRequest request, @PathVariable Long id){
        return BaseResponse.<FilmDto>builder()
                .httpCode(204)
                .message("NO CONTENT")
                .response(FilmMapper.toDTOFilm(filmService.updateAll(request, id)))
                .build();
    }

    @ApiOperation(
            value = "Add genre in movie",
            response = FilmDto.class,
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added genre"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Genre or Film not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @PutMapping("/addGenreToFilm/filmId/{filmId}")
    public BaseResponse<FilmDto> addGenreToFilm(
            @PathVariable Long filmId,
            @RequestParam("genreId") Long genreId
    ) {
        return BaseResponse.<FilmDto>builder()
                .httpCode(204)
                .message("NO CONTENT")
                .response(FilmMapper.toDTOFilm(filmService.addGenreToFilm(genreId, filmId)))
                .build();
    }

    @ApiOperation(
            value = "Delete film by id",
            response = FilmGenreDirectorDto.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted film"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Film not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable Long id){
        filmService.deleteById(id);
    }
}
