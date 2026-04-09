package com.ticket.electronico.application.dto.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConfirmarReservaCommand {

    @NotBlank(message = "Codigo de Reserva is required")
    private String codigoReserva;

    @NotNull(message = "Fecha de Confirmacion is required")
    private LocalDateTime fechaConfirmar;

    @NotNull(message = "Codigo de Avion  is required")
    private UUID  avionId ;

    @NotNull(message = "Asignacion de Asientos aleatorio is required")
    private Boolean asientoAleatorio;




    // Constructor vacío
    public ConfirmarReservaCommand() {
    }

    // Constructor con parámetros
    public ConfirmarReservaCommand(String codigoReserva, LocalDateTime fechaConfirmar,boolean asientoAleatorio, UUID avionId) {
        this.codigoReserva = codigoReserva;
        this.fechaConfirmar = fechaConfirmar;
        this.asientoAleatorio = asientoAleatorio;
        this.avionId = avionId;
    }

    // Getter y Setter
    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public LocalDateTime getFechaConfirmar() {
        return fechaConfirmar;
    }

    public void setFechaConfirmar(LocalDateTime fechaConfirmar) {
        this.fechaConfirmar = fechaConfirmar;
    }

    public boolean getAsientoAleatorio(){
        return asientoAleatorio;
    }

    public void setAsientoAleatorio(boolean asiento ){
        this.asientoAleatorio = asiento;
    }
    public UUID getAvionId(){ return this.avionId; }
    public void setAvionId(UUID avionId ){ this.avionId = avionId; }

}
