# Springboot Tutorial

# NOTE: 
Include *.yml in your .gitignore so you don't publicly share your Github developer secret keys

# Use Visual Studio Code AND install some Spring Boot packages for VS. They are very useful!

# Tutorial Used for Github SSO: https://www.youtube.com/watch?v=D2FuRIL95kk

# Tutorial used for DB connection and REST calls: https://www.youtube.com/watch?v=vtPkZShrvXQ

Both these tutorials are fairly new (at most a few months old)

# Dependencies:

* Spring Security (Not necessary for initial SSO but will be for securing URLs in the future)
* Cloud Oauth2
* Cloud Security
* Spring Web Starter
* Spring Security Web (disable csrf token to use POST requests in Postman)


# Architecture and Design:

For basics on how it works currently.
* User Request (HTTP: GET, POST, DELETE, PUT,.. CRUD stuff) is managed by PersonController which talks to:
* Service Layer (PersonService.java) which uses qualifiers and autowiring to figure out which DB to use
* DB layer which the service layer interacts with (can be local, mongoDB, etc. depending on which DB ur using)

# Overview:

* Dependency Injection
* RESTful API calls
* Many useful comments if you read through my files