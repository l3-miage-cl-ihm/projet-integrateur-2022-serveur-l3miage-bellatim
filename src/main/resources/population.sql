TRUNCATE TABLE defis CASCADE;
TRUNCATE TABLE chamis CASCADE;

INSERT INTO Chamis (login, age, email) VALUES 
('carobis', 21, null),
('escribis', 43, null),
('momo', 38, 'mo@mo.com'),
('nomoldu', 20, null),
('python38', 18, null),
('yes4moldus', 38, null);

INSERT INTO Defis (id, date_de_creation, description, titre, auteur_login) VALUES
('D127', '2021-03-15 16:03:00', 
'- Rendez vous à l''arrêt de bus "Maison du tourisme - Hubert Dubedout".
- Cherche ce qu''il faut pour faire un méchoui.', 'Le Méchoui', 'carobis'),
('D145', '2021-04-01 15:03:00', 
'- Rendez vous à l''arrêt de bus "Grenoble - hôtel de ville".', 'Le vert, je le mange !', 'escribis'),
('D151', '2021-04-13 12:03:00', 
'- Rendez-vous à l''arrêt Victor Hugo', 'Ils tournent et rond.', 'nomoldu'),
('D189', '2021-03-17 12:03:00', 
'- Rendez-vous à l''arrêt Victor-Hugo
- C''est l''été. Tu as besoin de grand air.
- Demande à quelqu''un où est la maison de la montagne.
- Longe le tram pour y aller. Tu demanderas où on peut voir des chamois.', 'Et l''écureil alors ?', 'carobis');
