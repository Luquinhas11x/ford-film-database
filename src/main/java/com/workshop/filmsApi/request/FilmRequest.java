package com.workshop.filmsApi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmRequest {
    private Long directorId;
    private String filmName;
    private String bannerURL;
    private LocalDate releaseDate;
    private Integer rating;
    private Boolean status;
    private String description;
}
