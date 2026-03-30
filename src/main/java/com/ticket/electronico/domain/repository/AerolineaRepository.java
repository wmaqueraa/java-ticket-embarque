package com.ticket.electronico.domain.repository;


import com.ticket.electronico.domain.model.entity.Aerolinea;
import com.ticket.electronico.domain.model.valueobjects.CodigoIata;

import java.util.Optional;
import java.util.UUID;

public interface AerolineaRepository {

    Aerolinea save(Aerolinea aerolinea);

    Optional<Aerolinea> findById(UUID id);

    Optional<Aerolinea> findByCodigoIata(CodigoIata codigoIata);

    Optional<Object> findByCodigoIata(String codigoIata);
}