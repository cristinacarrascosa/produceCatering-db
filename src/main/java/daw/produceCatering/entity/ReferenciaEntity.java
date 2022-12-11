package daw.produceCatering.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "referencia")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ReferenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "referencia", fetch = FetchType.LAZY)
    private final List<LineaEscandalloEntity> lineasEscandallo;

    public ReferenciaEntity() {
        this.lineasEscandallo = new ArrayList<>();
    }

    public ReferenciaEntity(Long id) {
        this.lineasEscandallo = new ArrayList<>();
        this.id = id;
    }

    public int getLineasEscandallo() {
        return lineasEscandallo.size();
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

    @PreRemove
    public void nullify() {
        this.lineasEscandallo.forEach(c -> c.setEscandallo(null));
    }
    
}
