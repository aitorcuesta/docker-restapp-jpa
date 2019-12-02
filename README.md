#Introduction
This guide walks you though Docker integration with a maven driven development and deployment with Spring Boot.

The goal for this guide is to create a simple web application, we are going to use Spring Boot for this, that connects to a database. Everything will be driven with maven.

##The web application

Our application is an easy Spring Boot REST and JPA application for a bookstore.
The repository management is delegated to JpaRepository and there is a simple RestController that exposes four services:
- /hello. Just says Hello. It's used for health checks.
- /all. Prints all the stored books. 
- /create. Post service for creating books, receives book information using JSON way. A simple curl example for creating Joyce's Ulysses would be: curl -v -X POST http://host_name:port_number/app_context_root/books/create -H "Content-type:application/json" -d "{\"name\":\"Ulysses\"}"
- /get/{id}. Prints the selected book.

##Docker images
All the Docker images are managed with maven using fabric8 docker-maven-plugin plugin. We are using this images:

- mysql:8.0.18. Database for storing data.
- flyway/flyway:6.0.8. For database migration. Creates and populates the schema. We are just creating a simple table using sql/V0_0_1__create_tables.sql database script.
- jboss/wildfly:18.0.1.Final. Is the application server that for running our application.

##Maven commands
For building and running this example you just need to run this commands:

- mvn clean install docker:build. This will build a new restapp-jpa docker image, you can check if is installed with docker image ls
- mvn docker:start. This starts all the containers. You can check if everything is OK via docker ps. You should see mysql and restapp-jpa containers running
- mvn docker:stop. This stops the started containers 

##Maven profiles
There are two profiles

- container. This is the default profile. All the containers comunicates via docker link.
- deployment. This is a special profile. If you want to use this profile, please check your IP address and replace the current one that is under the db.ip.host property with yours. Using this profile the restapp-jpa application doesn't add any link with mysql container, the communication is done in the host and port common way.  