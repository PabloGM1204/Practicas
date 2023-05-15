package com.serbatic.facturas.accessingData;

import jakarta.persistence.*;

@Entity
public class DemArt {
    @lombok.Setter
    @lombok.Getter
    @EmbeddedId
    DemArtKey id;

    @lombok.Setter
    @lombok.Getter
    @ManyToOne(cascade = CascadeType.REFRESH)
    @MapsId("idDemand")
    @JoinColumn(name="id_demand")
    Demand demand;


    @lombok.Setter
    @lombok.Getter
    @ManyToOne(cascade = CascadeType.REFRESH) // Sirve por si en algun momento queremos añadir la funcion de cambiar el ID del pedido (por ejemplo para reasignar un
    //                                          pedido a otro usuario).
    @MapsId("idArt") //Sirve para indicar que forma parte de una PK compuesta
    @JoinColumn(name="id_art")
    Article article;
    @lombok.Setter
    @lombok.Getter
    private int amount;

    public DemArt(Article art, Demand dem){
        this.article=art;
        this.demand=dem;
    }

    public DemArt() {

    }

}
