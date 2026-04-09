package com.ticket.electronico.shared.exception;

public enum ErrorCode {

    // Errores de validación
    VALIDATION_ERROR("VALIDATION_001", "Error de validación"),
    INVALID_INPUT("VALIDATION_002", "Entrada inválida"),

    // Errores de negocio
    BUSINESS_RULE_VIOLATION("BUSINESS_001", "Violación de regla de negocio"),
    RESOURCE_NOT_FOUND("BUSINESS_002", "Recurso no encontrado"),
    DUPLICATE_RESOURCE("BUSINESS_003", "Recurso duplicado"),

    // Errores técnicos
    DATABASE_ERROR("TECHNICAL_001", "Error de base de datos"),
    EXTERNAL_SERVICE_ERROR("TECHNICAL_002", "Error de servicio externo"),
    SYSTEM_ERROR("TECHNICAL_003", "Error del sistema");

    private final String code;
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}