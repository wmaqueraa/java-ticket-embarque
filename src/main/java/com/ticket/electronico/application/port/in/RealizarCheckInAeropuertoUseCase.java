// application/port/in/RealizarCheckInAeropuertoUseCase.java
package com.ticket.electronico.application.port.in;

import com.ticket.electronico.application.dto.command.CheckInAeropuertoCommand;
import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;
import java.util.List;

public interface RealizarCheckInAeropuertoUseCase {
    List<TicketEmbarqueResponse> realizarCheckIn(CheckInAeropuertoCommand command);
}