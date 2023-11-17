package mariangelamarasciuolo.ProgettoSettimanales7d5.payload;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record EventiDTO(@NotEmpty(message = "Campo del titolo Obbligatorio!") String titolo,
                        @NotEmpty(message = "Campo della descrizione Obbligatorio!") String descrizione,
                        @NotEmpty(message = "Campo della data Obbligatorio!") LocalDate dataEvento,
                        @NotEmpty(message = "Campo del luogo Obbligatorio!") String luogo,
                        @NotEmpty(message = "Campo dei posti disponibili Obbligatorio!") int postiDisponibili) {
}