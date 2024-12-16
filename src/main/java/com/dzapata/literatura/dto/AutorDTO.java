package com.dzapata.literatura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorDTO {
    private String name;
    private String birthYear;
    private String deathYear;
    private int cantidadLibros;
}
