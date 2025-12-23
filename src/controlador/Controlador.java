package controlador;

import modelo.ConsultaMoneda;
import modelo.RespuestaFixer;
import modelo.ConsultaConversion;
import modelo.HistorialConsultas;

import java.time.LocalDateTime;

public class Controlador {

    private final ConsultaMoneda consultaMoneda;
    private final HistorialConsultas historial;

    public Controlador() {
        this.consultaMoneda = new ConsultaMoneda();
        this.historial = new HistorialConsultas();
    }

    public double convertir(
            double monto,
            String origen,
            String destino
    ) {

        RespuestaFixer respuesta = consultaMoneda.obtenerTasas();

        if (!respuesta.success()) {
            throw new RuntimeException("Error en la respuesta de la API");
        }

        if (!respuesta.rates().containsKey(origen)
                || !respuesta.rates().containsKey(destino)) {
            throw new IllegalArgumentException("Moneda no válida");
        }

        double tasaOrigen = respuesta.rates().get(origen);
        double tasaDestino = respuesta.rates().get(destino);

        double enEuros = monto / tasaOrigen;
        double resultado = enEuros * tasaDestino;

        historial.guardar(
                new ConsultaConversion(
                        LocalDateTime.now().toString(),
                        origen,
                        destino,
                        monto,
                        resultado
                )
        );

        return resultado;
    }
}
