package org.soulcodeacademy.helpr.services.errors;

public class ParametrosInsuficientesError extends RuntimeException {
    public ParametrosInsuficientesError(String message) {
        super(message);
    }
}
