package mariangelamarasciuolo.ProgettoSettimanales7d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "eventi")
public class Eventi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titolo;
    private String descrizione;
    private LocalDate dataEvento;

    private String luogo;
    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
}
