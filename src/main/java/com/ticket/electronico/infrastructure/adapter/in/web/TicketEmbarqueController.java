package com.ticket.electronico.infrastructure.adapter.in.web;


import com.ticket.electronico.application.dto.command.ConfirmarReservaCommand;
import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;
import com.ticket.electronico.application.port.in.RealizarCheckInOnlineUseCase;
import com.ticket.electronico.domain.model.entity.TicketEmbarque;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticketembarque")
@Tag(name = "TicketEmbarque", description = "Gestión de Ticket de Embarque Vuelo en Aerolinea")
@CrossOrigin(origins = "*")
public class TicketEmbarqueController {

    private final RealizarCheckInOnlineUseCase realizarCheckInOnlineUseCase;

    public TicketEmbarqueController(RealizarCheckInOnlineUseCase realizarCheckInOnlineUseCase) {
        this.realizarCheckInOnlineUseCase = realizarCheckInOnlineUseCase;
    }

    @PostMapping
    @Operation(summary = "Confirmacion a Aerolinea asistencia para volar", description = "Programa de Generación de Ticket de Embarque")
    @ApiResponse(responseCode = "201", description = "ChekIn exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @ApiResponse(responseCode = "409", description = "Reprogramación del Vuelo por fuerza mayor")
    public ResponseEntity<List<TicketEmbarqueResponse>> checkinWeb(
            @Valid @RequestBody ConfirmarReservaCommand command) {
        List<TicketEmbarqueResponse> listadoResponse = realizarCheckInOnlineUseCase.emitirPorReserva(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(listadoResponse);
    }

}
