package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;


import com.ticket.electronico.domain.model.entity.Aerolinea;
import com.ticket.electronico.domain.model.valueobject.CodigoIata;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.AerolineaJpaEntity;

public class AerolineaJpaMapper {

    public static Aerolinea toDomain(AerolineaJpaEntity entity) {
        Aerolinea a = new Aerolinea();
        a.setId(entity.getId());
        a.setNombre(entity.getNombre());
        a.setCodigoIata(new CodigoIata(entity.getCodigoIata()));
        a.setPaisOrigen(entity.getPaisOrigen());
        return a;
    }

    public static AerolineaJpaEntity toJpa(Aerolinea domain) {
        AerolineaJpaEntity e = new AerolineaJpaEntity();
        e.setId(domain.getId());
        e.setNombre(domain.getNombre());
        e.setCodigoIata(domain.getCodigoIata().getValue());
        e.setPaisOrigen(domain.getPaisOrigen());
        return e;
    }
}
