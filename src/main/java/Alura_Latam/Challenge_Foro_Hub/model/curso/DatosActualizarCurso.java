package Alura_Latam.Challenge_Foro_Hub.model.curso;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(@NotNull Long id,
                                   String titulo,
                                   Categoria categoria,
                                   Boolean activo) {
}
