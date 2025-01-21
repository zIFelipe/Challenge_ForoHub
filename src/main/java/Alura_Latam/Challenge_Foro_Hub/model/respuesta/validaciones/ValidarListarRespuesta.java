package Alura_Latam.Challenge_Foro_Hub.model.respuesta.validaciones;

import Alura_Latam.Challenge_Foro_Hub.model.respuesta.RespuestaRepository;
import Alura_Latam.Challenge_Foro_Hub.model.topico.TopicoRepository;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarListarRespuesta implements ValidarListadoRespuesta{

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void validarBusquedaId(Long id){
        var respuesta = respuestaRepository.existsById(id);
        if(!respuesta){
            try {
                throw new jakarta.xml.bind.ValidationException("Esta respuesta no existe en los registros");
            } catch (jakarta.xml.bind.ValidationException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void validarBusquedaPorTopicoId(Long id){
        var topico = topicoRepository.existsById(id);
        if(!topico){
            try {
                throw new jakarta.xml.bind.ValidationException("No existe una respuesta con el topico buscado en los registros");
            } catch (jakarta.xml.bind.ValidationException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void validarBusquedaUsuario(Long id){
        var usuario = usuarioRepository.existsById(id);
        if(!usuario){
            try {
                throw new jakarta.xml.bind.ValidationException("No existe una respuesta con el usuario buscado en los registros");
            } catch (jakarta.xml.bind.ValidationException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
