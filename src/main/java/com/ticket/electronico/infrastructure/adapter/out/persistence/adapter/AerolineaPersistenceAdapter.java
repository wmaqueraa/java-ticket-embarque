package com.ticket.electronico.infrastructure.adapter.out.persistence.adapter;



import com.ticket.electronico.domain.model.entity.Aerolinea;
import com.ticket.electronico.domain.model.valueobject.CodigoIata;
import com.ticket.electronico.domain.repository.AerolineaRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.mapper.AerolineaJpaMapper;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository.AerolineaJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AerolineaPersistenceAdapter implements AerolineaRepository {

    private final AerolineaJpaRepository repository;

    public AerolineaPersistenceAdapter(AerolineaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Aerolinea save(Aerolinea aerolinea) {
        return AerolineaJpaMapper.toDomain(
                repository.save(AerolineaJpaMapper.toJpa(aerolinea))
        );
    }

    @Override
    public Optional<Aerolinea> findById(UUID id) {
        return repository.findById(id)
                .map(AerolineaJpaMapper::toDomain);
    }



    @Override
    public Optional<Aerolinea> findByCodigoIata(CodigoIata codigoIata) {
        return repository.findByCodigoIata(codigoIata)
                .map(AerolineaJpaMapper::toDomain);
    }

    @Override
    public List<Aerolinea> findAll() {
        return repository.findAll()
                .stream()
                .map(AerolineaJpaMapper::toDomain)
                .toList();
    }

    @Override
    public String getCodigoAerolinea(UUID id) {
        return "";
    }
}