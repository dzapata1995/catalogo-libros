package com.dzapata.literatura.service;

import java.util.List;

public interface BookService {
    void registrarLibro() throws Exception;
    void buscarLibro();
    void buscarAutor();
    void buscarAutoresVivos();
    void listarLibros();
    void listarAutores();
    void listarLibrosIdioma();

}
