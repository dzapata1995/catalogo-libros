package com.dzapata.literatura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {
    private String title;
    private List<String> authors;
    private String language;
    private int downloadCount;
}
