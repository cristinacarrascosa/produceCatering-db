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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "salon")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SalonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_espacio")
    private EspacioEntity espacio;

    @OneToMany(mappedBy = "salon", fetch = FetchType.LAZY)
    private final List<ServicioEntity> servicios;

    public SalonEntity() {
        this.servicios = new ArrayList<>();
    }

    public SalonEntity(Long id) {
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

    public EspacioEntity getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioEntity espacio) {
        this.espacio = espacio;
    }

    public int getServicios() {
        return servicios.size();
    }

    @PreRemove
    public void nullify() {
       this.servicios.forEach(servicios -> servicios.setSalon(null));
    }
    
}
