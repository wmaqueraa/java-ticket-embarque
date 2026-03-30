package com.ticket.electronico.domain.model.valueobjects;


import java.util.Objects;

public class Documento {

    private final String tipo;
    private final String numero;

    public Documento(String tipo, String numero) {
        if (numero == null || numero.isEmpty()) {
            throw new IllegalArgumentException("Número de documento inválido");
        }
        this.tipo = tipo;
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Documento)) return false;
        Documento that = (Documento) o;
        return Objects.equals(tipo, that.tipo) &&
                Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, numero);
    }
}