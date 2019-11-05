# Springboot Tutorial for PostgresQL connection and Heroku

# NOTE: 
Include *.yml in your .gitignore so you don't publicly share your Github developer secret keys

# There's another tutorial for connecting to postgres container using docker with springboot

# Tutorial used for Postgres connection: https://www.youtube.com/watch?v=vtPkZShrvXQ

# Target -> jar-name.JAR, you can deploy application w/o spring-boot:run

* On local, go to target in terminal
* type in java -jar jar-name.JAR
* Your application has been started via JAR and not spring-boot:run

# Dependencies:

* PostgreSQL
* Docker (not a dependency, but something I use on terminal)
* Flyway (database migrations and schemas)
* Cloud Security
* Spring Web Starter
* Spring Security Web (disable csrf token to use POST requests in Postman)

# Overview:

* Using PostgreSQL
* Using Docker alongside PostgreSQL
* Running application via java -jar jar-name.JAR
* Deployment to Heroku

# Starting Postgres instance:

* On terminal, run: docker run --name some-postgres-name -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine
* docker run --name postgres-spring -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:alpine

* -e is environment variable
* postgres:alpine runs postgres version alpine, the smallest version
* -d is detach mode
* -p takes port 5432 running in docker internally and exposes it outside of docker
* In your application.yml, you define the connection details to your postgres db

# Accessing the Postgres instance
* docker port postgres-container-name to expose the port 5432 to outside access
* docker ps to list all current containers, use this to find your container id
* docker exec -it container-id bin/bash accesses the db so you can run commands on it via terminal
* psql -U postgres logs you in with the "postgres user" credentials from application.yml

# Exiting out of DB
* \q to quit, but you'll be inside /bash so recommended you just have two terminals up

Note that you need docker running on your desktop before you can run docker commands

# Other Docker Commands
* docker kill $(docker ps -q) # stop all containers
* docker rm $(docker ps -a -q) # remove all containers 
* docker rmi $(docker images -q) # remove all images
* docker network prune # remove all networks
* docker volume prune # remove all volumes 
* docker port container-name # exposes running container port to outside world on localhost:5432

# Flyway Migrations
* Create directory /resources/db/migration
* Create a schema/migration with the format V1__NAME.sql (V1-9, etc.)
* If your file name format is wrong, you'll see "successfully validated 0 migrations" in console
* run mvn flyway:migrate
* Now in your postgres if you do psql -U postgres(your username), \c dbname, you should see new relations under \dt
* Note that  flyway relation will always exist there as default
* Everytime you want to do additional migration, create additional files and run mvn flyway:migrate

# PostgreSQL additional information
* SELECT uuid_generate_v1(); generates seeded UUID based on timestamp, etc.
* SELECT uuid_generate_v4(); generates random UUID
* In order to use these, you must do: CREATE EXTENSION "uuid-ossp"; to access the functions
* Then do SELECT uuid_generate_v4();
* INSERT INTO person (id, name, email) VALUES (uuid_generate_v4(), 'Stub Name', 'stub@ucsb.edu'); to insert into DB

# More PostgreSQL DB stuff:
* \l shows your databases
* CREATE DATABASE databasename; //(can't have dashes) to create db. Make this the same name as the jdbc-url ending name in .yml file 
* \c databasename connects you to demodb w/ user credentials from yml file
* \d tablename --> lists all of the table's attributes
* \dt --> lists table relations
* After your start your application, you'll notice that you will have populated tables/relations
* Format for migration file: V1__Create_person_table.sql; You must specificy the version followed by __, otherwise it'll detect 0 schemas

# IMPORTANT NOTE:
* Don't edit your SQL scripts while DB is running and re-run, it'll screw up the checksum and everything
* Add a new SQL script and call migration