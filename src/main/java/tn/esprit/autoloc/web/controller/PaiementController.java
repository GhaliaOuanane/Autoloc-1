package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class PaiementController {

    private final IPaiementService paiementService;

    @PostMapping
    public ResponseEntity<Paiement> creerPaiement(@RequestBody Paiement paiement) {
        log.info("POST /api/paiements - Création d'un paiement");
        Paiement paiementCree = paiementService.creerPaiement(paiement);
        return new ResponseEntity<>(paiementCree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> obtenirPaiement(@PathVariable Long id) {
        log.info("GET /api/paiements/{}", id);
        return paiementService.obtenirPaiementParId(id)
                .map(paiement -> new ResponseEntity<>(paiement, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Paiement>> obtenirTousLesPaiements() {
        log.info("GET /api/paiements");
        List<Paiement> paiements = paiementService.obtenirTousLesPaiements();
        return new ResponseEntity<>(paiements, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> modifierPaiement(@PathVariable Long id, @RequestBody Paiement paiement) {
        log.info("PUT /api/paiements/{}", id);
        paiement.setIdPaiement(id);
        Paiement paiementModifie = paiementService.modifierPaiement(paiement);
        return new ResponseEntity<>(paiementModifie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPaiement(@PathVariable Long id) {
        log.info("DELETE /api/paiements/{}", id);
        paiementService.supprimerPaiement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterPaiements() {
        log.info("GET /api/paiements/count");
        long count = paiementService.compterPaiements();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
