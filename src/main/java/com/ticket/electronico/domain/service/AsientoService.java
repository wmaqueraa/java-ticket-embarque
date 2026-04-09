package com.ticket.electronico.domain.service;



import com.ticket.electronico.domain.exception.AsientoDuplicateException;
import com.ticket.electronico.domain.exception.AsientoNotFoundException;
import com.ticket.electronico.domain.model.entity.Asiento;
import com.ticket.electronico.domain.model.valueobject.TipoAsiento;
import com.ticket.electronico.domain.repository.AsientoRepository;
import com.ticket.electronico.domain.exception.BusinessException;
import com.ticket.electronico.shared.annotation.DomainService;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
@DomainService
public class AsientoService {

    private final AsientoRepository repository;
    private final Random random = new Random();

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
            throw new AsientoDuplicateException("El asiento ya existe en este avión");
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
                .orElseThrow(() -> new AsientoNotFoundException("Asiento no encontrado"));
    }

    /**
     * Eliminar asiento
     */
    public void eliminar(UUID id) {
        repository.deleteById(id);
    }

    public Asiento generarAsientoAleatorioDisponible(List<Asiento> asientos) {

        // 1. Filtrar disponibles
        List<Asiento> disponibles = asientos.stream()
                .filter(a -> !a.getIsOcupado())
                .collect(Collectors.toList());

        if (disponibles.isEmpty()) {
            throw new RuntimeException("No hay asientos disponibles");
        }

        // 2. Priorizar los de menor demanda (MEDIO)
        List<Asiento> menosDemandados = disponibles.stream()
                .filter(a -> a.getTipoAsiento() == TipoAsiento.MEDIO)
                .collect(Collectors.toList());

        List<Asiento> candidatos;

        if (!menosDemandados.isEmpty()) {
            candidatos = menosDemandados;
        } else {
            // Si no hay MEDIO, usar cualquiera disponible
            candidatos = disponibles;
        }

        // 3. Selección aleatoria
        int index = random.nextInt(candidatos.size());
        return candidatos.get(index);
    }
}