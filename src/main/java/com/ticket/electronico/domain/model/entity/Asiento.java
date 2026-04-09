// domain/model/entity/Asiento.java
package com.ticket.electronico.domain.model.entity;

import com.ticket.electronico.domain.model.valueobject.ClaseAsiento;
import com.ticket.electronico.domain.model.valueobject.NumeroAsiento;
import com.ticket.electronico.domain.model.valueobject.TipoAsiento;

import java.util.Objects;
import java.util.UUID;

public class Asiento {

    private UUID id;
    private UUID avionId;
    private NumeroAsiento numeroAsiento;
    private ClaseAsiento clase;
    private TipoAsiento tipoAsiento;
    private boolean isOcupado;

    public Asiento() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getAvionId() { return avionId; }
    public void setAvionId(UUID avionId) { this.avionId = avionId; }

    public NumeroAsiento getNumeroAsiento() { return numeroAsiento; }
    public void setNumeroAsiento(NumeroAsiento numeroAsiento) { this.numeroAsiento = numeroAsiento; }

    public ClaseAsiento getClase() { return clase; }
    public void setClase(ClaseAsiento clase) { this.clase = clase; }

    public TipoAsiento getTipoAsiento() { return tipoAsiento; }
    public void setTipoAsiento(TipoAsiento tipoAsiento) { this.tipoAsiento = tipoAsiento; }

    public boolean getIsOcupado() { return isOcupado; }
    public void setIsOcupado(boolean isOcupado) { this.isOcupado = isOcupado; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asiento)) return false;
        Asiento asiento = (Asiento) o;
        return Objects.equals(id, asiento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}