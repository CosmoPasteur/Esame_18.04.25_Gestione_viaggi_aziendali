package it.epicode.Gestione_viaggi_aziendali.azienda.repository;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    Prenotazione findByDipendenteIdAndViaggioId(Long dipendenteId, Long viaggioId);

    boolean existsByDipendenteIdAndDataRichiesta(Long dipendenteId, LocalDate dataRichiesta);

}
