package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;


import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.DetalleReservaJpaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DetalleReservaJpaRepository extends JpaRepository<DetalleReservaJpaEntity, UUID> {

    java.util.List<DetalleReservaJpaEntity> findByReservaId(UUID reservaId);

    @Query("SELECT d FROM DetalleReservaJpaEntity d WHERE d.vueloId = :vueloId")
    java.util.List<DetalleReservaJpaEntity> findByVuelo(@Param("vueloId") UUID vueloId);

    @Query("SELECT d FROM DetalleReservaJpaEntity d WHERE d.estadoCheckin = 'PENDIENTE'")
    java.util.List<DetalleReservaJpaEntity> findPendientesCheckin();
}