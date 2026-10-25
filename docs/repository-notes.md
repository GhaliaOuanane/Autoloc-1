# Atelier 3 - Notes sur la couche Repository et Service

## 📋 Repositories créés (9)

| Entité | Interface Repository | Étend | Justification |
|--------|---------------------|-------|---------------|
| Agence | IAgenceRepository | JpaRepository<Agence, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible |
| Client | IClientRepository | JpaRepository<Client, Long> | CRUD complet + méthodes personnalisées (findByEmail, findByNumPermis) |
| Contrat | IContratRepository | JpaRepository<Contrat, Long> | CRUD complet, gestion des paiements en cascade |
| Employe | IEmployeRepository | JpaRepository<Employe, Long> | CRUD complet pour la gestion des employés d'agence |
| Equipement | IEquipementRepository | JpaRepository<Equipement, Long> | CRUD complet pour les équipements des véhicules |
| Maintenance | IMaintenanceRepository | JpaRepository<Maintenance, Long> | CRUD complet pour le suivi des maintenances |
| Paiement | IPaiementRepository | JpaRepository<Paiement, Long> | CRUD complet, lecture des paiements associés aux contrats |
| Reservation | IReservationRepository | JpaRepository<Reservation, Long> | CRUD complet pour la gestion des réservations |
| Vehicule | IVehiculeRepository | JpaRepository<Vehicule, Long> | CRUD complet + méthodes personnalisées (findByImmatriculation, findByStatut, etc.) |

## 🔍 Pourquoi JpaRepository ?

Nous avons choisi **JpaRepository** pour toutes les entités car elle offre :

1. **CRUD complet** : Toutes les opérations de base (save, findById, findAll, delete, etc.)
2. **Collections List** : Les méthodes findAll retournent une List au lieu d'Iterable
3. **Pagination et tri** : Méthodes findAll(Pageable) et findAll(Sort)
4. **Méthodes JPA spécifiques** : flush(), saveAndFlush(), getReferenceById()
5. **Facilité d'utilisation** : Interface la plus complète, adaptée pour une application complète

## 📦 Services créés (9)

### Interfaces de services

Chaque entité a son interface de service :

1. **IAgenceService** - Gestion des agences
2. **IClientService** - Gestion des clients (CRUD complet)
3. **IContratService** - Gestion des contrats
4. **IEmployeService** - Gestion des employés
5. **IEquipementService** - Gestion des équipements
6. **IMaintenanceService** - Gestion des maintenances
7. **IPaiementService** - Gestion des paiements
8. **IReservationService** - Gestion des réservations
9. **IVehiculeService** - Gestion des véhicules (CRUD complet)

### Implémentations de services

Toutes les implémentations ont été créées avec :

- **@Service** : Marque la classe comme composant de service Spring
- **@RequiredArgsConstructor** (Lombok) : Injection des dépendances par constructeur
- **@Slf4j** (Lombok) : Logger automatique pour tracer les opérations
- **@Transactional** : Gestion transactionnelle des opérations
- **@Transactional(readOnly = true)** : Optimisation pour les opérations de lecture

## 🎯 CRUD Complet développé pour 2 entités

### 1. **VehiculeServiceImpl** - CRUD complet

Opérations implémentées :

- ✅ **Create** : `creerVehicule()` avec validation d'immatriculation unique
- ✅ **Read** : 
  - `obtenirVehiculeParId()`
  - `obtenirTousLesVehicules()`
  - `obtenirVehiculeParImmatriculation()`
  - `obtenirVehiculesParStatut()`
  - `obtenirVehiculesParCategorie()`
  - `obtenirVehiculesParMarque()`
- ✅ **Update** : `modifierVehicule()` avec validation d'existence
- ✅ **Delete** : `supprimerVehicule()` avec validation d'existence
- ✅ **Autres** : 
  - `vehiculeExisteParImmatriculation()`
  - `compterVehicules()`

**Validations** :
- Vérification d'unicité de l'immatriculation à la création
- Vérification d'existence avant modification/suppression
- Logs détaillés de toutes les opérations

### 2. **ClientServiceImpl** - CRUD complet

Opérations implémentées :

- ✅ **Create** : `creerClient()` avec validation d'email unique
- ✅ **Read** : 
  - `obtenirClientParId()`
  - `obtenirTousLesClients()`
  - `obtenirClientParEmail()`
  - `obtenirClientParNumPermis()`
- ✅ **Update** : `modifierClient()` avec validation d'existence
- ✅ **Delete** : `supprimerClient()` avec validation d'existence
- ✅ **Autres** : 
  - `clientExisteParEmail()`
  - `compterClients()`

**Validations** :
- Vérification d'unicité de l'email à la création
- Vérification d'existence avant modification/suppression
- Gestion des erreurs avec exceptions explicites
- Logs détaillés de toutes les opérations

## 🔧 Méthodes personnalisées dans les repositories

### IVehiculeRepository
```java
Optional<Vehicule> findByImmatriculation(String immatriculation);
List<Vehicule> findByStatut(StatutVehicule statut);
List<Vehicule> findByCategorie(CategorieVehicule categorie);
List<Vehicule> findByMarque(String marque);
boolean existsByImmatriculation(String immatriculation);
```

### IClientRepository
```java
Optional<Client> findByEmail(String email);
Optional<Client> findByNumPermis(String numPermis);
boolean existsByEmail(String email);
```

**Avantage** : Spring Data JPA génère automatiquement l'implémentation basée sur le nom de la méthode.

## 📝 Bonnes pratiques appliquées

1. **Séparation des couches** :
   - Repository : Accès aux données
   - Service : Logique métier et validations
   - Transactions gérées au niveau service

2. **Injection de dépendances** :
   - Utilisation de `@RequiredArgsConstructor` (Lombok)
   - Injection par constructeur (recommandée par Spring)

3. **Logging** :
   - Utilisation de `@Slf4j` (Lombok)
   - Logs INFO pour les opérations principales
   - Logs ERROR pour les exceptions

4. **Transactions** :
   - `@Transactional` pour les opérations d'écriture
   - `@Transactional(readOnly = true)` pour les lectures (optimisation)

5. **Gestion des erreurs** :
   - Validation des données avant sauvegarde
   - Exceptions explicites (IllegalArgumentException)
   - Messages d'erreur clairs

6. **Convention de nommage** :
   - Interfaces repository préfixées par `I`
   - Méthodes en français pour la cohérence métier
   - Noms explicites et auto-documentés

## ⚠️ Points d'attention

1. **Cascade et orphanRemoval** :
   - Les suppressions via repository respectent les cascades JPA
   - Attention aux méthodes `deleteAllInBatch()` qui contournent le contexte de persistance

2. **Lazy Loading** :
   - Les associations sont chargées en LAZY par défaut
   - Utiliser les transactions pour éviter les LazyInitializationException

3. **Validation métier** :
   - Les validations sont faites au niveau service
   - Les contraintes de base de données (unique, nullable) sont définies dans les entités

## 📊 Statistiques

- **9 repositories** créés (tous étendent JpaRepository)
- **9 interfaces de service** définies
- **9 implémentations de service** créées
- **2 CRUD complets** développés (Vehicule et Client)
- **7 méthodes personnalisées** ajoutées aux repositories
- **Logs** ajoutés sur toutes les opérations principales

## 🚀 Prochaines étapes

- Atelier 4 : Développer la logique métier avancée
- Atelier 5 : Créer les contrôleurs REST pour exposer les services
- Atelier 6 : Ajouter les DTOs pour la transformation des données

---

**Date** : Atelier 3 - Spring Data JPA  
**Auteur** : Projet AutoLoc ASI 26-27
