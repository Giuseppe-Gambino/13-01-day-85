package it.epicode.gestione_viaggi.controller;

import it.epicode.gestione_viaggi.dto.RequestPrenotazione;
import it.epicode.gestione_viaggi.entity.Dipendente;
import it.epicode.gestione_viaggi.entity.Prenotazione;
import it.epicode.gestione_viaggi.services.DipendenteSvc;
import it.epicode.gestione_viaggi.services.PrenotazioneSvc;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazione")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneSvc prenotazioneSvc;

    @GetMapping
    private ResponseEntity<List<Prenotazione>> getAll(){
        return ResponseEntity.ok(prenotazioneSvc.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(prenotazioneSvc.findById(id));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{idDip}/{idViag}")
    public ResponseEntity<Prenotazione> save(@PathVariable Long idDip, @PathVariable Long idViag, @Valid @RequestBody RequestPrenotazione r) {
        return new ResponseEntity<>(prenotazioneSvc.save(idDip,idViag,r), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody RequestPrenotazione d) {
        return ResponseEntity.ok(prenotazioneSvc.edit(id, d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prenotazioneSvc.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
