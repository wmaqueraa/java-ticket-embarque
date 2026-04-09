package com.ticket.electronico.domain.repository;


import com.ticket.electronico.domain.model.entity.Reserva;

import java.util.Optional;

public interface ReservaRepository {
     Optional<Reserva> findByCodigoReserva(String CodigoReserva);
}
