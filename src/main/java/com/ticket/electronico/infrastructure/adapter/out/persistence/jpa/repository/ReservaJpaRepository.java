package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;

import com.ticket.electronico.domain.model.valueobject.EstadoReserva;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.PasajeroJpaEntity;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.ReservaJpaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface ReservaJpaRepository extends JpaRepository<ReservaJpaEntity, UUID> {


    Optional<ReservaJpaEntity> findByCodigoReserva(String codigoReserva);

    List<ReservaJpaEntity> findByEstado(  EstadoReserva estado);

    @Query("SELECT r FROM ReservaJpaEntity r WHERE r.fechaReserva >= :fecha")
    List<ReservaJpaEntity> findRecientes(@Param("fecha") java.time.LocalDateTime fecha);
}
