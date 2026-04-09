package com.ticket.electronico.domain.model.entity;



import com.ticket.electronico.domain.model.valueobject.EstadoCheckIn;

import java.util.Objects;
import java.util.UUID;

public class DetalleReserva {

    private UUID id;
    private UUID reservaId;
    private UUID pasajeroId;
    private UUID vueloId;
    private UUID asientoId;
    private EstadoCheckIn estadoCheckin;

    public DetalleReserva() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getReservaId() { return reservaId; }
    public void setReservaId(UUID reservaId) { this.reservaId = reservaId; }

    public UUID getPasajeroId() { return pasajeroId; }
    public void setPasajeroId(UUID pasajeroId) { this.pasajeroId = pasajeroId; }

    public UUID getVueloId() { return vueloId; }
    public void setVueloId(UUID vueloId) { this.vueloId = vueloId; }

    public UUID getAsientoId() { return asientoId; }
    public void setAsientoId(UUID asientoId) { this.asientoId = asientoId; }

    public EstadoCheckIn getEstadoCheckin() { return estadoCheckin; }
    public void setEstadoCheckin(EstadoCheckIn estadoCheckin) { this.estadoCheckin = estadoCheckin; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleReserva)) return false;
        DetalleReserva that = (DetalleReserva) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}