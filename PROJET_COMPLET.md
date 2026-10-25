# ✅ Projet AutoLoc - Atelier 1 Séance 2 - COMPLET

## 📋 Récapitulatif de ce qui a été créé

### ✅ Structure complète du projet Spring Boot

```
autoloc-api/
├── src/
│   ├── main/
│   │   ├── java/tn/esprit/autoloc/
│   │   │   ├── AutolocApiApplication.java          ✅ Classe principale Spring Boot
│   │   │   ├── domain/                             ✅ Package des entités
│   │   │   │   ├── Agence.java                     ✅ Entité 1/9
│   │   │   │   ├── Client.java                     ✅ Entité 2/9
│   │   │   │   ├── Employe.java                    ✅ Entité 3/9
│   │   │   │   ├── Vehicule.java                   ✅ Entité 4/9
│   │   │   │   ├── Equipement.java                 ✅ Entité 5/9
│   │   │   │   ├── Reservation.java                ✅ Entité 6/9
│   │   │   │   ├── Contrat.java                    ✅ Entité 7/9
│   │   │   │   ├── Paiement.java                   ✅ Entité 8/9
│   │   │   │   ├── Maintenance.java                ✅ Entité 9/9
│   │   │   │   ├── StatutVehicule.java             ✅ Enum 1/5
│   │   │   │   ├── CategorieVehicule.java          ✅ Enum 2/5
│   │   │   │   ├── RoleEmploye.java                ✅ Enum 3/5
│   │   │   │   ├── StatutReservation.java          ✅ Enum 4/5
│   │   │   │   └── ModePaiement.java               ✅ Enum 5/5
│   │   │   ├── repository/                         📂 Package vide (Atelier 3)
│   │   │   ├── service/                            📂 Package vide (Atelier 4)
│   │   │   └── web/
│   │   │       ├── controller/                     📂 Package vide (Atelier 5)
│   │   │       └── dto/                            📂 Package vide (Atelier 6)
│   │   └── resources/
│   │       ├── application.properties              ✅ Configuration Spring Boot
│   │       ├── static/                             📂 Ressources statiques
│   │       └── templates/                          📂 Templates
│   └── test/
│       └── java/tn/esprit/autoloc/
│           └── AutolocApiApplicationTests.java     ✅ Test de base
├── pom.xml                                          ✅ Configuration Maven
├── .gitignore                                       ✅ Exclusions Git
├── README.md                                        ✅ Documentation projet
└── SETUP_INSTRUCTIONS.md                            ✅ Guide d'installation

✅ = Créé et prêt à l'emploi
📂 = Package vide pour les prochains ateliers
```

---

## 📦 Détail des 9 entités JPA créées

### 1. **Vehicule** ✅
- `idVehicule` : Long (Primary Key, Auto-increment)
- `immatriculation` : String (unique, 20 caractères)
- `marque` : String (50 caractères)
- `modele` : String (50 caractères)
- `categorie` : CategorieVehicule (enum)
- `tarifJournalier` : BigDecimal (10,2)
- `statut` : StatutVehicule (enum)

### 2. **Agence** ✅
- `idAgence` : Long (Primary Key, Auto-increment)
- `nom` : String (100 caractères)
- `ville` : String (100 caractères)
- `adresse` : String (255 caractères)
- `telephone` : String (20 caractères)

### 3. **Client** ✅
- `idClient` : Long (Primary Key, Auto-increment)
- `nom` : String (100 caractères)
- `prenom` : String (100 caractères)
- `email` : String (unique, 100 caractères)
- `telephone` : String (20 caractères)
- `numPermis` : String (unique, 50 caractères)
- `dateInscription` : LocalDate

### 4. **Employe** ✅
- `idEmploye` : Long (Primary Key, Auto-increment)
- `nom` : String (100 caractères)
- `prenom` : String (100 caractères)
- `role` : RoleEmploye (enum: AGENT, MANAGER)

### 5. **Equipement** ✅
- `idEquipement` : Long (Primary Key, Auto-increment)
- `libelle` : String (100 caractères)

### 6. **Reservation** ✅
- `idReservation` : Long (Primary Key, Auto-increment)
- `dateDebut` : LocalDate
- `dateFin` : LocalDate
- `statut` : StatutReservation (enum)

### 7. **Contrat** ✅
- `idContrat` : Long (Primary Key, Auto-increment)
- `dateSignature` : LocalDate
- `montantTotal` : BigDecimal (10,2)
- `valide` : Boolean

### 8. **Paiement** ✅
- `idPaiement` : Long (Primary Key, Auto-increment)
- `montant` : BigDecimal (10,2)
- `datePaiement` : LocalDate
- `modePaiement` : ModePaiement (enum)

### 9. **Maintenance** ✅
- `idMaintenance` : Long (Primary Key, Auto-increment)
- `dateDebut` : LocalDate
- `dateFin` : LocalDate
- `description` : String (500 caractères)

---

## 🔧 Énumérations créées

1. **StatutVehicule** : DISPONIBLE, LOUE, MAINTENANCE
2. **CategorieVehicule** : CITADINE, BERLINE, SUV, UTILITAIRE
3. **RoleEmploye** : AGENT, MANAGER
4. **StatutReservation** : EN_ATTENTE, CONFIRMEE, ANNULEE, TERMINEE
5. **ModePaiement** : CARTE, ESPECES, VIREMENT

---

## 📝 Annotations utilisées

### Annotations JPA
- `@Entity` : Marque une classe comme entité JPA
- `@Table(name = "...")` : Spécifie le nom de la table
- `@Id` : Marque la clé primaire
- `@GeneratedValue(strategy = GenerationType.IDENTITY)` : Auto-incrémentation
- `@Column(...)` : Configuration des colonnes (nullable, unique, length, precision, scale)
- `@Enumerated(EnumType.STRING)` : Stockage des enums en String (pas en ordinal)

