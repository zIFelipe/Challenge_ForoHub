package Alura_Latam.Challenge_Foro_Hub.model.respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuesta (
        Long id,
        Long usuarioId,
        Long topicoId,

        LocalDateTime fechaActual,

        Boolean cerrado,

        String mensaje,
        String nombreUsuario,
        String topico

){

    public DatosListadoRespuesta(Respuesta respuesta){
        this(respuesta.getId(), respuesta.getUsuario().getId(), respuesta.getTopico().getId(),
                respuesta.getFechaActual(), respuesta.getCerrado(),
                respuesta.getMensaje(), respuesta.getUsuario().getLogin(), respuesta.getTopico().getTitulo());
    }



}
