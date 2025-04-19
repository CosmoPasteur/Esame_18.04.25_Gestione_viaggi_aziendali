package it.epicode.Gestione_viaggi_aziendali.azienda.dto;

import jakarta.validation.constraints.Pattern;

public record StatoDTO(
        @Pattern(regexp = "IN_PROGRAM|ASSIGNED|COMPLETED", message = "Status must be IN_PROGESS, ASSIGNED or " +
                "COMPLETED") String status) {
}
