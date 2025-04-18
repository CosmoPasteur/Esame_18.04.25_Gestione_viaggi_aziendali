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

    public List<Dipendente> getAllDipendenti() { return dipendenteRepository.findAll(); }

    public Dipendente getDipendenteById(Long id) {
        Optional<Dipendente> dipendente = dipendenteRepository.findById(id);
        return dipendente.orElse(null); // Se non trovato, restituisce null
    }

    public Dipendente createDipendente(Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    /*public Dipendente updateDipendente(Long id, Dipendente dipendenteDetails) {
        Optional<Dipendente> dipendenteOptional = dipendenteRepository.findById(id);
        if (dipendenteOptional.isPresent()) {
            Dipendente dipendente = dipendenteOptional.get();
            dipendente.setUsername(dipendenteDetails.getUsername());
            dipendente.setNome(dipendenteDetails.getNome());
            dipendente.setCognome(dipendenteDetails.getCognome());
            dipendente.setEmail(dipendenteDetails.getEmail());
            dipendente.setImmagineProfiloPath(dipendenteDetails.getImmagineProfiloPath());
            return dipendenteRepository.save(dipendente);
        }
        return null; // Se non trovato, restituisce null
    }*/

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
