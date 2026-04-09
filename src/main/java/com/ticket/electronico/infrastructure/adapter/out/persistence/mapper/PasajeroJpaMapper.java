package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;


import com.ticket.electronico.domain.model.entity.Pasajero;

import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.PasajeroJpaEntity;

public class PasajeroJpaMapper {

    public static Pasajero toDomain(PasajeroJpaEntity e) {
        Pasajero p = new Pasajero();
        p.setId(e.getId());
        p.setNombres(e.getNombres());
        p.setApellidos(e.getApellidos());
        p.setTipoDocumento(e.getTipoDocumento());
        p.setNroDocumento(e.getNroDocumento());
        p.setEmail(e.getEmail());
        p.setTelefono(e.getTelefono());
        return p;
    }

    public static PasajeroJpaEntity toJpa(Pasajero d) {
        PasajeroJpaEntity e = new PasajeroJpaEntity();
        e.setId(d.getId());
        e.setNombres(d.getNombres());
        e.setApellidos(d.getApellidos());
        e.setTipoDocumento(d.getTipoDocumento());
        e.setNroDocumento(d.getNroDocumento());
        e.setEmail(d.getEmail());
        e.setTelefono(d.getTelefono());
        return e;
    }
}
