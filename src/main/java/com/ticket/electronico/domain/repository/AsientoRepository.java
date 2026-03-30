package com.ticket.electronico.domain.repository;


import com.ticket.electronico.domain.model.entity.Asiento;
import com.ticket.electronico.domain.model.valueobjects.NumeroAsiento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AsientoRepository {

    Asiento save(Asiento asiento);

    Optional<Asiento> findById(UUID id);

    List<Asiento> findByAvionId(UUID avionId);

    Optional<Asiento> findByAvionIdAndNumeroAsiento(UUID avionId, NumeroAsiento numeroAsiento);

    void deleteById(UUID id);
}