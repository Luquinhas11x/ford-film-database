package com.workshop.filmsApi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "film_name", unique = true)
    private String filmName;

    @Column(name = "banner_link")
    private String bannerURL;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "director_id")
    private DirectorEntity director;

    @ManyToMany(mappedBy = "films")
    List<GenreEntity> genres;

    public GenreEntity addGenre(GenreEntity genre){
        genres.add(genre);
        genre.getFilms().add(this);
        return genre;
    }

    public void addDirector(DirectorEntity director){
        this.director = director;
        director.getFilms().add(this);
    }
}
