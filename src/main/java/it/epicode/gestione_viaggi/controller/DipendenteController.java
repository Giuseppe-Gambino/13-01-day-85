package it.epicode.gestione_viaggi.controller;

import it.epicode.gestione_viaggi.dto.RequestDipendente;
import it.epicode.gestione_viaggi.entity.Dipendente;
import it.epicode.gestione_viaggi.services.DipendenteSvc;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {

    @Autowired
    private DipendenteSvc dipendenteSvc;

    @GetMapping
    public ResponseEntity<List<Dipendente>> getAll(){
        return ResponseEntity.ok(dipendenteSvc.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(dipendenteSvc.findById(id));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Dipendente> save(@Valid @RequestBody RequestDipendente requestDipendente) {
        return new ResponseEntity<>(dipendenteSvc.save(requestDipendente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody RequestDipendente d) {
        return ResponseEntity.ok(dipendenteSvc.edit(id, d));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dipendenteSvc.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
