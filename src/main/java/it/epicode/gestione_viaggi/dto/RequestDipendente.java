package it.epicode.gestione_viaggi.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestDipendente {

    @NotBlank(message = "Il campo username non può essere vuoto")
    private String username;

    @NotBlank(message = "Il campo nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Il campo cognome non può essere vuoto")
    private String cognome;

    @NotBlank(message = "L'email non può essere vuota")
//    @Email(message = "Inserisci un'email valida")
    private String email;
}
