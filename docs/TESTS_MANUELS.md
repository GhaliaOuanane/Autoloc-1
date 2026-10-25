# Tests Manuels - Atelier 3

## 🧪 Comment tester les services

### Option 1 : Utiliser la classe de démonstration

La classe `AutolocDemoRunner` est fournie pour tester automatiquement les services au démarrage.

#### Activer la démonstration
Décommenter `@Component` dans `AutolocDemoRunner.java` :
```java
@Component  // Décommenter cette ligne
@RequiredArgsConstructor
@Slf4j
public class AutolocDemoRunner implements CommandLineRunner {
    // ...
}
```

#### Lancer l'application
```bash
mvn spring-boot:run
```

#### Observer les logs
Vous devriez voir dans la console :
```
=== Début de la démonstration AutoLoc ===

>>> Démonstration CRUD Vehicule <<<
1. Création d'un véhicule
Véhicule créé avec ID: 1
2. Lecture du véhicule par ID
Véhicule trouvé: Renault Clio 5
...
```

### Option 2 : Tests manuels via logs SQL

#### 1. Vérifier les logs SQL
Dans `application.properties`, assurez-vous que :
```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
```

#### 2. Observer les requêtes générées
Au démarrage, vous devriez voir :
```sql
Hibernate: select ... from vehicule where ...
Hibernate: insert into vehicule (marque, modele, ...) values (?, ?, ...)
Hibernate: update vehicule set ... where id=?
Hibernate: delete from vehicule where id=?
```

### Option 3 : Tests dans MySQL Workbench

#### 1. Se connecter à la base
```bash
mysql -u root -p
use autoloc_db;
```

#### 2. Vérifier la structure
```sql
-- Voir toutes les tables
SHOW TABLES;

-- Voir les repositories détectés dans les logs
-- Rechercher : "Found 9 JPA repository interfaces"

-- Vérifier la structure d'une table
DESC vehicule;
DESC client;
```

#### 3. Insérer des données de test
```sql
-- Insérer un véhicule
INSERT INTO vehicule (immatriculation, marque, modele, categorie, tarif_journalier, statut)
VALUES ('123TUN456', 'Renault', 'Clio 5', 'CITADINE', 150.00, 'DISPONIBLE');

-- Insérer un client
INSERT INTO client (nom, prenom, email, telephone, num_permis, date_inscription)
VALUES ('Ben Ali', 'Ahmed', 'ahmed@example.com', '20123456', 'A123456', CURDATE());

-- Vérifier les insertions
SELECT * FROM vehicule;
SELECT * FROM client;
```

---

## ✅ Scénarios de tests

### Test 1 : Création d'un véhicule

**Service testé** : `VehiculeServiceImpl.creerVehicule()`

**Test positif** :
```java
Vehicule vehicule = new Vehicule();
vehicule.setImmatriculation("123TUN456");
vehicule.setMarque("Renault");
vehicule.setModele("Clio 5");
vehicule.setCategorie(CategorieVehicule.CITADINE);
vehicule.setTarifJournalier(new BigDecimal("150.00"));
vehicule.setStatut(StatutVehicule.DISPONIBLE);

Vehicule created = vehiculeService.creerVehicule(vehicule);
// Résultat attendu : Véhicule créé avec ID auto-généré
```

**Test négatif** :
```java
// Tenter de créer un véhicule avec une immatriculation existante
Vehicule doublon = new Vehicule();
doublon.setImmatriculation("123TUN456"); // Déjà existe

vehiculeService.creerVehicule(doublon);
// Résultat attendu : IllegalArgumentException
// Message : "Un véhicule avec cette immatriculation existe déjà"
```

---

### Test 2 : Recherche de véhicules

**Service testé** : `VehiculeServiceImpl` (méthodes de recherche)

**Tests** :
```java
// 1. Par ID
Optional<Vehicule> v1 = vehiculeService.obtenirVehiculeParId(1L);
// Attendu : Optional contenant le véhicule si existe

// 2. Par immatriculation
Optional<Vehicule> v2 = vehiculeService.obtenirVehiculeParImmatriculation("123TUN456");
// Attendu : Optional contenant le véhicule

// 3. Par statut
List<Vehicule> disponibles = vehiculeService.obtenirVehiculesParStatut(StatutVehicule.DISPONIBLE);
// Attendu : Liste des véhicules disponibles

// 4. Par catégorie
List<Vehicule> citadines = vehiculeService.obtenirVehiculesParCategorie(CategorieVehicule.CITADINE);
// Attendu : Liste des véhicules de type CITADINE

// 5. Par marque
List<Vehicule> renault = vehiculeService.obtenirVehiculesParMarque("Renault");
// Attendu : Liste des véhicules Renault
```

---

### Test 3 : Modification d'un véhicule

**Service testé** : `VehiculeServiceImpl.modifierVehicule()`

**Test positif** :
```java
Vehicule vehicule = vehiculeService.obtenirVehiculeParId(1L).get();
vehicule.setTarifJournalier(new BigDecimal("200.00"));
vehicule.setStatut(StatutVehicule.LOUE);

Vehicule modified = vehiculeService.modifierVehicule(vehicule);
// Résultat attendu : Véhicule modifié
// Logs SQL : UPDATE vehicule SET ...
```

**Test négatif** :
```java
Vehicule inexistant = new Vehicule();
inexistant.setIdVehicule(999L); // ID qui n'existe pas

vehiculeService.modifierVehicule(inexistant);
// Résultat attendu : IllegalArgumentException
// Message : "Véhicule introuvable avec l'ID: 999"
```

