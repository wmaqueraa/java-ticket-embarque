package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity;

import com.ticket.electronico.domain.model.valueobject.EstadoReserva;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reserva")
public class ReservaJpaEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "codigo_reserva", nullable = false,length = 20)
    private String codigoReserva;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false )
    private EstadoReserva estado;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDateTime fechaReserva;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ReservaJpaEntity() {}

    public ReservaJpaEntity(UUID id, String codigoReserva, EstadoReserva estado, LocalDateTime fechaReserva
                            ) {
        this.id = id;
        this.codigoReserva = codigoReserva;
        this.estado = estado;
        this.fechaReserva = fechaReserva;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCodigoReserva() { return codigoReserva; }
    public void setCodigoReserva(String codigoReserva) { this.codigoReserva = codigoReserva; }

    public EstadoReserva getEstado() { return estado; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    public LocalDateTime getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDateTime fechaReserva) { this.fechaReserva = fechaReserva; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}