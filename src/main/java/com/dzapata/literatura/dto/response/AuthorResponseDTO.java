package com.dzapata.literatura.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponseDTO {
    private String name;
    private String birth_year;
    private String death_year;
}
