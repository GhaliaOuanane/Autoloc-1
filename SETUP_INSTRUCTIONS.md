# Instructions de Configuration - Atelier 1 Séance 2

## Étapes à suivre après avoir créé le projet

### 1. Configurer IntelliJ IDEA

#### 1.1 Activer l'Annotation Processing pour Lombok
1. Ouvrir IntelliJ IDEA
2. Aller dans **File → Settings** (ou **Preferences** sur Mac)
3. Naviguer vers **Build, Execution, Deployment → Compiler → Annotation Processors**
4. ✅ Cocher **"Enable annotation processing"**
5. Cliquer sur **Apply** puis **OK**

#### 1.2 Vérifier le plugin Lombok
1. Aller dans **File → Settings → Plugins**
2. Rechercher **"Lombok"**
3. Si le plugin n'est pas installé, l'installer et redémarrer IntelliJ
4. Le plugin devrait normalement être déjà installé sur IntelliJ récent

#### 1.3 Configurer le JDK
1. Aller dans **File → Project Structure → Project**
2. Définir **SDK: 17** (ou version supérieure)
3. Définir **Language level: 17**

### 2. Configurer MySQL

#### 2.1 S'assurer que MySQL est démarré
```bash
# Windows - vérifier le service MySQL
net start MySQL80

# Ou via MySQL Workbench - démarrer le serveur
```

#### 2.2 Configurer le mot de passe
1. Ouvrir le fichier `src/main/resources/application.properties`
2. Modifier la ligne :
   ```properties
   spring.datasource.password=votre_mot_de_passe
   ```
3. Remplacer `votre_mot_de_passe` par votre mot de passe MySQL root

**Note :** La base de données `autoloc_db` sera créée automatiquement au premier lancement grâce à `createDatabaseIfNotExist=true`

### 3. Télécharger les dépendances Maven

#### Via IntelliJ :
1. IntelliJ devrait automatiquement détecter le `pom.xml` et proposer d'importer le projet Maven
2. Cliquer sur **"Load Maven Project"** dans la notification qui apparaît
3. Ou clic droit sur `pom.xml` → **Maven → Reload Project**

#### Via ligne de commande :
```bash
mvn clean install
```

### 4. Lancer l'application

#### Méthode 1 : Via IntelliJ (recommandé)
1. Ouvrir `src/main/java/tn/esprit/autoloc/AutolocApiApplication.java`
2. Clic droit → **Run 'AutolocApiApplication'**
3. Ou cliquer sur le bouton ▶ vert en haut à droite

#### Méthode 2 : Via Maven
```bash
mvn spring-boot:run
```

### 5. Vérifier que tout fonctionne

#### 5.1 Vérifier les logs dans la console
Vous devriez voir :
```
Started AutolocApiApplication in X.XXX seconds
```

#### 5.2 Vérifier les requêtes SQL
Dans les logs, chercher les requêtes DDL de création des tables :
```sql
create table vehicule (...)
create table agence (...)
create table client (...)
-- etc.
```

#### 5.3 Vérifier la base de données
1. Ouvrir **MySQL Workbench** ou **DBeaver**
2. Se connecter à MySQL
3. Vérifier que la base `autoloc_db` existe
4. Vérifier que les 9 tables ont été créées :
   - vehicule
   - agence
   - client
   - employe
   - equipement
   - reservation
   - contrat
   - paiement
   - maintenance

### 6. Structure du projet créé

```
autoloc-api/
├── src/
│   ├── main/
│   │   ├── java/tn/esprit/autoloc/
│   │   │   ├── domain/                    ✅ 9 entités + 5 énumérations
│   │   │   │   ├── Vehicule.java
│   │   │   │   ├── Agence.java
│   │   │   │   ├── Client.java
│   │   │   │   ├── Employe.java
│   │   │   │   ├── Equipement.java
│   │   │   │   ├── Reservation.java
│   │   │   │   ├── Contrat.java
│   │   │   │   ├── Paiement.java
│   │   │   │   ├── Maintenance.java
│   │   │   │   ├── StatutVehicule.java
│   │   │   │   ├── CategorieVehicule.java
│   │   │   │   ├── RoleEmploye.java
│   │   │   │   ├── StatutReservation.java
│   │   │   │   └── ModePaiement.java
│   │   │   ├── repository/                📂 (Atelier 3)
│   │   │   ├── service/                   📂 (Atelier 4)
│   │   │   ├── web/
│   │   │   │   ├── controller/            📂 (Atelier 5)
│   │   │   │   └── dto/                   📂 (Atelier 6)
│   │   │   └── AutolocApiApplication.java ✅ Classe principale
│   │   └── resources/
│   │       ├── application.properties     ✅ Configuration
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/tn/esprit/autoloc/
│           └── AutolocApiApplicationTests.java
├── pom.xml                                 ✅ Configuration Maven
├── .gitignore                              ✅ Fichiers Git à ignorer
└── README.md                               ✅ Documentation

✅ = Créé et prêt
📂 = Package vide (sera utilisé dans les prochains ateliers)
```

## Problèmes courants et solutions

### ❌ Erreur : "Cannot resolve symbol 'Getter', 'Setter', etc."
**Solution :** L'annotation processing n'est pas activé
- Aller dans File → Settings → Compiler → Annotation Processors
- Cocher "Enable annotation processing"
- Redémarrer IntelliJ

### ❌ Erreur : "Access denied for user 'root'@'localhost'"
**Solution :** Mot de passe incorrect
- Vérifier le mot de passe dans `application.properties`
- Vérifier que MySQL est bien démarré

### ❌ Erreur : "Unknown database 'autoloc_db'"
**Solution :** Le paramètre `createDatabaseIfNotExist=true` devrait créer la base automatiquement
- Vérifier l'URL JDBC dans `application.properties`
- Ou créer manuellement la base : `CREATE DATABASE autoloc_db;`

### ❌ Les dépendances Maven ne se téléchargent pas
**Solution :** 
- Clic droit sur `pom.xml` → Maven → Reload Project
- Ou : mvn clean install
- Vérifier la connexion Internet

## Prochaines étapes

### ✅ Ce qui est fait (Atelier 1 - Séance 2)
- ✅ Projet Spring Boot créé avec Maven
- ✅ Configuration MySQL et Hibernate
- ✅ 9 entités JPA créées (sans associations)
- ✅ 5 énumérations créées
- ✅ Structure des packages préparée

### 📅 Prochains ateliers
- **Atelier 2 (Séance 3)** : Ajout des associations entre entités (@ManyToOne, @OneToMany, @ManyToMany)
- **Atelier 3** : Spring Data JPA Repositories
- **Atelier 4** : Couche Service (logique métier)
- **Atelier 5** : Contrôleurs REST
- **Atelier 6** : DTOs et validation

## Git - Commiter votre travail

Une fois que tout fonctionne :

```bash
# Initialiser Git (si ce n'est pas déjà fait)
git init

# Ajouter tous les fichiers
git add .

# Faire le premier commit
git commit -m "Atelier 1 : init projet Spring Boot + 9 entités JPA sans associations"

# Lier au dépôt distant (remplacer <url> par l'URL de votre dépôt)
git remote add origin <url>

# Pousser sur le dépôt
git push -u origin main
```

## Bon travail ! 🎉

Vous avez maintenant un projet Spring Boot fonctionnel avec :
- ✅ Connexion MySQL configurée
- ✅ 9 entités JPA prêtes à l'emploi
- ✅ Structure du projet claire et organisée
- ✅ Prêt pour l'Atelier 2 (associations)
