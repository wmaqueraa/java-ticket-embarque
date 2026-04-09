package com.ticket.electronico.domain.exception;

public class ReservaNoEncontradaException extends RuntimeException {

    public ReservaNoEncontradaException(String codigoReserva) {
        super("Reserva no encontrada con código: " + codigoReserva);
    }
}
