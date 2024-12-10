package com.dzapata.literatura.service.impl;

import com.dzapata.literatura.dto.response.BookResponseDTO;
import com.dzapata.literatura.service.BookService;
import com.dzapata.literatura.service.GutendexService;
import com.dzapata.literatura.utils.ConsoleUI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class BookServiceImpl implements BookService {

    private final ConsoleUI consoleUI;
    private final GutendexService gutendexService;

    public BookServiceImpl(ConsoleUI consoleUI, GutendexService gutendexService) {
        this.consoleUI = consoleUI;
        this.gutendexService = gutendexService;
    }

    @Override
    public void registrarLibro() {
        Scanner scanner = new Scanner(System.in);
        consoleUI.buscarLibro("registrar");
        String titulo = scanner.nextLine();

        BookResponseDTO response = gutendexService.registrarLibro(titulo);


    }

    @Override
    public void buscarLibro() {

    }

    @Override
    public void buscarAutor() {

    }

    @Override
    public void buscarAutoresVivos() {

    }

    @Override
    public void listarLibros() {

    }

    @Override
    public void listarAutores() {

    }

    @Override
    public void listarLibrosIdioma() {

    }
}
