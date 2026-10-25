package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    Reservation creerReservation(Reservation reservation);
    Reservation modifierReservation(Reservation reservation);
    void supprimerReservation(Long idReservation);
    Optional<Reservation> obtenirReservationParId(Long idReservation);
    List<Reservation> obtenirToutesLesReservations();
    long compterReservations();
}
