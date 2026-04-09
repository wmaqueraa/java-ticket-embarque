package com.ticket.electronico.domain.service;



import com.ticket.electronico.domain.exception.AeropuertoRegisterException;
import com.ticket.electronico.domain.model.entity.Aeropuerto;
import com.ticket.electronico.domain.model.valueobject.CodigoIata;
import com.ticket.electronico.domain.repository.AeropuertoRepository;
import com.ticket.electronico.domain.exception.BusinessException;
import com.ticket.electronico.shared.annotation.DomainService;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@DomainService
public class AeropuertoService {

    private final AeropuertoRepository aeropuertoRepository;

    public AeropuertoService(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    public Aeropuerto registrarAeropuerto(Aeropuerto aeropuerto) {

        aeropuertoRepository.findByCodigoIata(aeropuerto.getCodigoIata())
                .ifPresent(a -> {
                    throw new AeropuertoRegisterException("El aeropuerto ya existe con ese código IATA");
                });

        return aeropuertoRepository.save(aeropuerto);
    }

    public String obtenerEmbarque( UUID id ){
        String numeroEmbarque = "Puerta ";
        Aeropuerto  aeropuerto =  aeropuertoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aeropuerto no encontrado"));

        int numero = ThreadLocalRandom.current().nextInt(1, aeropuerto.getTotalEmbarques()); // 1 a 9
        numeroEmbarque = numeroEmbarque + String.format("%02d",numero);
        return numeroEmbarque;
    }

    public String obtenerCodigoIata(UUID id) {
        Aeropuerto aeropuerto = aeropuertoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aeropuerto no encontrado"));

        return aeropuerto.getCodigoIata().getValue();
    }

    public String obtenerCiudad(UUID id){
        Aeropuerto aeropuerto = aeropuertoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aeropuerto no encontrado"));
        return aeropuerto.getCiudad();
    }
}