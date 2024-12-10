package com.dzapata.literatura.client;

import com.dzapata.literatura.dto.response.BookResponseDTO;
import com.dzapata.literatura.dto.response.GutendexResponseDTO;
import com.dzapata.literatura.utils.Constants;
import com.dzapata.literatura.utils.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApiClient {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public ApiClient(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public List<BookResponseDTO> buscarLibro(String titulo) {
        String url = buildUrl(titulo);
        List<BookResponseDTO> allResults = new ArrayList<>();

        while (url != null) {
            GutendexResponseDTO currentResponse = sendRequest(url);

            if (currentResponse == null) {
                break;
            }

            allResults.addAll(currentResponse.getResults());
            url = currentResponse.getNext();
        }

        return Helper.filtrarResultadoApi(allResults);
    }

    private String buildUrl(String titulo) {
        return Constants.URL_GUNTEX_BASE + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
    }

    private GutendexResponseDTO sendRequest(String url) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), GutendexResponseDTO.class);
            } else {
                System.out.println("Error al hacer la solicitud. Código de estado: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los libros desde Guntendex.");
            e.printStackTrace();
        }

        return null;
    }
}
