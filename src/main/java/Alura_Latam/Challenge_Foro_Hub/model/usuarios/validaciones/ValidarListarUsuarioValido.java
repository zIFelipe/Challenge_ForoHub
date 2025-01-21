package Alura_Latam.Challenge_Foro_Hub.model.usuarios.validaciones;

import Alura_Latam.Challenge_Foro_Hub.model.usuarios.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarListarUsuarioValido implements ValidarListadoUsuario{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void validar(Long id){
        var usuario = usuarioRepository.existsById(id);
        if(!usuario){
            try {
                throw new jakarta.xml.bind.ValidationException("Este usuario no existe en los registros");
            } catch (jakarta.xml.bind.ValidationException e) {
                throw new RuntimeException(e);
            }
        }

    }
    @Override
    public void validarNombre(String nombre){
        var usuario = usuarioRepository.findByLogin(nombre);
        if(usuario == null){
            throw new ValidationException("Este usuario no existe en los registros");
        }

    }
}
