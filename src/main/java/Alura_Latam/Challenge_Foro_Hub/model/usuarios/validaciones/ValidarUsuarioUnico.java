package Alura_Latam.Challenge_Foro_Hub.model.usuarios.validaciones;

import Alura_Latam.Challenge_Foro_Hub.model.usuarios.DatosRegistrarUsuario;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarUsuarioUnico implements ValidarRegistroUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void validar(DatosRegistrarUsuario usuario){
        var usuarioDoble = usuarioRepository.findByLogin(usuario.login());
        if(usuarioDoble != null){
            throw new ValidationException("Este usuario ya se encuentra registrado");

        }
        var emailDoble = usuarioRepository.findByEmail(usuario.login());
        if(emailDoble != null){
            throw new ValidationException("Ya existe un usuario con este correo registrado");
        }
    }
}
