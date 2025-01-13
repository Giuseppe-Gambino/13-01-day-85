package it.epicode.gestione_viaggi.repo;

import it.epicode.gestione_viaggi.entity.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}