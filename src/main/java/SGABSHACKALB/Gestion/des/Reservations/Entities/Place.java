package SGABSHACKALB.Gestion.des.Reservations.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namePosition;
    private Double longX;
    private Double latY;
    private int rayon;

    public Place(String namePosition, Double longX, Double latY, int rayon) {
        this.namePosition = namePosition;
        this.longX = longX;
        this.latY = latY;
        this.rayon = rayon;
    }
    @ManyToOne
    private Etage etage;

    @OneToMany(mappedBy = "place" , fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Reservation> reservations;
}
