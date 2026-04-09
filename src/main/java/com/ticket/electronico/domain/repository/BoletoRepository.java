package com.ticket.electronico.domain.repository;

import com.ticket.electronico.domain.model.entity.Asiento;
import com.ticket.electronico.domain.model.entity.Boleto;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoletoRepository {
    Optional<Boleto>  findByVueloIdAndPasajeroIdAndCodigoReserva(UUID vueloId, UUID pasajeroId,String codigoReserva );
    Optional<Boleto> findByAvionId(UUID boletoId);
    List<Boleto> findByCodigoReserva(String  codigoReserva);
    Boleto save(Boleto boleto );
    void delete(UUID id);
    void saveAll(List<Boleto> boletos);
}
