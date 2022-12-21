package com.workshop.filmsApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    private Long id;
    private String filmName;
    private String bannerURL;
    private LocalDate releaseDate;
    private Integer rating;
    private Boolean status;
    private String description;
}
