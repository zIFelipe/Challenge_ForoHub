package Alura_Latam.Challenge_Foro_Hub.model.usuarios;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Entity(name = "Usuario")
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
//Entidad usuario y encargada del login para la obtencion del token
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String clave;
    private String email;
    private Boolean activo;


    public void registrarUsuario(DatosRegistrarUsuario datos, String claveEncriptada) {
        this.login = datos.login();
        this.clave = claveEncriptada;
        this.email = datos.email();
        this.activo = true;
    }

    public void actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario, String claveEncriptada) {
        if(datosActualizarUsuario.clave() != null){
            this.clave = claveEncriptada;
        }
        if(datosActualizarUsuario.login() != null){
            this.login = datosActualizarUsuario.login();
        }
        if(datosActualizarUsuario.email() != null) {
            this.email = datosActualizarUsuario.email();
        }
        if(datosActualizarUsuario.activo() != null){
            this.activo = datosActualizarUsuario.activo();
        }
    }
    public void eliminarUsuario() {
        this.activo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
