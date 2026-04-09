package com.ticket.electronico.infrastructure.adapter.out.persistence.adapter;


import com.ticket.electronico.domain.model.entity.TicketEmbarque;
import com.ticket.electronico.domain.repository.TicketEmbarqueRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.mapper.TicketEmbarqueJpaMapper;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository.TicketEmbarqueJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TicketPersistenceAdapter implements TicketEmbarqueRepository {

    private final TicketEmbarqueJpaRepository repository;

    public TicketPersistenceAdapter(TicketEmbarqueJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public TicketEmbarque save(TicketEmbarque ticket) {
        return TicketEmbarqueJpaMapper.toDomain(
                repository.save(TicketEmbarqueJpaMapper.toJpa(ticket))
        );
    }

    @Override
    public Optional<TicketEmbarque> findById(UUID id) {
        return repository.findById(id)
                .map(TicketEmbarqueJpaMapper::toDomain);
    }

    @Override
    public Optional<TicketEmbarque> findByNumeroTicket(String numero) {
        return repository.findByNumeroTicket(numero)
                .map(TicketEmbarqueJpaMapper::toDomain);
    }

    @Override
    public void saveAll(List<TicketEmbarque> tickets) {

    }

    @Override
    public String getNumberTicket(UUID vueloId) {
        return "";
    }
}