package modelo;

import java.time.LocalDateTime;

public record ConsultaConversion(
        String fecha,
        String origen,
        String destino,
        double monto,
        double resultado
) {}
