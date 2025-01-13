package it.epicode.gestione_viaggi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@NamedQuery(name="Trova_tutto_Prenotazione", query="SELECT a FROM Prenotazione a")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    @Column(name = "data_richiesta")
    private LocalDate dataRichiesta;

    @Column(name = "note")
    private String note;

}
