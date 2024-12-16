package com.dzapata.literatura.repository;

import com.dzapata.literatura.entity.BookEntity;
import com.dzapata.literatura.entity.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query(" SELECT b FROM BookEntity b " +
            " JOIN FETCH b.authors a " +
            " JOIN FETCH b.language l " +
            " WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')) ")
    List<BookEntity> findLibrosByTitulo(@Param("title") String title);

    @Query("SELECT b FROM BookEntity b JOIN FETCH b.authors WHERE b.language = :language")
    List<BookEntity> findByLanguage(LanguageEntity language);
}
