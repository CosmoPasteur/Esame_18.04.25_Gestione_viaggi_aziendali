package it.epicode.Gestione_viaggi_aziendali.azienda.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDTO {

    private Long id;
    private Long viaggioId;
    private Long dipendenteId;
    private LocalDate dataRichiesta;
    private String note;
}
