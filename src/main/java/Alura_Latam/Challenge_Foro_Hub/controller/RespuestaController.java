package Alura_Latam.Challenge_Foro_Hub.controller;

import Alura_Latam.Challenge_Foro_Hub.model.respuesta.*;
import Alura_Latam.Challenge_Foro_Hub.model.respuesta.validaciones.ValidarListadoRespuesta;
import Alura_Latam.Challenge_Foro_Hub.model.respuesta.validaciones.ValidarRegistroRespuesta;
import Alura_Latam.Challenge_Foro_Hub.model.topico.Topico;
import Alura_Latam.Challenge_Foro_Hub.model.topico.TopicoRepository;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.Usuario;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
//Clase controlador de Respuesta para el CRUD(incompleto) de este ultimo en el insomnia
@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    List<ValidarRegistroRespuesta> validaciones;
    @Autowired
    List<ValidarListadoRespuesta> validarListadoRespuestas;
    @Autowired
    private TopicoRepository topicoRepository;
    private Respuesta respuesta;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosListadoRespuesta> registrarRespuesta(@RequestBody @Valid DatosRegistrarRespuesta datosRegistrarRespuesta, UriComponentsBuilder uriBuilder) {
        validaciones.forEach(v -> v.validar(datosRegistrarRespuesta));
        Usuario usuario = usuarioRepository.getReferenceById(datosRegistrarRespuesta.usuario_id());
        Topico topico = topicoRepository.findById(datosRegistrarRespuesta.topico_id()).get();
        var respuestas = new Respuesta(datosRegistrarRespuesta, usuario, topico);
        respuestaRepository.save(respuestas);
        var uri = uriBuilder.path("/respuestas/{id}").buildAndExpand(respuestas.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosListadoRespuesta(respuestas));
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<DatosListadoRespuesta> obtenerRespuesta(@PathVariable Long id) {
        validarListadoRespuestas.forEach(v -> v.validarBusquedaId(id));
        respuesta = respuestaRepository.getReferenceById(id);
        var atributosRespuesta = new DatosListadoRespuesta(respuesta);
        return ResponseEntity.ok(atributosRespuesta);
    }

    @GetMapping("/topico/{topico_id}")
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestasTopico(@PageableDefault(size = 10) @PathVariable Long topico_id, Pageable pageable) {
        validarListadoRespuestas.forEach(v -> v.validarBusquedaPorTopicoId(topico_id));
        var paginacion = respuestaRepository.findByTopicoId(topico_id, pageable).map(DatosListadoRespuesta::new);
        return ResponseEntity.ok(paginacion);
    }

    @GetMapping("/usuario/{usuario_id}")
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestasUsuario(@PageableDefault(size = 10) @PathVariable Long usuario_id, Pageable pageable) {
        validarListadoRespuestas.forEach(v -> v.validarBusquedaUsuario(usuario_id));
        var paginacion = respuestaRepository.findByUsuarioId(usuario_id, pageable).map(DatosListadoRespuesta::new);
        return ResponseEntity.ok(paginacion);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuesta>> listarRespuestasActivas(@PageableDefault(size = 10) Pageable pageable){
        var paginacion = respuestaRepository.findAll(pageable).map(DatosListadoRespuesta::new);
        return ResponseEntity.ok(paginacion);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoRespuesta> eliminarRespuesta(@PathVariable Long id) {
        respuesta = respuestaRepository.getReferenceById(id);
        respuesta.eliminarRespuesta();
        System.out.println("******Eliminado con exito******");
        respuestaRepository.save(respuesta);
        return ResponseEntity.noContent().build();

    }
}
