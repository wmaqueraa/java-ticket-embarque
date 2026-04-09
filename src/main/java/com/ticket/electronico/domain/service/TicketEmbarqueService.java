package com.ticket.electronico.domain.service;



import com.ticket.electronico.domain.exception.TicketDuplicateException;
import com.ticket.electronico.domain.model.entity.TicketEmbarque;
import com.ticket.electronico.domain.model.valueobject.EstadoTicket;
import com.ticket.electronico.domain.repository.TicketEmbarqueRepository;
import com.ticket.electronico.shared.annotation.DomainService;

@DomainService
public class TicketEmbarqueService {

    private final TicketEmbarqueRepository ticketRepository;

    public TicketEmbarqueService(TicketEmbarqueRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketEmbarque emitirTicket(TicketEmbarque ticket) {

        if (ticketRepository.findByNumeroTicket(ticket.getNumeroTicket()).isPresent()) {
            throw new TicketDuplicateException("El ticket ya existe");
        }

        ticket.cambiarEstado(EstadoTicket.ACTIVO);

        return ticketRepository.save(ticket);
    }
}