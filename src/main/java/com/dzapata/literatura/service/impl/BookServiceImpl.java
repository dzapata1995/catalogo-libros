package com.dzapata.literatura.service.impl;

import com.dzapata.literatura.dto.AutorDTO;
import com.dzapata.literatura.dto.LibroDTO;
import com.dzapata.literatura.dto.response.BookResponseDTO;
import com.dzapata.literatura.entity.AuthorEntity;
import com.dzapata.literatura.entity.BookEntity;
import com.dzapata.literatura.entity.LanguageEntity;
import com.dzapata.literatura.repository.AuthorRepository;
import com.dzapata.literatura.repository.BookRepository;
import com.dzapata.literatura.repository.LanguageRepository;
import com.dzapata.literatura.service.BookService;
import com.dzapata.literatura.utils.ConsoleUI;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final ConsoleUI consoleUI;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final LanguageRepository languageRepository;

    public BookServiceImpl(ConsoleUI consoleUI, AuthorRepository authorRepository, BookRepository bookRepository, LanguageRepository languageRepository) {
        this.consoleUI = consoleUI;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.languageRepository = languageRepository;
    }

    @Override
    public void guardarLibro(BookResponseDTO responseDTO) {
        BookEntity book = new BookEntity();
        book.setTitle(responseDTO.getTitle());

        List<AuthorEntity> autores = responseDTO.getAuthors().stream()
                .map(authorDTO -> authorRepository.findByName(authorDTO.getName()).orElseGet(() -> {
                    AuthorEntity newAuthor = new AuthorEntity();
                    newAuthor.setName(authorDTO.getName());
                    newAuthor.setBirthYear(authorDTO.getBirth_year());
                    newAuthor.setDeathYear(authorDTO.getDeath_year());

                    return authorRepository.save(newAuthor);
                }))
                .toList();
        book.setAuthors(autores);

        List<LanguageEntity> idiomas = responseDTO.getLanguages().stream()
                .map(language -> languageRepository.findByCode(language).orElseGet(() -> {
                    LanguageEntity newLanguage = new LanguageEntity();
                    newLanguage.setCode(language);
                    newLanguage.setName(language);
                    return languageRepository.save(newLanguage);
                })).toList();
        book.setLanguage(idiomas.get(0));
        book.setDonwload_count(responseDTO.getDownload_count());

        bookRepository.save(book);
    }

    @Override
    public void buscarLibro() {
        Scanner scanner = new Scanner(System.in);
        boolean continuarBusqueda = true;

        while(continuarBusqueda) {
            consoleUI.buscarLibro("buscar");
            String titulo = scanner.nextLine();

            List<BookEntity> libros = bookRepository.findLibrosByTitulo(titulo);

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros con el titulo ingresado.");
            } else {
                List<LibroDTO> libroDTO = libros.stream()
                    .map(book -> new LibroDTO(
                            book.getTitle(),
                            book.getAuthors().stream()
                                    .map(AuthorEntity::getName).collect(Collectors.toList()),
                            book.getLanguage().getName(),
                            book.getDonwload_count()
                    )).toList();

                if (libroDTO.size() > 1) {
                    consoleUI.resultados("titulo");
                    for (int i = 0; i < libroDTO.size(); i++) {
                        System.out.println((i + 1) + ".- " +libroDTO.get(i).getTitle());
                    }

                    boolean opcionValida = false;
                    while(!opcionValida) {
                        System.out.println("Seleccione el numero del libro que desea ver, 0 para volver atras.");
                        int seleccion = scanner.nextInt();
                        scanner.nextLine();

                        if (seleccion == 0) {
                            opcionValida = true;
                        } else if (seleccion > 0 && seleccion <= libroDTO.size()) {
                            LibroDTO libroSeleccionado = libroDTO.get(seleccion - 1);
                            consoleUI.mostrarDetalleLibro(libroSeleccionado);
                            opcionValida = true;
                        } else {
                            System.out.println("Ingrese una opcion valida.");
                        }
                    }
                } else {
                    consoleUI.mostrarDetalleLibro(libroDTO.get(0));
                }
            }

            System.out.print("¿Desea buscar otro libro? (s/n): ");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("n")) {
                continuarBusqueda = false;
            }
        }
    }

    @Override
    public void buscarAutor() {
        Scanner scanner = new Scanner(System.in);
        boolean continuarBusqueda = true;

        while(continuarBusqueda) {
            consoleUI.buscarAutor();
            String autor = scanner.nextLine();

            List<AuthorEntity> autores = authorRepository.findDatosByName(autor);

            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores con ese nombre.");
            } else {
                List<AutorDTO> autorDTO = autores.stream()
                        .map(author -> new AutorDTO(
                                author.getName(),
                                author.getBirthYear(),
                                author.getDeathYear(),
                                author.getBooks().size()
                        )).toList();

                if (autorDTO.size() > 1) {
                    consoleUI.resultados("autor");
                    for (int i=0; i < autorDTO.size(); i++) {
                        System.out.println((i+1) + ".-" + autorDTO.get(i).getName());
                    }

                    boolean opcionValida = false;
                    while(!opcionValida) {
                        System.out.println("Seleccione el numero del autor que desea ver, 0 para volver atras.");
                        int seleccion = scanner.nextInt();
                        scanner.nextLine();

                        if (seleccion == 0) {
                            opcionValida = true;
                        } else if (seleccion > 0 && seleccion <= autorDTO.size()) {
                            AutorDTO autorSeleccionado = autorDTO.get(seleccion - 1);
                            consoleUI.mostrarDetalleAutor(autorSeleccionado);
                            opcionValida = true;
                        } else {
                            System.out.println("Ingrese una opcion valida.");
                        }
                    }
                } else {
                    consoleUI.mostrarDetalleAutor(autorDTO.get(0));
                }
            }

            System.out.print("¿Desea buscar otro autor? (s/n): ");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("n")) {
                continuarBusqueda = false;
            }
        }

    }

    @Override
    public void buscarAutoresVivos() {
        Scanner scanner = new Scanner(System.in);

        consoleUI.buscarAnio();
        String anio = scanner.nextLine();

        List<AuthorEntity> autoresVivos = authorRepository.findAutoresVivos(anio);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            for (AuthorEntity autor : autoresVivos) {
                consoleUI.mostrarAutoresVivos(autor);
            }
        }

        System.out.print("¿Desea realizar otra búsqueda? (s/n): ");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            buscarAutoresVivos();
        } else {
            System.out.println("Regresando al menú principal...");
        }
    }

    @Override
    @Transactional
    public void listarLibros() {
        List<BookEntity> libros = bookRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros registrados.");
        } else {
            System.out.println("Libros registrados:");

            for (BookEntity libro: libros) {
                consoleUI.detallesLibros(libro);
            }
        }
    }

    @Override
    @Transactional
    public void listarAutores() {
        List<AuthorEntity> autores = authorRepository.findAll();  // Obtiene todos los autores

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores registrados.");
        } else {
            System.out.println("Autores registrados:");

            for (AuthorEntity autor : autores) {
                consoleUI.detalleAutores(autor);
            }
        }
    }

    @Override
    public void listarLibrosIdioma() {
        Scanner scanner = new Scanner(System.in);

        List<LanguageEntity> idiomas = languageRepository.findAll();

        consoleUI.buscarIdioma(idiomas);
        int idiomaSelect = scanner.nextInt();
        scanner.nextLine();

        if (idiomaSelect < 1 || idiomaSelect > idiomas.size()) {
            System.out.println("Selección no válida.");
        } else {
            LanguageEntity idiomaSeleccionado = idiomas.get(idiomaSelect -1);
            List<BookEntity> libros = bookRepository.findByLanguage(idiomaSeleccionado);

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros en el idioma " + idiomaSeleccionado.getName());
            } else {
                System.out.println("Libros disponibles en el idioma " + idiomaSeleccionado.getName() + ":");
                for (BookEntity libro : libros) {
                    consoleUI.detalleIdioma(libro);
                }
            }
        }
    }
}
