package com.ticket.electronico.domain.model.valueobject;



public enum EstadoReserva {
    CONFIRMADA("CONFIRMADA"),
    CANCELADA("CANCELADA"),
    PENDIENTE("PENDIENTE");

    private final String description;
    EstadoReserva(String description) {this.description = description; }
    public boolean isActive() {
        return this == CONFIRMADA || this == PENDIENTE; }
}