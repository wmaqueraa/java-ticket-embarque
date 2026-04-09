package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity;

import com.ticket.electronico.domain.model.valueobject.EstadoTicket;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ticket_embarque")
public class TicketEmbarqueJpaEntity {

    @Id
    private UUID id;

    private UUID detalleId;
    private String numeroTicket;
    private String puertaEmbarque;
    private String grupoEmbarque;
    private LocalDateTime horaEmbarque;

    @Enumerated(EnumType.STRING)
    private EstadoTicket estado;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //public enum EstadoTicketJpa { ACTIVO, USADO, EXPIRADO }

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    //public UUID getDetalleId() { return detalleId; }
    //public void setDetalleId(UUID detalleId) { this.detalleId = detalleId; }

    public String getNumeroTicket() { return numeroTicket; }
    public void setNumeroTicket(String numeroTicket) { this.numeroTicket = numeroTicket; }

    public String getPuertaEmbarque() { return puertaEmbarque; }
    public void setPuertaEmbarque(String puertaEmbarque) { this.puertaEmbarque = puertaEmbarque; }

    public String getGrupoEmbarque() { return grupoEmbarque; }
    public void setGrupoEmbarque(String grupoEmbarque) { this.grupoEmbarque = grupoEmbarque; }

    public LocalDateTime getHoraEmbarque() { return horaEmbarque; }
    public void setHoraEmbarque(LocalDateTime horaEmbarque) { this.horaEmbarque = horaEmbarque; }

    public EstadoTicket getEstado() { return estado; }
    public void setEstado(EstadoTicket estado) { this.estado = estado; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}