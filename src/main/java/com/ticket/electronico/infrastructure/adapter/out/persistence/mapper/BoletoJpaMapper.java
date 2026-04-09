package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;


import com.ticket.electronico.domain.model.entity.Boleto;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.BoletoJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class BoletoJpaMapper {

    // Convertir de dominio → JPA
    public static BoletoJpaEntity toJpaEntity(Boleto d) {
        if (d == null) return null;

        BoletoJpaEntity e = new BoletoJpaEntity();
        e.setId(d.getId());
        e.setCodigoReserva(d.getCodigoReserva());
        e.setIdPasajero(d.getIdPasajero());
        e.setIdVuelo(d.getIdVuelo());
        e.setEstado(d.getEstado());
        e.setFechaCompra(d.getFechaCompra());
        e.setTipoEquipaje(d.getTipoEquipaje());

        return e;
    }

    // Convertir de JPA → dominio
    public  Boleto toDomainModel(BoletoJpaEntity e) {
        if (e == null) return null;

        Boleto d = new Boleto(
                e.getId(),
                e.getCodigoReserva(),
                e.getIdPasajero(),
                e.getIdVuelo(),
                e.getEstado(),
                e.getFechaCompra(),
                e.getTipoEquipaje()
        );

        return d;
    }
}
