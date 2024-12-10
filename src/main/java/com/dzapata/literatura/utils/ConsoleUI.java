package com.dzapata.literatura.utils;

import org.springframework.stereotype.Component;

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

    public void buscarLibro(String method) {
        Helper.limpiarConsola();
        System.out.printf("Ingrese el titulo del libro que desea %s: ", method);
    }

    public void buscarAutor() {
        Helper.limpiarConsola();
        System.out.print("Ingrese el autor que desea buscar: ");
    }

    public void buscarAnio() {
        Helper.limpiarConsola();
        System.out.print("Ingrese el año en el que desea buscar autores que estuvieron vivos: ");
    }

    public void buscarIdioma() {
        Helper.limpiarConsola();
        System.out.println("""
            **********************************************************************
                        
            **********************************************************************
            """);
        System.out.print("Ingrese el idioma que desea buscar: ");
    }
}
