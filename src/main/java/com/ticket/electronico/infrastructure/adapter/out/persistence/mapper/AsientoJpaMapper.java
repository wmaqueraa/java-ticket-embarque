package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;


import com.ticket.electronico.domain.model.entity.Asiento;
import com.ticket.electronico.domain.model.valueobject.ClaseAsiento;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.AsientoJpaEntity;

public class AsientoJpaMapper {

    // JPA → DOMAIN
    public static Asiento toDomain(AsientoJpaEntity e) {
        if (e == null) return null;

        Asiento a = new Asiento();
        a.setId(e.getId());
        a.setAvionId(e.getAvionId());
        a.setNumeroAsiento(e.getNumeroAsiento());

        // Si usas Enum en Domain
        if (e.getClase() != null) {
            a.setClase(ClaseAsiento.valueOf(e.getClase().name()));
        }

        return a;
    }

    // DOMAIN → JPA
    public static AsientoJpaEntity toJpa(Asiento d) {
        if (d == null) return null;

        AsientoJpaEntity e = new AsientoJpaEntity();
        e.setId(d.getId());
        e.setAvionId(d.getAvionId());
        e.setNumeroAsiento(d.getNumeroAsiento());

        // Si usas Enum en Domain
        if (d.getClase() != null) {
            e.setClase(
                    ClaseAsiento.valueOf(d.getClase().name())
            );
        }

        return e;
    }
}