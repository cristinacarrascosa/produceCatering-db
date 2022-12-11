package daw.produceCatering.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.PreRemove;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitialize", "handler"})
public class UsuarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipousuario")
    private TipousuarioEntity tipousuario;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private final List<ServicioEntity> servicios;


    public UsuarioEntity() {
        this.servicios = new ArrayList<>();
        
    }

    public UsuarioEntity(Long id) {
        this.servicios = new ArrayList<>();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipousuarioEntity getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipousuarioEntity tipousuario) {
        this.tipousuario = tipousuario;
    }

    public int getServicios() {
        return servicios.size();
    }

    // para que al borrar ponga a null
    @PreRemove
    public void nullify() {
        this.servicios.forEach(c -> c.setUsuario(null));
    }
}
