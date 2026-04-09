package com.ticket.electronico.infrastructure.adapter.out.persistence.adapter;

import com.ticket.electronico.domain.model.entity.DetalleReserva;
import com.ticket.electronico.domain.repository.DetalleReservaRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository.DetalleReservaJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleReservaPersistenceAdapter implements DetalleReservaRepository {

    private final DetalleReservaJpaRepository repository;

    public DetalleReservaPersistenceAdapter(DetalleReservaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public DetalleReserva save(DetalleReserva detalle) {
        return null;
    }

    // implementar métodos
}
