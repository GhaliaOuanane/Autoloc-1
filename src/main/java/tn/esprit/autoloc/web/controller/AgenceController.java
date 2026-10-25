package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class AgenceController {

    private final IAgenceService agenceService;

    @PostMapping
    public ResponseEntity<Agence> creerAgence(@RequestBody Agence agence) {
        log.info("POST /api/agences - Création d'une agence");
        Agence agenceCree = agenceService.creerAgence(agence);
        return new ResponseEntity<>(agenceCree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> obtenirAgence(@PathVariable Long id) {
        log.info("GET /api/agences/{}", id);
        return agenceService.obtenirAgenceParId(id)
                .map(agence -> new ResponseEntity<>(agence, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Agence>> obtenirToutesLesAgences() {
        log.info("GET /api/agences");
        List<Agence> agences = agenceService.obtenirToutesLesAgences();
        return new ResponseEntity<>(agences, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> modifierAgence(@PathVariable Long id, @RequestBody Agence agence) {
        log.info("PUT /api/agences/{}", id);
        agence.setIdAgence(id);
        Agence agenceModifiee = agenceService.modifierAgence(agence);
        return new ResponseEntity<>(agenceModifiee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerAgence(@PathVariable Long id) {
        log.info("DELETE /api/agences/{}", id);
        agenceService.supprimerAgence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterAgences() {
        log.info("GET /api/agences/count");
        long count = agenceService.compterAgences();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
