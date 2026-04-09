package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;


import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.TicketEmbarqueJpaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TicketEmbarqueJpaRepository extends JpaRepository<TicketEmbarqueJpaEntity, UUID> {

    Optional<TicketEmbarqueJpaEntity> findByNumeroTicket(String numeroTicket);

    @Query("SELECT t FROM TicketEmbarqueJpaEntity t WHERE t.estado = 'ACTIVO'")
    java.util.List<TicketEmbarqueJpaEntity> findActivos();

    @Query("SELECT t FROM TicketEmbarqueJpaEntity t WHERE t.detalleId = :detalleId")
    Optional<TicketEmbarqueJpaEntity> findByDetalle(@Param("detalleId") UUID detalleId);
}