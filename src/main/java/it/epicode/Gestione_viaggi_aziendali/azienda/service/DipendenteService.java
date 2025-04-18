package it.epicode.Gestione_viaggi_aziendali.azienda.service;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.Dipendente;
import it.epicode.Gestione_viaggi_aziendali.azienda.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    // Recupera tutti i dipendenti
    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    // Recupera un dipendente per ID
    public Dipendente getDipendenteById(Long id) {
        Optional<Dipendente> dipendente = dipendenteRepository.findById(id);
        return dipendente.orElse(null); // Restituisce null se non trovato
    }

    // Crea un nuovo dipendente
    public Dipendente createDipendente(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }


    // Modifica un dipendente esistente
    public Dipendente updateDipendente(Long id, Dipendente dipendenteDetails) {
        Optional<Dipendente> optionalDipendente = dipendenteRepository.findById(id);
        if (optionalDipendente.isPresent()) {
            Dipendente dipendente = optionalDipendente.get();
            dipendente.setUsername(dipendenteDetails.getUsername());
            dipendente.setNome(dipendenteDetails.getNome());
            dipendente.setCognome(dipendenteDetails.getCognome());
            dipendente.setEmail(dipendenteDetails.getEmail());
            dipendente.setImmagineProfiloPath(dipendenteDetails.getImmagineProfiloPath());
            return dipendenteRepository.save(dipendente);
        } else {
            return null; // Se il dipendente non esiste
        }
    }

    // Elimina un dipendente
    public void deleteDipendente(Long id) {
        dipendenteRepository.deleteById(id);
    }

    // Trova un dipendente per username
    public Dipendente getDipendenteByUsername(String username) {
        return dipendenteRepository.findByUsername(username);
    }

    // Trova un dipendente per email
    public Dipendente getDipendenteByEmail(String email) {
        return dipendenteRepository.findByEmail(email);
    }
}
