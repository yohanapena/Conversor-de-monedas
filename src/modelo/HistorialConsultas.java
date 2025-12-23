package modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class HistorialConsultas {

    private static final Path ARCHIVO = Path.of("historial.json");
    private final Gson gson = new Gson();

    public void guardar(ConsultaConversion consulta) {

        List<ConsultaConversion> historial = leerHistorial();
        historial.add(consulta);

        try (Writer writer = Files.newBufferedWriter(ARCHIVO)) {
            gson.toJson(historial, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error guardando historial", e);
        }
    }

    private List<ConsultaConversion> leerHistorial() {

        if (!Files.exists(ARCHIVO)) {
            return new ArrayList<>();
        }

        try (Reader reader = Files.newBufferedReader(ARCHIVO)) {
            Type tipoLista = new TypeToken<List<ConsultaConversion>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo historial", e);
        }
    }
}
