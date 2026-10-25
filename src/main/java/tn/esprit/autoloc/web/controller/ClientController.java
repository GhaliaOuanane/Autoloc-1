package tn.esprit.autoloc.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ClientController {

    private final IClientService clientService;

    @PostMapping
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
    public ResponseEntity<Client> obtenirClient(@PathVariable Long id) {
        log.info("GET /api/clients/{}", id);
        return clientService.obtenirClientParId(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
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
    public ResponseEntity<Client> modifierClient(@PathVariable Long id, @RequestBody Client client) {
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
    public ResponseEntity<Void> supprimerClient(@PathVariable Long id) {
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
