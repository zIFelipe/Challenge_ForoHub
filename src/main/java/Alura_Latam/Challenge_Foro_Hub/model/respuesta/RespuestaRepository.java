package Alura_Latam.Challenge_Foro_Hub.model.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//repositorio respuesta para la comunicacion entre la base de datos y la aplicacion
@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    Page<Respuesta> findByUsuarioId(Long usuario, Pageable paginacion);
    Page<Respuesta> findByTopicoId(Long topico, Pageable paginacion);
    Respuesta getReferenceById(Long id);
}
