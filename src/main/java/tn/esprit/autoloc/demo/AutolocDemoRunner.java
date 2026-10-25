package tn.esprit.autoloc.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.domain.*;
import tn.esprit.autoloc.service.IClientService;
import tn.esprit.autoloc.service.IVehiculeService;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe de démonstration pour tester les opérations CRUD
 * Commentez @Component pour désactiver l'exécution au démarrage
 */
//@Component
@RequiredArgsConstructor
@Slf4j
public class AutolocDemoRunner implements CommandLineRunner {

    private final IVehiculeService vehiculeService;
    private final IClientService clientService;

    @Override
    public void run(String... args) {
        log.info("=== Début de la démonstration AutoLoc ===");

        demonstrationVehicule();
        demonstrationClient();

        log.info("=== Fin de la démonstration AutoLoc ===");
    }

    private void demonstrationVehicule() {
        log.info("\n>>> Démonstration CRUD Vehicule <<<");

        // CREATE
        log.info("1. Création d'un véhicule");
        Vehicule vehicule = new Vehicule();
        vehicule.setImmatriculation("123TUN456");
        vehicule.setMarque("Renault");
        vehicule.setModele("Clio 5");
        vehicule.setCategorie(CategorieVehicule.CITADINE);
        vehicule.setTarifJournalier(new BigDecimal("150.00"));
        vehicule.setStatut(StatutVehicule.DISPONIBLE);

        Vehicule vehiculeCree = vehiculeService.creerVehicule(vehicule);
        log.info("Véhicule créé avec ID: {}", vehiculeCree.getIdVehicule());

        // READ
        log.info("2. Lecture du véhicule par ID");
        vehiculeService.obtenirVehiculeParId(vehiculeCree.getIdVehicule())
                .ifPresent(v -> log.info("Véhicule trouvé: {} {}", v.getMarque(), v.getModele()));

        log.info("3. Lecture du véhicule par immatriculation");
        vehiculeService.obtenirVehiculeParImmatriculation("123TUN456")
                .ifPresent(v -> log.info("Véhicule trouvé: {}", v.getImmatriculation()));

        // UPDATE
        log.info("4. Modification du véhicule");
        vehiculeCree.setTarifJournalier(new BigDecimal("180.00"));
        vehiculeCree.setStatut(StatutVehicule.LOUE);
        Vehicule vehiculeModifie = vehiculeService.modifierVehicule(vehiculeCree);
        log.info("Tarif modifié: {}, Statut: {}", vehiculeModifie.getTarifJournalier(), vehiculeModifie.getStatut());

        // LIST
        log.info("5. Liste de tous les véhicules");
        log.info("Nombre total de véhicules: {}", vehiculeService.compterVehicules());

        log.info("6. Recherche par statut LOUE");
        vehiculeService.obtenirVehiculesParStatut(StatutVehicule.LOUE)
                .forEach(v -> log.info("Véhicule loué: {} {}", v.getMarque(), v.getModele()));

        // DELETE
        log.info("7. Suppression du véhicule");
        vehiculeService.supprimerVehicule(vehiculeCree.getIdVehicule());
        log.info("Véhicule supprimé");

        log.info("Nombre de véhicules après suppression: {}", vehiculeService.compterVehicules());
    }

    private void demonstrationClient() {
        log.info("\n>>> Démonstration CRUD Client <<<");

        // CREATE
        log.info("1. Création d'un client");
        Client client = new Client();
        client.setNom("Ben Ali");
        client.setPrenom("Ahmed");
        client.setEmail("ahmed.benali@example.com");
        client.setTelephone("20123456");
        client.setNumPermis("A123456");
        client.setDateInscription(LocalDate.now());

        Client clientCree = clientService.creerClient(client);
        log.info("Client créé avec ID: {}", clientCree.getIdClient());

        // READ
        log.info("2. Lecture du client par ID");
        clientService.obtenirClientParId(clientCree.getIdClient())
                .ifPresent(c -> log.info("Client trouvé: {} {}", c.getNom(), c.getPrenom()));

        log.info("3. Lecture du client par email");
        clientService.obtenirClientParEmail("ahmed.benali@example.com")
                .ifPresent(c -> log.info("Client trouvé: {}", c.getEmail()));

        // UPDATE
        log.info("4. Modification du client");
        clientCree.setTelephone("21999888");
        Client clientModifie = clientService.modifierClient(clientCree);
        log.info("Téléphone modifié: {}", clientModifie.getTelephone());

        // LIST
        log.info("5. Liste de tous les clients");
        log.info("Nombre total de clients: {}", clientService.compterClients());

        // DELETE
        log.info("6. Suppression du client");
        clientService.supprimerClient(clientCree.getIdClient());
        log.info("Client supprimé");

        log.info("Nombre de clients après suppression: {}", clientService.compterClients());
    }
}
