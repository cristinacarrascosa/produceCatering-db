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
@Table(name = "tipoplato")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TipoPlatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "tipoplato", fetch = FetchType.LAZY)
    private final List<EscandalloEntity> escandallos;

    public TipoPlatoEntity() {
        this.escandallos = new ArrayList<>();
    }

    public TipoPlatoEntity(Long id) {
        this.escandallos = new ArrayList<>();
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

    public int getNumEscandallos() {
        return escandallos.size();
    }

    @PreRemove
    public void nullify() {
       this.escandallos.forEach(escandallo -> escandallo.setTipoPlato(null));
    }
    
}
