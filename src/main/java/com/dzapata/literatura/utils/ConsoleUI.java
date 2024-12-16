package com.dzapata.literatura.utils;

import com.dzapata.literatura.dto.AutorDTO;
import com.dzapata.literatura.dto.LibroDTO;
import com.dzapata.literatura.entity.AuthorEntity;
import com.dzapata.literatura.entity.BookEntity;
import com.dzapata.literatura.entity.LanguageEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleUI {

    public void principal(boolean isError, String msgError) {
        Helper.limpiarConsola();

        if (isError) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("                    "+msgError);
            System.out.println("----------------------------------------------------------------------");
        }

        System.out.println("""
            **********************************************************************
                        1 - Registrar libro de Gutendex
                        2 - Buscar libro
                        3 - Buscar Autor
                        4 - Buscar autores Vivos en un determinado año
                        5 - Listar libros registrados
                        6 - Listar autores Registrados
                        7 - Listar libros por idioma

                        0 - Salir
            **********************************************************************
            """);

        System.out.print(Constants.WRITE_OPTION_MESSAGE);
    }

    public void resultados(String msg) {
        System.out.println("**********************************************************************");
        System.out.printf("         Se entraron varios resultados con el %s buscado. ", msg);
        System.out.println("**********************************************************************");
    }

    public void buscarLibro(String method) {
        Helper.limpiarConsola();
        System.out.printf("Ingrese el titulo del libro que desea %s: ", method);
    }

    public void mostrarDetalleLibro(LibroDTO libroDTO) {
        System.out.println("**********************************************************************");
        System.out.println("    Titulo: " + libroDTO.getTitle());
        System.out.println("    Autor(es): " + String.join("| ", libroDTO.getAuthors()));
        System.out.println("    Idioma: " + libroDTO.getLanguage());
        System.out.println("    Descargas: " + libroDTO.getDownloadCount());
        System.out.println("**********************************************************************");
    }

    public void detallesLibros(BookEntity libro) {
        System.out.println("**********************************************************************");
        System.out.println("    Título: " + libro.getTitle());

        // Mostrar los autores asociados al libro
        if (libro.getAuthors() != null && !libro.getAuthors().isEmpty()) {
            System.out.println("    Autor(es):");
            for (AuthorEntity autor : libro.getAuthors()) {
                System.out.println("        - " + autor.getName());
            }
        } else {
            System.out.println("No tiene autores registrados.");
        }

        // Mostrar el idioma del libro
        if (libro.getLanguage() != null) {
            System.out.println("    Idioma: " + libro.getLanguage().getName());
        }
        System.out.println("    Descargas: " + libro.getDonwload_count());

        System.out.println("**********************************************************************");
        System.out.println();
    }

    public void buscarAutor() {
        Helper.limpiarConsola();
        System.out.print("Ingrese el autor que desea buscar: ");
    }

    public void mostrarDetalleAutor(AutorDTO autorDTO) {
        System.out.println("**********************************************************************");
        System.out.println("    Nombre: " + autorDTO.getName());
        if (autorDTO.getBirthYear() != null) {
            System.out.println("    Año de nacimiento: " + autorDTO.getBirthYear());
        } else {
            System.out.println("    Año de nacimiento: No Registrado.");
        }
        if (autorDTO.getDeathYear() != null) {
            System.out.println("    Año de fallecimiento: " + autorDTO.getDeathYear());
        } else {
            System.out.println("    Año de fallecimiento: No Registrado.");
        }
        System.out.println("    Cantidad de libros registrados: " + autorDTO.getCantidadLibros());
        System.out.println("**********************************************************************");
    }

    public void detalleAutores(AuthorEntity autor) {
        System.out.println("**********************************************************************");
        System.out.println("    Nombre: " + autor.getName());
        System.out.println("    Año de nacimiento: " + autor.getBirthYear());
        System.out.println("    Año de muerte: " + (autor.getDeathYear() != null ? autor.getDeathYear() : "Actualmente vivo"));

        if (autor.getBooks() != null && !autor.getBooks().isEmpty()) {
            System.out.println("    Libros:");
            for (BookEntity libro : autor.getBooks()) {
                System.out.println("        - " + libro.getTitle());
            }
        } else {
            System.out.println("        Este autor no tiene libros registrados.");
        }
        System.out.println("**********************************************************************");

        System.out.println();
    }

    public void buscarAnio() {
        Helper.limpiarConsola();
        System.out.print("Ingrese el año en el que desea buscar autores que estuvieron vivos: ");
    }

    public void mostrarAutoresVivos(AuthorEntity autor) {
        System.out.println("**********************************************************************");
        System.out.println("Nombre: " + autor.getName());
        System.out.println("Año de nacimiento: " + autor.getBirthYear());
        System.out.println("Año de muerte: " + (autor.getDeathYear() != null ? autor.getDeathYear() : "Actualmente vivo"));

        if (autor.getDeathYear() != null) {
            int edadAlMorir = Integer.parseInt(autor.getDeathYear()) - Integer.parseInt(autor.getBirthYear());
            System.out.println("Edad al morir: " + edadAlMorir);
        }

        if (autor.getBooks() != null && !autor.getBooks().isEmpty()) {
            System.out.println("Libros:");
            for (BookEntity libro : autor.getBooks()) {
                System.out.println(" - " + libro.getTitle());
            }
        } else {
            System.out.println("Este autor no tiene libros registrados.");
        }
        System.out.println("**********************************************************************");
        System.out.println();
    }

    public void buscarIdioma(List<LanguageEntity> languages) {
        Helper.limpiarConsola();
        System.out.println("**********************************************************************");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println("    " + (i + 1) + ".- " + languages.get(i).getCode() + "| " + languages.get(i).getName());
        }

        System.out.println("**********************************************************************");
        System.out.println();
        System.out.print("Ingrese el idioma que desea buscar: ");
    }

    public void detalleIdioma(BookEntity libro) {
        System.out.println("**********************************************************************");
        System.out.println("Título: " + libro.getTitle());

        if (libro.getAuthors() != null && !libro.getAuthors().isEmpty()) {
            System.out.println("Autores:");
            for (AuthorEntity autor : libro.getAuthors()) {
                System.out.println(" - " + autor.getName());
            }
        } else {
            System.out.println("No tiene autores registrados.");
        }
        System.out.println("**********************************************************************");

        System.out.println();
    }
}
