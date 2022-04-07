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
public class Collaborateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String domaine;
    private String pole;
    private String fonction;

    public Collaborateur(String nom, String prenom, String domaine, String pole, String fonction) {
        this.nom = nom;
        this.prenom = prenom;
        this.domaine = domaine;
        this.pole = pole;
        this.fonction = fonction;
    }

    @OneToMany(mappedBy = "collaborateur" , fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Reservation> reservations;
}
