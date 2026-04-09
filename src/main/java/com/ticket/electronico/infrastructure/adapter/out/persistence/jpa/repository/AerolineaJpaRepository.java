package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;

import com.ticket.electronico.domain.model.valueobject.CodigoIata;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.AerolineaJpaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AerolineaJpaRepository extends JpaRepository<AerolineaJpaEntity, UUID> {

    Optional<AerolineaJpaEntity> findByCodigoIata(CodigoIata codigoIata);

    @Query("SELECT a FROM AerolineaJpaEntity a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    Optional<AerolineaJpaEntity> findByNombreLike(@Param("nombre") String nombre);
}