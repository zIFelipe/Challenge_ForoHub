package Alura_Latam.Challenge_Foro_Hub.model.topico;

import Alura_Latam.Challenge_Foro_Hub.model.curso.Categoria;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCracion,
        Boolean estado,
        String usuario,
        String curso,
        Categoria categoria) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                topico.getAutor().getLogin(),
                topico.getCurso().getTitulo(), topico.getCurso().getCategoria());
    }

}
