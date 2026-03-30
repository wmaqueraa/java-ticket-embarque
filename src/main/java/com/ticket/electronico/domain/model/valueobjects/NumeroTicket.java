package com.ticket.electronico.domain.model.valueobjects;


import java.util.Objects;

public class NumeroTicket {

    private final String value;

    public NumeroTicket(String value) {
        if (value == null || value.length() < 5) {
            throw new IllegalArgumentException("Número de ticket inválido");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumeroTicket)) return false;
        NumeroTicket that = (NumeroTicket) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}