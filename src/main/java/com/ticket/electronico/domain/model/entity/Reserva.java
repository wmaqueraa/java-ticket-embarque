package com.ticket.electronico.domain.model.entity;

import com.ticket.electronico.domain.model.valueobject.EstadoReserva;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Reserva {

    private UUID id;
    private String codigoReserva;
    private LocalDateTime fechaReserva;
    private EstadoReserva estado;
    private int correlativo;

    public Reserva() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCodigoReserva() { return codigoReserva; }
    public void setCodigoReserva(String codigoReserva) { this.codigoReserva = codigoReserva; }

    public LocalDateTime getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDateTime fechaReserva) { this.fechaReserva = fechaReserva; }

    public EstadoReserva getEstado() { return estado; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    public int getCorrelativo(){return this.correlativo; }
    public void setCorrelativo(int correlativo ) { this.correlativo = correlativo;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}