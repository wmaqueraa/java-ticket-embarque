package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;

import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.PasajeroJpaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasajeroJpaRepository extends JpaRepository<PasajeroJpaEntity, UUID> {

    Optional<PasajeroJpaEntity> findByNroDocumento(String nroDocumento);

    @Query("SELECT p FROM PasajeroJpaEntity p WHERE LOWER(p.apellidos) LIKE LOWER(CONCAT('%', :apellido, '%'))")
    java.util.List<PasajeroJpaEntity> buscarPorApellido(@Param("apellido") String apellido);
}