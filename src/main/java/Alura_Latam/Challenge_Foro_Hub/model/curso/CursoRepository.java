package Alura_Latam.Challenge_Foro_Hub.model.curso;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//repositorio curso para la comunicacion entre la base de datos y la aplicacion
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    Page<Curso> findAllByActivo(Boolean activo, Pageable pageable);
}
