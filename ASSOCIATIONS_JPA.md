# Associations JPA - Atelier 2

## 📊 Récapitulatif des associations créées

### 1. **Agence** ↔ **Vehicule** (OneToMany / ManyToOne)
- Une agence possède plusieurs véhicules
- Un véhicule appartient à une agence
```java
// Agence
@OneToMany(mappedBy = "agence")
private List<Vehicule> vehicules;

// Vehicule
@ManyToOne
@JoinColumn(name = "agence_id")
private Agence agence;
```

### 2. **Agence** ↔ **Employe** (OneToMany / ManyToOne)
- Une agence a plusieurs employés
- Un employé travaille dans une agence
```java
// Agence
@OneToMany(mappedBy = "agence")
private List<Employe> employes;

// Employe
@ManyToOne
@JoinColumn(name = "agence_id")
private Agence agence;
```

### 3. **Client** ↔ **Reservation** (OneToMany / ManyToOne)
- Un client peut avoir plusieurs réservations
- Une réservation appartient à un client
```java
// Client
@OneToMany(mappedBy = "client")
private List<Reservation> reservations;

// Reservation
@ManyToOne
@JoinColumn(name = "client_id", nullable = false)
private Client client;
```

### 4. **Vehicule** ↔ **Reservation** (OneToMany / ManyToOne)
- Un véhicule peut avoir plusieurs réservations
- Une réservation concerne un véhicule
```java
// Vehicule
@OneToMany(mappedBy = "vehicule")
private List<Reservation> reservations;

// Reservation
@ManyToOne
@JoinColumn(name = "vehicule_id", nullable = false)
private Vehicule vehicule;
```

### 5. **Reservation** ↔ **Contrat** (OneToOne bidirectionnel)
- Une réservation a un contrat unique
- Un contrat correspond à une réservation unique
```java
// Reservation
@OneToOne(mappedBy = "reservation")
private Contrat contrat;

// Contrat
@OneToOne
@JoinColumn(name = "reservation_id", nullable = false, unique = true)
private Reservation reservation;
```

### 6. **Contrat** ↔ **Paiement** (OneToMany / ManyToOne)
- Un contrat peut avoir plusieurs paiements
- Un paiement est associé à un contrat
```java
// Contrat
@OneToMany(mappedBy = "contrat")
private List<Paiement> paiements;

// Paiement
@ManyToOne
@JoinColumn(name = "contrat_id", nullable = false)
private Contrat contrat;
```

### 7. **Vehicule** ↔ **Maintenance** (OneToMany / ManyToOne)
- Un véhicule peut avoir plusieurs maintenances
- Une maintenance concerne un véhicule
```java
// Vehicule
@OneToMany(mappedBy = "vehicule")
private List<Maintenance> maintenances;

// Maintenance
@ManyToOne
@JoinColumn(name = "vehicule_id", nullable = false)
private Vehicule vehicule;
```

### 8. **Vehicule** ↔ **Equipement** (ManyToMany)
- Un véhicule peut avoir plusieurs équipements
- Un équipement peut être installé sur plusieurs véhicules
- Table de jointure : `vehicule_equipement`
```java
// Vehicule (propriétaire de la relation)
@ManyToMany
@JoinTable(
    name = "vehicule_equipement",
    joinColumns = @JoinColumn(name = "vehicule_id"),
    inverseJoinColumns = @JoinColumn(name = "equipement_id")
)
private List<Equipement> equipements;

// Equipement
@ManyToMany(mappedBy = "equipements")
private List<Vehicule> vehicules;
```

---

## 📋 Nombre de tables attendues

### Tables d'entités : 9
1. `agence`
2. `client`
3. `contrat`
4. `employe`
5. `equipement`
6. `maintenance`
7. `paiement`
8. `reservation`
9. `vehicule`

### Table de jointure : 1
10. `vehicule_equipement` (pour la relation ManyToMany)

