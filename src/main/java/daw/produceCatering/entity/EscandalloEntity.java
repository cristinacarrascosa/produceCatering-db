package daw.produceCatering.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name = "escandallo")
public class EscandalloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;

    @OneToMany(mappedBy = "escandallo", fetch = FetchType.LAZY)
    private final List<LineaServicioEntity> lineasServicio;

    @OneToMany(mappedBy = "escandallo", fetch = FetchType.LAZY)
    private final List<LineaEscandalloEntity> lineasEscandallo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipoPlato")
    private TipoPlatoEntity tipoPlato;

    public EscandalloEntity() {
        this.lineasServicio = new ArrayList<>();
        this.lineasEscandallo = new ArrayList<>();
    }

    public EscandalloEntity(Long id) {
        this.lineasServicio = new ArrayList<>();
        this.lineasEscandallo = new ArrayList<>();
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

    public TipoPlatoEntity getTipoPlato() {
        return tipoPlato;
    }

    public void setTipoPlato(TipoPlatoEntity tipoPlato) {
        this.tipoPlato = tipoPlato;
    }

    public int getLineasServicio() {
        return lineasServicio.size();
    }

    public int getLineasEscandallo() {
        return lineasEscandallo.size();
    }

    @PreRemove
    public void nullify() {
        this.lineasServicio.forEach(c -> c.setServicio(null));
        this.lineasEscandallo.forEach(c -> c.setEscandallo(null));
    }
}
