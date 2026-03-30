package com.ticket.electronico.domain.repository;

import com.ticket.electronico.domain.model.entity.TicketEmbarque;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository {

    TicketEmbarque save(TicketEmbarque ticket);

    Optional<TicketEmbarque> findById(UUID id);

    Optional<TicketEmbarque> findByNumeroTicket(String numeroTicket);
}