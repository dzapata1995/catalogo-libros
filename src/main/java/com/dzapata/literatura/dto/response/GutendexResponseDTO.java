package com.dzapata.literatura.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GutendexResponseDTO {
    private int count;
    private String next;
    private String previous;
    private List<BookResponseDTO> results;
}
