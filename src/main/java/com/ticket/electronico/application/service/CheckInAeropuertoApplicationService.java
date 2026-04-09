package com.ticket.electronico.application.service;


import com.ticket.electronico.application.dto.command.CheckInAeropuertoCommand;
import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;
import com.ticket.electronico.application.port.in.RealizarCheckInAeropuertoUseCase;
import com.ticket.electronico.domain.model.entity.*;
import com.ticket.electronico.domain.model.valueobject.EstadoBoleto;
import com.ticket.electronico.domain.model.valueobject.EstadoCheckIn;
import com.ticket.electronico.domain.model.valueobject.EstadoTicket;
import com.ticket.electronico.domain.model.valueobject.TipoEquipaje;
import com.ticket.electronico.domain.repository.*;
import com.ticket.electronico.domain.service.AsientoService;
import com.ticket.electronico.domain.service.VueloService;
import com.ticket.electronico.shared.annotation.UseCase;
import com.ticket.electronico.shared.util.QRGenerator;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@UseCase
@Transactional
public class CheckInAeropuertoApplicationService implements RealizarCheckInAeropuertoUseCase {

    private final ReservaRepository reservaRepository;
    private final BoletoRepository boletoRepository;
    private final PasajeroRepository pasajeroRepository;
    private final AsientoService asientoService;
    private final VueloRepository vueloRepository;
    private final VueloService vueloService;
    private final DetalleReservaRepository detalleReservaRepository;
    private final TicketEmbarqueRepository ticketEmbarqueRepository;

    public CheckInAeropuertoApplicationService(
            ReservaRepository reservaRepository,
            BoletoRepository boletoRepository,
            PasajeroRepository pasajeroRepository,
            AsientoService asientoService,
            VueloRepository vueloRepository,
            VueloService vueloService,
            DetalleReservaRepository detalleReservaRepository,
            TicketEmbarqueRepository ticketEmbarqueRepository) {
        this.reservaRepository = reservaRepository;
        this.boletoRepository = boletoRepository;
        this.pasajeroRepository = pasajeroRepository;
        this.asientoService = asientoService;
        this.vueloRepository = vueloRepository;
        this.vueloService = vueloService;
        this.detalleReservaRepository = detalleReservaRepository;
        this.ticketEmbarqueRepository = ticketEmbarqueRepository;
    }

