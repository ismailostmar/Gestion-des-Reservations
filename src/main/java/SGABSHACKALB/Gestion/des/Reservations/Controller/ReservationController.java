package SGABSHACKALB.Gestion.des.Reservations.Controller;

import SGABSHACKALB.Gestion.des.Reservations.Entities.Collaborateur;
import SGABSHACKALB.Gestion.des.Reservations.Entities.Etage;
import SGABSHACKALB.Gestion.des.Reservations.Entities.Reservation;
import SGABSHACKALB.Gestion.des.Reservations.Repositories.CollaborateurRepository;
import SGABSHACKALB.Gestion.des.Reservations.Repositories.EtageRepository;
import SGABSHACKALB.Gestion.des.Reservations.Repositories.ReservationRepository;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    private CollaborateurRepository collaborateurRepository;
    private EtageRepository etageRepository;
    private ReservationRepository reservationRepository;

    public ReservationController(CollaborateurRepository collaborateurRepository, EtageRepository etageRepository, ReservationRepository reservationRepository , RepositoryRestConfiguration restConfiguration) {
        restConfiguration.exposeIdsFor(Collaborateur.class,Etage.class,Reservation.class);
        this.collaborateurRepository = collaborateurRepository;
        this.etageRepository = etageRepository;
        this.reservationRepository = reservationRepository;
    }

    //*********************** Collaborateur *****************************************//

    @GetMapping(path = "/collab/{nom}")
    public Collaborateur getCollab(@PathVariable String nom) {
        return collaborateurRepository.findByNom(nom.toLowerCase());
    }
    //***************************** Etage ********************************************//

    @GetMapping(path = "/etage")
    public List<Etage> etages() {
        return etageRepository.findAll();
    }

    @GetMapping(path = "/etage/{domaineEtage}")
    public Etage getEtage(@PathVariable String domaineEtage) {
        return etageRepository.findByDomaineEtage(domaineEtage);
    }

    //**************************** Reservations *****************************************//

    @GetMapping(path = "/reservation")
    public List<Reservation> reservationList(){
        return reservationRepository.findAll();
    }

    @DeleteMapping(path = "/supprimerReservation")
    public HttpStatus deleteById(@PathVariable(name = "id") Long id){
        reservationRepository.deleteById(id);
        return HttpStatus.GONE;
    }
}