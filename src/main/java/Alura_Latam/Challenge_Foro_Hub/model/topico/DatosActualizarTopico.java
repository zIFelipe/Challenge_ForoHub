package Alura_Latam.Challenge_Foro_Hub.model.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id,
                                    String titulo,
                                    String mensaje,
                                    Boolean estado) {
}
