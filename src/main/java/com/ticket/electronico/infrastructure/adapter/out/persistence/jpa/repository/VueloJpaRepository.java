package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;


import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.VueloJpaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VueloJpaRepository extends JpaRepository<VueloJpaEntity, UUID> {

    java.util.List<VueloJpaEntity> findByAerolineaId(UUID aerolineaId);

    @Query("SELECT v FROM VueloJpaEntity v WHERE v.aeropuertoOrigenId = :origen AND v.aeropuertoDestinoId = :destino")
    java.util.List<VueloJpaEntity> buscarRutas(
            @Param("origen") UUID origen,
            @Param("destino") UUID destino
    );

    @Query("SELECT v FROM VueloJpaEntity v WHERE v.fechaSalida BETWEEN :inicio AND :fin")
    java.util.List<VueloJpaEntity> findByRangoFechas(
            @Param("inicio") java.time.LocalDateTime inicio,
            @Param("fin") java.time.LocalDateTime fin
    );

    @Query("SELECT v FROM VueloJpaEntity v WHERE v.estado = 'PROGRAMADO' AND v.fechaSalida >= :fecha")
    java.util.List<VueloJpaEntity> vuelosProgramados(@Param("fecha") java.time.LocalDateTime fecha);
}