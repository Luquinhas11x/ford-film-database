package com.workshop.filmsApi.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseError {
    private Integer httpCode;
    private String message;
    private String details;
}
