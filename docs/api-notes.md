# Notes de l'API AutoLoc - Atelier 5

## A. Endpoints

| Contrôleur | Verbe et Route | Service appelé | Code succès | Codes d'erreur |
|------------|----------------|----------------|-------------|----------------|
| **VehiculeController** | GET /api/vehicules | findAll() | 200 | - |
| | GET /api/vehicules/{id} | findById() | 200 | 404 |
| | POST /api/vehicules | creerVehicule() | 201 | 400 |
| | PUT /api/vehicules/{id} | modifierVehicule() | 200 | 404, 400 |
| | DELETE /api/vehicules/{id} | supprimerVehicule() | 204 | 404 |
| **ClientController** | GET /api/clients | findAll() | 200 | - |
| | GET /api/clients/{id} | findById() | 200 | 404 |
| | POST /api/clients | creerClient() | 201 | 400 |
| | PUT /api/clients/{id} | modifierClient() | 200 | 404, 400 |
| | DELETE /api/clients/{id} | supprimerClient() | 204 | 404 |
| **AgenceController** | GET /api/agences | findAll() | 200 | - |
| | GET /api/agences/{id} | findById() | 200 | 404 |
| | POST /api/agences | creerAgence() | 201 | 400 |
| | PUT /api/agences/{id} | modifierAgence() | 200 | 404, 400 |
| | DELETE /api/agences/{id} | supprimerAgence() | 204 | 404 |
| **ContratController** | GET /api/contrats | findAll() | 200 | - |
| | GET /api/contrats/{id} | findById() | 200 | 404 |
| | POST /api/contrats | creerContrat() | 201 | 400 |
| | PUT /api/contrats/{id} | modifierContrat() | 200 | 404, 400 |
| | DELETE /api/contrats/{id} | supprimerContrat() | 204 | 404 |
| **PaiementController** | GET /api/paiements | findAll() | 200 | - |
| | GET /api/paiements/{id} | findById() | 200 | 404 |
| **ReservationController** | GET /api/reservations | findAll() | 200 | - |
| | GET /api/reservations/{id} | findById() | 200 | 404 |
| | POST /api/reservations | creerReservation() | 201 | 400 |
| | PUT /api/reservations/{id} | modifierReservation() | 200 | 404, 400 |
| | DELETE /api/reservations/{id} | supprimerReservation() | 204 | 404 |
| **EmployeController** | GET /api/employes | findAll() | 200 | - |
| | GET /api/employes/{id} | findById() | 200 | 404 |
| | POST /api/employes | creerEmploye() | 201 | 400 |
| | PUT /api/employes/{id} | modifierEmploye() | 200 | 404, 400 |
| | DELETE /api/employes/{id} | supprimerEmploye() | 204 | 404 |
| **EquipementController** | GET /api/equipements | findAll() | 200 | - |
| | GET /api/equipements/{id} | findById() | 200 | 404 |
| | POST /api/equipements | creerEquipement() | 201 | 400 |
| | PUT /api/equipements/{id} | modifierEquipement() | 200 | 404, 400 |
| | DELETE /api/equipements/{id} | supprimerEquipement() | 204 | 404 |
| **MaintenanceController** | GET /api/maintenances | findAll() | 200 | - |
| | GET /api/maintenances/{id} | findById() | 200 | 404 |
| | POST /api/maintenances | creerMaintenance() | 201 | 400 |
| | PUT /api/maintenances/{id} | modifierMaintenance() | 200 | 404, 400 |
| | DELETE /api/maintenances/{id} | supprimerMaintenance() | 204 | 404 |

## B. Annotations Jackson

### Association Contrat ↔ Paiement (Composition)

**Annotation retenue** : `@JsonManagedReference` côté Contrat et `@JsonBackReference` côté Paiement

**Justification** : 
- Cette association est une vraie composition (cascade = ALL, orphanRemoval = true)
- Les paiements sont créés dans le JSON du contrat parent
- `@JsonManagedReference("contrat-paiements")` sur la liste permet de sérialiser les paiements
- `@JsonBackReference("contrat-paiements")` sur la référence au contrat évite la boucle infinie

**Différence entre @JsonBackReference et @JsonIgnore** :
- `@JsonBackReference` : La propriété est ignorée à la sérialisation mais reste présente à la désérialisation. Le lien parent-enfant est maintenu automatiquement par Jackson.
- `@JsonIgnore` : La propriété est complètement ignorée dans les deux sens (sérialisation et désérialisation). Utile pour les associations inverses où l'enfant est créé séparément et désigne son parent par ID.

