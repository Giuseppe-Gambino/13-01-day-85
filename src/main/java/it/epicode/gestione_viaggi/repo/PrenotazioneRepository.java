package it.epicode.gestione_viaggi.repo;

import it.epicode.gestione_viaggi.entity.Dipendente;
import it.epicode.gestione_viaggi.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    List<Prenotazione> findByDipendente(Dipendente dipendente);

    void deleteByDipendenteId(Long id);

    boolean existsByViaggioId(Long viaggioId);

}