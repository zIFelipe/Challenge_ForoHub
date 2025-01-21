package Alura_Latam.Challenge_Foro_Hub.model.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistrarUsuario(@NotBlank String login, @NotBlank String clave, @NotBlank @Email String email) {
}
