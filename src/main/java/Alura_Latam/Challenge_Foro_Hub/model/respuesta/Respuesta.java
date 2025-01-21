package Alura_Latam.Challenge_Foro_Hub.model.respuesta;

import Alura_Latam.Challenge_Foro_Hub.model.topico.Topico;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Getter
@Setter
//Entidad respuesta
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    private Boolean cerrado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;


    public Respuesta() {
    }

    public Respuesta(DatosRegistrarRespuesta datosRegistrarRespuesta, Usuario usuario, Topico topico) {
        this.mensaje = datosRegistrarRespuesta.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.cerrado = false;
        this.usuario = usuario;
        this.topico = topico;
    }
    public void eliminarRespuesta(){
        this.cerrado = true;
    }


    public Long getId() {
        return id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaActual() {
        return fechaCreacion;
    }


    public Boolean getCerrado() {
        return cerrado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Topico getTopico() {
        return topico;
    }
}
