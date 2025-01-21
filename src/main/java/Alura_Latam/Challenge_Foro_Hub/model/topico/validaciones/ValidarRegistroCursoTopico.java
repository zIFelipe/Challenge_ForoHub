package Alura_Latam.Challenge_Foro_Hub.model.topico.validaciones;

import Alura_Latam.Challenge_Foro_Hub.model.curso.CursoRepository;
import Alura_Latam.Challenge_Foro_Hub.model.topico.DatosRegistrarTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarRegistroCursoTopico implements ValidarRegistroTopico{

    @Autowired
    private CursoRepository cursoRepository;
    @Override
    public void validar(DatosRegistrarTopico topico) {
        var curso = cursoRepository.existsById(topico.curso_id());
        if(!curso){
            throw new ValidationException("Curso no encontrado en los registros");
        }
        var cursoActivo = cursoRepository.findById(topico.curso_id()).orElseThrow(() -> new ValidationException("Curso no encontrado"));
        if(!cursoActivo.getActivo()){
            throw new ValidationException("Curso no encontrado en los registros o no habilitado");
        }
    }
}
