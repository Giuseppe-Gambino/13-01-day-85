package it.epicode.gestione_viaggi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestCambioStato {

    @NotNull(message = "Il campo stato non può essere vuoto")
    private it.epicode.gestione_viaggi.enums.stato stato;
}
