package com.ticket.electronico.domain.model.event;



import com.ticket.electronico.domain.model.entity.Aerolinea;
import com.ticket.electronico.domain.model.entity.Aeropuerto;
import com.ticket.electronico.domain.model.entity.Asiento;
import com.ticket.electronico.domain.model.entity.Pasajero;
import com.ticket.electronico.domain.model.entity.Vuelo;

public class CheckInEvent extends DomainEvent {

    private final Vuelo vuelo;
    private final Pasajero pasajero;
    private final Aerolinea aerolinea;
    private final Asiento asiento;
    private final Aeropuerto aeropuertoOrigen;
    private final Aeropuerto aeropuertoDestino;

    public CheckInEvent(
            Vuelo vuelo,
            Pasajero pasajero,
            Aerolinea aerolinea,
            Asiento asiento,
            Aeropuerto aeropuertoOrigen,
            Aeropuerto aeropuertoDestino
    ) {
        super();
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.aerolinea = aerolinea;
        this.asiento = asiento;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
    }

    @Override
    public String getEventName() {
        return "checkin.event";
    }

    // Getters

    public Vuelo getVuelo() {
        return vuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }
}
