package com.ticket.electronico.domain.repository;


import com.ticket.electronico.domain.model.entity.Aerolinea;
import com.ticket.electronico.domain.model.valueobject.CodigoIata;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AerolineaRepository {

    Aerolinea save(Aerolinea aerolinea);

    Optional<Aerolinea> findById(UUID id);

    Optional<Aerolinea> findByCodigoIata(CodigoIata codigoIata);

    List<Aerolinea> findAll();

    String getCodigoAerolinea(UUID id);

}