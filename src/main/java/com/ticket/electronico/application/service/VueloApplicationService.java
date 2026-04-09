package com.ticket.electronico.application.service;

import com.ticket.electronico.application.dto.command.ConfirmarReservaCommand;
import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;
import com.ticket.electronico.domain.model.entity.Asiento;
import com.ticket.electronico.domain.model.entity.Vuelo;
import com.ticket.electronico.domain.repository.AsientoRepository;
import com.ticket.electronico.domain.service.VueloService;

import java.util.List;

public class VueloApplicationService {
    private final VueloService vueloService;
    private final AsientoRepository asientoRepository;

    public VueloApplicationService(VueloService vueloService, AsientoRepository asientoRepository) {
        this.vueloService = vueloService;
        this.asientoRepository = asientoRepository;
    }

    @Override
    public Vuelo registrar(RegistrarVueloCommand command) {
       Vuelo vuelo =  vueloService.registrarVuelo(command);
       // Listar los asientos disponibles generados
    }
}
