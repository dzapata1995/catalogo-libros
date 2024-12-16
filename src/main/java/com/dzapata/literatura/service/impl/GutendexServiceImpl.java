package com.dzapata.literatura.service.impl;

import com.dzapata.literatura.client.ApiClient;
import com.dzapata.literatura.dto.response.BookResponseDTO;
import com.dzapata.literatura.service.BookService;
import com.dzapata.literatura.service.GutendexService;
import com.dzapata.literatura.utils.ConsoleUI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class GutendexServiceImpl implements GutendexService {

    private final ApiClient apiClient;
    private final ConsoleUI consoleUI;
    private final BookService bookService;

    public GutendexServiceImpl(ApiClient apiClient, ConsoleUI consoleUI, BookService bookService) {
        this.apiClient = apiClient;
        this.consoleUI = consoleUI;
        this.bookService = bookService;
    }

    @Override
    public void registrarLibro() {
        Scanner scanner = new Scanner(System.in);
        consoleUI.buscarLibro("registrar");
        String titulo = scanner.nextLine();
        List<BookResponseDTO> response = apiClient.buscarLibro(titulo);

        while (response.size() > 10) {
            System.out.println("Hemos encontrado más de 10 libros con el término de búsqueda: " + titulo);
            System.out.print("Ingrese lo que desea buscar: ");
            String nuevoTitulo = scanner.nextLine();

            if (nuevoTitulo.trim().isEmpty()) {
                nuevoTitulo = titulo;
            }

            response = apiClient.buscarLibro(nuevoTitulo);
            titulo = nuevoTitulo;
        }

        if (!response.isEmpty()) {
            System.out.println("Aquí están los resultados encontrados para la búsqueda \"" + titulo + "\":");
            for (int i = 0; i < response.size(); i++) {
                BookResponseDTO libro = response.get(i);
                String autores = libro.getAuthors().stream()
                        .map(author -> author.getName() != null ? author.getName() : "Sin nombre")
                        .collect(Collectors.joining("| "));

                System.out.println((i + 1) + ". " + libro.getTitle() + " - Autor(es): " + autores);
            }

            System.out.print("Selecciona el número del libro que deseas registrar (1-" + response.size() + "): ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();

            if (seleccion > 0 && seleccion <= response.size()) {
                BookResponseDTO libroSeleccionado = response.get(seleccion - 1);

                System.out.println("\nHas seleccionado el siguiente libro:");
                System.out.println("Título: " + libroSeleccionado.getTitle());
                String autores = libroSeleccionado.getAuthors().stream()
                        .map(author -> author.getName() != null ? author.getName() : "Sin nombre")
                        .collect(Collectors.joining(", "));
                System.out.println("Autor(es): " + autores);
                System.out.println("Idioma: " + libroSeleccionado.getLanguages().get(0));

                System.out.print("\n¿Deseas guardar este libro? (S/N): ");
                String confirmacion = scanner.nextLine().trim().toUpperCase();

                if ("S".equals(confirmacion)) {
                    bookService.guardarLibro(libroSeleccionado);
                    System.out.println("El libro se ha registrado exitosamente.");
                } else {
                    System.out.println("El libro no se guardó.");
                }
            } else {
                System.out.println("Selección no válida. Intentando nuevamente.");
            }
        } else {
            System.out.println("No se encontraton libros.");
        }
    }
}
