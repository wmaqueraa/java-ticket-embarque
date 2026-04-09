package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;

import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.AsientoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AsientoJpaRepository extends JpaRepository<AsientoJpaEntity, UUID> {

    // 🔎 Buscar asientos por avión
    List<AsientoJpaEntity> findByAvionId(UUID avionId);

    // 🔎 Buscar asiento específico por avión + número
    @Query("SELECT a FROM AsientoJpaEntity a WHERE a.avionId = :avionId AND a.numeroAsiento = :numero")
    AsientoJpaEntity findByAvionAndNumero(
            @Param("avionId") UUID avionId,
            @Param("numero") String numero
    );

    // 🔎 Buscar asientos por clase (ECONOMICA, BUSINESS, etc.)
    @Query("SELECT a FROM AsientoJpaEntity a WHERE a.clase = :clase")
    List<AsientoJpaEntity> findByClase(@Param("clase") String clase);

    // 🔎 Buscar asientos disponibles (ejemplo extendido)
    @Query("SELECT a FROM AsientoJpaEntity a WHERE a.avionId = :avionId")
    List<AsientoJpaEntity> findDisponiblesByAvion(@Param("avionId") UUID avionId);
}