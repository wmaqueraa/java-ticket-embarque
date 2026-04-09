package com.ticket.electronico.application.port.in;

import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;


import java.util.List;

public interface RealizarCheckInAutomaticoUseCase {
    List<TicketEmbarqueResponse> emitirPorReserva();
    List<TicketEmbarqueResponse> emitirPorVuelo();
    TicketEmbarqueResponse emitirPorPasajero();
}
