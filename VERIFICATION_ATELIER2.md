# ✅ Checklist de vérification - Atelier 2

## 📋 Étapes à suivre

### ✅ Étape 1 : Vérification du code
- [x] `application.properties` modifié : `ddl-auto=create`
- [x] 8 associations JPA ajoutées dans les entités
- [x] Imports `java.util.List` ajoutés où nécessaire
- [x] Annotations JPA correctes (@ManyToOne, @OneToMany, @OneToOne, @ManyToMany)

### ⏳ Étape 2 : Lancer l'application
1. Ouvrir `AutolocApiApplication.java`
2. Clic droit → Run
3. Observer la console

### ⏳ Étape 3 : Vérifier les logs de création des tables

Dans la console, vous devriez voir :

```sql
Hibernate: drop table if exists agence cascade
Hibernate: drop table if exists client cascade
Hibernate: drop table if exists contrat cascade
Hibernate: drop table if exists employe cascade
Hibernate: drop table if exists equipement cascade
Hibernate: drop table if exists maintenance cascade
Hibernate: drop table if exists paiement cascade
Hibernate: drop table if exists reservation cascade
Hibernate: drop table if exists vehicule cascade
Hibernate: drop table if exists vehicule_equipement cascade

Hibernate: create table agence (...)
Hibernate: create table client (...)
Hibernate: create table contrat (...)
Hibernate: create table employe (...)
Hibernate: create table equipement (...)
Hibernate: create table maintenance (...)
Hibernate: create table paiement (...)
Hibernate: create table reservation (...)
Hibernate: create table vehicule (...)
Hibernate: create table vehicule_equipement (...)

Hibernate: alter table contrat add constraint FK... foreign key (reservation_id) references reservation
Hibernate: alter table employe add constraint FK... foreign key (agence_id) references agence
Hibernate: alter table maintenance add constraint FK... foreign key (vehicule_id) references vehicule
Hibernate: alter table paiement add constraint FK... foreign key (contrat_id) references contrat
Hibernate: alter table reservation add constraint FK... foreign key (client_id) references client
Hibernate: alter table reservation add constraint FK... foreign key (vehicule_id) references vehicule
Hibernate: alter table vehicule add constraint FK... foreign key (agence_id) references agence
Hibernate: alter table vehicule_equipement add constraint FK... foreign key (equipement_id) references equipement
Hibernate: alter table vehicule_equipement add constraint FK... foreign key (vehicule_id) references vehicule

Started AutolocApiApplication in X.XXX seconds
```

**Points importants à vérifier :**
- ✅ 10 tables `drop` (suppression des anciennes tables)
- ✅ 10 tables `create` (création des nouvelles tables)
- ✅ 9 contraintes `foreign key` créées
- ✅ Message "Started AutolocApiApplication" sans erreur

---

### ⏳ Étape 4 : Vérifier dans MySQL

#### Option 1 : MySQL Workbench
1. Se connecter à MySQL
2. Sélectionner la base `autoloc_db`
3. Regarder la liste des tables

#### Option 2 : Ligne de commande MySQL
```sql
USE autoloc_db;

-- Compter les tables (attendu : 10)
SELECT COUNT(*) as nombre_tables 
FROM information_schema.tables 
WHERE table_schema = 'autoloc_db';

-- Lister les tables
SHOW TABLES;
```

**Résultat attendu :**
```
+---------------------------+
| Tables_in_autoloc_db      |
+---------------------------+
| agence                    |
| client                    |
| contrat                   |
| employe                   |
| equipement                |
| maintenance               |
| paiement                  |
| reservation               |
| vehicule                  |
| vehicule_equipement       |
+---------------------------+
10 rows in set
```

#### Vérifier les clés étrangères

```sql
-- Voir la structure de chaque table avec FK

DESC vehicule;
-- Devrait montrer : agence_id

DESC employe;
-- Devrait montrer : agence_id

DESC reservation;
-- Devrait montrer : client_id, vehicule_id

DESC contrat;
-- Devrait montrer : reservation_id

DESC paiement;
-- Devrait montrer : contrat_id

DESC maintenance;
-- Devrait montrer : vehicule_id

DESC vehicule_equipement;
-- Devrait montrer : vehicule_id, equipement_id
```

#### Vérifier les contraintes de clés étrangères

```sql
-- Afficher toutes les FK de la base
SELECT 
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM information_schema.KEY_COLUMN_USAGE
WHERE TABLE_SCHEMA = 'autoloc_db' 
  AND REFERENCED_TABLE_NAME IS NOT NULL
ORDER BY TABLE_NAME, COLUMN_NAME;
```

