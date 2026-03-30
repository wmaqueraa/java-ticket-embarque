package com.ticket.electronico.domain.service;

import com.ticket.electronico.domain.model.entity.Aerolinea;
import com.ticket.electronico.domain.model.valueobjects.CodigoIata;
import com.ticket.electronico.domain.repository.AerolineaRepository;
import com.ticket.electronico.domain.exception.BusinessException;

import java.util.UUID;

public class AerolineaService {

    private final AerolineaRepository repository;

    public AerolineaService(AerolineaRepository repository) {
        this.repository = repository;
    }

    public Aerolinea registrar(Aerolinea aerolinea) {

        repository.findByCodigoIata(aerolinea.getCodigoIata())
                .ifPresent(a -> {
                    throw new BusinessException("Ya existe una aerolínea con ese código IATA");
                });

        return repository.save(aerolinea);
    }

    public Aerolinea actualizarNombre(UUID id, String nuevoNombre) {

        Aerolinea aerolinea = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Aerolínea no encontrada"));

        aerolinea.setNombre(nuevoNombre);

        return repository.save(aerolinea);
    }
}