package it.epicode.Gestione_viaggi_aziendali.azienda.controller;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.Dipendente;
import it.epicode.Gestione_viaggi_aziendali.azienda.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // Recupera tutti i dipendenti
    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return dipendenteService.getAllDipendenti();
    }

    // Recupera un dipendente per ID
    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getDipendenteById(@PathVariable Long id) {
        Dipendente dipendente = dipendenteService.getDipendenteById(id);
        if (dipendente != null) {
            return ResponseEntity.ok(dipendente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Crea un nuovo dipendente
    @PostMapping
    public ResponseEntity<Dipendente> createDipendente(@RequestBody Dipendente dipendente) {
        Dipendente nuovoDipendente = dipendenteService.createDipendente(dipendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoDipendente);
    }

    // Modifica un dipendente esistente
    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateDipendente(@PathVariable Long id, @RequestBody Dipendente dipendenteDetails) {
        Dipendente dipendenteAggiornato = dipendenteService.updateDipendente(id, dipendenteDetails);
        if (dipendenteAggiornato != null) {
            return ResponseEntity.ok(dipendenteAggiornato);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Elimina un dipendente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
