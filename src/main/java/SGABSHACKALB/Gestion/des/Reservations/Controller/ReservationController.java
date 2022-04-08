package SGABSHACKALB.Gestion.des.Reservations.Controller;

import SGABSHACKALB.Gestion.des.Reservations.Entities.Collaborateur;
import SGABSHACKALB.Gestion.des.Reservations.Entities.Etage;
import SGABSHACKALB.Gestion.des.Reservations.Entities.Reservation;
import SGABSHACKALB.Gestion.des.Reservations.Repositories.CollaborateurRepository;
import SGABSHACKALB.Gestion.des.Reservations.Repositories.EtageRepository;
import SGABSHACKALB.Gestion.des.Reservations.Repositories.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    private CollaborateurRepository collaborateurRepository;
    private EtageRepository etageRepository;
    private ReservationRepository reservationRepository;

    public ReservationController(CollaborateurRepository collaborateurRepository, EtageRepository etageRepository, ReservationRepository reservationRepository) {
        this.collaborateurRepository = collaborateurRepository;
        this.etageRepository = etageRepository;
        this.reservationRepository = reservationRepository;
    }

    //*********************** Collaborateur *****************************************//

    @PostMapping("/saveCollab")
    public HttpStatus saveCollab(@RequestBody Collaborateur collaborateur) {
        collaborateurRepository.save(collaborateur);
        return HttpStatus.CREATED;
    }

    @GetMapping(path = "/collab/{nom}")
    public Collaborateur getCollab(@PathVariable String nom) {
        return collaborateurRepository.findByNom(nom.toLowerCase());
    }

    @GetMapping(path = "/collab/domaine")
    public Collaborateur getCollabDomaine(@PathVariable String domaine) {
        return collaborateurRepository.findByDomaine(domaine);
    }

    @GetMapping(path = "/allCollab")
    public List<Collaborateur> collaborateurList() {
        return collaborateurRepository.findAll();
    }

    //***************************** Etage ********************************************//

    @PostMapping(path = "/saveEtage")
    public HttpStatus saveEtage(@RequestBody Etage etage) {
        etageRepository.save(etage);
        return HttpStatus.CREATED;
    }

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

    @GetMapping(path = "/reservation/{position}")
    public Reservation getReservation(@PathVariable String position){
        return  reservationRepository.findByPosition(position);
    }

    @PostMapping(path = "/saveReservation")
    public HttpStatus newReservation(@RequestBody Reservation reservation){
        reservationRepository.save(reservation);
        return HttpStatus.CREATED;
    }

    @DeleteMapping(path = "/supprimerReservation")
    public HttpStatus deleteById(@PathVariable(name = "id") Long id){
        reservationRepository.deleteById(id);
        return HttpStatus.GONE;
    }
}
