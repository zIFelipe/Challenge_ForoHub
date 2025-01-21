package Alura_Latam.Challenge_Foro_Hub.model.usuarios;

public record DatosListadoUsuario(
        Long id,
        String login,
        String email,
        Boolean activo
) {

    public DatosListadoUsuario (Usuario usuario){
        this(usuario.getId(),usuario.getLogin(),usuario.getEmail(), usuario.getActivo());
    }
}
