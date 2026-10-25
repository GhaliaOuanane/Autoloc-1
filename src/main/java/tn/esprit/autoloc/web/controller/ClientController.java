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
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final IClientService clientService;

    @PostMapping
    @Operation(summary = "Créer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<Client> creerClient(@RequestBody Client client) {
        log.info("POST /api/clients - Création d'un client");
        try {
            Client clientCree = clientService.creerClient(client);
            return new ResponseEntity<>(clientCree, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.error("Erreur lors de la création du client: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public ResponseEntity<Client> obtenirClient(@Parameter(description = "Identifiant du client") @PathVariable Long id) {
        log.info("GET /api/clients/{}", id);
        return clientService.obtenirClientParId(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @Operation(summary = "Lister tous les clients")
    @ApiResponse(responseCode = "200", description = "Liste des clients retournée avec succès")
    public ResponseEntity<List<Client>> obtenirTousLesClients() {
        log.info("GET /api/clients");
        List<Client> clients = clientService.obtenirTousLesClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Client> obtenirClientParEmail(@PathVariable String email) {
        log.info("GET /api/clients/email/{}", email);
        return clientService.obtenirClientParEmail(email)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/permis/{numPermis}")
    public ResponseEntity<Client> obtenirClientParNumPermis(@PathVariable String numPermis) {
        log.info("GET /api/clients/permis/{}", numPermis);
        return clientService.obtenirClientParNumPermis(numPermis)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Client introuvable"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public ResponseEntity<Client> modifierClient(@Parameter(description = "Identifiant du client") @PathVariable Long id, @RequestBody Client client) {
        log.info("PUT /api/clients/{}", id);
        try {
            client.setIdClient(id);
            Client clientModifie = clientService.modifierClient(client);
            return new ResponseEntity<>(clientModifie, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Erreur lors de la modification du client: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    public ResponseEntity<Void> supprimerClient(@Parameter(description = "Identifiant du client") @PathVariable Long id) {
        log.info("DELETE /api/clients/{}", id);
        try {
            clientService.supprimerClient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            log.error("Erreur lors de la suppression du client: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> compterClients() {
        log.info("GET /api/clients/count");
        long count = clientService.compterClients();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
