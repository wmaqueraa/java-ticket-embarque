package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;

import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.AvionJpaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AvionJpaRepository extends JpaRepository<AvionJpaEntity, UUID> {

    java.util.List<AvionJpaEntity> findByAerolineaId(UUID aerolineaId);

    @Query("SELECT a FROM AvionJpaEntity a WHERE a.capacidad >= :capacidad")
    java.util.List<AvionJpaEntity> findByCapacidadMinima(@Param("capacidad") Integer capacidad);
}