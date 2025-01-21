package Alura_Latam.Challenge_Foro_Hub.controller;

import Alura_Latam.Challenge_Foro_Hub.model.curso.*;
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

//Clase controlador de cursos para el CRUD de este ultimo en el insomnia
@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursosController {

    @Autowired
    private CursoRepository cursoRepository;

    private Curso curso = new Curso();

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRegistrarCurso> registrarCurso(@RequestBody @Valid DatosRegistrarCurso datosCurso, UriComponentsBuilder uriComponentsBuilder) {
        curso = cursoRepository.save(new Curso(datosCurso));
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRegistrarCurso(curso.getTitulo(), curso.getCategoria()));
    }
    @GetMapping("/todos")
    public ResponseEntity<Page<DatosListadoCurso>> listarCursos(@PageableDefault Pageable paginacion) {
        var pagina = cursoRepository.findAll(paginacion).map(DatosListadoCurso::new);
        return ResponseEntity.ok(pagina);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoCurso> listarPorId(@PathVariable Long id) {
        curso = cursoRepository.getReferenceById(id);
        var datosCurso = new DatosListadoCurso(curso);
        return ResponseEntity.ok(datosCurso);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoCurso>> listarCursosActivos(@PageableDefault(size = 10) Pageable pageable){
        var paginacion = cursoRepository.findAllByActivo(true, pageable).map(DatosListadoCurso::new);
        return ResponseEntity.ok(paginacion);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosListadoCurso> actualizacionCurso(@RequestBody @Valid DatosActualizarCurso datosCurso) {
        curso = cursoRepository.getReferenceById(datosCurso.id());
        curso.actualizarDatos(datosCurso);
        var datoCurso = new DatosListadoCurso(curso);
        return ResponseEntity.ok(datoCurso);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        curso = cursoRepository.getReferenceById(id);
        curso.desactivarCurso();
        System.out.println("******Eliminado con exito******");
        cursoRepository.save(curso);
        return ResponseEntity.noContent().build();
    }

}