### Autres associations

Pour les autres associations (Agence ↔ Vehicules, Client ↔ Reservations, etc.), nous utilisons `@JsonIgnore` sur le côté inverse car :
- Ces entités sont créées séparément (pas de cascade ALL)
- L'enfant désigne son parent via un identifiant : `{ "agence": {"id": 1} }`
- Cela évite les références circulaires sans perdre la fonctionnalité

## C. Postman et Swagger

### Postman
**Rôle** : Outil de test d'API REST
**Apports** :
- Permet d'envoyer des requêtes HTTP personnalisées avec différents verbes (GET, POST, PUT, DELETE)
- Sauvegarde et organisation des requêtes en collections
- Possibilité d'écrire des scripts de test pour valider automatiquement les réponses
- Variables d'environnement pour faciliter les tests sur différents serveurs
- Historique des requêtes et résultats

**Exemple concret de l'atelier** : Création d'une collection "AutoLoc" avec un dossier "Contrats" contenant les requêtes pour tester le CRUD complet. Utilisation d'une variable `{{baseUrl}}` et d'un script pour mémoriser l'ID créé : `pm.environment.set("contratId", pm.response.json().id)`

### Swagger UI
**Rôle** : Documentation interactive de l'API
**Apports** :
- Génère automatiquement la documentation à partir des annotations du code
- Interface web permettant d'explorer et tester l'API directement
- Documentation toujours synchronisée avec le code (évite les documentations obsolètes)
- Affiche les schémas de données, codes de retour, paramètres obligatoires
- Permet aux développeurs frontend de comprendre l'API sans lire le code backend

**Exemple concret de l'atelier** : Accès à `http://localhost:8080/api/swagger-ui.html` pour voir tous les endpoints organisés par tags (@Tag). Les annotations @Operation et @ApiResponse enrichissent automatiquement la documentation visible dans Swagger.

### Complémentarité
- **Postman** : Idéal pour tester en profondeur, créer des scénarios de test, automatiser les validations
- **Swagger** : Idéal pour découvrir l'API, partager la documentation avec l'équipe, tester rapidement un endpoint

## D. Anomalies SonarQube

### Anomalie 1 : Imports inutilisés
**Règle** : "Unused imports should be removed" (java:S1128)
**Explication** : Des imports de classes qui ne sont jamais utilisées dans le code augmentent inutilement la taille du fichier et peuvent prêter à confusion.
**Correction** : Suppression des imports inutilisés avec le raccourci IDE (Ctrl+Alt+O dans IntelliJ) ou manuellement.

### Anomalie 2 : Injection par constructeur préférée
**Règle** : "Field injection is not recommended" (java:S3306)
**Explication** : L'injection par attribut (@Autowired sur un champ) rend les tests difficiles et crée un couplage fort. L'injection par constructeur avec des champs final garantit l'immutabilité et facilite les tests.
**Correction** : Utilisation de `@RequiredArgsConstructor` de Lombok avec des champs `private final` pour l'injection des services.

### Anomalie 3 : Exposition directe des entités JPA
**Règle** : "JPA entities should not be used directly in REST controllers"
**Explication** : Exposer directement les entités JPA couple l'API à la structure de la base de données, expose des champs internes (version, lazy proxies), et rend difficile l'évolution de l'API indépendamment du modèle de données.
**Correction** : Utilisation de DTO (Data Transfer Objects) pour découpler l'API du modèle de données. Cette correction sera appliquée dans le Chapitre 10.

## E. Questions

### 1. Quel composant reçoit toutes les requêtes, et comment retrouve-t-il la méthode du contrôleur à appeler ?

Le **DispatcherServlet** est le composant central de Spring MVC qui reçoit toutes les requêtes HTTP. Il utilise le **HandlerMapping** pour trouver la méthode du contrôleur appropriée en comparant :
- Le chemin de l'URL (`/api/contrats/{id}`)
- Le verbe HTTP (GET, POST, PUT, DELETE)
- Les annotations `@RequestMapping`, `@GetMapping`, etc.

Une fois la méthode trouvée, le DispatcherServlet délègue l'exécution au contrôleur, puis utilise Jackson pour convertir le résultat en JSON.

