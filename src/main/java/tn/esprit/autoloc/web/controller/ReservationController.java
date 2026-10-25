package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> creerReservation(@RequestBody Reservation reservation) {
        log.info("POST /api/reservations - Création d'une réservation");
        Reservation reservationCreee = reservationService.creerReservation(reservation);
        return new ResponseEntity<>(reservationCreee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> obtenirReservation(@PathVariable Long id) {
        log.info("GET /api/reservations/{}", id);
        return reservationService.obtenirReservationParId(id)
                .map(reservation -> new ResponseEntity<>(reservation, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> obtenirToutesLesReservations() {
        log.info("GET /api/reservations");
        List<Reservation> reservations = reservationService.obtenirToutesLesReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> modifierReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        log.info("PUT /api/reservations/{}", id);
        reservation.setIdReservation(id);
        Reservation reservationModifiee = reservationService.modifierReservation(reservation);
        return new ResponseEntity<>(reservationModifiee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerReservation(@PathVariable Long id) {
        log.info("DELETE /api/reservations/{}", id);
        reservationService.supprimerReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterReservations() {
        log.info("GET /api/reservations/count");
        long count = reservationService.compterReservations();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
