package com.ticket.electronico.infrastructure.adapter.out.persistence.mapper;

import com.ticket.electronico.domain.model.entity.TicketEmbarque;
import com.ticket.electronico.domain.model.valueobject.EstadoTicket;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.entity.TicketEmbarqueJpaEntity;

public class TicketEmbarqueJpaMapper {

    public static TicketEmbarque toDomain(TicketEmbarqueJpaEntity e) {
        TicketEmbarque t = new TicketEmbarque();
        t.setId(e.getId());

        t.setNumeroTicket(e.getNumeroTicket());
        t.setPuertaEmbarque(e.getPuertaEmbarque());
        t.setGrupoEmbarque(e.getGrupoEmbarque());
        t.setHoraEmbarque(e.getHoraEmbarque());
        t.setEstado(EstadoTicket.valueOf(e.getEstado().name()));
        return t;
    }

    public static TicketEmbarqueJpaEntity toJpa(TicketEmbarque d) {
        TicketEmbarqueJpaEntity e = new TicketEmbarqueJpaEntity();
        e.setId(d.getId());

        e.setNumeroTicket(d.getNumeroTicket());
        e.setPuertaEmbarque(d.getPuertaEmbarque());
        e.setGrupoEmbarque(d.getGrupoEmbarque());
        e.setHoraEmbarque(d.getHoraEmbarque());
        e.setEstado(EstadoTicket.valueOf(d.getEstado().name()));
        return e;
    }
}