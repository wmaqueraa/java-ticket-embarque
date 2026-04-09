package com.ticket.electronico.domain.model.entity;


import com.ticket.electronico.domain.model.valueobject.EstadoBoleto;
import com.ticket.electronico.domain.model.valueobject.TipoEquipaje;

import java.time.LocalDateTime;
import java.util.UUID;
public class Boleto {

    private UUID id;
    private String codigoReserva;
    private UUID idPasajero;
    private UUID idVuelo;
    private EstadoBoleto estado;
    private LocalDateTime fechaCompra;
    private TipoEquipaje tipoEquipaje;

    // Constructor completo
    public Boleto(UUID id, String codigoReserva, UUID idPasajero,
                  UUID idVuelo, EstadoBoleto estado,
                  LocalDateTime fechaCompra,
                  TipoEquipaje tipoEquipaje) {

        this.id = id;
        this.codigoReserva = codigoReserva;
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
        this.tipoEquipaje = tipoEquipaje;
    }

    // ======================
    // GETTERS
    // ======================
    public UUID getId() { return id; }
    public String getCodigoReserva() { return codigoReserva; }
    public UUID getIdPasajero() { return idPasajero; }
    public UUID getIdVuelo() { return idVuelo; }
    public EstadoBoleto getEstado() { return estado; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public TipoEquipaje getTipoEquipaje() { return tipoEquipaje; }

    // ======================
    // SETTERS
    // ======================
    public void setId(UUID id) { this.id = id; }
    public void setCodigoReserva(String codigoReserva) { this.codigoReserva = codigoReserva; }
    public void setIdPasajero(UUID idPasajero) { this.idPasajero = idPasajero; }
    public void setIdVuelo(UUID idVuelo) { this.idVuelo = idVuelo; }
    public void setEstado(EstadoBoleto estado) { this.estado = estado; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }
    public void setTipoEquipaje(TipoEquipaje tipoEquipaje) { this.tipoEquipaje = tipoEquipaje; }

    // ======================
    // LÓGICA DE NEGOCIO 🔥
    // ======================

    public void confirmar() {
        if (this.estado == EstadoBoleto.CANCELADO) {
            throw new IllegalStateException("No se puede confirmar un boleto cancelado");
        }
        this.estado = EstadoBoleto.CONFIRMADO;
    }

    public void cancelar() {
        if (this.estado == EstadoBoleto.CANCELADO) {
            throw new IllegalStateException("El boleto ya está cancelado");
        }
        this.estado = EstadoBoleto.CANCELADO;
    }

    public boolean esActivo() {
        return this.estado == EstadoBoleto.CONFIRMADO;
    }
}