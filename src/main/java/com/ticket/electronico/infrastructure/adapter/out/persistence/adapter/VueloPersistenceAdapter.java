package com.ticket.electronico.infrastructure.adapter.out.persistence.adapter;


import com.ticket.electronico.domain.model.entity.Vuelo;
import com.ticket.electronico.domain.repository.VueloRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository.VueloJpaRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.mapper.VueloJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
public class VueloPersistenceAdapter implements VueloRepository {

    private final VueloJpaRepository repository;

    public VueloPersistenceAdapter(VueloJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vuelo save(Vuelo vuelo) {
        return VueloJpaMapper.toDomain(
                repository.save(VueloJpaMapper.toJpa(vuelo))
        );
    }

    @Override
    public List<Vuelo> buscarPorRangoFechas(java.time.LocalDateTime inicio,
                                            java.time.LocalDateTime fin) {
        return repository.findByRangoFechas(inicio, fin)
                .stream()
                .map(VueloJpaMapper::toDomain)
                .toList();
    }

    @Override
    public Vuelo findById(UUID id) {
        return null;
    }
}