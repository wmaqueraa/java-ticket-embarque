package com.ticket.electronico.domain.service;



import com.ticket.electronico.domain.model.entity.Aeropuerto;
import com.ticket.electronico.domain.repository.AeropuertoRepository;
import com.ticket.electronico.domain.exception.BusinessException;

public class AeropuertoService {

    private final AeropuertoRepository aeropuertoRepository;

    public AeropuertoService(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    public Aeropuerto registrarAeropuerto(Aeropuerto aeropuerto) {

        aeropuertoRepository.findByCodigoIata(aeropuerto.getCodigoIata())
                .ifPresent(a -> {
                    throw new BusinessException("El aeropuerto ya existe con ese código IATA");
                });

        return aeropuertoRepository.save(aeropuerto);
    }
}