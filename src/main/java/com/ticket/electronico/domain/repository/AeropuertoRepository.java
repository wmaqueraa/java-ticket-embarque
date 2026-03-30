package com.ticket.electronico.domain.repository;


import com.ticket.electronico.domain.model.entity.Aeropuerto;

import java.util.Optional;
import java.util.UUID;

public interface AeropuertoRepository {

    Aeropuerto save(Aeropuerto aeropuerto);

    Optional<Aeropuerto> findById(UUID id);

    Optional<Aeropuerto> findByCodigoIata(String codigoIata);
}