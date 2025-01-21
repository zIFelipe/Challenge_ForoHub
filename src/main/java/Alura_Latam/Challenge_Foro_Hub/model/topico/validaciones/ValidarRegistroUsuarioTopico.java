package Alura_Latam.Challenge_Foro_Hub.model.topico.validaciones;

import Alura_Latam.Challenge_Foro_Hub.model.topico.DatosRegistrarTopico;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.UsuarioRepository;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarRegistroUsuarioTopico implements ValidarRegistroTopico{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void validar(DatosRegistrarTopico topico){
        var usuario = usuarioRepository.existsById(topico.usuario_id());
        if(!usuario){
            try {
                throw new ValidationException("Este usuario no existe en los registros");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
        var usuarioActivo = usuarioRepository.findById(topico.usuario_id());
        if(!usuarioActivo.get().getActivo()){
            try {
                throw new ValidationException("Este usuario no se encuentra activo en este momento");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
