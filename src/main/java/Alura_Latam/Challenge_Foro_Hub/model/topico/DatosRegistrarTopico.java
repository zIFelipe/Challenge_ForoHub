package Alura_Latam.Challenge_Foro_Hub.model.topico;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long usuario_id,
        @NotNull Long curso_id) {
}
