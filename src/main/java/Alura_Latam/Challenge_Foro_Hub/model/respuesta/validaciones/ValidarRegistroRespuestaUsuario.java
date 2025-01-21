package Alura_Latam.Challenge_Foro_Hub.model.respuesta.validaciones;

import Alura_Latam.Challenge_Foro_Hub.model.respuesta.DatosRegistrarRespuesta;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.UsuarioRepository;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarRegistroRespuestaUsuario implements ValidarRegistroRespuesta {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(DatosRegistrarRespuesta respuesta){
        var usuario = usuarioRepository.existsById(respuesta.usuario_id());
        if(!usuario){
            try {
                throw new ValidationException("Este usuario no existe o no se ha encontrado en los registros");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
        var usuarioActivo = usuarioRepository.findById(respuesta.usuario_id());
        if(!usuarioActivo.get().getActivo()){
            try {
                throw new ValidationException("El usuario no se encuentra activo en este momento");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
