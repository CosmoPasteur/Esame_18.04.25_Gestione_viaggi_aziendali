package it.epicode.Gestione_viaggi_aziendali.azienda.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
