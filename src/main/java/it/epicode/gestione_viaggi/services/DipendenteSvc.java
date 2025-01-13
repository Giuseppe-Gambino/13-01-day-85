package it.epicode.gestione_viaggi.services;

import it.epicode.gestione_viaggi.dto.RequestDipendente;
import it.epicode.gestione_viaggi.entity.Dipendente;
import it.epicode.gestione_viaggi.repo.DipendenteRepository;
import it.epicode.gestione_viaggi.repo.PrenotazioneRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DipendenteSvc {
    private final DipendenteRepository dipendenteRepo;
    private final PrenotazioneRepository prenotazioneRepo;

    public List<Dipendente> getAll() {
        return dipendenteRepo.findAll();
    }

    public Optional<Dipendente> findById(Long id) {
        if(!dipendenteRepo.existsById(id)) {
            throw new EntityNotFoundException("Dipendente non trovato");
        }

        return dipendenteRepo.findById(id);
    }

    public Dipendente save(RequestDipendente requestDipendente) {
        Dipendente dipendente = new Dipendente();
        BeanUtils.copyProperties(requestDipendente, dipendente);
        return dipendenteRepo.save(dipendente);
    }


    public Dipendente edit(Long id, RequestDipendente newDipendente) {

        Dipendente existingDipendente = dipendenteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente con ID " + id + " non trovato"));

        BeanUtils.copyProperties(newDipendente, existingDipendente);


        return dipendenteRepo.save(existingDipendente);
    }

    public Dipendente editCover(Long id, String newCover) {
        if(!dipendenteRepo.existsById(id)) {
            throw new EntityNotFoundException("Il Dipendente non è stato trovato");
        }


        Dipendente existingDipendente = dipendenteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Dipendente con ID " + id + " non trovato"));

        existingDipendente.setCover(newCover);

        return dipendenteRepo.save(existingDipendente);
    }



    @Transactional
    public void delete(Long id) {

        prenotazioneRepo.deleteByDipendenteId(id);

        if(!dipendenteRepo.existsById(id)) {
            throw new EntityNotFoundException("Dipendente non trovato");
        }
        dipendenteRepo.deleteById(id);
    }

}
