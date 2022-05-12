Groupe 5

# Doc

## Peuplement de la base de données

Un script sql est à disposition dans /src/main/resources/population.sql
Pour peupler votre base, entrez
`sudo psql <UTILISATEUR> -d <BASE_DE_DONNÉES> -f src/main/resources/population.sql`

/!\ Si l'utilisateur de la base PostGreSQL a un nom différent de votre utilisateur UNIX, vous allez obtenir un message du type
```
psql: erreur : la connexion au serveur sur le socket « /var/run/postgresql/.s.PGSQL.5432 » a échoué : FATAL:  authentification peer échouée pour l'utilisateur « <UTILISATEUR> »
```

Pour y remédier, éditez en mode administrateur le fichier `/etc/postgresql/14/main/pg_hba.conf`.
À la ligne
```
# "local" is for Unix domain socket connections only
local   all             all                                     peer
```
remplacez `peer` par `password` pour activer l'identification par mot de passe.

Puis redémarrez le service postgresql `service postgresql restart`

___________
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7711717&assignment_repo_type=AssignmentRepo)
# l3m-PI-serveur : Partie serveur du Projet Intégrateur L3 MIAGE 2020-2021

A stub which can easily be deployed to Heroku.

This application supports the [Getting Started with Java on Heroku](https://devcenter.heroku.com/articles/getting-started-with-java) article - check it out.

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Running Locally

Make sure you have Java and Maven installed.  Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ mvn clean install
$ heroku local:start
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

If you're going to use a database, ensure you have a local `.env` file that reads something like this:

```
JDBC_DATABASE_URL=jdbc:postgresql://HOST:PORT/DATABASE?sslmode=require&user=USER&password=PASSWORD
```

## Deploying to Heroku

Configure Heroku Deploying mode to GitHub so that you can automatically deploy on Heroku when pushing on GitHub.

## Documentation

For more information about using Java on Heroku, see these Dev Center articles:

- [Java on Heroku](https://devcenter.heroku.com/categories/java)

# Modèle de données
Un Chami possède un login unique, un age et une adresse mail (faisant le lien avec firebase).
Un Chami créer plusieurs Défis.
Un <ins>Défi</ins> possède un titre, une date d création et un auteur qui est un Chami
