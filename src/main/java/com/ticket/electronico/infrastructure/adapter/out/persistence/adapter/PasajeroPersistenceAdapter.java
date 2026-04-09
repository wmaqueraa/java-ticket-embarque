package com.ticket.electronico.infrastructure.adapter.out.persistence.adapter;

import com.ticket.electronico.domain.model.entity.Pasajero;
import com.ticket.electronico.domain.repository.PasajeroRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository.PasajeroJpaRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.mapper.PasajeroJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PasajeroPersistenceAdapter implements PasajeroRepository {

    private final PasajeroJpaRepository repository;

    public PasajeroPersistenceAdapter(PasajeroJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pasajero save(Pasajero pasajero) {
        return PasajeroJpaMapper.toDomain(
                repository.save(PasajeroJpaMapper.toJpa(pasajero))
        );
    }

    @Override
    public Optional<Pasajero> findById(UUID id) {
        return repository.findById(id)
                .map(PasajeroJpaMapper::toDomain);
    }

    @Override
    public Optional<Pasajero> findByDocumento(String nroDocumento) {
        return Optional.empty();
    }

    @Override
    public List<Pasajero> findAll() {
        return repository.findAll()
                .stream()
                .map(PasajeroJpaMapper::toDomain)
                .toList();
    }

    @Override
    public String getNombreApellido(UUID id) {
        return "";
    }
}