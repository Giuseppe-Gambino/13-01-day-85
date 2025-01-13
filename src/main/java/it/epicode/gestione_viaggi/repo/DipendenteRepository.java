package it.epicode.gestione_viaggi.repo;

import it.epicode.gestione_viaggi.entity.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
}