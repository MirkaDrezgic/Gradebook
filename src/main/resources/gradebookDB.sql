CREATE DATABASE db_gradebook;
/*napravili smo novu bazu koja se zove db_gradebook */

CREATE USER 'gradeuser'@'localhost' IDENTIFIED BY 'gradebookPass';
/*kreirali smo novog korisnika za Spring aplikaciju
zove se gbuser a ima lozinku 'gradebookPass'*/

GRANT ALL ON db_gradebook.* TO 'gradeuser'@'localhost';
/*dodelili smo sva prava korisniku nad ovom bazom podataka*/