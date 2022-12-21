package com.workshop.filmsApi.controller;

import com.workshop.filmsApi.dto.*;
import com.workshop.filmsApi.entity.GenreEntity;
import com.workshop.filmsApi.mapper.GenreMapper;
import com.workshop.filmsApi.request.GenreRequest;
import com.workshop.filmsApi.response.BaseResponse;
import com.workshop.filmsApi.response.BaseResponseError;
import com.workshop.filmsApi.service.GenreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Genre Controller")
@RestController
@AllArgsConstructor
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;

    @ApiOperation(
            value = "Post a new genre",
            response = GenreEntity.class,
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Genre successfully posted"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Genre not found", response = BaseResponseError.class),
            @ApiResponse(code = 409, message = "Genre already exists", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @PostMapping
    public BaseResponse<GenreEntity> addGenre(@RequestBody GenreRequest request) throws Exception {
        return BaseResponse.<GenreEntity>builder()
                .httpCode(201)
                .message("CREATED")
                .response(genreService.addGenre(request))
                .build();

    }

    @ApiOperation(
            value = "Get genre list",
            response = GenreFilmDto.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully obtain genre list"),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Genre not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @GetMapping
    public BaseResponse<List<GenreFilmDto>> getAll() {
        return BaseResponse.<List<GenreFilmDto>>builder()
                .httpCode(200)
                .message("OK")
                .response(genreService.getAll())
                .build();
    }

    @ApiOperation(
            value = "Get genre by id",
            response = GenreFilmDto.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully obtain genre"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Genre not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
    })
    @GetMapping("/{genreId}")
    public BaseResponse<GenreFilmDto> getById(@PathVariable Long genreId) {
        return BaseResponse.<GenreFilmDto>builder()
                .httpCode(200)
                .message("OK")
                .response(GenreMapper.toGenreFilmDto(genreService.getGenreById(genreId)))
                .build();
    }

//    @ApiOperation(
//            value = "Add genre in movie",
//            response = GenreDto.class,
//            produces = "application/json",
//            consumes = "application/json"
//    )
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully added genre"),
//            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
//            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
//            @ApiResponse(code = 404, message = "Genre or Film not found", response = BaseResponseError.class),
//            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class)
//    })
//    @PutMapping("/addGenreToFilm")
//    public BaseResponse<GenreDto> addGenreToFilm(
//            @RequestParam("filmId") Long filmId,
//            @RequestParam("genreId") Long genreId
//    ) {
//        return BaseResponse.<GenreDto>builder()
//                .httpCode(204)
//                .message("NO CONTENT")
//                .response(GenreMapper.toGenreDto(genreService.addGenreToFilm(genreId, filmId)))
//                .build();
//    }
}
