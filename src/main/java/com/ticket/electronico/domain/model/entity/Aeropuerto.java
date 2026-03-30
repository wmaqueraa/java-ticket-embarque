package com.ticket.electronico.domain.model.entity;


import java.util.Objects;
import java.util.UUID;

public class Aeropuerto {

    private UUID id;
    private String codigoIata;
    private String nombre;
    private String ciudad;
    private String pais;

    public Aeropuerto() {}

    public Aeropuerto(UUID id, String codigoIata, String nombre) {
        this.id = id;
        this.codigoIata = codigoIata;
        this.nombre = nombre;
    }

    // GETTERS Y SETTERS

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigoIata() {
        return codigoIata;
    }

    public void setCodigoIata(String codigoIata) {
        this.codigoIata = codigoIata;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    // MÉTODOS DE NEGOCIO

    public String getDescripcion() {
        return nombre + " (" + codigoIata + ")";
    }

    // EQUALS Y HASHCODE

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeropuerto that = (Aeropuerto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}