package Alura_Latam.Challenge_Foro_Hub.model.curso;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Curso")
@Table(name = "cursos")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Getter
@Setter
//Entidad curso
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Boolean activo;

    public Curso() {
    }

    public Curso(DatosRegistrarCurso datosRegistrarCurso){
        this.titulo = datosRegistrarCurso.titulo();
        this.categoria = datosRegistrarCurso.categoria();
        this.activo = true;
    }

    public void actualizarDatos (DatosActualizarCurso datosActualizarCurso){
        if(datosActualizarCurso.titulo() != null){
            this.titulo = datosActualizarCurso.titulo();
        }else if(datosActualizarCurso.categoria() != null){
            this.categoria = datosActualizarCurso.categoria();
        }else if(datosActualizarCurso.activo() != null){
            this.activo = datosActualizarCurso.activo();
        }
    }

    public void desactivarCurso(){
        this.activo = false;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Boolean getActivo() {
        return activo;
    }
}
