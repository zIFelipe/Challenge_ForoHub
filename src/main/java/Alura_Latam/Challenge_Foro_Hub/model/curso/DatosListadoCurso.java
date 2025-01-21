package Alura_Latam.Challenge_Foro_Hub.model.curso;

public record DatosListadoCurso(Long id, String name, Categoria categoria, Boolean activo) {

    public DatosListadoCurso(Curso curso){
        this(curso.getId(),
                curso.getTitulo(),
                curso.getCategoria(),
                curso.getActivo());
    }
}
