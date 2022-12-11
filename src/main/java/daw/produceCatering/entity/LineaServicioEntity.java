package daw.produceCatering.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "lineaservicio")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class LineaServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int pax;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_servicio")
    private ServicioEntity servicio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_escandallo")
    private EscandalloEntity escandallo;

    public LineaServicioEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public ServicioEntity getServicio() {
        return servicio;
    }

    public void setServicio(ServicioEntity servicio) {
        this.servicio = servicio;
    }

    public EscandalloEntity getEscandallo() {
        return escandallo;
    }

    public void setEscandallo(EscandalloEntity escandallo) {
        this.escandallo = escandallo;
    }

    @PreRemove
    public void nullify() {
        
    }
}