### Annotations Lombok
- `@Getter` : Génère les getters
- `@Setter` : Génère les setters
- `@NoArgsConstructor` : Génère un constructeur sans paramètres
- `@AllArgsConstructor` : Génère un constructeur avec tous les paramètres

**Note :** Utilisation de `@Getter/@Setter` au lieu de `@Data` pour éviter les boucles infinies avec equals/hashCode/toString quand on aura des associations bidirectionnelles (Atelier 2).

---

## ⚙️ Configuration application.properties

```properties
spring.application.name=autoloc-api

# Connexion MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/autoloc_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=                          ⚠️ À CONFIGURER

# Hibernate
spring.jpa.hibernate.ddl-auto=update                 # Crée/met à jour les tables automatiquement
spring.jpa.show-sql=true                             # Affiche les requêtes SQL
spring.jpa.properties.hibernate.format_sql=true      # Formate les requêtes SQL

# Logs
logging.level.org.hibernate.SQL=DEBUG                # Logs SQL détaillés
logging.level.tn.esprit.autoloc=INFO                 # Logs applicatifs
```

---

## 📚 Dépendances Maven (pom.xml)

- ✅ **Spring Boot Web** : Pour créer des API REST (prochains ateliers)
- ✅ **Spring Data JPA** : Accès aux données avec JPA/Hibernate
- ✅ **MySQL Driver** : Connexion à MySQL
- ✅ **Lombok** : Réduction du code boilerplate
- ✅ **Validation** : Validation des données (prochains ateliers)
- ✅ **Spring Boot DevTools** : Rechargement automatique en développement

---

## 🚀 Comment lancer le projet

### Prérequis
1. ✅ JDK 17+ installé
2. ✅ MySQL installé et démarré
3. ✅ IntelliJ IDEA avec plugin Lombok
4. ✅ Annotation processing activé dans IntelliJ

### Étapes de lancement

1. **Configurer le mot de passe MySQL**
   - Ouvrir `src/main/resources/application.properties`
   - Modifier : `spring.datasource.password=votre_mot_de_passe`

2. **Recharger les dépendances Maven**
   - IntelliJ devrait le faire automatiquement
   - Ou : Clic droit sur `pom.xml` → Maven → Reload Project

3. **Lancer l'application**
   - Ouvrir `AutolocApiApplication.java`
   - Clic droit → Run

4. **Vérifier dans les logs**
   ```
   Started AutolocApiApplication in X.XXX seconds
   ```

5. **Vérifier dans MySQL**
   - La base `autoloc_db` doit être créée
   - Les 9 tables doivent être créées :
     - vehicule, agence, client, employe, equipement
     - reservation, contrat, paiement, maintenance

---

## ✅ Livrables de l'Atelier 1 - Séance 2

| Livrable | Statut |
|----------|--------|
| Projet Spring Boot exécutable | ✅ |
| Connexion MySQL configurée | ✅ |
| 9 entités JPA créées sans associations | ✅ |
| 5 énumérations créées | ✅ |
| Lombok configuré correctement | ✅ |
| application.properties configuré | ✅ |
| Structure des packages préparée | ✅ |
| Logs SQL activés | ✅ |
| Prêt pour Git commit | ✅ |

---

## 🎯 Critères d'évaluation respectés

- ✅ **Conventions de nommage Java/Spring** : Respectées
- ✅ **Utilisation correcte de Lombok** : `@Getter/@Setter` ciblés (pas @Data)
- ✅ **Clean Code** : Pas de code mort, configuration minimale
- ✅ **Schéma en base conforme** : Tables créées automatiquement
- ✅ **Documentation** : README.md et SETUP_INSTRUCTIONS.md fournis

---

## 📌 Remarques importantes

### ⚠️ Pas d'associations pour l'instant
Les entités sont créées **sans relations** (@ManyToOne, @OneToMany, etc.).
Les associations seront ajoutées lors de l'**Atelier 2 (Séance 3)**.

### ⚠️ Stratégie ddl-auto=update
La configuration `spring.jpa.hibernate.ddl-auto=update` est adaptée au **développement uniquement**.
En production, utiliser `validate` ou un outil de migration (Flyway, Liquibase).

### ⚠️ Mot de passe en clair
Le mot de passe dans `application.properties` est en clair.
En production, utiliser des variables d'environnement ou un coffre-fort de secrets.

---

## 📅 Prochaines étapes (Atelier 2)

L'Atelier 2 ajoutera :
- Relations `@ManyToOne`, `@OneToMany`, `@ManyToMany`
- Stratégies de cascade (`CascadeType`)
- Stratégies de fetch (`FetchType.LAZY` vs `EAGER`)
- Clés étrangères et contraintes

---

## 🎉 Félicitations !

Votre projet AutoLoc est maintenant **complètement configuré** et prêt pour l'Atelier 2 !

Toutes les entités JPA sont créées avec :
- ✅ Annotations JPA correctes
- ✅ Lombok pour réduire le code
- ✅ Types appropriés (BigDecimal pour l'argent, LocalDate pour les dates)
- ✅ Contraintes de base de données (unique, nullable, length)
- ✅ Énumérations pour les statuts

**Commande Git pour sauvegarder votre travail :**
```bash
git add .
git commit -m "Atelier 1 Séance 2 : Projet Spring Boot + 9 entités JPA sans associations"
git push
```

---

**Auteur :** Projet réalisé dans le cadre de l'Atelier 1 Séance 2 - ASI 26-27 ESPRIT  
**Date :** Octobre 2026  
**Prochaine séance :** Atelier 2 - Modélisation complète avec associations JPA
