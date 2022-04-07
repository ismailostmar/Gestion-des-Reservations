package SGABSHACKALB.Gestion.des.Reservations.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Data
public class Etage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String emplacement;
    private String domaineEtage;
    private int nombrePosition;
    private int nombrePosEtage;

    public Etage(String emplacement, String domaineEtage, int nombrePosition, int nombrePosEtage) {
        this.emplacement = emplacement;
        this.domaineEtage = domaineEtage;
        this.nombrePosition = nombrePosition;
        this.nombrePosEtage = nombrePosEtage;
    }

    @OneToMany(mappedBy = "etage" , fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Reservation> reservations;
}
