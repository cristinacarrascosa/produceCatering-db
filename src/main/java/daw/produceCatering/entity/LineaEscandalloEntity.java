package daw.produceCatering.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name = "lineaescandallo")
public class LineaEscandalloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_escandallo")
    private EscandalloEntity escandallo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_referencia")
    private ReferenciaEntity referencia;

    public LineaEscandalloEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EscandalloEntity getEscandallo() {
        return escandallo;
    }

    public void setEscandallo(EscandalloEntity escandallo) {
        this.escandallo = escandallo;
    }

    public ReferenciaEntity getReferencia() {
        return referencia;
    }

    public void setReferencia(ReferenciaEntity referencia) {
        this.referencia = referencia;
    }

    @PreRemove
    public void nullify() {

    }
}
