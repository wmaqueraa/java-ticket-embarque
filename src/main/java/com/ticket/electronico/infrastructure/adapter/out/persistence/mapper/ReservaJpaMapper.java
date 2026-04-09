package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;

import com.ticket.electronico.domain.model.entity.Reserva;
import com.ticket.electronico.domain.model.valueobject.EstadoReserva;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.ReservaJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservaJpaMapper {

    public Reserva toDomainModel(ReservaJpaEntity e) {
        Reserva r = new Reserva();
        r.setId(e.getId());
        r.setCodigoReserva(e.getCodigoReserva());
        r.setEstado(EstadoReserva.valueOf(e.getEstado().name()));
        r.setFechaReserva(e.getFechaReserva());
        return r;
    }

    public static ReservaJpaEntity toJpaEntity(Reserva d) {
        ReservaJpaEntity e = new ReservaJpaEntity();
        e.setId(d.getId());
        e.setCodigoReserva(d.getCodigoReserva());
        e.setEstado(EstadoReserva.valueOf(d.getEstado().name()));
        e.setFechaReserva(d.getFechaReserva());
        return e;
    }
}