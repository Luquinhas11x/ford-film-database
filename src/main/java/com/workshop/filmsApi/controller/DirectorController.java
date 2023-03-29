package com.workshop.filmsApi.controller;

import com.workshop.filmsApi.dto.DirectorDto;
import com.workshop.filmsApi.dto.DirectorFilmDto;
import com.workshop.filmsApi.mapper.DirectorMapper;
import com.workshop.filmsApi.request.DirectorRequest;
import com.workshop.filmsApi.response.BaseResponse;
import com.workshop.filmsApi.response.BaseResponseError;
import com.workshop.filmsApi.service.DirectorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Director Controller")
@RestController
@AllArgsConstructor
@RequestMapping("/director")
public class DirectorController {
    private final DirectorService directorService;

    @ApiOperation(
            value = "Get director by id",
            response = DirectorFilmDto.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully obtain director"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Director not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @GetMapping("/{id}")
    public BaseResponse<DirectorFilmDto>  getDirectorById(@PathVariable Long id){
        return BaseResponse.<DirectorFilmDto>builder()
                .httpCode(200)
                .message("OK")
                .response(DirectorMapper.toDirectorFilmDto(directorService.getDirectorById(id)))
                .build();
    }

    @ApiOperation(
            value = "Get director list",
            response = DirectorFilmDto.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully obtain director list"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Director not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @GetMapping()
    public BaseResponse<List<DirectorFilmDto>> getDirector(){
        return BaseResponse.<List<DirectorFilmDto>>builder()
                .httpCode(200)
                .message("OK")
                .response(directorService.getAll())
                .build();
    }
    @GetMapping("/test")
    public BaseResponse<List<DirectorFilmDto>> getDirectorTest(@RequestParam(value = "directorName") String directorName){
        return BaseResponse.<List<DirectorFilmDto>>builder()
                .httpCode(200)
                .message("OK")
                .response(directorService.getAllTest(directorName))
                .build();
    }

    @ApiOperation(
            value = "Post a new director",
            response = DirectorDto.class,
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Director successfully posted"),
            @ApiResponse(code = 400, message = "Bad request", response = BaseResponseError.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = BaseResponseError.class),
            @ApiResponse(code = 404, message = "Director not found", response = BaseResponseError.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = BaseResponseError.class),
    })
    @PostMapping()
    public BaseResponse<DirectorDto>  addDirector(@RequestBody DirectorRequest request) {
        return BaseResponse.<DirectorDto>builder()
                .httpCode(201)
                .message("CREATED")
                .response(directorService.addDirector(request))
                .build();
    }

//    @DeleteMapping("/{id}")
//    public void deleteDirector(@PathVariable Long id){
//        directorService.deleteById(id);
//    }
}
