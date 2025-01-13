package it.epicode.gestione_viaggi.dto;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class RequestViaggio {

    @NotBlank(message = "Il campo destinazione non può essere vuoto")
    private String destinazione;

    @NotNull(message = "Il campo data non può essere nullo")
    @FutureOrPresent(message = "La data deve essere nel futuro o nel presente")
    private LocalDate data;

    @NotNull(message = "Il campo stato non può essere nullo. Deve essere IN_PROGRAMMA o COMPLETATO")
    private it.epicode.gestione_viaggi.enums.stato stato;
}