    @Override
    public List<TicketEmbarqueResponse> realizarCheckIn(CheckInAeropuertoCommand command) {

        // 1. Buscar reserva por código
        Reserva reserva = reservaRepository.findByCodigoReserva(command.getCodigoReserva())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada: " + command.getCodigoReserva()));

        // 2. Validar pasajero por DNI
        Pasajero pasajero = pasajeroRepository.findByDni(command.getDniPasajero())
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado con DNI: " + command.getDniPasajero()));

        // 3. Obtener boletos de la reserva
        List<Boleto> boletos = boletoRepository.findByCodigoReserva(reserva.getCodigoReserva());
        if (boletos.isEmpty()) {
            throw new RuntimeException("No existen boletos para la reserva: " + command.getCodigoReserva());
        }

        // 4. Obtener asientos disponibles del avión
        List<Asiento> asientosDisponibles = asientoService.listarPorAvion(command.getAvionId());

        DateTimeFormatter formatter    = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatoDDMMM = DateTimeFormatter.ofPattern("dd MMM", new Locale("es", "ES"));
        DateTimeFormatter hora         = DateTimeFormatter.ofPattern("HH:mm");

        List<TicketEmbarque> tickets = new ArrayList<>();
        List<TicketEmbarqueResponse> ticketsResponse = new ArrayList<>();
        int correlativo = 0;

        for (Boleto boleto : boletos) {

            // 5. Confirmar boleto
            boleto.setEstado(EstadoBoleto.CONFIRMADO);

            // 6. Asignar asiento
            Asiento asiento = asientoService.generarAsientoAleatorioDisponible(asientosDisponibles);
            asiento.setIsOcupado(true);

            // 7. Registrar detalle del check-in presencial
            DetalleReserva detalle = new DetalleReserva();
            detalle.setReservaId(reserva.getId());
            detalle.setAsientoId(asiento.getId());
            detalle.setEstadoCheckin(EstadoCheckIn.REALIZADO);
            detalleReservaRepository.save(detalle);

            // 8. Obtener vuelo
            Vuelo vuelo = vueloRepository.findById(boleto.getIdVuelo());

            // 9. Calcular horarios de embarque
            LocalDateTime horaInicioEmbarque;
            LocalDateTime horaFinEmbarque;
            if (vuelo.getEsInternacional()) {
                horaInicioEmbarque = vuelo.getFechaSalida().minusMinutes(55);
                horaFinEmbarque    = vuelo.getFechaSalida().minusMinutes(10);
            } else {
                horaInicioEmbarque = vuelo.getFechaSalida().minusMinutes(35);
                horaFinEmbarque    = vuelo.getFechaSalida().minusMinutes(5);
            }

            // 10. Generar QR — incluye canal AEROPUERTO
            String contenidoQR = "CANAL:AEROPUERTO"
                    + "|RESERVA:" + reserva.getCodigoReserva()
                    + "|VUELO:" + vuelo.getCodigoVuelo()
                    + "|ASIENTO:" + asiento.getNumeroAsiento()
                    + "|DNI:" + pasajero.getDni();
            String qrBase64 = QRGenerator.generarQRBase64(contenidoQR);

            // 11. Mapear response
            correlativo++;
            TicketEmbarqueResponse response = new TicketEmbarqueResponse();
            response.setCodigoReserva(command.getCodigoReserva());
            response.setOrdenPasajero(Integer.toString(correlativo));
            response.setNumeroOrden(boleto.getId().toString());
            response.setNombreCompleto(pasajero.getNombre() + " " + pasajero.getApellido());
            response.setNumeroAsiento(asiento.getNumeroAsiento());
            response.setCodigoQR(qrBase64);

            // Equipaje — diferencia clave del check-in presencial: puede registrar bodega
            if (command.getTieneEquipajeBodega()) {
                response.setEquipaje("Maleta de bodega registrada — dimensiones 41x27x65 cm, peso máx. 23 kg");
            } else if (boleto.getTipEquipaje() == TipoEquipaje.MANO) {
                response.setEquipaje("Solo esta permitido Maleta de Mano de dimensiones 36x19x53 cm y peso 10 kg");
            } else {
                response.setEquipaje("Solo esta permitido bolso o mochilas");
            }

            response.setHoraInicioEmbarque(horaInicioEmbarque.format(formatter));
            response.setHoraFinEmbarque(horaFinEmbarque.format(formatter));
            response.setPuertaEmbarque(vuelo.getPuertaEmbarque());
            response.setVuelo("Vuelo " + vuelo.getCodigoVuelo());
            response.setFechaVuelo(vuelo.getFechaSalida().format(formatoDDMMM));

            response.setCiudadOrigen(vueloService.obtenerCodigoIata(vuelo.getAeropuertoOrigenId()));
            response.setHoraOrigen(vuelo.getFechaSalida().format(hora));
            response.setTerminalOrigen(vueloService.obtenerCiudad(vuelo.getAeropuertoOrigenId()));

            response.setCiudadDestino(vueloService.obtenerCodigoIata(vuelo.getAeropuertoDestinoId()));
            response.setHoraDestino(vuelo.getFechaLlegada().format(hora));
            response.setTerminalDestino(vueloService.obtenerCiudad(vuelo.getAeropuertoDestinoId()));

            // 12. Crear entidad ticket — canal PRESENCIAL → ticket IMPRESO
            String numeroTicket = ticketEmbarqueRepository.getNumberTicket(vuelo.getId());
            TicketEmbarque ticket = new TicketEmbarque();
            ticket.setNumeroTicket(numeroTicket);
            ticket.setEstado(EstadoTicket.ACTIVO);
            ticket.setGrupoEmbarque(vueloService.obtenerGrupo(numeroTicket));
            ticket.setPuertaEmbarque(vuelo.getPuertaEmbarque());
            ticket.setHoraEmbarque(horaInicioEmbarque);
            ticket.setVueloId(vuelo.getId());

            tickets.add(ticket);
            ticketsResponse.add(response);
        }

        // 13. Persistir en lote
        boletoRepository.saveAll(boletos);
        ticketEmbarqueRepository.saveAll(tickets);

        return ticketsResponse;
    }
}
