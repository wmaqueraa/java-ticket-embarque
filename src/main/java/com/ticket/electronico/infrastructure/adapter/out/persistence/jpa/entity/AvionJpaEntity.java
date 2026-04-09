package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "avion")
public class AvionJpaEntity {

    @Id
    private UUID id;

    private String modelo;
    private Integer capacidad;

    @Column(name = "aerolinea_id")
    private UUID aerolineaId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public UUID getAerolineaId() { return aerolineaId; }
    public void setAerolineaId(UUID aerolineaId) { this.aerolineaId = aerolineaId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}