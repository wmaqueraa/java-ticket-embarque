package com.ticket.electronico.domain.model.entity;

import java.util.Objects;
import java.util.UUID;

public class Avion {

    private UUID id;
    private String modelo;
    private Integer capacidad;
    private UUID aerolineaId;

    public Avion() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public UUID getAerolineaId() { return aerolineaId; }
    public void setAerolineaId(UUID aerolineaId) { this.aerolineaId = aerolineaId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avion)) return false;
        Avion avion = (Avion) o;
        return Objects.equals(id, avion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
