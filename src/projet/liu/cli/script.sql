INSERT INTO Bibliotheque (idBibliotheque, emplacement)
VALUES
(1, 'Bibliothèque Nationale, Nouakchott'),
(2, 'Bibliothèque Régionale, Nouadhibou'),
(3, 'Bibliothèque Municipale, Atar'),
(4, 'Bibliothèque Communautaire, Zouerate'),
(5, 'Bibliothèque Universitaire, Nouakchott'),
(6, 'Bibliothèque Scolaire, Kaédi'),
(7, 'Bibliothèque de Quartier, Rosso'),
(8, 'Bibliothèque Mobile, Adrar'),
(9, 'Bibliothèque Culturelle, Néma'),
(10, 'Bibliothèque Médiathèque, Kiffa');
INSERT INTO Auteur (idAuteur, nom, adresse, region)
VALUES
(1, "Mohamed Ahmed", "Avenue des Palmiers, Nouakchott", 'Mauritanie'),
(2, "Fatimata Fall", "Rue des Pêcheurs, Nouadhibou", 'Mauritanie'),
(3, 'Ismaila Sow', 'Rue du Savoir, Atar', 'Mauritanie'),
(4, 'Aicha Ould', 'Avenue de la Connaissance, Zouerate', 'Mauritanie'),
(5, 'Omar Kane', 'Boulevard Universitaire, Nouakchott', 'Mauritanie'),
(6, 'Hawa Camara', "Rue des Écoles, Kaédi", 'Mauritanie'),
(7, 'Baba Ahmed', 'Avenue de la Culture, Rosso', 'Mauritanie'),
(8, 'Mariem Cheikh', 'Route Mobile, Adrar', 'Mauritanie'),
(9, 'Brahim Ould', 'Avenue des Arts, Néma', 'Mauritanie'),
(10, 'Aminetou Ould', 'Rue de la Médiathèque, Kiffa', 'Mauritanie');
INSERT INTO Livre (ISBN, titre, idAuteur)
VALUES
(123456, "Chroniques du Désert", 1),
(789012, "Légendes de l'Océan", 2),
(345678, "Poésie du Sahara", 3),
(901234, "Histoires de Zouerate", 4),
(567890, "Université et Savoir", 5),
(234567, "Contes de Kaédi", 6),
(890123, "Rosso à travers les Âges", 7),
(456789, "Adrar et sa Mobile", 8),
(123789, "L'Art de Néma", 9),
(987654, "Médiathèque de Kiffa", 10);
INSERT INTO Telephone (numero, type, idAuteur)
VALUES
(111111, 'Mobile', 1),
(222222, 'Fixe', 2),
(333333, 'Mobile', 3),
(444444, 'Fixe', 4),
(555555, 'Mobile', 5),
(666666, 'Fixe', 6),
(777777, 'Mobile', 7),
(888888, 'Fixe', 8),
(999999, 'Mobile', 9),
(101010, 'Fixe', 10);
INSERT INTO associer (idBibliotheque, idAuteur)
VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 4),
(5, 5),
(6, 5),
(7, 5),
(8, 1),
(9, 2),
(10, 5);