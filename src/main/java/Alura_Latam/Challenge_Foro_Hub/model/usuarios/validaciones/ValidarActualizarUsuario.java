package Alura_Latam.Challenge_Foro_Hub.model.usuarios.validaciones;

import Alura_Latam.Challenge_Foro_Hub.model.usuarios.DatosActualizarUsuario;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.UsuarioRepository;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarActualizarUsuario implements ValidarActualizacionUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void validar(DatosActualizarUsuario usuario){
        var usuarioDoble = usuarioRepository.findByLogin(usuario.login());
        if(usuarioDoble != null){
            throw new jakarta.validation.ValidationException("Este usuario ya se encuentra registrado");

        }
        if(usuario.email() != null){
            var emailDoble = usuarioRepository.findByEmail(usuario.email());
            if(emailDoble != null){
                try {
                    throw new ValidationException("Ya existe un usuario con este correo registrado");
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
