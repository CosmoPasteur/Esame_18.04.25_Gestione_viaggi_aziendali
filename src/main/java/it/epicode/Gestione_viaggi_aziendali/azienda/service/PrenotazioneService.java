package it.epicode.Gestione_viaggi_aziendali.azienda.service;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.Dipendente;
import it.epicode.Gestione_viaggi_aziendali.azienda.model.Prenotazione;
import it.epicode.Gestione_viaggi_aziendali.azienda.model.Viaggio;
import it.epicode.Gestione_viaggi_aziendali.azienda.repository.DipendenteRepository;
import it.epicode.Gestione_viaggi_aziendali.azienda.repository.PrenotazioneRepository;
import it.epicode.Gestione_viaggi_aziendali.azienda.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    // Crea una nuova prenotazione
    public Prenotazione createPrenotazione(Long dipendenteId, Long viaggioId, LocalDate dataRichiesta, String note) {
        // Verifica se il dipendente ha già una prenotazione per la stessa data
        if (prenotazioneRepository.existsByDipendenteIdAndDataRichiesta(dipendenteId, dataRichiesta)) {
            return null; // Prenotazione già esistente per quella data
        }

        // Recupera dipendente e viaggio
        Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(dipendenteId);
        Optional<Viaggio> viaggioOptional = viaggioRepository.findById(viaggioId);

        if (dipendenteOptional.isPresent() && viaggioOptional.isPresent()) {
            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setDipendente(dipendenteOptional.get());
            prenotazione.setViaggio(viaggioOptional.get());
            prenotazione.setDataRichiesta(dataRichiesta);
            prenotazione.setNote(note);
            return prenotazioneRepository.save(prenotazione);
        }
        return null; // Se dipendente o viaggio non esistono, restituisce null
    }

    // Elimina una prenotazione
    public void deletePrenotazione(Long id) {
        prenotazioneRepository.deleteById(id);
    }

}
