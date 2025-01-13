package it.epicode.gestione_viaggi.services;

import it.epicode.gestione_viaggi.dto.RequestPrenotazione;
import it.epicode.gestione_viaggi.entity.Dipendente;
import it.epicode.gestione_viaggi.entity.Prenotazione;
import it.epicode.gestione_viaggi.entity.Viaggio;
import it.epicode.gestione_viaggi.repo.DipendenteRepository;
import it.epicode.gestione_viaggi.repo.PrenotazioneRepository;
import it.epicode.gestione_viaggi.repo.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrenotazioneSvc {
    private final PrenotazioneRepository prenotazioneRepo;
    private final DipendenteRepository dipendenteRepo;
    private final ViaggioRepository viaggioRepo;

    public List<Prenotazione> getAll() {
        return prenotazioneRepo.findAll();
    }

    public Optional<Prenotazione> findById(Long id) {
        if(!prenotazioneRepo.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }

        return prenotazioneRepo.findById(id);
    }

    public Prenotazione save(Long idDip, Long idViag, RequestPrenotazione requestPrenotazione) {



        Dipendente d = dipendenteRepo.findById(idDip)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        Viaggio v = viaggioRepo.findById(idViag)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        List<Prenotazione> prenotazioneList = prenotazioneRepo.findByDipendente(d);

        for (Prenotazione pre : prenotazioneList) {
            if (pre.getDataRichiesta().equals(requestPrenotazione.getDataRichiesta())) { // Usa equals per confrontare le date
                throw new IllegalStateException("L'utente ha già una prenotazione per la data specificata.");
            }
        }

        Prenotazione p = new Prenotazione();
        BeanUtils.copyProperties(requestPrenotazione, p);
        p.setDipendente(d);
        p.setViaggio(v);
        return prenotazioneRepo.save(p);
    }


    public Prenotazione edit(Long id, RequestPrenotazione newPrenotazione) {
        if(!prenotazioneRepo.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }

        Prenotazione existingPrenotazione = prenotazioneRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione con ID " + id + " non trovata"));

        BeanUtils.copyProperties(newPrenotazione, existingPrenotazione);


        return prenotazioneRepo.save(existingPrenotazione);
    }

    public void delete(Long id) {
        if(!prenotazioneRepo.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }
        prenotazioneRepo.deleteById(id);
    }

    public boolean existsByViaggioId(Long id) {
        return prenotazioneRepo.existsByViaggioId(id);
    }
}
