package it.epicode.Gestione_viaggi_aziendali.azienda.service;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.StatoViaggio;
import it.epicode.Gestione_viaggi_aziendali.azienda.model.Viaggio;
import it.epicode.Gestione_viaggi_aziendali.azienda.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    // Recupera tutti i viaggi
    public List<Viaggio> getAllViaggi() {
        return viaggioRepository.findAll();
    }

    // Recupera un viaggio per ID
    public Viaggio getViaggioById(Long id) {
        Optional<Viaggio> viaggio = viaggioRepository.findById(id);
        return viaggio.orElse(null); // Restituisce null se non trovato
    }

    // Crea un nuovo viaggio
    public Viaggio createViaggio(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    // Modifica lo stato di un viaggio
    public Viaggio updateStatoViaggio(Long id, String stato) {
        Optional<Viaggio> viaggio = viaggioRepository.findById(id);
        if (viaggio.isPresent()) {
            Viaggio viaggioEsistente = viaggio.get();
            viaggioEsistente.setStato(StatoViaggio.valueOf(stato));
            return viaggioRepository.save(viaggioEsistente);
        } else {
            return null; // Se il viaggio non esiste
        }
    }

    // Elimina un viaggio
    public void deleteViaggio(Long id) {
        viaggioRepository.deleteById(id);
    }

    // Trova un viaggio per destinazione
    public Viaggio getViaggioByDestinazione(String destinazione) {
        return viaggioRepository.findByDestinazione(destinazione);
    }

    // Trova un viaggio per stato
    public Viaggio getViaggioByStato(String stato) {
        return viaggioRepository.findByStato(stato);
    }

}
