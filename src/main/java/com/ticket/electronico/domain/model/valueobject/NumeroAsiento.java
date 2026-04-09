package com.ticket.electronico.domain.model.valueobject;


import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class NumeroAsiento {

    private  String value;
    protected NumeroAsiento() {}
    public NumeroAsiento(String value) {
        if (value == null || !value.matches("\\d+[A-Z]")) {
            throw new IllegalArgumentException("Número de asiento inválido");
        }
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumeroAsiento)) return false;
        NumeroAsiento that = (NumeroAsiento) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}