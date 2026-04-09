package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;

import com.ticket.electronico.domain.model.valueobject.CodigoIata;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.AeropuertoJpaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AeropuertoJpaRepository extends JpaRepository<AeropuertoJpaEntity, UUID> {

    Optional<AeropuertoJpaEntity> findByCodigoIata(CodigoIata codigoIata);

    @Query("SELECT a FROM AeropuertoJpaEntity a WHERE a.ciudad = :ciudad")
    java.util.List<AeropuertoJpaEntity> findByCiudad(@Param("ciudad") String ciudad);
}