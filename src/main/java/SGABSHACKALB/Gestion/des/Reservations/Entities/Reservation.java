package SGABSHACKALB.Gestion.des.Reservations.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date dateDebut;
    @NotNull
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    private Date dateFin;
    private Boolean isReserved;
    @ManyToOne
    private Collaborateur collaborateur;
    @ManyToOne
    private Place place;

    @Override
    public String toString() {
        return "Reservation{" +
                "dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", isReserved=" + isReserved +
                '}';
    }
}