**Résultat attendu : 9 lignes de contraintes FK**

---

### ⏳ Étape 5 : Remettre ddl-auto=update

Une fois toutes les vérifications faites :

1. Arrêter l'application (carré rouge dans IntelliJ)
2. Ouvrir `src/main/resources/application.properties`
3. Modifier la ligne :
   ```properties
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Sauvegarder le fichier

---

### ⏳ Étape 6 : Relancer pour vérifier

1. Relancer l'application
2. Vérifier dans les logs qu'il n'y a **pas** de `drop table`
3. Vérifier qu'il n'y a **pas** de `create table`
4. L'application devrait démarrer directement avec le schéma existant

---

## 📊 Tableau de vérification

| Item à vérifier | Attendu | Vérifié |
|-----------------|---------|---------|
| Nombre de tables | 10 | ⬜ |
| Table `agence` | ✓ | ⬜ |
| Table `client` | ✓ | ⬜ |
| Table `contrat` | ✓ | ⬜ |
| Table `employe` | ✓ | ⬜ |
| Table `equipement` | ✓ | ⬜ |
| Table `maintenance` | ✓ | ⬜ |
| Table `paiement` | ✓ | ⬜ |
| Table `reservation` | ✓ | ⬜ |
| Table `vehicule` | ✓ | ⬜ |
| Table `vehicule_equipement` | ✓ | ⬜ |
| FK : vehicule → agence | ✓ | ⬜ |
| FK : employe → agence | ✓ | ⬜ |
| FK : reservation → client | ✓ | ⬜ |
| FK : reservation → vehicule | ✓ | ⬜ |
| FK : contrat → reservation | ✓ | ⬜ |
| FK : paiement → contrat | ✓ | ⬜ |
| FK : maintenance → vehicule | ✓ | ⬜ |
| FK : vehicule_equipement → vehicule | ✓ | ⬜ |
| FK : vehicule_equipement → equipement | ✓ | ⬜ |
| `ddl-auto=update` restauré | ✓ | ⬜ |

---

## ❌ Problèmes potentiels et solutions

### Erreur : "Cannot delete or update a parent row"
**Cause :** Tentative de suppression avec ddl-auto=create alors que des FK existent

**Solution :** 
- MySQL doit supporter les contraintes CASCADE
- Ou supprimer manuellement les tables dans le bon ordre

### Erreur : "Table already exists"
**Cause :** Les tables n'ont pas été supprimées correctement

**Solution :**
```sql
DROP DATABASE autoloc_db;
CREATE DATABASE autoloc_db;
```
Puis relancer l'application

### Erreur : "Column 'xxx_id' not found"
**Cause :** Mauvais nom de colonne dans @JoinColumn

**Solution :** Vérifier que le nom correspond bien à l'attribut mappedBy

### Les FK ne sont pas créées
**Cause :** Moteur de stockage MySQL

**Solution :** Vérifier que MySQL utilise InnoDB (par défaut)
```sql
SHOW TABLE STATUS WHERE Name='vehicule';
-- Engine devrait être InnoDB
```

---

## 📝 Commandes Git après vérification

Une fois tout vérifié et ddl-auto remis à `update` :

```bash
git add .
git commit -m "Atelier 2: Ajout des associations JPA entre entités"
git push
```

---

## 🎯 Résumé des modifications

### Fichiers modifiés : 10
1. `application.properties` → ddl-auto=create puis update
2. `Agence.java` → +2 associations (vehicules, employes)
3. `Vehicule.java` → +4 associations (agence, reservations, maintenances, equipements)
4. `Client.java` → +1 association (reservations)
5. `Employe.java` → +1 association (agence)
6. `Equipement.java` → +1 association (vehicules)
7. `Reservation.java` → +3 associations (client, vehicule, contrat)
8. `Contrat.java` → +2 associations (reservation, paiements)
9. `Paiement.java` → +1 association (contrat)
10. `Maintenance.java` → +1 association (vehicule)

### Associations ajoutées : 8
1. Agence ↔ Vehicule (OneToMany/ManyToOne)
2. Agence ↔ Employe (OneToMany/ManyToOne)
3. Client ↔ Reservation (OneToMany/ManyToOne)
4. Vehicule ↔ Reservation (OneToMany/ManyToOne)
5. Vehicule ↔ Maintenance (OneToMany/ManyToOne)
6. Vehicule ↔ Equipement (ManyToMany)
7. Reservation ↔ Contrat (OneToOne)
8. Contrat ↔ Paiement (OneToMany/ManyToOne)

---

**Bon courage pour la vérification ! 🚀**
