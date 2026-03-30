package com.ticket.electronico.domain.model.entity;



import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Vuelo {

    private UUID id;
    private String codigoVuelo;
    private UUID aerolineaId;
    private UUID aeropuertoOrigenId;
    private UUID aeropuertoDestinoId;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private UUID avionId;
    private String estado;

    public Vuelo() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getCodigoVuelo() { return codigoVuelo; }
    public void setCodigoVuelo(String codigoVuelo) { this.codigoVuelo = codigoVuelo; }

    public UUID getAerolineaId() { return aerolineaId; }
    public void setAerolineaId(UUID aerolineaId) { this.aerolineaId = aerolineaId; }

    public UUID getAeropuertoOrigenId() { return aeropuertoOrigenId; }
    public void setAeropuertoOrigenId(UUID aeropuertoOrigenId) { this.aeropuertoOrigenId = aeropuertoOrigenId; }

    public UUID getAeropuertoDestinoId() { return aeropuertoDestinoId; }
    public void setAeropuertoDestinoId(UUID aeropuertoDestinoId) { this.aeropuertoDestinoId = aeropuertoDestinoId; }

    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }

    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDateTime fechaLlegada) { this.fechaLlegada = fechaLlegada; }

    public UUID getAvionId() { return avionId; }
    public void setAvionId(UUID avionId) { this.avionId = avionId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vuelo)) return false;
        Vuelo vuelo = (Vuelo) o;
        return Objects.equals(id, vuelo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}