**Total attendu : 10 tables**

---

## 🔑 Clés étrangères créées

| Table | Clé étrangère | Référence |
|-------|---------------|-----------|
| `vehicule` | `agence_id` | → `agence(idAgence)` |
| `employe` | `agence_id` | → `agence(idAgence)` |
| `reservation` | `client_id` | → `client(idClient)` |
| `reservation` | `vehicule_id` | → `vehicule(idVehicule)` |
| `contrat` | `reservation_id` | → `reservation(idReservation)` |
| `paiement` | `contrat_id` | → `contrat(idContrat)` |
| `maintenance` | `vehicule_id` | → `vehicule(idVehicule)` |
| `vehicule_equipement` | `vehicule_id` | → `vehicule(idVehicule)` |
| `vehicule_equipement` | `equipement_id` | → `equipement(idEquipement)` |

---

## ⚙️ Types d'associations utilisées

### @ManyToOne (7 occurrences)
- Vehicule → Agence
- Employe → Agence
- Reservation → Client
- Reservation → Vehicule
- Paiement → Contrat
- Maintenance → Vehicule

### @OneToMany (7 occurrences)
- Agence → Vehicule
- Agence → Employe
- Client → Reservation
- Vehicule → Reservation
- Vehicule → Maintenance
- Contrat → Paiement

### @OneToOne (1 occurrence bidirectionnelle)
- Reservation ↔ Contrat

### @ManyToMany (1 occurrence bidirectionnelle)
- Vehicule ↔ Equipement

---

## 📝 Notes importantes

### Côté propriétaire vs côté inverse
- **@ManyToOne** : toujours côté propriétaire (contient @JoinColumn)
- **@OneToMany** : toujours côté inverse (utilise mappedBy)
- **@OneToOne** : le côté avec @JoinColumn est propriétaire
- **@ManyToMany** : le côté avec @JoinTable est propriétaire

### Attribut mappedBy
- Utilisé du côté inverse de la relation
- Pointe vers le nom de l'attribut dans l'entité propriétaire
- Indique que la gestion de la relation est déléguée à l'autre entité

### Cascade et Fetch
- **Pas de cascade configurée** : prudence pour éviter les suppressions/modifications involontaires
- **FetchType par défaut** :
  - @ManyToOne et @OneToOne : EAGER (chargement immédiat)
  - @OneToMany et @ManyToMany : LAZY (chargement à la demande)

---

## ✅ Vérification à effectuer

1. ✅ Modifier `application.properties` : `ddl-auto=create` (fait)
2. ⏳ Lancer l'application
3. ⏳ Vérifier dans les logs la création des 10 tables
4. ⏳ Vérifier dans MySQL que les tables et FK sont créées
5. ⏳ Remettre `ddl-auto=update` dans `application.properties`

---

## 🎯 Commandes SQL de vérification

```sql
-- Se connecter à la base
USE autoloc_db;

-- Compter les tables
SELECT COUNT(*) as nombre_tables 
FROM information_schema.tables 
WHERE table_schema = 'autoloc_db';
-- Attendu : 10

-- Lister toutes les tables
SHOW TABLES;

-- Voir les clés étrangères d'une table
SHOW CREATE TABLE vehicule;
SHOW CREATE TABLE reservation;
SHOW CREATE TABLE contrat;
SHOW CREATE TABLE paiement;
SHOW CREATE TABLE maintenance;
SHOW CREATE TABLE employe;

-- Vérifier la table de jointure
DESC vehicule_equipement;
```

---

## 🚀 Prochaine étape

Une fois la vérification effectuée et les 10 tables confirmées :
1. Remettre `spring.jpa.hibernate.ddl-auto=update`
2. Commit Git avec message : "Atelier 2: Ajout des associations JPA"
3. Push sur GitHub

---

**Date de création :** Atelier 2 - Séance 3  
**Auteur :** Projet AutoLoc ASI 26-27
