package Alura_Latam.Challenge_Foro_Hub.model.topico;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//repositorio topico para la comunicacion entre la base de datos y la aplicacion
@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAll(Pageable pageable);
    Page<Topico> findByEstado(Boolean estado, Pageable pageable);
    Boolean existsByTituloAndMensaje(String titulo, String mensaje);
}
