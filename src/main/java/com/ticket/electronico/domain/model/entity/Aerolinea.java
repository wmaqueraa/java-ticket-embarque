package com.ticket.electronico.domain.model.entity;


import com.ticket.electronico.domain.model.valueobject.CodigoIata;

import java.util.Objects;
import java.util.UUID;

public class Aerolinea {

    private UUID id;
    private String nombre;
    private CodigoIata codigoIata;
    private String paisOrigen;

    public Aerolinea() {}

    public Aerolinea(UUID id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public CodigoIata getCodigoIata() { return codigoIata; }
    public void setCodigoIata(CodigoIata codigoIata) { this.codigoIata = codigoIata; }

    public String getPaisOrigen() { return paisOrigen; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aerolinea)) return false;
        Aerolinea that = (Aerolinea) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}