package com.workshop.filmsApi.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre_name", unique = true)
    private String genreName;

    @ManyToMany()
    @JoinTable(name = "genre_film",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    List<FilmEntity> films;

    public void addFilm(FilmEntity film){
        films.add(film);
        film.getGenres().add(this);
    }
}
