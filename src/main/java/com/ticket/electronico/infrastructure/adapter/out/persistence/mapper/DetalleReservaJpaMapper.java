package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;


import com.ticket.electronico.domain.model.entity.DetalleReserva;
import com.ticket.electronico.domain.model.valueobject.EstadoCheckIn;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.DetalleReservaJpaEntity;

public class DetalleReservaJpaMapper {

    public static DetalleReserva toDomain(DetalleReservaJpaEntity e) {
        DetalleReserva d = new DetalleReserva();
        d.setId(e.getId());
        d.setReservaId(e.getReservaId());
        d.setPasajeroId(e.getPasajeroId());
        d.setVueloId(e.getVueloId());
        d.setAsientoId(e.getAsientoId());
        d.setEstadoCheckin(
                EstadoCheckIn.valueOf(e.getEstadoCheckin().name())
        );
        return d;
    }

    public static DetalleReservaJpaEntity toJpa(DetalleReserva d) {
        DetalleReservaJpaEntity e = new DetalleReservaJpaEntity();
        e.setId(d.getId());
        e.setReservaId(d.getReservaId());
        e.setPasajeroId(d.getPasajeroId());
        e.setVueloId(d.getVueloId());
        e.setAsientoId(d.getAsientoId());
        e.setEstadoCheckin(
                DetalleReservaJpaEntity.EstadoCheckInJpa.valueOf(d.getEstadoCheckin().name())
        );
        return e;
    }
}