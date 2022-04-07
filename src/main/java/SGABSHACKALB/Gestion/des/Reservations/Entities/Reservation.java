package SGABSHACKALB.Gestion.des.Reservations.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateReservation = LocalDate.now();
    private String position;
    private Boolean isReserved;
    @ManyToOne
    private Collaborateur collaborateur;
    @ManyToOne
    private Etage etage;

    @Override
    public String toString() {
        return "Reservation{" +
                "dateReservation=" + dateReservation +
                ", position='" + position + '\'' +
                ", isReserved=" + isReserved +
                '}';
    }
}
