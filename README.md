# Springboot Tutorial for PostgresQL connection and Heroku

# NOTE: 
Include *.yml in your .gitignore so you don't publicly share your Github developer secret keys

# There's another tutorial for connecting to postgres container using docker with springboot

# Tutorial used for Postgres connection: https://www.youtube.com/watch?v=vtPkZShrvXQ

# Target -> jar-name.JAR, you can deploy this on a server

* On local, go to target in terminal
* type in java -jar jar-name.JAR
* Your server has been started via JAR and not spring-boot:run

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

* -e is environment variable
* postgres:alpine runs postgres version alpine, the smallest version
* -d is detach mode
* -p takes port 5432 running in docker internally and exposes it outside of docker
* In your application.yml, you define the connection details to your postgres db

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
* Now in your postgres if you do psql -U postgres, \c dbname, you should see new relations under \dt
* Note that  flyway relation will always exist there as default
* Everytime you want to do additional migration, create additional files and run mvn flyway:migrate