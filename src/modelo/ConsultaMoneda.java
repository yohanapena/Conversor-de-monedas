package modelo;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    private static final String API_KEY = "9996a9bbc26545f7de0afc3927d01073";
    private static final String URL =
            "http://data.fixer.io/api/latest?access_key=" + API_KEY;

    private final Gson gson = new Gson();

    public RespuestaFixer obtenerTasas() {

        URI direccion = URI.create(URL);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(response.body(), RespuestaFixer.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error consultando la API de Fixer", e);
        }
    }
}
