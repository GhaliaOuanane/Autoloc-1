# 🚀 Quick Start - AutoLoc API

## Démarrage rapide en 5 étapes

### ✅ Étape 1: Configurer le mot de passe MySQL
Ouvrir `src/main/resources/application.properties` et modifier:
```properties
spring.datasource.password=VOTRE_MOT_DE_PASSE_ICI
```

### ✅ Étape 2: Activer Annotation Processing (IntelliJ)
```
File → Settings → Build, Execution, Deployment → Compiler → Annotation Processors
☑ Enable annotation processing
```

### ✅ Étape 3: Recharger Maven
Dans IntelliJ: Clic droit sur `pom.xml` → Maven → Reload Project

### ✅ Étape 4: Lancer l'application
Ouvrir `AutolocApiApplication.java` → Clic droit → Run

### ✅ Étape 5: Vérifier
- Console: "Started AutolocApiApplication in X.XXX seconds"
- MySQL: Base `autoloc_db` avec 9 tables créées

---

## 📁 Fichiers importants

| Fichier | Description |
|---------|-------------|
| `pom.xml` | Configuration Maven avec toutes les dépendances |
| `src/main/resources/application.properties` | Configuration Spring Boot et MySQL |
| `AutolocApiApplication.java` | Classe principale à lancer |
| `domain/*.java` | 9 entités JPA + 5 énumérations |

---

## 🔍 Vérification rapide

### Dans les logs (console IntelliJ)
```
Hibernate: create table vehicule (...)
Hibernate: create table agence (...)
Hibernate: create table client (...)
Hibernate: create table employe (...)
Hibernate: create table equipement (...)
Hibernate: create table reservation (...)
Hibernate: create table contrat (...)
Hibernate: create table paiement (...)
Hibernate: create table maintenance (...)

Started AutolocApiApplication in 3.456 seconds
```

### Dans MySQL (Workbench/DBeaver)
```sql
USE autoloc_db;
SHOW TABLES;
```

Résultat attendu: 9 tables
- agence
- client
- contrat
- employe
- equipement
- maintenance
- paiement
- reservation
- vehicule

---

## ❌ Problèmes courants

### "Cannot resolve symbol 'Getter'"
→ Activer Annotation Processing (voir Étape 2)

### "Access denied for user 'root'"
→ Vérifier le mot de passe dans application.properties

### Les dépendances ne se téléchargent pas
→ Maven → Reload Project ou `mvn clean install`

---

## 📚 Documentation complète

Pour plus de détails:
- **SETUP_INSTRUCTIONS.md** - Guide détaillé d'installation
- **PROJET_COMPLET.md** - Documentation technique complète
- **CHECKLIST.md** - Liste de vérification et résolution de problèmes
- **README.md** - Vue d'ensemble du projet

---

## 🎯 Objectifs atteints

✅ Projet Spring Boot créé  
✅ 9 entités JPA sans associations  
✅ 5 énumérations  
✅ Configuration MySQL/Hibernate  
✅ Structure professionnelle  

**→ Prêt pour l'Atelier 2: Associations JPA**

---

## 💾 Sauvegarder sur Git

```bash
git add .
git commit -m "Atelier 1 Séance 2 : Init projet Spring Boot + 9 entités JPA"
git push
```

---

**Bon courage! 🚀**
