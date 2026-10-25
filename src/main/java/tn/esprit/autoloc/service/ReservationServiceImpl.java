package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation creerReservation(Reservation reservation) {
        log.info("Création d'une nouvelle réservation");
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation modifierReservation(Reservation reservation) {
        log.info("Modification de la réservation avec ID: {}", reservation.getIdReservation());
        return reservationRepository.save(reservation);
    }

    @Override
    public void supprimerReservation(Long idReservation) {
        log.info("Suppression de la réservation avec ID: {}", idReservation);
        reservationRepository.deleteById(idReservation);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> obtenirReservationParId(Long idReservation) {
        return reservationRepository.findById(idReservation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> obtenirToutesLesReservations() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long compterReservations() {
        return reservationRepository.count();
    }
}
