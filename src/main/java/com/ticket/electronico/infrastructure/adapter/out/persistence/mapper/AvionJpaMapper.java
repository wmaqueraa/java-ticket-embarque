package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;


import com.ticket.electronico.domain.model.entity.Avion;

import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.AvionJpaEntity;


public class AvionJpaMapper {

    public static Avion toDomain(AvionJpaEntity e) {
        Avion a = new Avion();
        a.setId(e.getId());
        a.setModelo(e.getModelo());
        a.setCapacidad(e.getCapacidad());
        a.setAerolineaId(e.getAerolineaId());
        return a;
    }

    public static AvionJpaEntity toJpa(Avion d) {
        AvionJpaEntity e = new AvionJpaEntity();
        e.setId(d.getId());
        e.setModelo(d.getModelo());
        e.setCapacidad(d.getCapacidad());
        e.setAerolineaId(d.getAerolineaId());
        return e;
    }
}