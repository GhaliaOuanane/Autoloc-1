package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    public ResponseEntity<Vehicule> creerVehicule(@RequestBody Vehicule vehicule) {
        log.info("POST /api/vehicules - Création d'un véhicule");
        try {
            Vehicule vehiculeCree = vehiculeService.creerVehicule(vehicule);
            return new ResponseEntity<>(vehiculeCree, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.error("Erreur lors de la création du véhicule: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> obtenirVehicule(@PathVariable Long id) {
        log.info("GET /api/vehicules/{}", id);
        return vehiculeService.obtenirVehiculeParId(id)
                .map(vehicule -> new ResponseEntity<>(vehicule, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Vehicule>> obtenirTousLesVehicules() {
        log.info("GET /api/vehicules");
        List<Vehicule> vehicules = vehiculeService.obtenirTousLesVehicules();
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    @GetMapping("/immatriculation/{immatriculation}")
    public ResponseEntity<Vehicule> obtenirVehiculeParImmatriculation(@PathVariable String immatriculation) {
        log.info("GET /api/vehicules/immatriculation/{}", immatriculation);
        return vehiculeService.obtenirVehiculeParImmatriculation(immatriculation)
                .map(vehicule -> new ResponseEntity<>(vehicule, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Vehicule>> obtenirVehiculesParStatut(@PathVariable StatutVehicule statut) {
        log.info("GET /api/vehicules/statut/{}", statut);
        List<Vehicule> vehicules = vehiculeService.obtenirVehiculesParStatut(statut);
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Vehicule>> obtenirVehiculesParCategorie(@PathVariable CategorieVehicule categorie) {
        log.info("GET /api/vehicules/categorie/{}", categorie);
        List<Vehicule> vehicules = vehiculeService.obtenirVehiculesParCategorie(categorie);
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    @GetMapping("/marque/{marque}")
    public ResponseEntity<List<Vehicule>> obtenirVehiculesParMarque(@PathVariable String marque) {
        log.info("GET /api/vehicules/marque/{}", marque);
        List<Vehicule> vehicules = vehiculeService.obtenirVehiculesParMarque(marque);
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> modifierVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        log.info("PUT /api/vehicules/{}", id);
        try {
            vehicule.setIdVehicule(id);
            Vehicule vehiculeModifie = vehiculeService.modifierVehicule(vehicule);
            return new ResponseEntity<>(vehiculeModifie, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Erreur lors de la modification du véhicule: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerVehicule(@PathVariable Long id) {
        log.info("DELETE /api/vehicules/{}", id);
        try {
            vehiculeService.supprimerVehicule(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            log.error("Erreur lors de la suppression du véhicule: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterVehicules() {
        log.info("GET /api/vehicules/count");
        long count = vehiculeService.compterVehicules();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
