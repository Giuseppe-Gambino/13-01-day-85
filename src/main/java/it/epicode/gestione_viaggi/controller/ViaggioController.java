package it.epicode.gestione_viaggi.controller;

import it.epicode.gestione_viaggi.dto.RequestCambioStato;
import it.epicode.gestione_viaggi.dto.RequestViaggio;
import it.epicode.gestione_viaggi.entity.Viaggio;
import it.epicode.gestione_viaggi.services.ViaggioSvc;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/viaggio")
public class ViaggioController {
    private final ViaggioSvc viaggioSvc;

    @GetMapping("/viaggiAll")
    @PreAuthorize("permitAll")
    public ResponseEntity<List<Viaggio>> getAll(){
        return ResponseEntity.ok(viaggioSvc.getAll());
    }



    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(viaggioSvc.findById(id));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Viaggio> save(@Valid @RequestBody RequestViaggio requestViaggio) {
        return new ResponseEntity<>(viaggioSvc.save(requestViaggio), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody RequestViaggio d) {
        return ResponseEntity.ok(viaggioSvc.edit(id, d));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody RequestCambioStato d) {
        return ResponseEntity.ok(viaggioSvc.editStato(id, d));
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        viaggioSvc.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
