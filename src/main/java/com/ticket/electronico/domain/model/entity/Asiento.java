package com.ticket.electronico.domain.model.entity;

import com.ticket.electronico.domain.model.valueobjects.NumeroAsiento;

import java.util.Objects;
import java.util.UUID;

public class Asiento {

    private UUID id;
    private UUID avionId;
    private NumeroAsiento numeroAsiento;
    private String clase;

    public Asiento() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getAvionId() { return avionId; }
    public void setAvionId(UUID avionId) { this.avionId = avionId; }

    public NumeroAsiento getNumeroAsiento() { return numeroAsiento; }
    public void setNumeroAsiento(NumeroAsiento numeroAsiento) { this.numeroAsiento = numeroAsiento; }

    public String getClase() { return clase; }
    public void setClase(String clase) { this.clase = clase; }

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