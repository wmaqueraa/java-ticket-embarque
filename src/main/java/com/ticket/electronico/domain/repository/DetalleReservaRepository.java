package com.ticket.electronico.domain.repository;

import com.ticket.electronico.domain.model.entity.Boleto;
import com.ticket.electronico.domain.model.entity.DetalleReserva;

public interface DetalleReservaRepository {

    DetalleReserva save(DetalleReserva detalle );
}
