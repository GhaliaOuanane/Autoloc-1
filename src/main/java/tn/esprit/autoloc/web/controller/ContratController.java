package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ContratController {

    private final IContratService contratService;

    @PostMapping
    public ResponseEntity<Contrat> creerContrat(@RequestBody Contrat contrat) {
        log.info("POST /api/contrats - Création d'un contrat");
        Contrat contratCree = contratService.creerContrat(contrat);
        return new ResponseEntity<>(contratCree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> obtenirContrat(@PathVariable Long id) {
        log.info("GET /api/contrats/{}", id);
        return contratService.obtenirContratParId(id)
                .map(contrat -> new ResponseEntity<>(contrat, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Contrat>> obtenirTousLesContrats() {
        log.info("GET /api/contrats");
        List<Contrat> contrats = contratService.obtenirTousLesContrats();
        return new ResponseEntity<>(contrats, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> modifierContrat(@PathVariable Long id, @RequestBody Contrat contrat) {
        log.info("PUT /api/contrats/{}", id);
        contrat.setIdContrat(id);
        Contrat contratModifie = contratService.modifierContrat(contrat);
        return new ResponseEntity<>(contratModifie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerContrat(@PathVariable Long id) {
        log.info("DELETE /api/contrats/{}", id);
        contratService.supprimerContrat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterContrats() {
        log.info("GET /api/contrats/count");
        long count = contratService.compterContrats();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
