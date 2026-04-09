package com.ticket.electronico.domain.service;

import com.ticket.electronico.domain.model.entity.Aeropuerto;
import com.ticket.electronico.domain.model.entity.Asiento;
import com.ticket.electronico.domain.model.entity.Vuelo;
import com.ticket.electronico.domain.model.valueobject.ClaseAsiento;
import com.ticket.electronico.domain.model.valueobject.NumeroAsiento;
import com.ticket.electronico.domain.model.valueobject.TipoAsiento;
import com.ticket.electronico.domain.repository.AerolineaRepository;
import com.ticket.electronico.domain.repository.AeropuertoRepository;
import com.ticket.electronico.domain.repository.AsientoRepository;
import com.ticket.electronico.domain.repository.VueloRepository;
import com.ticket.electronico.shared.annotation.DomainService;

import java.util.Random;
import java.util.UUID;
import java.util.Arrays;
import java.util.List;

@DomainService
public class VueloService {
    private final AsientoRepository asientoRepository;
    private final VueloRepository vueloRepository;
    private final AerolineaRepository aerolineaRepository;
    private final AeropuertoService aeropuertoService;
    private final AeropuertoRepository aeropuertoRepository;
    List<String> letras = Arrays.asList("A", "B", "C", "D", "E", "F");



    public VueloService(AsientoRepository asientoRepository, VueloRepository vueloRepository, AerolineaRepository aerolineaRepository, AeropuertoRepository aeropuertoRepository, AeropuertoService aeropuertoService, AeropuertoRepository aeropuertoRepository1) {
        this.asientoRepository = asientoRepository;
        this.vueloRepository = vueloRepository;
        this.aerolineaRepository = aerolineaRepository;
        this.aeropuertoService = aeropuertoService;

        this.aeropuertoRepository = aeropuertoRepository1;
    }
    public Vuelo registrarVuelo(Vuelo vuelo){

        for (int i = 0; i < vuelo.getNumeroFilas(); i++) {
            Asiento asiento = new Asiento();
            for (int j = 0; j < letras.size(); j++) {
                System.out.println(letras.get(i));
                if(letras.get(i).contentEquals("B" ) && letras.get(i).contentEquals("E" )){
                    asiento.setTipoAsiento(TipoAsiento.MEDIO);
                }
                if(letras.get(i).contentEquals("A" ) && letras.get(i).contentEquals("E" )){
                    asiento.setTipoAsiento(TipoAsiento.VENTANA);
                }
                if(letras.get(i).contentEquals("C" ) && letras.get(i).contentEquals("D" )){
                    asiento.setTipoAsiento(TipoAsiento.PASILLO);
                }
                // El Value Object debe tener un constructor o factory method
                String valorAsiento = Integer.toString(i) + letras.get(i);
                asiento.setNumeroAsiento(new NumeroAsiento(valorAsiento));

                asiento.setIsOcupado(false);
                asiento.setClase(ClaseAsiento.EJECUTIVA);
                if(i <= 5){
                    asiento.setClase(ClaseAsiento.PRIMERA);
                }
                if(i > 5 && i <= 15 ){
                    asiento.setClase(ClaseAsiento.ECONOMICA);
                }

                asiento.setAvionId(vuelo.getAvionId());
            }
        }
        vuelo.setCodigoVuelo(generarCodigoVuelo(vuelo.getAerolineaId()));
        vuelo.setPuertaEmbarque( aeropuertoService.obtenerEmbarque(vuelo.getAeropuertoOrigenId()));
        return  vueloRepository.save(vuelo);
    }
    // Método para generar código de vuelo
    private String generarCodigoVuelo(UUID AerolineaId) {
        Random random = new Random();

        // Número entre 100 y 9999
        int numero = 100 + random.nextInt(9900);
        String prefijoAerolinea = aerolineaRepository.getCodigoAerolinea(AerolineaId);

        return prefijoAerolinea.toUpperCase() + numero;
    }

    public String obtenerCodigoIata(UUID aeropuertoId){
      return  aeropuertoService.obtenerCodigoIata(aeropuertoId);
    }

    public String obtenerCiudad(UUID aeropuertoId){
        return aeropuertoService.obtenerCiudad(aeropuertoId);
    }

    public String obtenerGrupo(String numeroTicket){
        String numeros = numeroTicket.replaceAll("\\D", ""); // elimina todo lo que NO es número
        int numero = Integer.parseInt(numeros);
        String grupo = "";
        if (numero >= 1 && numero <= 20) {
            grupo = "Grupo 1";
        } else if (numero >= 21 && numero <= 40) {
            grupo = "Grupo 2";
        } else if (numero >= 41 && numero <= 60) {
            grupo = "Grupo 3";
        } else if (numero >= 41 && numero <= 80) {
            grupo = "Grupo 4";
        } else if (numero >= 81 && numero <= 100) {
            grupo = "Grupo 5";
        }else{
            grupo = "Grupo 6";
        }
        return grupo;
    }
}
