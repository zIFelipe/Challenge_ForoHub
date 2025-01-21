package Alura_Latam.Challenge_Foro_Hub.model.respuesta.validaciones;

public interface ValidarListadoRespuesta {
    void validarBusquedaId(Long id);
    void validarBusquedaPorTopicoId(Long id);
    void validarBusquedaUsuario(Long id);
}
