package com.ticket.electronico.infrastructure.adapter.in.web;

import com.ticket.electronico.application.dto.command.CheckInAeropuertoCommand;
import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;
import com.ticket.electronico.application.port.in.RealizarCheckInAeropuertoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/checkin/aeropuerto")
@Tag(name = "CheckIn Aeropuerto", description = "Check-In presencial en mostrador de aerolínea")
@CrossOrigin(origins = "*")
public class CheckInAeropuertoController {

    private final RealizarCheckInAeropuertoUseCase realizarCheckInAeropuertoUseCase;

    public CheckInAeropuertoController(RealizarCheckInAeropuertoUseCase realizarCheckInAeropuertoUseCase) {
        this.realizarCheckInAeropuertoUseCase = realizarCheckInAeropuertoUseCase;
    }

    @PostMapping
    @Operation(
            summary = "Check-In en Aeropuerto",
            description = "Registro presencial en mostrador de aerolínea — emite ticket físico impreso"
    )
    @ApiResponse(responseCode = "201", description = "Check-In realizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @ApiResponse(responseCode = "404", description = "Reserva o pasajero no encontrado")
    @ApiResponse(responseCode = "409", description = "Reprogramación del vuelo por fuerza mayor")
    public ResponseEntity<List<TicketEmbarqueResponse>> checkInAeropuerto(
            @Valid @RequestBody CheckInAeropuertoCommand command) {
        List<TicketEmbarqueResponse> response = realizarCheckInAeropuertoUseCase.realizarCheckIn(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}