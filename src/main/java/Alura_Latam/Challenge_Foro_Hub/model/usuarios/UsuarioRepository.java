package Alura_Latam.Challenge_Foro_Hub.model.usuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
//repositorio usuairo para la comunicacion entre la base de datos y la aplicacion
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String username);
    UserDetails findByEmail(String email);
    Page<Usuario> findAll(Pageable pageable);
    Page<Usuario> findAllByActivo(boolean activo, Pageable pageable);
    Usuario getReferenceById(Long id);

}
