package com.dzapata.literatura.service;

import com.dzapata.literatura.dto.response.BookResponseDTO;

public interface GutendexService {
    BookResponseDTO registrarLibro(String titulo);
}
