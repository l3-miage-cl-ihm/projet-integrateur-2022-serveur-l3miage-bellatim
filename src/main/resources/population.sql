TRUNCATE TABLE defi CASCADE;
TRUNCATE TABLE chami CASCADE;
TRUNCATE TABLE etape CASCADE;

INSERT INTO Chami (id, login, age, email) VALUES 
('a', 'carobis', 21, null),
('b', 'escribis', 43, null),
('c', 'momo', 38, 'mo@mo.com'),
('d', 'nomoldu', 20, null),
('e', 'python38', 18, null),
('f', 'yes4moldus', 38, null),
('g', 'groupe5', 22, 'pi2022g5@gmail.com');

INSERT INTO defi (id, categorie, date_de_creation, description, titre, auteur_id) VALUES
(127, 'ENIGME', '2021-03-15 16:03', 
'Pour le présentiel regarder la vidéo avant d''aller sur place.', 
'Le Méchoui et l''Animal', 'a'),
(128, 'ENIGME', '2021-04-03 22:42',
'Possible uniquement en présentiel. ~ 2h30
Télécharge l''application "Graaly" sur ton téléphone portable.
Cette application est indispensable pour jouer.
Le défi consiste à jouer à l''escape game "Street art".',
 'Escape game Street art', 'a'),
(145, 'ENIGME', '2021-04-01 15:03', 
'', 'Le vert, je le mange !', 'b'),
(021, 'SPORTIF', '2021-05-10 12:09', 
'La Bastille est un fort militaire surplombant de 264 mètres la ville de Grenoble. Édifié durant la première partie du XIX e siècle et culminant à 476 mètres d''altitude sur les derniers contreforts du massif de la Chartreuse.', 'Prise de la Bastille', 'g');

INSERT INTO etape (type_etape, id, label, rang, url, point, reponse_attendu, cout) VALUES
('mere', 1, 'Rendez vous à l''arrêt de bus "Grenoble hôtel de ville".', 1, '', 0, '', 0),
('mere', 2, 'Passe par "l''orangerie".', 2, '', 0, '', 0),
('indice', 3, 'Vas du coté de Belledonne.', 3, '', 0, '', 1),
('mere', 4, 'Les oranges te donneront de l''énergie.', 4, '', 0, '', 0),
('mere', 5, 'Mais surtout ne dépasse pas les bornes !', 5, '', 0, '', 0),
('mere', 6, 'Fatiguée ? T''as pas fait des bornes. Tu devrais avoir de l''énergie.', 6, '', 0, '', 0),
('mere', 7, 'Reste sur le parking pour te recharger les piles.', 7, '', 0, '', 0),
('indice', 8, 'Grenoble est un ville verte. Donc avec des voitures vertes.', 8, '', 0, '', 2),
('indice', 9, 'A l''angle de l''orangerie, regarde l''autre angle.', 9, '', 0, '', 2),
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
('question', 31 , 'Quel outil a-t-il été emprunté à un artiste par un autre artiste ?',  8 , '',  40 , '',  0 ),
('mere', 32, 'Un historien a perdu une page de son livre d’histoire regroupant des informations à propos du fort de la Bastille de Grenoble, aide-le à rassembler les informations qui lui manque.', 32, '', 0, '', 0),
('mere', 33, 'Part à la découverte d’un des plus beaux points de vue de Grenoble !', 33, '', 0, '', 0),
('mere', 34, 'Rendez-vous à l’arrêt de bus « Saint-Laurent ».', 34, '', 0, '', 0),
('question', 35, 'En partant de la porte Saint-Laurent, compte le nombre de marches jusqu’au point de vue se trouvant tout en haut du fort.\nArrêtes-toi quand tu arrives au restaurant O2.', 35, '', 20, '1244', 0),
('indice', 36, 'N’utilises pas tes doigts pour compter.', 36, '', 0, '', 5),
('mere', 37, 'Bravo, graine de sportif ! Tu as atteint ton premier objectif !', 37, '', 0, '', 0),
('mere', 38, 'Prends-toi en selfie sur le point de vue encore en hauteur le plus proche de toi, surplombant Grenoble.', 38, '', 15, '', 0),
('indice', 39, 'C’est une plateforme en bois en face de l’acrobastille.', 39, '', 0, '', 5),
('mere', 40, 'Super, ça te fera un beau souvenir !', 40, '', 0, '', 0),
('question', 41, 'Quel est le nom du restaurant gastronomique se situant sur les
hauteurs de la Bastille ?', 41, '', 10, 'Per’Gras', 0),
('indice', 42, 'Tu trouveras l’information sur les brochures ou sur les panneaux.', 42, '', 0, '', 2),
('question', 43, 'Quel est le sujet du tableau se situant le plus à gauche du point de
vue où tu te trouves ?', 43, '', 10, 'Les jeux Olympiques d’hiver de 1968', 0),
('indice', 44, 'Le panneau est tout à gauche du point de vue en étant face à
Grenoble. Le sujet est en lien avec la catégorie du défi.', 44, '', 0, '', 2),
('mere', 45, 'Très bien ! Maintenant il faut redescendre.', 45, '', 0, '', 0),
('question', 46, 'Combien de temps met la descente en bulle ?', 46, '', 5, '4 minutes', 0),
('indice', 47, 'Sois pas radin, paye ta bulle.', 47, '', 0, '', 1),
('indice', 48, 'Tu captes pas la 4G en haut de la Bastille ?', 48, '', 0, '', 1),
('mere', 49, 'Bien arrivé en bas ? Cool, encore une dernière petite question et c’est terminé !', 49, '', 0, '', 0),
('question', 50, 'Quelle est la couleur du bâtiment dans lequel tu arrives en descendant
des bulles ?', 50, '', 5, 'Rouge et gris', 0),
('mere', 51, 'Félicitations, tu as récupéré toutes les informations nécessaires à l’historien. Grace à
toi il n’y aura plus de vide dans les livres d’histoire !', 51, '', 0, '', 0),
('mere', 52, 'Si tu veux continuer à te renseigner sur la Bastille, tu peux retourner y faire un tour
par toi-même, tu verras c’est super sympa.', 52, '', 0, '', 0);

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
(128, 31),
(021, 32),
(021, 33),
(021, 34),
(021, 35),
(021, 36),
(021, 37),
(021, 38),
(021, 39),
(021, 40),
(021, 41),
(021, 42),
(021, 43),
(021, 44),
(021, 45),
(021, 46),
(021, 47),
(021, 48),
(021, 49),
(021, 50),
(021, 51),
(021, 52);
