
-- Insertion des données de test pour les auteurs (plus complets maintenant)
INSERT INTO auteur (nom) VALUES
('Victor Hugo'),
('George Orwell'),
('J.K. Rowling'),
('George R.R. Martin'),
('J.R.R. Tolkien'),
('Agatha Christie'),
('Stephen King'),
('Haruki Murakami'),
('Albert Camus'),
('Jane Austen');

-- Insertion des données de test pour les types de livre
INSERT INTO typelivre (libelle) VALUES
('Roman'),
('Science-Fiction'),
('Fantasy'),
('Policier'),
('BD'),
('Biographie'),
('Horreur'),
('Romance'),
('Nouvelle'),
('Théâtre');

-- Insertion des données de test pour les catégories
INSERT INTO categorie (nom) VALUES
('Classique'),
('Jeunesse'),
('Adulte'),
('Historique'),
('Dystopie'),
('Médiéval'),
('Enquête'),
('Magie'),
('Horreur'),
('Psychologique'),
('Société'),
('Aventure');

-- Insertion des données de test pour les livres (plus nombreux maintenant)
INSERT INTO livre (titre, description, nom, auteur_id, typelivre_id, date_edition, isbn, tag) VALUES
('Les Misérables', 'Histoire de Jean Valjean et Cosette dans la France du XIXe siècle', 'Les Misérables', 1, 1, '1862-01-01', '978-2070409228', 'classique'),
('1984', 'Une dystopie sur un régime totalitaire', '1984', 2, 2, '1949-06-08', '978-2070368228', 'dystopie'),
('Harry Potter à l''école des sorciers', 'Premier tome des aventures du jeune sorcier', 'Harry Potter 1', 3, 3, '1997-06-26', '978-2070518425', 'magie'),
('Le Trône de Fer', 'Premier tome de la saga du Trône de Fer', 'AGOT', 4, 3, '1996-08-01', '978-2253150941', 'médiéval'),
('Le Seigneur des Anneaux', 'La communauté de l''anneau', 'LOTR 1', 5, 3, '1954-07-29', '978-2266282362', 'fantasy'),
('Le Crime de l''Orient-Express',
 (*ù'Une célèbre enquête d''Hercule Poirot', 'Orient-Express', 6, 4, '1934-01-01', '978-2253010696', 'enquête'),


-- Suppression des données existantes (optionnel)
TRUNCATE TABLE adherent RESTART IDENTITY CASCADE;

INSERT INTO type_adherent (cota, cotisation, libelle) VALUES
(15.00, 0.00, 'Adhérent standard'),
(5.00, 0.00, 'Adhérent étudiant'),
(30.00, 50.00, 'Adhérent bienfaiteur'),
(0.00, 100.00, 'Membre bienfaiteur premium'),
(10.00, 0.00, 'Adhérent senior');

-- Insertion des données de test (sans caractères spéciaux)
INSERT INTO adherent (email, fin_adhesion, nom, prenom, id_type_adherent) VALUES
('jean.dupont@email.com', '2024-12-31', 'Dupont', 'Jean', 1),
('marie.durand@email.com', '2024-06-30', 'Durand', 'Marie', 1),
('pierre.martin@email.com', '2023-12-31', 'Martin', 'Pierre', 2),
('sophie.leroy@email.com', '2024-09-15', 'Leroy', 'Sophie', 1),
('luc.berger@email.com', '2025-01-31', 'Berger', 'Luc', 3),
('anne.morel@email.com', NULL, 'Morel', 'Anne', 2),
('thomas.lefevre@email.com', '2023-11-30', 'Lefevre', 'Thomas', 1),
('isabelle.girard@email.com', '2024-08-20', 'Girard', 'Isabelle', 1),
('francois.simon@email.com', '2024-07-01', 'Simon', 'Francois', 2),
('valerie.roux@email.com', '2025-03-15', 'Roux', 'Valerie', 3),
('alain.dumont@email.com', '2024-10-10', 'Dumont', 'Alain', 1),
('elodie.mercier@email.com', NULL, 'Mercier', 'Elodie', 2),
('remi.blanc@email.com', '2024-05-31', 'Blanc', 'Remi', 1),
('caroline.leroux@email.com', '2024-11-30', 'Leroux', 'Caroline', 3),
('guillaume.henry@email.com', '2025-02-28', 'Henry', 'Guillaume', 1);

-- On suppose que les livres avec id 1, 2 et 3 existent déjà dans la table `livre`.

INSERT INTO exemplaire (numero, livre_id) VALUES
('1', 7),
('2', 12),
('3', 11),
('4', 8),
('5', 9),
('6', 10);

INSERT INTO reservation (date_demande, date_pret, id_adherent, id_exemplaire) VALUES
('2025-07-01', '2025-07-02', 16, 7),
('2025-07-02', '2025-07-03', 20, 8);

INSERT INTO penalite (date_fin, adherent_id) VALUES
('2025-07-15',  16),
('2025-07-20',  20);




