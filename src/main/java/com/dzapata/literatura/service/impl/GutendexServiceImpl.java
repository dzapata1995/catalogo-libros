package com.dzapata.literatura.service.impl;

import com.dzapata.literatura.client.ApiClient;
import com.dzapata.literatura.dto.response.BookResponseDTO;
import com.dzapata.literatura.dto.response.GutendexResponseDTO;
import com.dzapata.literatura.service.GutendexService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class GutendexServiceImpl implements GutendexService {

    private final ApiClient apiClient;

    public GutendexServiceImpl(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public BookResponseDTO registrarLibro(String titulo) {
        Scanner scanner = new Scanner(System.in);
        List<BookResponseDTO> response = apiClient.buscarLibro(titulo);

        return response.get(0);
    }
}
