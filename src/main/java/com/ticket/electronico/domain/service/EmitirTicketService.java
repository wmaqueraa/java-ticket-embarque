package com.ticket.electronico.domain.service;



import com.ticket.electronico.domain.model.entity.TicketEmbarque;
import com.ticket.electronico.domain.repository.TicketRepository;
import com.ticket.electronico.domain.exception.BusinessException;

public class EmitirTicketService {

    private final TicketRepository ticketRepository;

    public EmitirTicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketEmbarque emitirTicket(TicketEmbarque ticket) {

        if (ticketRepository.findByNumeroTicket(ticket.getNumeroTicket()).isPresent()) {
            throw new BusinessException("El ticket ya existe");
        }

        ticket.cambiarEstado("ACTIVO");

        return ticketRepository.save(ticket);
    }
}