---

### Test 4 : Suppression d'un véhicule

**Service testé** : `VehiculeServiceImpl.supprimerVehicule()`

**Test positif** :
```java
vehiculeService.supprimerVehicule(1L);
// Résultat attendu : Véhicule supprimé
// Logs SQL : DELETE FROM vehicule WHERE id=?
```

**Test négatif** :
```java
vehiculeService.supprimerVehicule(999L); // ID inexistant
// Résultat attendu : IllegalArgumentException
// Message : "Véhicule introuvable avec l'ID: 999"
```

---

### Test 5 : Création d'un client

**Service testé** : `ClientServiceImpl.creerClient()`

**Test positif** :
```java
Client client = new Client();
client.setNom("Ben Ali");
client.setPrenom("Ahmed");
client.setEmail("ahmed@example.com");
client.setTelephone("20123456");
client.setNumPermis("A123456");
client.setDateInscription(LocalDate.now());

Client created = clientService.creerClient(client);
// Résultat attendu : Client créé avec ID auto-généré
```

**Test négatif** :
```java
Client doublon = new Client();
doublon.setEmail("ahmed@example.com"); // Email déjà existe
// ... autres attributs

clientService.creerClient(doublon);
// Résultat attendu : IllegalArgumentException
// Message : "Un client avec cet email existe déjà"
```

---

### Test 6 : Recherche de clients

**Service testé** : `ClientServiceImpl` (méthodes de recherche)

**Tests** :
```java
// 1. Par email
Optional<Client> c1 = clientService.obtenirClientParEmail("ahmed@example.com");
// Attendu : Optional contenant le client

// 2. Par numéro de permis
Optional<Client> c2 = clientService.obtenirClientParNumPermis("A123456");
// Attendu : Optional contenant le client

// 3. Vérifier existence par email
boolean existe = clientService.clientExisteParEmail("ahmed@example.com");
// Attendu : true
```

---

## 📊 Logs attendus

### Création réussie
```
INFO  c.t.e.a.s.VehiculeServiceImpl : Création d'un nouveau véhicule avec immatriculation: 123TUN456
Hibernate: insert into vehicule (agence_id, categorie, immatriculation, marque, modele, statut, tarif_journalier) values (?, ?, ?, ?, ?, ?, ?)
INFO  c.t.e.a.s.VehiculeServiceImpl : Véhicule créé avec succès avec l'ID: 1
```

### Erreur de validation
```
ERROR c.t.e.a.s.VehiculeServiceImpl : L'immatriculation 123TUN456 existe déjà
Exception in thread "main" java.lang.IllegalArgumentException: Un véhicule avec cette immatriculation existe déjà
```

### Lecture
```
INFO  c.t.e.a.s.VehiculeServiceImpl : Recherche du véhicule avec ID: 1
Hibernate: select v1_0.id_vehicule,v1_0.agence_id,v1_0.categorie,v1_0.immatriculation,v1_0.marque,v1_0.modele,v1_0.statut,v1_0.tarif_journalier from vehicule v1_0 where v1_0.id_vehicule=?
```

### Modification
```
INFO  c.t.e.a.s.VehiculeServiceImpl : Modification du véhicule avec ID: 1
Hibernate: update vehicule set agence_id=?, categorie=?, immatriculation=?, marque=?, modele=?, statut=?, tarif_journalier=? where id_vehicule=?
INFO  c.t.e.a.s.VehiculeServiceImpl : Véhicule modifié avec succès
```

### Suppression
```
INFO  c.t.e.a.s.VehiculeServiceImpl : Suppression du véhicule avec ID: 1
Hibernate: delete from vehicule where id_vehicule=?
INFO  c.t.e.a.s.VehiculeServiceImpl : Véhicule supprimé avec succès
```

---

## 🔍 Vérifications dans les logs au démarrage

### Rechercher ces lignes :
```
✅ "Found 9 JPA repository interfaces"
✅ "Started AutolocApiApplication in X.XXX seconds"
✅ Aucune erreur de compilation
✅ Aucune exception au démarrage
```

---

## ⚠️ Problèmes courants et solutions

### Problème 1 : LazyInitializationException
**Symptôme** : Exception lors de l'accès aux associations
```
org.hibernate.LazyInitializationException: could not initialize proxy
```

**Solution** : Utiliser @Transactional ou charger les associations explicitement

### Problème 2 : Violation de contrainte unique
**Symptôme** : 
```
java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '123TUN456' for key 'immatriculation'
```

**Solution** : La validation métier dans le service devrait empêcher cela

### Problème 3 : Repository non trouvé
**Symptôme** : 
```
NoSuchBeanDefinitionException: No qualifying bean of type 'IVehiculeRepository'
```

**Solution** : Vérifier que l'interface est dans le bon package et que le composant scan fonctionne

---

## 📝 Checklist de tests

- [ ] Application démarre sans erreur
- [ ] Logs affichent "Found 9 JPA repository interfaces"
- [ ] Création de véhicule fonctionne
- [ ] Recherche par ID fonctionne
- [ ] Recherche par immatriculation fonctionne
- [ ] Recherche par statut fonctionne
- [ ] Modification de véhicule fonctionne
- [ ] Suppression de véhicule fonctionne
- [ ] Création de client fonctionne
- [ ] Recherche de client par email fonctionne
- [ ] Validation d'email unique fonctionne
- [ ] Logs SQL sont visibles et corrects
- [ ] Pas d'erreur dans la console

---

**Date** : Atelier 3  
**Auteur** : Projet AutoLoc ASI 26-27
