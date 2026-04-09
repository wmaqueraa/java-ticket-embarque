package com.ticket.electronico.domain.service;

import com.ticket.electronico.domain.model.entity.Reserva;
import com.ticket.electronico.domain.repository.ReservaRepository;
import com.ticket.electronico.shared.annotation.DomainService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@DomainService
public class ReservaService  {
    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }


    public Optional<Reserva> findByCodigoReserva(String CodigoReserva) {
        Optional<Reserva> reserva = repository.findByCodigoReserva(CodigoReserva);

        return reserva;
    }
}
