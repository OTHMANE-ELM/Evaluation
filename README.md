# Hibernate JPA — Gestion de Stock | Projets | État Civil



## Méthodologie

### 1. Dépendances `pom.xml`
- `hibernate-core`
- `mysql-connector-java`
- `jakarta.persistence-api`

### 2. Configuration `hibernate.cfg.xml`
- driver, url, username, password
- dialect · hbm2ddl.auto · show_sql
- `<mapping class="..."/>` pour chaque entité

### 3. `HibernateUtil` — Singleton SessionFactory
```java
new Configuration().configure().buildSessionFactory()
```

### 4. Entités JPA (`ma.projet.classes`)
- `@Entity` `@Table` `@Id` `@GeneratedValue` `@Column`
- `@OneToMany(mappedBy=...)` / `@ManyToOne` `@JoinColumn`
- `@ManyToMany` via entité de jointure avec attributs propres
- Héritage : `@Inheritance(strategy = SINGLE_TABLE | JOINED | TABLE_PER_CLASS)`
- `@NamedQuery` / `@NamedNativeQuery` déclarées sur l'entité

### 5. Interface `IDao<T>` — `ma.projet.dao`
```
create · update · delete · findById · findAll
```

### 6. `AbstractFacade<T>` — `ma.projet.service`
- Implémente `IDao<T>`
- Gestion Session + Transaction + rollback
- Type générique récupéré via `ParameterizedType`

### 7. Services métier (héritent de `AbstractFacade`)
- JPQL : `session.createQuery(...)`
- Named Query : `session.createNamedQuery(...)`
- Named Native Query : `session.createNativeQuery(...)`
- Criteria API : `CriteriaBuilder` → `CriteriaQuery` → `Root`

### 8. Tests `main`
- Insertion de données de test
- Appel de chaque méthode + affichage console formaté

---

## Exercices

| # | Application | Entités clés | Méthodes notables |
|---|-------------|-------------|-------------------|
| 1 | Gestion de Stock | Categorie, Produit, Commande, LigneCommande | Produits par catégorie · par commande · entre deux dates · prix > 100 *(Named Query)* |
| 2 | Gestion de Projets | Projet, Tache, Employe, EmployeTache | Tâches/projets par employé · tâches réalisées · coût > 1000 *(Named Query)* · entre deux dates |
| 3 | État Civil | Homme, Femme, Mariage | Épouses entre dates · nb enfants *(Named Native Query)* · femmes mariées 2x+ *(Named Query)* · hommes mariés à 4 femmes *(Criteria API)* |

---

## Démonstrations vidéo

### Exercice 1 — Gestion de Stock


https://github.com/user-attachments/assets/ae54e8b7-3b5c-4be7-978d-adc026989bee



### Exercice 2 — Gestion de Projets


https://github.com/user-attachments/assets/3cb8641d-7427-4b88-a5a2-0ae13e120dc7



### Exercice 3 — État Civil


https://github.com/user-attachments/assets/08a1a785-02ce-42ec-b39a-abc1a0a6feb6

