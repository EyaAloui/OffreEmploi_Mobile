 Plateforme E-Learning avec Microservices

 **Description:**
 Ce projet est une plateforme de e-learning basée sur une architecture de microservices. Elle utilise plusieurs technologies et frameworks pour gérer différents aspects du système. L'application est divisée en plusieurs services indépendants qui interagissent entre eux. Voici un aperçu des fonctionnalités et des technologies utilisées :

- **Microservices avec Spring Boot :**
  gestion des évaluations, gestion des cours, la gestion des modules, la gestion des leçons, la geston des Réclamations)
  -systèmes de gestion de bases de données relationelles des différents microservices: MySQL et H2
  -et systèmes de gestion de base données non relationelles du microservice avancée gestion des forums: MongoDB
- **Micrpservices avec  - **Express.js**
  -la gestion des forums avec MongoDB
**Authentification**
  - **Next.js** pour l'interface utilisateur
  - **Clerk** pour l'authentification
  - **Google Maps API** pour l'intégration des cartes géographiques
**Accès et gestion des microservices**
  - un api router: "API Gateway" avec un registre: "Eureka Discovery" pour la gestion des services
- **Déploiement et gestion des microservices :**
  - **Docker Compose** pour faciliter le déploiement et l'orchestration des microservices dans des conteneurs Docker

Fonctionnalités

- **Évaluations** : Gestion des évaluations des étudiants, avec un stockage en base de données H2.
- **Cours** : Gestion des cours avec une base de données H2.
- **Modules** : Gestion des modules de cours avec une base de données MySQL.
- **Leçons et Ressources pédagogiques** : Gestion des leçons et des ressources pédagogiques, stockées dans une base de données H2.
- **Réclamations et réponses** : Les étudiants peuvent soumettre des réclamations (avec un filtre bad words) uploder une image avec filtrage, recherche et sort par date de création et priorité et recevoir des réponses via un système de gestion basé sur MySQL.
- **Forums** : Système de forum pour les étudiants, géré via un backend Express.js et MongoDB.
- **Authentification** : Authentification des utilisateurs via **Clerk** et gestion des sessions avec **Next.js**.
