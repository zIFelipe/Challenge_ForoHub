package Alura_Latam.Challenge_Foro_Hub.controller;

import Alura_Latam.Challenge_Foro_Hub.model.curso.Curso;
import Alura_Latam.Challenge_Foro_Hub.model.curso.CursoRepository;
import Alura_Latam.Challenge_Foro_Hub.model.topico.*;
import Alura_Latam.Challenge_Foro_Hub.model.topico.validaciones.ValidarActualizacionTopico;
import Alura_Latam.Challenge_Foro_Hub.model.topico.validaciones.ValidarListadoTopico;
import Alura_Latam.Challenge_Foro_Hub.model.topico.validaciones.ValidarRegistroTopico;
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

//Clase controlador de Topico para el CRUD de este ultimo en el insomnia
@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    List<ValidarRegistroTopico> validaciones;
    @Autowired
    List<ValidarActualizacionTopico> actualizarValidaciones;
    @Autowired
    List<ValidarListadoTopico> validarListadoTopicos;
    private Topico topico;
    private Curso curso;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosListadoTopico> registrarTopico(@RequestBody @Valid DatosRegistrarTopico datosRegistrarTopico,  UriComponentsBuilder uriComponentsBuilder) {
        validaciones.forEach(v-> v.validar(datosRegistrarTopico));
        curso = cursoRepository.findById(datosRegistrarTopico.curso_id()).get();
        Usuario usuario = usuarioRepository.findById(datosRegistrarTopico.usuario_id()).get();
        topico = new Topico(datosRegistrarTopico, usuario, curso);
        topicoRepository.save(topico);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosListadoTopico(topico));
    }
    @GetMapping("/todos")
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 10) Pageable pageable) {
        var paginacion = topicoRepository.findAll(pageable).map(DatosListadoTopico::new);
        return ResponseEntity.ok(paginacion);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicosActivos(@PageableDefault(size = 10) Pageable pageable) {
        var paginacion = topicoRepository.findByEstado(true, pageable).map(DatosListadoTopico::new);
        return ResponseEntity.ok(paginacion);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerTopico(@PathVariable Long id) {
        validarListadoTopicos.forEach(v->v.validar(id));
        topico = topicoRepository.getReferenceById(id);
        var atributosTopico = new DatosListadoTopico(topico);
        return ResponseEntity.ok(atributosTopico);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<DatosListadoTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        actualizarValidaciones.forEach(v->v.validar(datosActualizarTopico));
        topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarTopico(datosActualizarTopico);
        var atributosTopico = new DatosListadoTopico(topico);
        return ResponseEntity.ok(atributosTopico);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoTopico> eliminarTopico(@PathVariable Long id) {
        topico = topicoRepository.getReferenceById(id);
        topico.eliminarTopico();
        System.out.println("******Eliminado con exito******");
        topicoRepository.save(topico);
        return ResponseEntity.noContent().build();
    }
}
