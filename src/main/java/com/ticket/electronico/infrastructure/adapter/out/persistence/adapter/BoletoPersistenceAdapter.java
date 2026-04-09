package com.ticket.electronico.infrastructure.adapter.out.persistence.adapter;

import com.ticket.electronico.domain.model.entity.Boleto;
import com.ticket.electronico.domain.repository.BoletoRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.jpa.repository.BoletoJpaRepository;
import com.ticket.electronico.infrastructure.adapter.out.persistence.mapper.BoletoJpaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class BoletoPersistenceAdapter implements BoletoRepository {

    private final BoletoJpaRepository jpaRepository;
    private final BoletoJpaMapper mapper;
    public BoletoPersistenceAdapter(BoletoJpaRepository jpaRepository, BoletoJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }


    @Override
    public Optional<Boleto> findByVueloIdAndPasajeroIdAndCodigoReserva(UUID vueloId, UUID pasajeroId, String codigoReserva) {
        return Optional.empty();
    }

    @Override
    public Optional<Boleto> findByAvionId(UUID boletoId) {
        return Optional.empty();
    }

    @Override
    public List<Boleto> findByCodigoReserva(String codigoReserva) {
        return jpaRepository.findByCodigoReserva(codigoReserva)
                .stream()
                .map(mapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Boleto save(Boleto boleto) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void saveAll(List<Boleto> boletos) {

    }
}
