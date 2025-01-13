package it.epicode.gestione_viaggi.services;

import it.epicode.gestione_viaggi.dto.RequestCambioStato;
import it.epicode.gestione_viaggi.dto.RequestViaggio;
import it.epicode.gestione_viaggi.entity.Viaggio;
import it.epicode.gestione_viaggi.enums.stato;
import it.epicode.gestione_viaggi.repo.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ViaggioSvc {
    private final ViaggioRepository viaggioRepo;
    private final PrenotazioneSvc prenotazioneSvc;

    public List<Viaggio> getAll() {
        return viaggioRepo.findAll();
    }

    public Optional<Viaggio> findById(Long id) {
        if(!viaggioRepo.existsById(id)) {
            throw new EntityNotFoundException("Il viaggio non è stato trovato");
        }

        return viaggioRepo.findById(id);
    }

    public Viaggio save(RequestViaggio requestViaggio) {
        Viaggio dipendente = new Viaggio();
        BeanUtils.copyProperties(requestViaggio, dipendente);
        return viaggioRepo.save(dipendente);
    }


    public Viaggio edit(Long id, RequestViaggio newViaggio) {

        Viaggio existingViaggio = viaggioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio con ID " + id + " non trovato"));

        BeanUtils.copyProperties(newViaggio, existingViaggio);


        return viaggioRepo.save(existingViaggio);
    }

    public Viaggio editStato(Long id, RequestCambioStato newViaggio) {
        if(!viaggioRepo.existsById(id)) {
            throw new EntityNotFoundException("Il viaggio non è stato trovato");
        }

        if (newViaggio.getStato() == null ||
                (newViaggio.getStato() != stato.IN_PROGRAMMA &&
                        newViaggio.getStato() != stato.COMPLETATO)) {
            throw new IllegalArgumentException("Lo stato deve essere IN_PROGRAMMA oppure COMPLETATO");
        }

        Viaggio existingViaggio = viaggioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaggio con ID " + id + " non trovato"));

        existingViaggio.setStato(newViaggio.getStato());

        return viaggioRepo.save(existingViaggio);
    }

    public void delete(Long id) {
        if(!viaggioRepo.existsById(id)) {
            throw new EntityNotFoundException("Viaggio non è stato trovato");
        }

        if (prenotazioneSvc.existsByViaggioId(id)){
            throw new IllegalStateException("Non puoi eliminare un viaggio con delle prenotazioni in corso, disdici prima le prenotazioni");
        }

        viaggioRepo.deleteById(id);
    }

}
