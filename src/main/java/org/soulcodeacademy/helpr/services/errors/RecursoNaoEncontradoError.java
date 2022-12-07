package org.soulcodeacademy.helpr.services.errors;

public class RecursoNaoEncontradoError extends RuntimeException {
    public RecursoNaoEncontradoError(String message) {
        super(message);
    }
}
