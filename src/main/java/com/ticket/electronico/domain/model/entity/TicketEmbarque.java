package com.ticket.electronico.domain.model.entity;



import com.ticket.electronico.domain.model.valueobject.EstadoTicket;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TicketEmbarque {

    private UUID id;
    private String numeroTicket;
    private String puertaEmbarque;
    private String grupoEmbarque;
    private LocalDateTime horaEmbarque;
    private EstadoTicket estado;
    private UUID vueloId;
    public TicketEmbarque() {
    }

    public TicketEmbarque(UUID id, String numeroTicket) {
        this.id = id;
        this.numeroTicket = numeroTicket;
    }

    // GETTERS Y SETTERS

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(String numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public String getPuertaEmbarque() {
        return puertaEmbarque;
    }

    public void setPuertaEmbarque(String puertaEmbarque) {
        this.puertaEmbarque = puertaEmbarque;
    }

    public String getGrupoEmbarque() {
        return grupoEmbarque;
    }

    public void setGrupoEmbarque(String grupoEmbarque) {
        this.grupoEmbarque = grupoEmbarque;
    }

    public LocalDateTime getHoraEmbarque() {
        return horaEmbarque;
    }

    public void setHoraEmbarque(LocalDateTime horaEmbarque) {
        this.horaEmbarque = horaEmbarque;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    public UUID getVueloId() {return this.vueloId; }
    public void setVueloId(UUID id) {this.vueloId = id; }

    // MÉTODOS DE NEGOCIO

    public void cambiarEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    public boolean estaActivo() {

        return this.estado == EstadoTicket.ACTIVO;
    }

    // EQUALS Y HASHCODE (basado en ID)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketEmbarque that = (TicketEmbarque) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}