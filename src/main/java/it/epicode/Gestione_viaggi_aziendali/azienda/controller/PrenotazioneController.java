package it.epicode.Gestione_viaggi_aziendali.azienda.controller;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.Prenotazione;
import it.epicode.Gestione_viaggi_aziendali.azienda.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    // Crea una nuova prenotazione
    @PostMapping
    public ResponseEntity<Prenotazione> createPrenotazione(@RequestParam Long dipendenteId,
                                                           @RequestParam Long viaggioId,
                                                           @RequestParam LocalDate dataRichiesta,
                                                           @RequestParam(required = false) String note) {
        Prenotazione prenotazione = prenotazioneService.createPrenotazione(dipendenteId, viaggioId, dataRichiesta, note);
        if (prenotazione != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(prenotazione);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // In caso di conflitto di prenotazione
        }
    }

    // Elimina una prenotazione
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
