package it.epicode.Gestione_viaggi_aziendali.azienda.entities;

import it.epicode.Gestione_viaggi_aziendali.azienda.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long viaggioId;

    @Column(nullable = false, length = 50)
    private String destinazione;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

    @ManyToOne
    @JoinColumn(name = "viaggio")
    private Prenotazione prenotazione;


    public Viaggio(String destinazione) {
        this.destinazione = destinazione;
        this.statoViaggio = StatoViaggio.IN_PROGRAMMA;
    }

    @Override
    public String toString() {
        return "Viaggio: " + + viaggioId + '\n' +
                "Destinazione: " + destinazione + '\n' +
                "Data: " + data + '\n' +
                "Stato del viaggio: " + statoViaggio;

    }

}
