package Alura_Latam.Challenge_Foro_Hub.model.respuesta.validaciones;

import Alura_Latam.Challenge_Foro_Hub.model.respuesta.DatosRegistrarRespuesta;
import Alura_Latam.Challenge_Foro_Hub.model.topico.TopicoRepository;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarRegistroRespuestaTopica implements ValidarRegistroRespuesta {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistrarRespuesta respuesta) {
        var topicoExiste = topicoRepository.existsById(respuesta.topico_id());
        if(!topicoExiste){
            try {
                throw new ValidationException("Este topico no existe o no se ha encontrado en los registros");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
        var topico = topicoRepository.findById(respuesta.topico_id());
        if(!topico.get().getEstado()){
            try {
                throw new ValidationException("Este topico no se encuentra habilitado en este momento");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
