package it.epicode.Gestione_viaggi_aziendali.azienda.service;

import it.epicode.Gestione_viaggi_aziendali.azienda.dto.PrenotazioneDTO;
import it.epicode.Gestione_viaggi_aziendali.azienda.entities.Dipendente;
import it.epicode.Gestione_viaggi_aziendali.azienda.entities.Prenotazione;
import it.epicode.Gestione_viaggi_aziendali.azienda.entities.Viaggio;
import it.epicode.Gestione_viaggi_aziendali.azienda.service.ViaggioService;
import it.epicode.Gestione_viaggi_aziendali.azienda.exception.NotFoundException;
import it.epicode.Gestione_viaggi_aziendali.azienda.repository.DipendenteRepository;
import it.epicode.Gestione_viaggi_aziendali.azienda.repository.PrenotazioneRepository;
import it.epicode.Gestione_viaggi_aziendali.azienda.repository.ViaggioRepository;
import it.epicode.Gestione_viaggi_aziendali.azienda.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository  prenotazioneRepository;
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private ViaggioService viaggioService;

    public Page<Prenotazione> findAllPrenotazioni(int page, int size, String sortBy, Long dipendenteId) {
        if (dipendenteId != null) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Dipendente dipendente = dipendenteService.findDipendenteById(dipendenteId);
            return prenotazioneRepository.findByDipendente(dipendente, pageable);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione savePrenotazione(PrenotazioneDTO body) {
        Dipendente dipendente = dipendenteService.findDipendenteById(body.dipendenteId());
        Viaggio Viaggio = viaggioService.findViaggioById(body.viaggioId());
        if (prenotazioneRepository.existsByViaggio(Viaggio))
            throw new BadRequestException("Viaggio already assigned");
        if (prenotazioneRepository.checkIfDipendenteIsNotAvailable(dipendente, Viaggio.getData()))
            throw new BadRequestException("dipendente unavailable for Viaggio's date");
        Prenotazione prenotazione= new Prenotazione(Viaggio, dipendente);
        if (body.preferenza() != null) prenotazione.setPreferenza(body.preferenza());
        else prenotazione.setPreferenza("N/A");
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione findPrenotazioniById(Long prenotazioneId) {
        return prenotazioneRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId, "prenotazioni"));
    }

    public void findPrenotazioniByIdAndDelete(Long prenotazioneId) {
        Prenotazione prenotazione = findPrenotazioniById(prenotazioneId);
        prenotazioneRepository.delete(prenotazione);
    }
}
