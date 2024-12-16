package com.dzapata.literatura.service;

import com.dzapata.literatura.dto.response.BookResponseDTO;

public interface BookService {
    void guardarLibro(BookResponseDTO dto);
    void buscarLibro();
    void buscarAutor();
    void buscarAutoresVivos();
    void listarLibros();
    void listarAutores();
    void listarLibrosIdioma();

}
