package com.ticket.electronico.application.port.in;

import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;

import java.util.List;

public interface RealizarCheckInManualUseCase {
    List<TicketEmbarqueResponse> emitirPorReserva();
    TicketEmbarqueResponse emitirPorPasajero();
}
