package com.ticket.electronico.domain.repository;


import com.ticket.electronico.domain.model.entity.Aeropuerto;
import com.ticket.electronico.domain.model.valueobject.CodigoIata;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AeropuertoRepository {

    Aeropuerto save(Aeropuerto aeropuerto);

    Optional<Aeropuerto> findById(UUID id);

    Optional<Aeropuerto> findByCodigoIata(CodigoIata codigoIata);

    List<Aeropuerto> findAll();
}