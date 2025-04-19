package it.epicode.Gestione_viaggi_aziendali.azienda.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id, String resource) {
        super(resource + " con id " + id + " non esistente");
    }
}
