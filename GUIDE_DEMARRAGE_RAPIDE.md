# 🚀 Guide de Démarrage Rapide - AutoLoc API

## En 5 minutes

### Étape 1 : Vérifier les prérequis
```bash
java -version      # ✅ Java 17+
mvn -version       # ✅ Maven 3.6+
mysql --version    # ✅ MySQL 8+
```

### Étape 2 : Configurer MySQL
1. Démarrer MySQL
   ```bash
   net start MySQL80  # Windows
   ```

2. Éditer `src/main/resources/application.properties`
   ```properties
   spring.datasource.password=VOTRE_MOT_DE_PASSE
   ```

### Étape 3 : Lancer l'application
```bash
mvn spring-boot:run
```

### Étape 4 : Vérifier
✅ Logs : "Started AutolocApiApplication in X.XXX seconds"  
✅ Logs : "Found 9 JPA repository interfaces"  
✅ MySQL : 10 tables créées dans `autoloc_db`

---

## 🧪 Tester les services (optionnel)

### Activer la démonstration automatique
1. Ouvrir `src/main/java/tn/esprit/autoloc/demo/AutolocDemoRunner.java`
2. Décommenter `@Component`
3. Relancer l'application
4. Observer les logs de test

---

## 📁 Structure du projet

```
src/main/java/tn/esprit/autoloc/
├── domain/           → 9 entités + 5 enums
├── repository/       → 9 interfaces JpaRepository
├── service/          → 9 interfaces + 9 implémentations
└── demo/             → Classe de test (AutolocDemoRunner)
```

---

## 🔍 Vérifications rapides

### Dans MySQL Workbench
```sql
USE autoloc_db;
SHOW TABLES;  -- Doit afficher 10 tables
```

### Dans les logs
Rechercher :
- ✅ `Found 9 JPA repository interfaces`
- ✅ `Started AutolocApiApplication`
- ❌ Aucune exception

---

## 📚 Prochaines étapes

1. **Tester les services** : Activer AutolocDemoRunner
2. **Créer des contrôleurs REST** (Atelier 5)
3. **Ajouter des DTOs** (Atelier 6)
4. **Tester avec Postman** (après Atelier 5)

---

## 🆘 Problèmes courants

### Problème : "Access denied for user 'root'"
**Solution** : Vérifier le mot de passe dans `application.properties`

### Problème : "Cannot resolve symbol 'Getter'"
**Solution** : Activer Annotation Processing dans IntelliJ

### Problème : Tables non créées
**Solution** : Vérifier que MySQL est démarré et accessible

---

## 📖 Documentation complète

Pour plus de détails, consulter :
- `README.md` - Vue d'ensemble
- `SETUP_INSTRUCTIONS.md` - Installation détaillée
- `ATELIER3_RESUME.md` - Détails techniques
- `docs/TESTS_MANUELS.md` - Guide de tests

---

**Temps estimé** : 5-10 minutes  
**Difficulté** : ⭐ Facile
