TRUNCATE TABLE defi CASCADE;
TRUNCATE TABLE chami CASCADE;
TRUNCATE TABLE etape CASCADE;

INSERT INTO Chami (id, login, age, email) VALUES 
('a', 'carobis', 21, null),
('b', 'escribis', 43, null),
('c', 'momo', 38, 'mo@mo.com'),
('d', 'nomoldu', 20, null),
('e', 'python38', 18, null),
('f', 'yes4moldus', 38, null);

INSERT INTO defi (id, categorie, date_de_creation, description, titre, auteur_id) VALUES
(127, 'ENIGME', '2021-03-15 16:03', 
'Pour le présentiel regarder la vidéo avant d''aller sur place.', 
'Le Méchoui et l''Animal', 'a'),
(128, 'ENIGME', '2021-04-03 22:42',
'Possible uniquement en présentiel. ~ 2h30
Télécharge l''application "Graaly" sur ton téléphone portable.
Cette application est indispensable pour jouer.
Le défi consiste à jouer à l''escape game "Street art".',
 'Escape gameStreet art', 'a'),
(145, 'ENIGME', '2021-04-01 15:03', 
'', 'Le vert, je le mange !', 'b');

INSERT INTO etape (type_etape, id, label, rang, url, point, reponse_attendu, cout) VALUES
('mere', 1, 'Rendez vous à l''arrêt de bus "Grenoble hôtel de ville".', 1, '', 0, '', 0),
('mere', 2, 'Passe par "l''orangerie".', 2, '', 0, '', 0),
('indice', 3, 'Vas du coté de Belledonne.', 3, '', 1, '', 0),
('mere', 4, 'Les oranges te donneront de l''énergie.', 4, '', 0, '', 0),
('mere', 5, 'Mais surtout ne dépasse pas les bornes !', 5, '', 0, '', 0),
('mere', 6, 'Fatiguée ? T''as pas fait des bornes. Tu devrais avoir de l''énergie.', 6, '', 0, '', 0),
('mere', 7, 'Reste sur le parking pour te recharger les piles.', 7, '', 0, '', 0),
('indice', 8, 'Grenoble est un ville verte. Donc avec des voitures vertes.', 8, '', 2, '', 0),
('indice', 9, 'A l''angle de l''orangerie, regarde l''autre angle.', 9, '', 2, '', 0),
('mere', 10, 'Cherche celui qui pourrait te donner matière à te protéger pendant l''hiver.', 10, '', 0, '', 0),
('mere', 11, 'Il est là. Il te regarde de haut. Il t''observe.', 11, '', 0, '', 0),
('mere', 12, 'Il bêle. "Je suis ceux que je suis. Et le vert, je le mange !"', 12, '', 0, '', 0),
('question', 13, 'Combien de boucles sous son cou ?', 13, '', 5, '', 0),
('mere', 14, 'La devise officielle des moutons est "Je suis ceux que je suis".', 14, '', 0, '', 0),
('mere', 15, 'Il y a des centaines de moutons à grenoble.', 15, '', 0, '', 0),
('mere', 16, 'Il y en a aussi dans de nombreuses villes autour du monde !', 16, '', 0, '', 0),
('mere', 17, 'Sheepest dit :\', 17, '', 0, '', 0),
('mere', 18, '"A Paris ou à New-York, je recherche les coins reculés de la ville".', 18, '', 0, '', 0),
('mere', 19, '"Les endroits qui sont subtils, qui ont du sens et qui durent dans le temps".', 19, '', 0, '', 0),
('mere', 20, 'Oublie le mouton. Retourne toi.', 20, '', 0, '', 0),
('mere', 21, 'Derrière toi tu vois la première tour en béton du monde ! (1924)', 21, '', 0, '', 0),
('mere', 22, 'Tour construite pour une exposition internationale [1].', 22, '', 0, '', 0),
('media', 23, 'Affiche de l''exposition de la houille blanche en 1924', 23, 'defis/D145-I1.png', 0, '', 0),
('mere', 24 , 'Vas du coté de l''oiseau tout plat.',  1 , '',  0 , '',  0 ),
('mere', 25 , 'Mais cherche aussi sur tes pas le renard tout plat.',  2 , '',  0 , '',  0 ),
('question', 26 , 'Qu''a le renard entre les deux yeux ?',  3 , '',  3 , '',  0 ),
('mere', 27 , 'Prend la direction de la caserne.',  4 , '',  0 , '',  0 ),
('mere', 28 , 'Trouve un troupeau de moutons et tourne à droite.',  5 , '',  0 , '',  0 ),
('question', 29 , 'Qu''y a t il à coté du troupeau de mouton ?',  6 , '',  2 , '',  0 ),
('mere', 30 , 'Ouvre l''application Graaly et choisi l''escape game "Street art" - bon jeu.',  7 , '',  0 , '',  0 ),
('question', 31 , 'Quel outil a-t-il été emprunté à un artiste par un autre artiste ?',  8 , '',  40 , '',  0 );

INSERT INTO defi_list_etape (defi_id, list_etape_id) VALUES 
(145, 1),
(145, 2),
(145, 3),
(145, 4),
(145, 5),
(145, 6),
(145, 7),
(145, 8),
(145, 9),
(145, 10),
(145, 11),
(145, 12),
(145, 13),
(145, 14),
(145, 15),
(145, 16),
(145, 17),
(145, 18),
(145, 19),
(145, 20),
(145, 21),
(145, 22),
(145, 23),
(128, 24),
(128, 25),
(128, 26),
(128, 27),
(128, 28),
(128, 29),
(128, 30),
(128, 31);