package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "aerolinea")
public class AerolineaJpaEntity {

    @Id
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "codigo_iata", nullable = false, length = 3)
    private String codigoIata;

    @Column(name = "pais_origen")
    private String paisOrigen;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public AerolineaJpaEntity() {}

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigoIata() { return codigoIata; }
    public void setCodigoIata(String codigoIata) { this.codigoIata = codigoIata; }

    public String getPaisOrigen() { return paisOrigen; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}