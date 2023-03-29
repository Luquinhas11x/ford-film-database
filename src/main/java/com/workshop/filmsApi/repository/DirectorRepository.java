package com.workshop.filmsApi.repository;

import com.workshop.filmsApi.entity.DirectorEntity;
import com.workshop.filmsApi.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {
    Optional<DirectorEntity> findByDirectorName(String directorName);
}
