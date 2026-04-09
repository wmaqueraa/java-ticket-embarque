package com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository;

import com.ticket.electronico.domain.model.valueobject.EstadoBoleto;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.BoletoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoletoJpaRepository extends JpaRepository<BoletoJpaEntity, UUID> {

    // 🔍 Buscar por ID (ya existe, pero opcional redefinir)
    Optional<BoletoJpaEntity> findById(UUID id);

    // 🔍 Buscar por código de reserva
    List<BoletoJpaEntity> findByCodigoReserva(String codigoReserva);

    // 🔍 Buscar boletos por pasajero
    List<BoletoJpaEntity> findByIdPasajero(UUID idPasajero);

    // 🔍 Buscar boletos por vuelo
    List<BoletoJpaEntity> findByIdVuelo(UUID idVuelo);

    // 🔍 Buscar por estado
    List<BoletoJpaEntity> findByEstado(EstadoBoleto estado);

    // 🔍 Buscar por pasajero y estado
    List<BoletoJpaEntity> findByIdPasajeroAndEstado(UUID idPasajero, EstadoBoleto estado);

    // 🔍 Buscar por vuelo y estado
    List<BoletoJpaEntity> findByIdVueloAndEstado(UUID idVuelo, EstadoBoleto estado);

    // 🔍 Verificar si existe un código de reserva
    boolean existsByCodigoReserva(String codigoReserva);

    // 🗑 Eliminar por código de reserva
    void deleteByCodigoReserva(String codigoReserva);
}