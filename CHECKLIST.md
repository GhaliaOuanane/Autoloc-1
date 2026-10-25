# ✅ Checklist - Atelier 1 Séance 2

## 📋 Ce qui a été fait automatiquement

### Structure du projet
- [x] Projet Maven créé avec structure complète
- [x] Fichier `pom.xml` avec toutes les dépendances
- [x] Classe principale `AutolocApiApplication.java`
- [x] Fichier `application.properties` configuré
- [x] Structure des packages (domain, repository, service, web)
- [x] Fichier `.gitignore`
- [x] Documentation (README.md, SETUP_INSTRUCTIONS.md)

### Entités JPA (9/9)
- [x] 1. Vehicule
- [x] 2. Agence
- [x] 3. Client
- [x] 4. Employe
- [x] 5. Equipement
- [x] 6. Reservation
- [x] 7. Contrat
- [x] 8. Paiement
- [x] 9. Maintenance

### Énumérations (5/5)
- [x] StatutVehicule
- [x] CategorieVehicule
- [x] RoleEmploye
- [x] StatutReservation
- [x] ModePaiement

---

## ⚙️ Configuration à faire MANUELLEMENT

### 1. IntelliJ IDEA

#### ✅ À FAIRE : Activer Annotation Processing
```
File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors
☑ Enable annotation processing
```

#### ✅ À VÉRIFIER : Plugin Lombok installé
```
File → Settings → Plugins
Rechercher: "Lombok"
Si absent: Installer et redémarrer IntelliJ
```

#### ✅ À VÉRIFIER : JDK configuré
```
File → Project Structure → Project
SDK: 17 (ou supérieur)
Language level: 17
```

---

### 2. MySQL

#### ✅ À FAIRE : Démarrer MySQL
Windows:
```cmd
net start MySQL80
```

Ou via MySQL Workbench: Démarrer le serveur

#### ✅ À FAIRE : Configurer le mot de passe
Ouvrir: `src/main/resources/application.properties`

Modifier la ligne:
```properties
spring.datasource.password=votre_mot_de_passe
```

⚠️ **IMPORTANT**: Remplacer par VOTRE mot de passe MySQL root

---

### 3. Maven

#### ✅ À FAIRE : Charger les dépendances
Dans IntelliJ:
- Notification "Maven projects need to be imported" → Click "Import"
- Ou: Clic droit sur `pom.xml` → Maven → Reload Project

Ou en ligne de commande:
```bash
mvn clean install
```

---

## 🚀 Lancement de l'application

### ✅ ÉTAPE 1: Lancer l'application

**Dans IntelliJ:**
1. Ouvrir: `src/main/java/tn/esprit/autoloc/AutolocApiApplication.java`
2. Clic droit → Run 'AutolocApiApplication'
3. Ou cliquer sur le bouton ▶ vert

**Ou en ligne de commande:**
```bash
mvn spring-boot:run
```

---

### ✅ ÉTAPE 2: Vérifier les logs

Dans la console IntelliJ, chercher:
```
Started AutolocApiApplication in X.XXX seconds
```

Vérifier aussi les requêtes DDL:
```sql
Hibernate: create table vehicule (...)
Hibernate: create table agence (...)
Hibernate: create table client (...)
...
```

---

### ✅ ÉTAPE 3: Vérifier la base de données

**Avec MySQL Workbench ou DBeaver:**

1. Se connecter à MySQL (localhost:3306)
2. Vérifier que la base `autoloc_db` existe
3. Vérifier que les 9 tables ont été créées:
   - [ ] vehicule
   - [ ] agence
   - [ ] client
   - [ ] employe
   - [ ] equipement
   - [ ] reservation
   - [ ] contrat
   - [ ] paiement
   - [ ] maintenance

---

## 📝 Git - Sauvegarder votre travail

Une fois que tout fonctionne:

```bash
# Si Git n'est pas initialisé
git init

# Ajouter tous les fichiers
git add .

# Créer le commit
git commit -m "Atelier 1 Séance 2 : Init projet Spring Boot + 9 entités JPA"

# Si vous avez un dépôt distant
git remote add origin <URL_DE_VOTRE_DEPOT>
git push -u origin main
```

---

## ❌ Problèmes fréquents et solutions

### Problème 1: "Cannot resolve symbol 'Getter'"
**Cause:** Annotation processing non activé

**Solution:**
```
File → Settings → Compiler → Annotation Processors
☑ Enable annotation processing
Redémarrer IntelliJ
```

---

### Problème 2: "Access denied for user 'root'@'localhost'"
**Cause:** Mot de passe incorrect

**Solution:**
- Vérifier le mot de passe dans `application.properties`
- S'assurer que MySQL est démarré

---

### Problème 3: Les tables ne sont pas créées
**Cause:** Problème de connexion ou configuration

**Solution:**
1. Vérifier que MySQL est démarré
2. Vérifier l'URL, username, password dans `application.properties`
3. Regarder les logs pour voir l'erreur exacte
4. La base `autoloc_db` sera créée automatiquement grâce à `createDatabaseIfNotExist=true`

---

### Problème 4: Maven ne télécharge pas les dépendances
**Cause:** Problème de connexion ou configuration Maven

**Solution:**
```
Clic droit sur pom.xml → Maven → Reload Project
Ou: mvn clean install -U
```

Vérifier la connexion Internet

---

## 📊 Récapitulatif

### ✅ Fichiers créés
- 29 fichiers Java (1 main + 9 entités + 5 enums + packages + test)
- 1 fichier pom.xml
- 1 fichier application.properties
- 4 fichiers de documentation (README, SETUP, PROJET_COMPLET, CHECKLIST)
- 1 fichier .gitignore

### ✅ Technologies configurées
- Spring Boot 3.2.0
- Spring Data JPA
- Hibernate
- MySQL Driver
- Lombok
- Validation
- DevTools

### ✅ Prêt pour
- Atelier 2: Associations JPA (@ManyToOne, @OneToMany, @ManyToMany)
- Atelier 3: Spring Data JPA Repositories
- Atelier 4: Services métier
- Atelier 5: Contrôleurs REST
- Atelier 6: DTOs

---

## 🎯 Objectifs de l'Atelier 1 - Statut

| Objectif | Statut |
|----------|--------|
| Créer un projet Spring Boot/Maven avec IntelliJ | ✅ |
| Comprendre l'arborescence et le rôle du pom.xml | ✅ |
| Configurer MySQL et Hibernate | ✅ |
| Créer entité Vehicule propre avec Lombok | ✅ |
| Vérifier la génération DDL au démarrage | ⏳ À tester |
| **BONUS**: Créer les 8 autres entités | ✅ |

⏳ = À vérifier en lançant l'application

---

## 📞 Aide

Si vous rencontrez des problèmes:

1. Consulter `SETUP_INSTRUCTIONS.md` pour les détails
2. Consulter `PROJET_COMPLET.md` pour comprendre la structure
3. Vérifier la section "Problèmes fréquents" ci-dessus
4. Consulter les logs de l'application pour voir l'erreur exacte

---

## 🎉 Prochaine étape

Une fois que l'application démarre correctement et que les tables sont créées:

**→ Passer à l'Atelier 2 (Séance 3): Associations JPA**

Les associations permettront de lier les entités entre elles:
- Vehicule ↔ Agence
- Reservation ↔ Client
- Reservation ↔ Vehicule
- Contrat ↔ Reservation
- etc.

**Bon courage! 🚀**
