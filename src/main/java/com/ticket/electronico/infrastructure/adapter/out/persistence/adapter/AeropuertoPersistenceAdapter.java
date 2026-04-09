package com.ticket.electronico.infrastructure.adapter.out.persistence.adapter;


import com.ticket.electronico.domain.model.entity.Aeropuerto;
import com.ticket.electronico.domain.model.valueobject.CodigoIata;
import com.ticket.electronico.domain.repository.AeropuertoRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.mapper.AeropuertoJpaMapper;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository.AeropuertoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AeropuertoPersistenceAdapter implements AeropuertoRepository {

    private final AeropuertoJpaRepository repository;

    public AeropuertoPersistenceAdapter(AeropuertoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Aeropuerto save(Aeropuerto aeropuerto) {
        return AeropuertoJpaMapper.toDomain(
                repository.save(AeropuertoJpaMapper.toJpa(aeropuerto))
        );
    }

    @Override
    public Optional<Aeropuerto> findById(UUID id) {
        return repository.findById(id)
                .map(AeropuertoJpaMapper::toDomain);
    }

    @Override
    public Optional<Aeropuerto> findByCodigoIata(CodigoIata codigo) {
        return repository.findByCodigoIata(codigo)
                .map(AeropuertoJpaMapper::toDomain);
    }

    @Override
    public List<Aeropuerto> findAll() {
        return repository.findAll()
                .stream()
                .map(AeropuertoJpaMapper::toDomain)
                .toList();
    }
}
