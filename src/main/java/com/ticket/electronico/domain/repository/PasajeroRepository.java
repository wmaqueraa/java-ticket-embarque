package com.ticket.electronico.domain.repository;


import com.ticket.electronico.domain.model.entity.Pasajero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasajeroRepository {

    Pasajero save(Pasajero pasajero);

    Optional<Pasajero> findById(UUID id);

    Optional<Pasajero> findByDocumento(String nroDocumento);

    List<Pasajero> findAll();
    String getNombreApellido(UUID id);
}