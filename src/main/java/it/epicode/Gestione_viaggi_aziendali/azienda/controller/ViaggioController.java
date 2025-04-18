package it.epicode.Gestione_viaggi_aziendali.azienda.controller;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.Viaggio;
import it.epicode.Gestione_viaggi_aziendali.azienda.service.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    // Recupera tutti i viaggi
    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return viaggioService.getAllViaggi();
    }

    // Recupera un viaggio per ID
    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> getViaggioById(@PathVariable Long id) {
        Viaggio viaggio = viaggioService.getViaggioById(id);
        if (viaggio != null) {
            return ResponseEntity.ok(viaggio);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Crea un nuovo viaggio
    @PostMapping
    public ResponseEntity<Viaggio> createViaggio(@RequestBody Viaggio viaggio) {
        Viaggio nuovoViaggio = viaggioService.createViaggio(viaggio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovoViaggio);
    }

    // Modifica lo stato di un viaggio
    @PutMapping("/{id}/stato")
    public ResponseEntity<Viaggio> updateStatoViaggio(@PathVariable Long id, @RequestBody String stato) {
        Viaggio viaggioAggiornato = viaggioService.updateStatoViaggio(id, stato);
        if (viaggioAggiornato != null) {
            return ResponseEntity.ok(viaggioAggiornato);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Elimina un viaggio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteViaggio(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
