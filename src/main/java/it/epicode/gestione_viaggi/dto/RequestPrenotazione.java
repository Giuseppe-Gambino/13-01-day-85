package it.epicode.gestione_viaggi.dto;

import it.epicode.gestione_viaggi.entity.Dipendente;
import it.epicode.gestione_viaggi.entity.Viaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestPrenotazione {


    @NotNull(message = "Il campo titolo non può essere vuoto")
    private LocalDate dataRichiesta;

    @NotBlank(message = "Il campo titolo non può essere vuoto")
    private String note;
}
