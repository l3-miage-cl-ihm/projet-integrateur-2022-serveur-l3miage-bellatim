Groupe 5

# Description api

L'API expose des points CRUD à travers 4 controleurs différents: ChamiController, DefiController, VisiteController, et SSEController.
Les 3 premiers accèdent à des Services qui servent d'interface aux différents Repository qui implémentent JPARepository, ce qui nous évite d'écrire des requêtes SQL (JPA + Hibernate).
On un repository par modèle de données ainsi qu'un service par repository. //dto

Une classe VisiteDTO (Data Transfer Object) ainsi qu'un mapper (DTO -> modèle, modèls -> DTO) ont été prévu pour faciliter certains transferts de données (transmisson d'identifiant uniquement) mais n'ont pas été utilisé finalement.

On a une deuxième version des contrôleurs entièrement commentés, qui est implémenté différemment du reste (JDBC).

SSEController (ServiceSideEvent Controller) est un contrôleur à part il permet aux clients de "s'abonner" au serveur et de recevoir des notifications à chaque modification de la base de données, ainsi le client sera mis à jour en temps réel sans recharger la page et sans faire des demandes à répétition (polling). Le service SSEservice permet à un contrôleur qu l'injecte de lancer une notification manuelle.

Sécurité : chaque client qui interroge l'API doit envoyer un jeton (token) qui lui donne accès à l'API. Selon le point exposé, il y a des vérifications supplémentaires comme l'identifiant.

Extrait du token, afin d'empêcher des utilisateurs malveillants de supprimer ou de modifier les données des autres chamis.

Au démarrage, le serveur cherche certaines variables globales qui contiennent les éléments nécessaires à la liaison avec Firebase et Heroku. (FBinitializer).


# Modèle de données
Un <ins>Chami</ins> possède un login unique, un age et une adresse mail (faisant le lien avec firebase).<br>
Un <ins>Chami</ins> <strong>créer</strong> plusieurs <ins>Défi</ins>.<br>
Un <ins>Défi</ins> possède un titre, une date d création et un auteur qui est un <ins>Chami</ins>.<br>
Une <ins>Étape</ins> possède un rang et un label.<br>
Une liste d'<ins>Étape</ins> <strong>organise un </strong> <ins>Défi</ins><br>
Un <ins>Média</ins> (photo ou vidéo) est une <in>Étape</ins> possèdant en plus une url.<br>
Un <ins>Indice</ins> est une <in>Étape</ins> possèdant en plus un cout (perte de point).<br>
Une <ins>Question</ins> est une <in>Étape</ins> possèdant en plus une réponse et un point (nombre de point gagner si la <ins>Question</ins> est répondu juste).<br>
Une liste d'<ins>Indice</ins> <strong>Aide</strong> à répondre à une <ins>Question</ins><br>
REMARQUE : Un <ins>Indice</ins> peut être une simple <ins>Étape</ins> mais ne pas être une <strong>aide</strong> pour une <ins>Question</ins><br>
Un <ins>Chami</ins> <strong> participe </strong> à une <ins>Visite</ins>.<br>
Une <ins>Visite</ins> possède un rang, une date de fin, une date de début, une liste de joueurs qui sont des <ins>Chami</ins> ainsi qu'une liste de <ins>Réponses</ins> qu'elle <strong>vérifie</strong>.<br>
Une <ins>Réponse</ins> possède un type, une valeur et il est possible de savoir s'il est validé.<br>
Une <ins>Réponse</ins> <strong>répond</strong> à une <ins>Question</ins><br>
![image](https://github.com/l3-miage-cl-ihm/projet-integrateur-2022-serveur-l3miage-bellatim/blob/master2/src/modelisation/modele.png)
