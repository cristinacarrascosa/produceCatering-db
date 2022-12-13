package daw.produceCatering.entity;

import javax.persistence.Column;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "servicio")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "fechaHora")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime fechahora;

    private int comensales;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_salon")
    private SalonEntity salon;

    @OneToMany(mappedBy = "servicio", fetch = FetchType.LAZY)
    private final List<LineaServicioEntity> lineasServicio;

    public ServicioEntity() {
        this.lineasServicio = new ArrayList<>();
    }

    public ServicioEntity(Long id) {
        this.lineasServicio = new ArrayList<>();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
    public LocalDateTime getFechaHora() {
        return fechahora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechahora = fechaHora;
    }

    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public SalonEntity getSalon() {
        return salon;
    }

    public void setSalon(SalonEntity salon) {
        this.salon = salon;
    }

    public int getLineasServicio() {
        return lineasServicio.size();
    }

    @PreRemove
    public void nullify() {
        this.lineasServicio.forEach(c -> c.setServicio(null));
    }
    




}
