package com.ticket.electronico.application.service;

import com.ticket.electronico.application.dto.command.ConfirmarReservaCommand;
import com.ticket.electronico.application.dto.response.TicketEmbarqueResponse;
import com.ticket.electronico.application.port.in.RealizarCheckInOnlineUseCase;
import com.ticket.electronico.domain.exception.ReservaNoEncontradaException;
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

/*
 * El check-in es el registro obligatorio para confirmar
 * tu asistencia al vuelo, generalmente disponible 24-48 horas antes.
 * Se realiza online en la web/app de la aerolínea para obtener la
 * tarjeta de embarque, seleccionar asiento y evitar filas.
 */
@UseCase
@Transactional
public class TicketEmbarqueApplicationService implements RealizarCheckInOnlineUseCase {

    private final BoletoRepository boletoRepository;
    private final ReservaRepository reservaRepository;
    private final AsientoService asientoService;
    private final PasajeroRepository pasajeroRepository;
    private final VueloRepository vueloRepository;
    private final VueloService vueloService;
    private final DetalleReservaRepository detalleReservaRepository;
    private final TicketEmbarqueRepository ticketEmbarqueRepository;

    // ✅ Bug 5 corregido: eliminado parámetro DetalleReserva que no se asignaba
    public TicketEmbarqueApplicationService(BoletoRepository boletoRepository,
                                            ReservaRepository reservaRepository,
                                            AsientoService asientoService,
                                            PasajeroRepository pasajeroRepository,
                                            VueloRepository vueloRepository,
                                            VueloService vueloService,
                                            DetalleReservaRepository detalleReservaRepository,
                                            TicketEmbarqueRepository ticketEmbarqueRepository) {
        this.boletoRepository = boletoRepository;
        this.reservaRepository = reservaRepository;
        this.asientoService = asientoService;
        this.pasajeroRepository = pasajeroRepository;
        this.vueloRepository = vueloRepository;
        this.vueloService = vueloService;
        this.detalleReservaRepository = detalleReservaRepository;
        this.ticketEmbarqueRepository = ticketEmbarqueRepository;
    }

