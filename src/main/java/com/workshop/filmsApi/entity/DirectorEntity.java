package com.workshop.filmsApi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "director_name")
    private String directorName;

    @OneToMany(mappedBy = "director")
    private List<FilmEntity> films;
}
