package com.dzapata.literatura.repository;

import com.dzapata.literatura.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByName(String name);

    @Query(" SELECT a FROM AuthorEntity a " +
            " LEFT JOIN FETCH a.books " +
            " WHERE LOWER(a.name) LIKE LOWER(CONCAT('%',:name, '%')) ")
    List<AuthorEntity> findDatosByName(String name);

    @Query("SELECT a FROM AuthorEntity a " +
            " LEFT JOIN FETCH a.books " +
            " WHERE a.birthYear <= :anio AND (a.deathYear >= :anio OR a.deathYear IS NULL) ")
    List<AuthorEntity> findAutoresVivos(@Param("anio") String anio);
}
