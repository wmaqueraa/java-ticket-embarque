package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "detalle_reserva")
public class DetalleReservaJpaEntity {

    @Id
    private UUID id;

    private UUID reservaId;
    private UUID pasajeroId;
    private UUID vueloId;
    private UUID asientoId;

    @Enumerated(EnumType.STRING)
    private EstadoCheckInJpa estadoCheckin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum EstadoCheckInJpa { PENDIENTE, REALIZADO }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getReservaId() { return reservaId; }
    public void setReservaId(UUID reservaId) { this.reservaId = reservaId; }

    public UUID getPasajeroId() { return pasajeroId; }
    public void setPasajeroId(UUID pasajeroId) { this.pasajeroId = pasajeroId; }

    public UUID getVueloId() { return vueloId; }
    public void setVueloId(UUID vueloId) { this.vueloId = vueloId; }

    public UUID getAsientoId() { return asientoId; }
    public void setAsientoId(UUID asientoId) { this.asientoId = asientoId; }

    public EstadoCheckInJpa getEstadoCheckin() { return estadoCheckin; }
    public void setEstadoCheckin(EstadoCheckInJpa estadoCheckin) { this.estadoCheckin = estadoCheckin; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}