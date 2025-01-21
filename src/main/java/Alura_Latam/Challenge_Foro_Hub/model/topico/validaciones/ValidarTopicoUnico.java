package Alura_Latam.Challenge_Foro_Hub.model.topico.validaciones;


import Alura_Latam.Challenge_Foro_Hub.model.topico.DatosRegistrarTopico;
import Alura_Latam.Challenge_Foro_Hub.model.topico.TopicoRepository;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarTopicoUnico implements ValidarRegistroTopico{

    @Autowired
    private TopicoRepository topicoRepository;
    @Override
    public void validar(DatosRegistrarTopico topico){
        var topicoDoble = topicoRepository.existsByTituloAndMensaje(topico.titulo(), topico.mensaje());
        if(topicoDoble){
            try {
                throw new ValidationException("Ya hay un Topico con el mismo titulo y mensaje");
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
