package org.soulcodeacademy.helpr.services.errors;

public class LimiteQuantidadeError extends RuntimeException{
    public LimiteQuantidadeError(String message) {
        super(message);
    }
}
