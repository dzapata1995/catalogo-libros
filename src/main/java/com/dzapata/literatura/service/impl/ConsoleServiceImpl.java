package com.dzapata.literatura.service.impl;

import com.dzapata.literatura.service.BookService;
import com.dzapata.literatura.service.ConsoleService;
import com.dzapata.literatura.utils.Constants;
import com.dzapata.literatura.utils.ConsoleUI;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final ConsoleUI consoleUI;
    private final BookService bookService;

    public ConsoleServiceImpl(ConsoleUI consoleUI, BookService bookService) {
        this.consoleUI = consoleUI;
        this.bookService = bookService;
    }

    @Override
    public void start() throws Exception {
        boolean isExecution = true;
        boolean isError = false;
        Scanner scanner = new Scanner(System.in);
        String msgError = "";

        while (isExecution) {
            consoleUI.principal(isError, msgError);

            try {
                int selection = scanner.nextInt();

                switch (selection) {
                    case 1:
                        bookService.registrarLibro();
                        break;

                    case 2:
                        bookService.buscarLibro();
                        break;

                    case 3:
                        bookService.buscarAutor();
                        break;

                    case 4:
                        bookService.buscarAutoresVivos();
                        break;

                    case 5:
                        bookService.listarLibros();
                        break;

                    case 6:
                        bookService.listarAutores();
                        break;

                    case 7:
                        bookService.listarLibrosIdioma();

                    case 0:
                        isExecution = false;
                        break;

                    default:
                        msgError = Constants.INVALID_OPTION_MESSAGE;
                        isError = true;
                        break;
                }

            } catch (InputMismatchException e) {
                msgError = Constants.INVALID_OPTION_MESSAGE;
                isError = true;
                scanner.nextLine();
            }
        }

        scanner.close();
        System.exit(0);
    }
}
