# bank-account-manager
Project using Java Spring Boot with Hibernate and JPA as a back-end and Angular as a front-end and uses the best practices to the best of my abilities.
This project is a work in progress, as of now I've only worked on the back-end and the dockerisation of the tech stack.

In order to start the project, you must create an ```.env``` file with the following variables:
```DB_NAME= 
DB_USER=
DB_PASSWORD=
DB_PORT=
API_URL= //must correspond to the back-end container name + the port used, so backend:8080 by default
```

Also you should also change the parameters inside the application.properties, application-dev.properties and application-docker.properties to reflect the right values.

One it's done you can start the application with a ```docker compose up -d``` command inside the root folder.
Note that you can also start the database and front-end from the docker and use the ```mvn spring-boot-run``` inside the back-end folder to run the app outside of Docker.

The app allow users to create accounts to make banking transactions inside or outside the bank itself, allow people to share the account (in the case of a family for example). 
There is also a flag and logging system to monitor the users activities and flag the more suspicious ones so that the admins can check the activities in greater details.

The app also allow an admin type of account which have a greater overview of the differents account and the metrics of the bank itself. That type of account can therefore check the accounts data, actions and transactions, who logged in at what time, who initiated this and that action, and allow manual flagging of suspicious activity, and if needed the ability to suspend or close accounts and users accesses to it.
