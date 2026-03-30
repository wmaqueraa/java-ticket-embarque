package com.ticket.electronico.domain.model.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Pasajero {

    private UUID id;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String nroDocumento;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private String email;
    private String telefono;

    public Pasajero() {
    }

    public Pasajero(UUID id, String nombres, String apellidos, String tipoDocumento, String nroDocumento) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
    }

    // GETTERS Y SETTERS

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // MÉTODOS DE NEGOCIO

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }

    // EQUALS Y HASHCODE (basado en ID)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return Objects.equals(id, pasajero.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}