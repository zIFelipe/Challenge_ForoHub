package Alura_Latam.Challenge_Foro_Hub.model.topico.validaciones;

import Alura_Latam.Challenge_Foro_Hub.model.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarListarTopico implements ValidarListadoTopico {

    @Autowired
    private TopicoRepository topicoRepository;
    @Override
    public void validar(Long id){
        var topico = topicoRepository.existsById(id);
        if(!topico){
            try {
                throw new jakarta.xml.bind.ValidationException("Este topico no existe en los registros");
            } catch (jakarta.xml.bind.ValidationException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
