package it.epicode.Gestione_viaggi_aziendali.azienda.dto;

import java.time.LocalDateTime;

public record ErroreDTO(String message, LocalDateTime timestamp) {
}
