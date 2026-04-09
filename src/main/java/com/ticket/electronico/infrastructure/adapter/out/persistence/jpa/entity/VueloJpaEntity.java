package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity;

import com.ticket.electronico.domain.model.valueobject.EstadoVuelo;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "vuelo")
public class VueloJpaEntity {

    @Id
    private UUID id;

    private String codigoVuelo;
    private UUID aerolineaId;
    private UUID aeropuertoOrigenId;
    private UUID aeropuertoDestinoId;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private UUID avionId;

    @Enumerated(EnumType.STRING)
    private EstadoVuelo estado;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
/*
    public enum EstadoVueloJpa {
        PROGRAMADO, ABORDANDO, CANCELADO, FINALIZADO
    }
*/
    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCodigoVuelo() { return codigoVuelo; }
    public void setCodigoVuelo(String codigoVuelo) { this.codigoVuelo = codigoVuelo; }

    public UUID getAerolineaId() { return aerolineaId; }
    public void setAerolineaId(UUID aerolineaId) { this.aerolineaId = aerolineaId; }

    public UUID getAeropuertoOrigenId() { return aeropuertoOrigenId; }
    public void setAeropuertoOrigenId(UUID aeropuertoOrigenId) { this.aeropuertoOrigenId = aeropuertoOrigenId; }

    public UUID getAeropuertoDestinoId() { return aeropuertoDestinoId; }
    public void setAeropuertoDestinoId(UUID aeropuertoDestinoId) { this.aeropuertoDestinoId = aeropuertoDestinoId; }

    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }

    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDateTime fechaLlegada) { this.fechaLlegada = fechaLlegada; }

    public UUID getAvionId() { return avionId; }
    public void setAvionId(UUID avionId) { this.avionId = avionId; }

    public EstadoVuelo getEstado() { return estado; }
    public void setEstado(EstadoVuelo estado) { this.estado = estado; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}