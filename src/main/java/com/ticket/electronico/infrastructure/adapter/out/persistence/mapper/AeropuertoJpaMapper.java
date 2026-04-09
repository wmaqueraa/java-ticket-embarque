package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;


import com.ticket.electronico.domain.model.entity.Aerolinea;
import com.ticket.electronico.domain.model.entity.Aeropuerto;
import com.ticket.electronico.domain.model.valueobject.CodigoIata;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.AerolineaJpaEntity;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.AeropuertoJpaEntity;


public class AeropuertoJpaMapper {

    public static Aeropuerto toDomain(AeropuertoJpaEntity e) {
        Aeropuerto a = new Aeropuerto();
        a.setId(e.getId());
        a.setCodigoIata(new CodigoIata(e.getCodigoIata()));
        a.setNombre(e.getNombre());
        a.setCiudad(e.getCiudad());
        a.setPais(e.getPais());
        return a;
    }

    public static AeropuertoJpaEntity toJpa(Aeropuerto d) {
        AeropuertoJpaEntity e = new AeropuertoJpaEntity();
        e.setId(d.getId());
        e.setCodigoIata(d.getCodigoIata().getValue());
        e.setNombre(d.getNombre());
        e.setCiudad(d.getCiudad());
        e.setPais(d.getPais());
        return e;
    }
}