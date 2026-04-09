package com.ticket.electronico.domain.model.valueobject;



import java.util.Objects;

public class CodigoReserva {

    private final String value;

    public CodigoReserva(String value) {
        if (value == null || value.length() != 6) {
            throw new IllegalArgumentException("Código de reserva inválido");
        }
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodigoReserva)) return false;
        CodigoReserva that = (CodigoReserva) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}