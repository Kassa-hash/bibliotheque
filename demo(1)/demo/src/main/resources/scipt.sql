
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
('Le Crime de l''Orient-Express', 'Une célèbre enquête d''Hercule Poirot', 'Orient-Express', 6, 4, '1934-01-01', '978-2253010696', 'enquête'),