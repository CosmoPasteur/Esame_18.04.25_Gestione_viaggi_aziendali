package it.epicode.Gestione_viaggi_aziendali.azienda.dto;

import jakarta.validation.constraints.NotNull;

public record PrenotazioneDTO (

        String preferenza,
        @NotNull(message = "Viaggio obbligatorio")
        Long viaggioId,
        @NotNull(message = "Dipendente obbligatorio")
        Long dipendenteId) {

}
