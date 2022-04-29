TRUNCATE TABLE defis CASCADE;
TRUNCATE TABLE chamis CASCADE;

INSERT INTO Chamis (login, age, email) VALUES 
('carobis', 21, null),
('escribis', 43, null),
('momo', 38, 'mo@mo.com'),
('nomoldu', 20, null),
('python38', 18, null),
('yes4moldus', 38, null);

INSERT INTO Defis (id, date_de_creation, description, titre, auteur) VALUES
('D127', '2021-03-15 16:03:00', '- Rendez vous à l''arrêt de bus "Maison du tourisme - Hubert Dubedout".\n- Cherche ce qu''il faut pour faire un méchoui.', 'Le Méchoui', 'carobis'),
('D145', '2021-04-01 15:03:00', '- Rendez vous à l''arrêt de bus "Grenoble - hôtel de ville".\n
- Passe par "l''orangerie".\n
- Ca te donneras de l''énergie mais surtout ne dépasse pas les bornes !\n
- Fatiguée ? T''as pas fait des bornes. Tu devrais avoir de l''énergie.\n
- Reste sur le parking pour te recharger les piles.\n
- Cherche plutôt celui qui pourrait te donner matière à te protéger pendant l''hiver.\n
- Il est là. Il te regarde de haut. Il t''observe.\n
- Il bêle. "Je suis ceux que je suis. Et le vert, je le mange !"\n
- Combien de boucles sous mon cou ?', 'Le vert, je le mange !', 'escribis'),
('D151', '2021-04-13 12:03:00', 
'- Rendez-vous à l''arrêt Victor Hugo\n
- Il tourne en rond pour le plaisir des petits. Cherche le.\n
- Si il n''y est pas demande à quelqu''un où il est quand il y est :-)\n
- Fait toi un selfie avec les 2 moutons en utilisant ton sens de la créativité.\n
- Si tu n''y arrives pas demande à quelqu''un qu''il/elle te tire un portrait.', 'Ils tournent et rond.', 'nomoldu'),
('D189', '2021-03-17 12:03:00', 
'- Rendez-vous à l''arrêt Victor-Hugo\n
- C''est l''été. Tu as besoin de grand air.\n
- Demande à quelqu''un où est la maison de la montagne.\n
- Longe le tram pour y aller. Tu demanderas où on peut voir des chamois.\n
- Vas pas trop vite ! Les chamoix sont en plein centre ville !\n
- Grenoble c''est la ville du béton, mais c''est aussi la capitale des Alpes.\n
- Fait attention tu pourrais te faire écraser par des pachidermes !\n
- (Q1) Combien d''écureuils (compter uniquement les tout plats) ?\n
- (Q2) Combien de chamois ?\n
- (Q3) Combien d''éléphants ?', 'Et l''écureil alors ?', 'carobis');
