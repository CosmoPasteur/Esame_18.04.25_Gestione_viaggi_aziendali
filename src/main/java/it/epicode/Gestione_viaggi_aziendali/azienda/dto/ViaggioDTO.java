package it.epicode.Gestione_viaggi_aziendali.azienda.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ViaggioDTO(
        @NotEmpty
        @Size(min = 3, message = "Destinazione deve essere lunga almeno 3 caratteri")
        String destinazione,
        @NotEmpty(message = "Data partenza obbligatoria")
        String data

) {
}
