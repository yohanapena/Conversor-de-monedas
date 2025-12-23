package modelo;

import java.util.Map;

public record RespuestaFixer(
        boolean success,
        String base,
        Map<String, Double> rates
) {}
