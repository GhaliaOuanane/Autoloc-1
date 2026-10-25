# AutoLoc API

Système de gestion de location de véhicules développé avec Spring Boot.

## Description

Ce projet fait partie de l'Atelier 1 du cours Architecture des Systèmes d'Information (ASI).
Il implémente un système complet de gestion de location de véhicules avec Spring Boot, JPA/Hibernate et MySQL.

## Technologies utilisées

- **Java 17+**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Lombok**
- **Maven**

## Prérequis

- JDK 17 ou supérieur
- Maven 3.6+
- MySQL 8.0+
- IntelliJ IDEA (recommandé) ou tout autre IDE Java

## Configuration

1. **Base de données**: Assurez-vous que MySQL est démarré
2. **Mot de passe**: Modifiez le mot de passe dans `src/main/resources/application.properties`:
   ```properties
   spring.datasource.password=votre_mot_de_passe
   ```

La base de données `autoloc_db` sera créée automatiquement au premier démarrage grâce au paramètre `createDatabaseIfNotExist=true`.

## Démarrage

### Avec Maven
```bash
mvn spring-boot:run
```

### Avec IntelliJ IDEA
1. Ouvrir le projet
2. Activer l'annotation processing pour Lombok :
   - File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors
   - Cocher "Enable annotation processing"
3. Vérifier que le plugin Lombok est installé
4. Lancer `AutolocApiApplication.java`

## Structure du projet

```
autoloc-api
├── src/main/java/tn/esprit/autoloc
│   ├── domain/              # Entités JPA et énumérations
│   ├── repository/          # Interfaces Spring Data JPA
│   ├── service/             # Couche métier
│   └── web/
│       ├── controller/      # Contrôleurs REST
│       └── dto/             # Data Transfer Objects
├── src/main/resources
│   └── application.properties
└── pom.xml
```

## Entités

Le projet comprend 9 entités principales :

1. **Vehicule** - Gestion des véhicules (marque, modèle, tarif, statut)
2. **Agence** - Agences de location
3. **Client** - Clients de l'agence
4. **Employe** - Employés (agents, managers)
5. **Equipement** - Équipements additionnels
6. **Reservation** - Réservations de véhicules
7. **Contrat** - Contrats de location
8. **Paiement** - Gestion des paiements
9. **Maintenance** - Suivi des maintenances

## Énumérations

- `StatutVehicule`: DISPONIBLE, LOUE, MAINTENANCE
- `CategorieVehicule`: CITADINE, BERLINE, SUV, UTILITAIRE
- `RoleEmploye`: AGENT, MANAGER
- `StatutReservation`: EN_ATTENTE, CONFIRMEE, ANNULEE, TERMINEE
- `ModePaiement`: CARTE, ESPECES, VIREMENT

## Vérification

Après le démarrage, vérifiez que :
1. L'application démarre sans erreur
2. Les logs affichent "Started AutolocApiApplication in ... seconds"
3. Les tables sont créées dans la base de données `autoloc_db`
4. Les requêtes SQL de création de tables sont visibles dans les logs

## Prochaines étapes (Atelier 2)

Les associations entre entités seront ajoutées lors de l'Atelier 2 :
- Relations @ManyToOne, @OneToMany, @ManyToMany
- Stratégies de cascade et de fetch
- Gestion des clés étrangères

## Auteur

Projet réalisé dans le cadre du cours ASI 26-27 - ESPRIT

## Licence

Projet académique - ESPRIT
