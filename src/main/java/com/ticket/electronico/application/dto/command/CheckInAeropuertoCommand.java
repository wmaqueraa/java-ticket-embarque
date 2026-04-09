package com.ticket.electronico.application.dto.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class CheckInAeropuertoCommand {

    @NotBlank(message = "Codigo de reserva es requerido")
    private String codigoReserva;

    @NotNull(message = "ID del agente es requerido")
    private UUID agenteId;

    @NotNull(message = "ID del avion es requerido")
    private UUID avionId;

    @NotBlank(message = "DNI del pasajero es requerido")
    private String dniPasajero;

    @NotNull(message = "Indica si lleva equipaje de bodega")
    private Boolean tieneEquipajeBodega;

    // Constructor vacío
    public CheckInAeropuertoCommand() {}

    // Getters y Setters
    public String getCodigoReserva() { return codigoReserva; }
    public void setCodigoReserva(String codigoReserva) { this.codigoReserva = codigoReserva; }

    public UUID getAgenteId() { return agenteId; }
    public void setAgenteId(UUID agenteId) { this.agenteId = agenteId; }

    public UUID getAvionId() { return avionId; }
    public void setAvionId(UUID avionId) { this.avionId = avionId; }

    public String getDniPasajero() { return dniPasajero; }
    public void setDniPasajero(String dniPasajero) { this.dniPasajero = dniPasajero; }

    public Boolean getTieneEquipajeBodega() { return tieneEquipajeBodega; }
    public void setTieneEquipajeBodega(Boolean tieneEquipajeBodega) { this.tieneEquipajeBodega = tieneEquipajeBodega; }
}