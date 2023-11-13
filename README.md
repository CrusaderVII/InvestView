This is my pet-project for checking and analyzing stocks qoutes of issuers, that are presented on MOEX.

There is **Market microservice**, that is responsible for getting data using MOEX API about issuers, that
represented in MOEX list, and sending this data to client by using REST API.

Besides, there is a **User microservice**, that is responsible for CRUD operations 
on "user" entities, such as saving new user to database, delete him, add to user "favorite"
issuer, delete it and so on. For this I'm using hibernate and PostgreSQL.

This project also includes **Discovery service** managed by eureka. It used for 
**Docker** containers _discovery_ and _load-balancing_. 