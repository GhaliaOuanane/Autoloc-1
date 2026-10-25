package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<Maintenance> creerMaintenance(@RequestBody Maintenance maintenance) {
        log.info("POST /api/maintenances - Création d'une maintenance");
        Maintenance maintenanceCreee = maintenanceService.creerMaintenance(maintenance);
        return new ResponseEntity<>(maintenanceCreee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> obtenirMaintenance(@PathVariable Long id) {
        log.info("GET /api/maintenances/{}", id);
        return maintenanceService.obtenirMaintenanceParId(id)
                .map(maintenance -> new ResponseEntity<>(maintenance, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> obtenirToutesLesMaintenances() {
        log.info("GET /api/maintenances");
        List<Maintenance> maintenances = maintenanceService.obtenirToutesLesMaintenances();
        return new ResponseEntity<>(maintenances, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> modifierMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        log.info("PUT /api/maintenances/{}", id);
        maintenance.setIdMaintenance(id);
        Maintenance maintenanceModifiee = maintenanceService.modifierMaintenance(maintenance);
        return new ResponseEntity<>(maintenanceModifiee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerMaintenance(@PathVariable Long id) {
        log.info("DELETE /api/maintenances/{}", id);
        maintenanceService.supprimerMaintenance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterMaintenances() {
        log.info("GET /api/maintenances/count");
        long count = maintenanceService.compterMaintenances();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
