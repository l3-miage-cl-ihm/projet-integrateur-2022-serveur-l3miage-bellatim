Groupe 5
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
