package it.epicode.Gestione_viaggi_aziendali.azienda.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
