package com.ticket.electronico.infrastructure.adapter.out.persistence.adapter;


import com.ticket.electronico.domain.model.entity.Reserva;
import com.ticket.electronico.domain.repository.ReservaRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.ReservaJpaEntity;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository.ReservaJpaRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.mapper.ReservaJpaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ReservaPersistenceAdapter implements ReservaRepository {

    private final ReservaJpaRepository repository;
    private final ReservaJpaMapper mapper;

    public ReservaPersistenceAdapter(ReservaJpaRepository repository, ReservaJpaMapper reservaJpaMapper) {
        this.repository = repository;
        this.mapper = reservaJpaMapper;
    }

    @Override
    public Optional<Reserva> findByCodigoReserva(String codigoReserva) {

        return repository.findByCodigoReserva(codigoReserva)
                .map(mapper::toDomainModel);
    }

    // implementar métodos
}