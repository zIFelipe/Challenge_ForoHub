package Alura_Latam.Challenge_Foro_Hub.model.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarRespuesta(
        @NotBlank String mensaje,
        @NotNull Long usuario_id,
        @NotNull Long topico_id
) {
}
