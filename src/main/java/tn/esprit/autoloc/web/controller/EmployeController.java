package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Employés", description = "Gestion des employés")
public class EmployeController {

    private final IEmployeService employeService;

    @PostMapping
    public ResponseEntity<Employe> creerEmploye(@RequestBody Employe employe) {
        log.info("POST /api/employes - Création d'un employé");
        Employe employeCree = employeService.creerEmploye(employe);
        return new ResponseEntity<>(employeCree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> obtenirEmploye(@PathVariable Long id) {
        log.info("GET /api/employes/{}", id);
        return employeService.obtenirEmployeParId(id)
                .map(employe -> new ResponseEntity<>(employe, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Employe>> obtenirTousLesEmployes() {
        log.info("GET /api/employes");
        List<Employe> employes = employeService.obtenirTousLesEmployes();
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> modifierEmploye(@PathVariable Long id, @RequestBody Employe employe) {
        log.info("PUT /api/employes/{}", id);
        employe.setIdEmploye(id);
        Employe employeModifie = employeService.modifierEmploye(employe);
        return new ResponseEntity<>(employeModifie, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEmploye(@PathVariable Long id) {
        log.info("DELETE /api/employes/{}", id);
        employeService.supprimerEmploye(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterEmployes() {
        log.info("GET /api/employes/count");
        long count = employeService.compterEmployes();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
