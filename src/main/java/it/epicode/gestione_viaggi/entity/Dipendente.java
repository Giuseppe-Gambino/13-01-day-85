package it.epicode.gestione_viaggi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Dipendente", query = "SELECT a FROM Dipendente a")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "cover")
    private String cover;

    @Column(name = "email")
    private String email;



}
