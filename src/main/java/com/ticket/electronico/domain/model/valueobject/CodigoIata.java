package com.ticket.electronico.domain.model.valueobject;


public class CodigoIata {

    private final String value;

    public CodigoIata(String value) {
        if (value == null || value.length() != 3) {
            throw new IllegalArgumentException("Código IATA inválido");
        }
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }
}