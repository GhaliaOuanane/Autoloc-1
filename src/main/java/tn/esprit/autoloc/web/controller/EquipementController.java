package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class EquipementController {

    private final IEquipementService equipementService;

    @PostMapping
    public ResponseEntity<Equipement> creerEquipement(@RequestBody Equipement equipement) {
        log.info("POST /api/equipements - Création d'un équipement");
        Equipement equipementCree = equipementService.creerEquipement(equipement);
        return new ResponseEntity<>(equipementCree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> obtenirEquipement(@PathVariable Long id) {
        log.info("GET /api/equipements/{}", id);
        return equipementService.obtenirEquipementParId(id)
                .map(equipement -> new ResponseEntity<>(equipement, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Equipement>> obtenirTousLesEquipements() {
        log.info("GET /api/equipements");
        List<Equipement> equipements = equipementService.obtenirTousLesEquipements();
        return new ResponseEntity<>(equipements, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> modifierEquipement(@PathVariable Long id, @RequestBody Equipement equipement) {
        log.info("PUT /api/equipements/{}", id);
        equipement.setIdEquipement(id);
        Equipement equipementModifie = equipementService.modifierEquipement(equipement);
        return new ResponseEntity<>(equipementModifie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEquipement(@PathVariable Long id) {
        log.info("DELETE /api/equipements/{}", id);
        equipementService.supprimerEquipement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterEquipements() {
        log.info("GET /api/equipements/count");
        long count = equipementService.compterEquipements();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
