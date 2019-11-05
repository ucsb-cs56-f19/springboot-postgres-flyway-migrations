CREATE TABLE person (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL
    /*VARCHAR(SIZE) defines character limit */
);

/*
terminal: docker exec -it container-id bin/bash accesses your db
Now you can run commands on your db via terminal

psql -U postgres --> logs you in with your credentials from application.yml

\l shows your databases

CREATE DATABASE databasename; //(can't have dashes) to create db

\c databasename connects you to demodb w/ user credentials from yml file

\d --> lists all relations

\dt --> lists table relations

After your start your application, you'll notice that you will have
*/

/*V1__Create_person_table.sql
You must specificy the version followed by __, otherwise it'll detect 0 schemas
*/
