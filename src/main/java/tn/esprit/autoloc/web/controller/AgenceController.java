package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Agences", description = "Gestion des agences")
public class AgenceController {

    private final IAgenceService agenceService;

    @PostMapping
    @Operation(summary = "Créer une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agence créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<Agence> creerAgence(@RequestBody Agence agence) {
        log.info("POST /api/agences - Création d'une agence");
        Agence agenceCree = agenceService.creerAgence(agence);
        return new ResponseEntity<>(agenceCree, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvée"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public ResponseEntity<Agence> obtenirAgence(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id) {
        log.info("GET /api/agences/{}", id);
        return agenceService.obtenirAgenceParId(id)
                .map(agence -> new ResponseEntity<>(agence, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @Operation(summary = "Lister toutes les agences")
    @ApiResponse(responseCode = "200", description = "Liste des agences retournée avec succès")
    public ResponseEntity<List<Agence>> obtenirToutesLesAgences() {
        log.info("GET /api/agences");
        List<Agence> agences = agenceService.obtenirToutesLesAgences();
        return new ResponseEntity<>(agences, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence modifiée avec succès"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<Agence> modifierAgence(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id, @RequestBody Agence agence) {
        log.info("PUT /api/agences/{}", id);
        agence.setIdAgence(id);
        Agence agenceModifiee = agenceService.modifierAgence(agence);
        return new ResponseEntity<>(agenceModifiee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une agence")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agence supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    public ResponseEntity<Void> supprimerAgence(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id) {
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
