package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity;


import com.ticket.electronico.domain.model.valueobject.ClaseAsiento;
import com.ticket.electronico.domain.model.valueobject.NumeroAsiento;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "asiento")
public class AsientoJpaEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "avion_id", nullable = false)
    private UUID avionId;

    @Column(name = "numero_asiento", nullable = false, length = 5)
    private NumeroAsiento numeroAsiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "clase", nullable = false)
    private ClaseAsiento clase;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // CONSTRUCTORES

    public AsientoJpaEntity() {}

    public AsientoJpaEntity(UUID id, UUID avionId, NumeroAsiento numeroAsiento, ClaseAsiento clase) {
        this.id = id;
        this.avionId = avionId;
        this.numeroAsiento = numeroAsiento;
        this.clase = clase;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // HOOK JPA

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // GETTERS Y SETTERS

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAvionId() {
        return avionId;
    }

    public void setAvionId(UUID avionId) {
        this.avionId = avionId;
    }

    public NumeroAsiento getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(NumeroAsiento numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public ClaseAsiento getClase() {
        return clase;
    }

    public void setClase(ClaseAsiento clase) {
        this.clase = clase;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // ENUM


}