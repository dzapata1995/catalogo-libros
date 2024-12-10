package com.dzapata.literatura.utils;

import com.dzapata.literatura.dto.response.BookResponseDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {
    public static void limpiarConsola() {
        try {
            String operatingSystem = System.getProperty("os.name");

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (final Exception e) {
            System.err.println("Error al limpiar la consola.");
        }

        System.out.println(Constants.TITLE);
    }

    public static List<BookResponseDTO> filtrarResultadoApi(List<BookResponseDTO> resultados) {
        Map<String, BookResponseDTO> bookMap = new HashMap<>();

        for (BookResponseDTO libro : resultados) {
            String titulo = libro.getTitle();

            if (!bookMap.containsKey(titulo)) {
                bookMap.put(titulo, libro);
            } else {
                BookResponseDTO libroExistente = bookMap.get(titulo);

                if (libroExistente.getAuthors().equals(libro.getAuthors())) {
                    if (libroExistente.getDownload_count() < libro.getDownload_count()) {
                        bookMap.put(titulo, libro);
                    }
                } else {
                    bookMap.put(titulo, libro);
                }
            }
        }

        return new ArrayList<>(bookMap.values());
    }
}
