package Alura_Latam.Challenge_Foro_Hub.model.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistrarCurso(
        @NotBlank
        String titulo,
        @NotNull
        Categoria categoria
){
}