### 2. Pourquoi ContratController dépend-il de IContratService et non de ContratRepository ni de ContratServiceImpl ?

**Dépendance à l'interface (IContratService)** :
- **Principe d'inversion de dépendance (SOLID)** : Les modules de haut niveau ne doivent pas dépendre des modules de bas niveau, mais d'abstractions
- **Testabilité** : Permet de mocker facilement le service dans les tests unitaires
- **Flexibilité** : On peut changer l'implémentation du service sans toucher au contrôleur
- **Séparation des responsabilités** : Le contrôleur ne doit pas accéder directement aux données (repository) ni être couplé à une implémentation concrète

**Pourquoi pas ContratRepository** : Le contrôleur doit rester dans la couche web et ne pas accéder directement à la couche de persistance. La logique métier doit rester dans la couche service.

### 3. Pourquoi un POST renvoie-t-il 201 et un DELETE 204 ? Pourquoi une ressource absente donne-t-elle 404 et non 500 ?

**POST → 201 Created** :
- Indique qu'une nouvelle ressource a été créée avec succès
- Le corps de la réponse contient la ressource créée (avec son ID généré)
- Conforme aux conventions REST

**DELETE → 204 No Content** :
- Indique que la ressource a été supprimée avec succès
- Pas de corps de réponse car la ressource n'existe plus
- Permet de distinguer le succès (204) de l'échec (404)

**404 Not Found vs 500 Internal Server Error** :
- **404** : Erreur client - la ressource demandée n'existe pas (comportement attendu et géré)
- **500** : Erreur serveur - problème technique inattendu (bug, base de données inaccessible, exception non gérée)
- Le 404 est approprié car l'absence de ressource est une situation normale et prévisible, gérée explicitement par l'exception `ResourceNotFoundException`

### 4. Pourquoi PaiementController n'expose-t-il que des GET ? Quelle réponse reçoit un POST /api/paiements ?

**Lecture seule (GET uniquement)** :
- Les paiements sont gérés par cascade depuis le Contrat (cascade = ALL)
- Un paiement n'existe pas indépendamment de son contrat
- La création/modification/suppression se fait via le contrat parent

**POST /api/paiements → 405 Method Not Allowed** :
- Le endpoint POST n'existe pas dans PaiementController
- Spring retourne automatiquement 405 pour indiquer que la méthode HTTP n'est pas supportée
- C'est le comportement correct : on ne peut pas créer un paiement isolément

### 5. Quels risques présente l'exposition directe des entités JPA ? Pourquoi Contrat.paiements utilise-t-il @JsonManagedReference alors que Agence.vehicules utilise @JsonIgnore ?

**Risques de l'exposition directe des entités JPA** :
1. **Couplage API/Base de données** : Toute modification de la structure de la base impacte l'API
2. **Exposition de champs internes** : Version JPA, lazy proxies, timestamps techniques
3. **Problèmes de sérialisation** : Boucles infinies, proxies Hibernate, LazyInitializationException
4. **Sécurité** : Risque d'exposer des données sensibles (mots de passe, tokens)
5. **Évolution difficile** : Impossible de modifier l'API sans changer le modèle de données
6. **Performance** : Chargement de données inutiles (associations EAGER non contrôlées)

**Contrat.paiements avec @JsonManagedReference** :
- **Composition stricte** : Les paiements font partie intégrante du contrat
- **Cascade ALL** : Créer un contrat crée ses paiements (composition parent-enfant)
- **JSON imbriqué** : On veut créer le contrat ET ses paiements en une seule requête POST
- **Gestion du cycle de vie** : Les paiements sont créés, modifiés et supprimés avec le contrat

**Agence.vehicules avec @JsonIgnore** :
- **Association simple** : Un véhicule peut exister indépendamment de son agence
- **Création séparée** : On crée d'abord une agence, puis on lui affecte des véhicules
- **Référence par ID** : `{ "agence": {"id": 1} }` suffit pour lier un véhicule à une agence
- **Pas de cascade** : Supprimer une agence ne supprime pas automatiquement ses véhicules
- **Éviter la surcharge** : Lister les agences ne doit pas charger tous leurs véhicules

Cette distinction reflète la différence entre **composition** (Contrat/Paiement) et **association** (Agence/Véhicule) dans le modèle métier.
