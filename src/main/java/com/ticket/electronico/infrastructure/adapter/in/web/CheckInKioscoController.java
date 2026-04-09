package com.ticket.electronico.infrastructure.adapter.in.web;


import com.ticket.electronico.application.dto.command.CheckInKioscoCommand;
import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;
import com.ticket.electronico.application.port.in.RealizarCheckInKioscoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/checkin/kiosco")
@Tag(name = "CheckIn Kiosco", description = "Check-In en terminal de autoservicio del aeropuerto")
@CrossOrigin(origins = "*")
public class CheckInKioscoController {

    private final RealizarCheckInKioscoUseCase realizarCheckInKioscoUseCase;

    public CheckInKioscoController(RealizarCheckInKioscoUseCase realizarCheckInKioscoUseCase) {
        this.realizarCheckInKioscoUseCase = realizarCheckInKioscoUseCase;
    }

    @PostMapping
    @Operation(
            summary = "Check-In en Kiosco",
            description = "Registro en terminal de autoservicio — imprime ticket físico y opcionalmente envía ticket digital por email"
    )
    @ApiResponse(responseCode = "201", description = "Check-In en kiosco realizado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o email faltante")
    @ApiResponse(responseCode = "404", description = "Reserva o pasajero no encontrado")
    @ApiResponse(responseCode = "409", description = "Reprogramación del vuelo por fuerza mayor")
    public ResponseEntity<List<TicketEmbarqueResponse>> checkInKiosco(
            @Valid @RequestBody CheckInKioscoCommand command) {
        List<TicketEmbarqueResponse> response = realizarCheckInKioscoUseCase.realizarCheckIn(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}