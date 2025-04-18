package it.epicode.Gestione_viaggi_aziendali.azienda.dto;

import lombok.Data;

@Data
public class DipendenteDTO {
    private Long id;
    private String username;
    private String name;
    private String cognome;
    private String email;
    private String immagineProfilo;
}