    @Override
    public List<TicketEmbarqueResponse> emitirPorReserva(ConfirmarReservaCommand command) {

        // 1. Validar reserva
        String codigoReserva = command.getCodigoReserva();
        Reserva reserva = reservaRepository.findByCodigoReserva(codigoReserva)
                .orElseThrow(() -> new ReservaNoEncontradaException(codigoReserva));

        List<TicketEmbarqueResponse> ticketsResponse = new ArrayList<>();

        // 2. Obtener boletos
        List<Boleto> boletos = boletoRepository.findByCodigoReserva(reserva.getCodigoReserva());
        if (boletos.isEmpty()) {
            throw new RuntimeException("No existen boletos para la reserva");
        }
/*
        // 3. Obtener asientos disponibles UNA SOLA VEZ
        List<Asiento> asientosDisponibles = asientoService.listarPorAvion(command.getAvionId());

        List<TicketEmbarque> tickets = new ArrayList<>();
        List<TicketEmbarqueResponse> ticketsResponse = new ArrayList<>(); // ✅ Bug 1 corregido
        int correlativo = 0;

        DateTimeFormatter formatter    = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter formatoDDMMM = DateTimeFormatter.ofPattern("dd MMM", new Locale("es", "ES"));
        DateTimeFormatter hora         = DateTimeFormatter.ofPattern("HH:mm");

        for (Boleto boleto : boletos) {

            // 4. Cambiar estado del boleto
            boleto.setEstado(EstadoBoleto.CONFIRMADO);

            // 5. Generar asiento y marcarlo como ocupado
            Asiento asiento = asientoService.generarAsientoAleatorioDisponible(asientosDisponibles);
            asiento.setIsOcupado(true);

            // 6. Crear detalle de reserva
            DetalleReserva detalle = new DetalleReserva();
            detalle.setReservaId(reserva.getId());
            detalle.setEstadoCheckin(EstadoCheckIn.REALIZADO);
            detalle.setAsientoId(asiento.getId());
            detalleReservaRepository.save(detalle);

            // 7. Mapear a response
            TicketEmbarqueResponse response = new TicketEmbarqueResponse();
            correlativo++;
            response.setCodigoReserva(command.getCodigoReserva());
            response.setOrdenPasajero(Integer.toString(correlativo));
            response.setNumeroOrden(boleto.getId().toString());
            response.setNombreCompleto(pasajeroRepository.getNombreApellido(boleto.getIdPasajero()));
            response.setNumeroAsiento(asiento.getNumeroAsiento());

            String contenidoQR = "RESERVA:" + reserva.getCodigoReserva()
                    + "|VUELO:" + boleto.getIdVuelo()
                    + "|ASIENTO:" + asiento.getNumeroAsiento();
            response.setCodigoQR(QRGenerator.generarQRBase64(contenidoQR));

            // Equipaje con if-else para evitar evaluaciones innecesarias
            if (boleto.getTipEquipaje() == TipoEquipaje.BOLSO) {
                response.setEquipaje("Solo esta permitido bolso o mochilas");
            } else if (boleto.getTipEquipaje() == TipoEquipaje.MANO) {
                response.setEquipaje("Solo esta permitido Maleta de Mano de dimensiones 36x19x53 cm y peso 10 kg");
            } else if (boleto.getTipEquipaje() == TipoEquipaje.MALETA) {
                response.setEquipaje("Solo esta permitido Maleta para bodega dimensiones 41x27x65 cm y peso 23 kg");
            }

            Vuelo vuelo = vueloRepository.findById(boleto.getIdVuelo());

            // Horarios de embarque según tipo de vuelo
            LocalDateTime horaInicioEmbarque;
            LocalDateTime horaFinEmbarque;
            if (vuelo.getEsInternacional()) {
                horaInicioEmbarque = vuelo.getFechaSalida().minusMinutes(55);
                horaFinEmbarque    = vuelo.getFechaSalida().minusMinutes(10);
            } else {
                horaInicioEmbarque = vuelo.getFechaSalida().minusMinutes(35);
                horaFinEmbarque    = vuelo.getFechaSalida().minusMinutes(5);
            }

            response.setHoraInicioEmbarque(horaInicioEmbarque.format(formatter));
            response.setHoraFinEmbarque(horaFinEmbarque.format(formatter));
            response.setPuertaEmbarque(vuelo.getPuertaEmbarque());
            response.setVuelo("Vuelo " + vuelo.getCodigoVuelo());
            response.setFechaVuelo(vuelo.getFechaSalida().format(formatoDDMMM));

            // Origen
            response.setCiudadOrigen(vueloService.obtenerCodigoIata(vuelo.getAeropuertoOrigenId()));
            response.setHoraOrigen(vuelo.getFechaSalida().format(hora));        // ✅ Bug 3 corregido
            response.setTerminalOrigen(vueloService.obtenerCiudad(vuelo.getAeropuertoOrigenId()));

            // Destino
            response.setCiudadDestino(vueloService.obtenerCodigoIata(vuelo.getAeropuertoDestinoId()));
            response.setHoraDestino(vuelo.getFechaLlegada().format(hora));      // ✅ Bug 3 corregido
            response.setTerminalDestino(vueloService.obtenerCiudad(vuelo.getAeropuertoDestinoId()));

            // 8. Crear entidad TicketEmbarque
            String numeroTicket = ticketEmbarqueRepository.getNumberTicket(vuelo.getId());
            TicketEmbarque ticket = new TicketEmbarque();
            ticket.setNumeroTicket(numeroTicket);
            ticket.setEstado(EstadoTicket.ACTIVO);
            ticket.setGrupoEmbarque(vueloService.obtenerGrupo(numeroTicket));
            ticket.setPuertaEmbarque(vuelo.getPuertaEmbarque());
            ticket.setHoraEmbarque(horaInicioEmbarque);
            ticket.setVueloId(vuelo.getId());

            tickets.add(ticket);
            ticketsResponse.add(response); // ✅ Bug 2 corregido
        }

        // 9. Persistir en lote
        boletoRepository.saveAll(boletos);
        ticketEmbarqueRepository.saveAll(tickets);
*/
        return ticketsResponse; // ✅ Bug 1 corregido
    }

    @Override
    public TicketEmbarqueResponse emitirPorPasajero(String nombre, String apellido) {
        return null;
    }
}