package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;

import com.ticket.electronico.domain.model.entity.Vuelo;
import com.ticket.electronico.domain.model.valueobject.EstadoVuelo;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.VueloJpaEntity;

public class VueloJpaMapper {

    public static Vuelo toDomain(VueloJpaEntity e) {
        Vuelo v = new Vuelo();
        v.setId(e.getId());
        v.setCodigoVuelo(e.getCodigoVuelo());
        v.setAerolineaId(e.getAerolineaId());
        v.setAeropuertoOrigenId(e.getAeropuertoOrigenId());
        v.setAeropuertoDestinoId(e.getAeropuertoDestinoId());
        v.setFechaSalida(e.getFechaSalida());
        v.setFechaLlegada(e.getFechaLlegada());
        v.setAvionId(e.getAvionId());
        v.setEstado(EstadoVuelo.valueOf(e.getEstado().name()));
        return v;
    }

    public static VueloJpaEntity toJpa(Vuelo d) {
        VueloJpaEntity e = new VueloJpaEntity();
        e.setId(d.getId());
        e.setCodigoVuelo(d.getCodigoVuelo());
        e.setAerolineaId(d.getAerolineaId());
        e.setAeropuertoOrigenId(d.getAeropuertoOrigenId());
        e.setAeropuertoDestinoId(d.getAeropuertoDestinoId());
        e.setFechaSalida(d.getFechaSalida());
        e.setFechaLlegada(d.getFechaLlegada());
        e.setAvionId(d.getAvionId());
        e.setEstado(EstadoVuelo.valueOf(d.getEstado().name()));
        return e;
    }
}