package it.epicode.Gestione_viaggi_aziendali.azienda.repository;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {

    Viaggio findByDestinazione(String destinazione);

    Viaggio findByStato(String stato);

}
