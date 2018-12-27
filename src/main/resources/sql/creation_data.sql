INSERT INTO `role` VALUES (1,'ADMIN');
INSERT INTO `role` VALUES (2,'GESTIONNAIRE');
INSERT INTO `role` VALUES (3,'EMPLOYE');


INSERT INTO `sexe` (`sexe_id`, `sexe`) VALUES ('1', 'Homme');
INSERT INTO `sexe` (`sexe_id`, `sexe`) VALUES ('2', 'Femme');


INSERT INTO `niveau` (`niveau_id`, `intitule`) VALUES ('1', 'DEBUTANT');
INSERT INTO `niveau` (`niveau_id`, `intitule`) VALUES ('2', 'CONFIRME');
INSERT INTO `niveau` (`niveau_id`, `intitule`) VALUES ('3', 'EXPERT');

INSERT INTO `typecontrat` (`type_id`, `type`) VALUES ('1', 'CDD');
INSERT INTO `typecontrat` (`type_id`, `type`) VALUES ('2', 'CDI');

INSERT INTO `statut` (`statut_id`, `statut`) VALUES ('1', 'EN COURS');
INSERT INTO `statut` (`statut_id`, `statut`) VALUES ('2', 'VALIDE');
INSERT INTO `statut` (`statut_id`, `statut`) VALUES ('3', 'ANNULE');

INSERT INTO `typeconge` (`type_id`, `type`) VALUES ('1', 'RTT');
INSERT INTO `typeconge` (`type_id`, `type`) VALUES ('2', 'Conge payé');
INSERT INTO `typeconge` (`type_id`, `type`) VALUES ('3', 'Conge non payé');


--pwd=$2a$10$bmwUa9yKyM4QPCLhAmzJ7OA/R.JlRJinxWANIzgJpW1Q5o109IuGG
--kilifa=$2a$10$pfSFCZRZ2GXaXC3dDfkxreFO612WshBw//exudYrl4GI2ASZ073rK