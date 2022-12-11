package daw.produceCatering.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.FetchType;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tipousuario")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TipousuarioEntity {

    @Id
    private Long id;

    private String tipo;

    @OneToMany(mappedBy = "tipousuario", fetch = FetchType.LAZY)
    private final List<UsuarioEntity> usuarios;

    public TipousuarioEntity() {
        this.usuarios = new ArrayList<>();
    }

    public TipousuarioEntity(Long id, String tipo) {
        this.usuarios = new ArrayList<>();
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return this.tipo;
    }

    public int getUsuarios() {
        return usuarios.size();
    }

    @PreRemove
    public void nullify() {
        this.usuarios.forEach(c -> c.setTipousuario(null));
    }
    
}
