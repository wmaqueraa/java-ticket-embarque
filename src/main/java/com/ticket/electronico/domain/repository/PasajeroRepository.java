package com.ticket.electronico.domain.repository;


import com.ticket.electronico.domain.model.entity.Pasajero;

import java.util.Optional;
import java.util.UUID;

public interface PasajeroRepository {

    Pasajero save(Pasajero pasajero);

    Optional<Pasajero> findById(UUID id);

    Optional<Pasajero> findByDocumento(String nroDocumento);
}