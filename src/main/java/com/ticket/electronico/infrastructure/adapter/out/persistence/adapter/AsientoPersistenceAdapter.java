package com.ticket.electronico.infrastructure.adapter.out.persistence.adapter;


import com.ticket.electronico.domain.model.entity.Asiento;
import com.ticket.electronico.domain.model.valueobject.NumeroAsiento;
import com.ticket.electronico.domain.repository.AsientoRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.mapper.AsientoJpaMapper;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository.AsientoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AsientoPersistenceAdapter implements AsientoRepository {

    private final AsientoJpaRepository repository;

    public AsientoPersistenceAdapter(AsientoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Asiento save(Asiento asiento) {
        return AsientoJpaMapper.toDomain(
                repository.save(AsientoJpaMapper.toJpa(asiento))
        );
    }

    @Override
    public Optional<Asiento> findById(UUID id) {
        return repository.findById(id)
                .map(AsientoJpaMapper::toDomain);
    }

    @Override
    public List<Asiento> findAll() {
        return repository.findAll()
                .stream()
                .map(AsientoJpaMapper::toDomain)
                .toList();
    }

    @Override
    public List<Asiento> findByAvionId(UUID avionId) {
        return repository.findByAvionId(avionId)
                .stream()
                .map(AsientoJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Asiento> findByAvionIdAndNumeroAsiento(UUID avionId, NumeroAsiento numeroAsiento) {
        return Optional.empty();
    }

    @Override
    public void deleteById(UUID id) {

    }
}