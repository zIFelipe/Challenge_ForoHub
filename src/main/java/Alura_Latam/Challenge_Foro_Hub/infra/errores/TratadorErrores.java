package Alura_Latam.Challenge_Foro_Hub.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarError404() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<String> tratarErrorValidacion(ValidacionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<?>> tratandoError400(MethodArgumentNotValidException e){
        var erro400 = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(erro400);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> tratarErrorBodyHandlerInvalido(Exception e) {
        return ResponseEntity.badRequest().body("Faltan datos en el cuerpo del Json para completar la solicitud");
    }
    public record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError errores){
            this(errores.getField(), errores.getDefaultMessage());
        }
    }

}
