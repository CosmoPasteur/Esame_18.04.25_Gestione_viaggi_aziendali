package it.epicode.Gestione_viaggi_aziendali.azienda.repository;

import it.epicode.Gestione_viaggi_aziendali.azienda.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {

    Dipendente findByUsername(String username);

    Dipendente findByEmail(String email);

}
