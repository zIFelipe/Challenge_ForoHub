package Alura_Latam.Challenge_Foro_Hub.controller;

import Alura_Latam.Challenge_Foro_Hub.model.usuarios.*;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.validaciones.ValidarActualizacionUsuario;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.validaciones.ValidarListadoUsuario;
import Alura_Latam.Challenge_Foro_Hub.model.usuarios.validaciones.ValidarRegistroUsuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
//Clase controlador de Usuario para el CRUD de este ultimo en el insomnia
@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    List<ValidarRegistroUsuario> validaciones;
    @Autowired
    List<ValidarActualizacionUsuario> actualizarValidaciones;
    @Autowired
    List<ValidarListadoUsuario> listadoValidaciones;

    private final BCryptPasswordEncoder contrasenaEncriptada = new BCryptPasswordEncoder();
    private Usuario usuario = new Usuario();

    @PostMapping
    @Transactional
    public ResponseEntity<DatosListadoUsuario> registrarUsuario(@RequestBody @Valid DatosRegistrarUsuario datosRegistrarUsuario, UriComponentsBuilder uriComponentsBuilder) {
        validaciones.forEach(v->v.validar(datosRegistrarUsuario));
        String contrasenaHash = contrasenaEncriptada.encode(datosRegistrarUsuario.clave());
        usuario.registrarUsuario(datosRegistrarUsuario, contrasenaHash);
        usuarioRepository.save(usuario);
        var uriComponents = uriComponentsBuilder.path("/usuarios/{username}").buildAndExpand(usuario.getLogin());
        return ResponseEntity.created(uriComponents.toUri()).body(new DatosListadoUsuario(usuario));//puede cambiarse la estructura
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuarios(@PageableDefault(size = 10) Pageable pageable) {
        var paginacion = usuarioRepository.findAll(pageable).map(DatosListadoUsuario::new);
        return ResponseEntity.ok(paginacion);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listarUsuariosActivos(@PageableDefault(size = 10) Pageable pageable){
        var paginacion = usuarioRepository.findAllByActivo(true, pageable).map(DatosListadoUsuario::new);
        return ResponseEntity.ok(paginacion);
    }
    @GetMapping("/nombre/{login}")
    public ResponseEntity<DatosListadoUsuario> listarUsuariosPorNombre(@PathVariable String login) {
        listadoValidaciones.forEach(v->v.validarNombre(login));
        usuario = (Usuario) usuarioRepository.findByLogin(login);
        var atributosUsuario = new DatosListadoUsuario(usuario);
        return ResponseEntity.ok(atributosUsuario);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<DatosListadoUsuario> listarUsuariosPorId(@PathVariable Long id) {
        listadoValidaciones.forEach(v->v.validar(id));
        usuario = usuarioRepository.getReferenceById(id);
        var atributosUsuario = new DatosListadoUsuario(usuario);
        return ResponseEntity.ok(atributosUsuario);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DatosListadoUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario, @PathVariable Long id){
        actualizarValidaciones.forEach(v->v.validar(datosActualizarUsuario));
        usuario = usuarioRepository.getReferenceById(id);
        String contrasenaHash = contrasenaEncriptada.encode(datosActualizarUsuario.clave());
        usuario.actualizarUsuario(datosActualizarUsuario, contrasenaHash);
        var atributosUsuario = new DatosListadoUsuario(usuario);
        return ResponseEntity.ok(atributosUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuario = usuarioRepository.getReferenceById(id);
        usuario.eliminarUsuario();
        System.out.println("******Eliminado con exito******");
        usuarioRepository.save(usuario);
        return ResponseEntity.noContent().build();
    }

}
