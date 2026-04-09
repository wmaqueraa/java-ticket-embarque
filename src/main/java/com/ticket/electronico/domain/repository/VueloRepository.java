package com.ticket.electronico.domain.repository;

import com.ticket.electronico.domain.model.entity.Vuelo;
import com.ticket.electronico.infrastructure.adapter.out.persistence.mapper.VueloJpaMapper;

import java.util.List;
import java.util.UUID;

public interface  VueloRepository {
     Vuelo save(Vuelo vuelo);
     List<Vuelo> buscarPorRangoFechas(java.time.LocalDateTime inicio,java.time.LocalDateTime fin);
     Vuelo findById(UUID id);
}
