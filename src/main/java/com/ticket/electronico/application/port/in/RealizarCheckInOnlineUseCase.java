package com.ticket.electronico.application.port.in;

import com.ticket.electronico.application.dto.command.ConfirmarReservaCommand;
import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;

import java.util.List;

/*


Obtienes tu tarjeta de embarque digital
Ideal si viajas solo con equipaje de mano
Puedes elegir asiento antes
* */
public interface RealizarCheckInOnlineUseCase {
    List<TicketEmbarqueResponse> emitirPorReserva( ConfirmarReservaCommand command );
    TicketEmbarqueResponse emitirPorPasajero(String Nombre, String Apellido);

}
