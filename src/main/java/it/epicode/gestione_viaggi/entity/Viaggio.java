package it.epicode.gestione_viaggi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Viaggio", query = "SELECT a FROM Viaggio a")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "destinazione")
    private String destinazione;

    @Column(name = "data")
    private LocalDate data;

    @Enumerated
    @Column(name = "stato")
    private it.epicode.gestione_viaggi.enums.stato stato;


}
