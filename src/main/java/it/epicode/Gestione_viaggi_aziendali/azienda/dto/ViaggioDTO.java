package it.epicode.Gestione_viaggi_aziendali.azienda.dto;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.StatoViaggio;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioDTO {
    private Long id;
    private String destinazione;
    private LocalDate data;
    private StatoViaggio stato;
}
