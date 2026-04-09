package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity;

import com.ticket.electronico.domain.model.valueobject.EstadoBoleto;
import com.ticket.electronico.domain.model.valueobject.TipoEquipaje;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "boleto")
public class BoletoJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "codigo_reserva", nullable = false)
    private String codigoReserva;

    @Column(name = "id_pasajero", nullable = false)
    private UUID idPasajero;

    @Column(name = "id_vuelo", nullable = false)
    private UUID idVuelo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoBoleto estado;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_equipaje")
    private TipoEquipaje tipoEquipaje;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public BoletoJpaEntity() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ===== GETTERS =====

    public UUID getId() {
        return id;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public UUID getIdPasajero() {
        return idPasajero;
    }

    public UUID getIdVuelo() {
        return idVuelo;
    }

    public EstadoBoleto getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public TipoEquipaje getTipoEquipaje() {
        return tipoEquipaje;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // ===== SETTERS =====

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public void setIdPasajero(UUID idPasajero) {
        this.idPasajero = idPasajero;
    }

    public void setIdVuelo(UUID idVuelo) {
        this.idVuelo = idVuelo;
    }

    public void setEstado(EstadoBoleto estado) {
        this.estado = estado;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setTipoEquipaje(TipoEquipaje tipoEquipaje) {
        this.tipoEquipaje = tipoEquipaje;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}