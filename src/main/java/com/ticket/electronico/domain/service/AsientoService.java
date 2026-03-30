package com.ticket.electronico.domain.service;



import com.ticket.electronico.domain.model.entity.Asiento;
import com.ticket.electronico.domain.model.valueobjects.NumeroAsiento;
import com.ticket.electronico.domain.repository.AsientoRepository;
import com.ticket.electronico.domain.exception.BusinessException;

import java.util.List;
import java.util.UUID;

public class AsientoService {

    private final AsientoRepository repository;

    public AsientoService(AsientoRepository repository) {
        this.repository = repository;
    }

    /**
     * Registrar un asiento validando que no exista duplicado en el avión
     */
    public Asiento registrar(Asiento asiento) {

        repository.findByAvionIdAndNumeroAsiento(
                asiento.getAvionId(),
                asiento.getNumeroAsiento()
        ).ifPresent(a -> {
            throw new BusinessException("El asiento ya existe en este avión");
        });

        return repository.save(asiento);
    }

    /**
     * Obtener todos los asientos de un avión
     */
    public List<Asiento> listarPorAvion(UUID avionId) {
        return repository.findByAvionId(avionId);
    }

    /**
     * Buscar asiento por ID
     */
    public Asiento obtenerPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Asiento no encontrado"));
    }

    /**
     * Eliminar asiento
     */
    public void eliminar(UUID id) {
        repository.deleteById(id);
    }
}