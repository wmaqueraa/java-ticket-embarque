package com.ticket.electronico.domain.service;

import com.ticket.electronico.domain.exception.AsientoDuplicateException;
import com.ticket.electronico.domain.exception.BoletoRegisterException;
import com.ticket.electronico.domain.model.entity.Boleto;
import com.ticket.electronico.domain.repository.BoletoRepository;
import com.ticket.electronico.shared.annotation.DomainService;

@DomainService
public class BoletoService {
    private final BoletoRepository repository;

    public BoletoService(BoletoRepository boletoRepository) {
        this.repository = boletoRepository;
    }

    public Boleto registrar(Boleto boleto){
        repository.findByVueloIdAndPasajeroIdAndCodigoReserva(
                boleto.getIdVuelo(),
                boleto.getIdPasajero(),
                boleto.getCodigoReserva()
        ).ifPresent(a -> {
            throw new BoletoRegisterException("El Pasajero tiene Reserva en este Vuelo");
        });

        return repository.save(boleto);
    }

    /*
    public String getCodigoReserva() { return codigoReserva; }
    public UUID getIdPasajero() { return idPasajero; }
    public UUID getIdVuelo() { return idVuelo;  }
    * */
}
