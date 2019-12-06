# Phonebook API with Spring Boot, Mysql, JPA and Hibernate 

## Setup & Running

**1. Clone the application**

```bash
https://github.com/pbxed/phonebook_api.git
```

**2. Create Mysql database**
```bash
create database user_database
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/spring-boot-rest-api-tutorial-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

Hibernate will auto create tables to correspond with classes that are marked as @Entity.

## Endpoints

The app defines the following endpoints

    POST /api/auth/signup
    POST /api/auth/signin
    
    Send a valid JSON in the following format
    {
        "name": "username",
        "username": "username",
        "email": "username@abc.com",
        "password" : "password"
    }
    
    Response will indicate whether user has been sucessfully created or not. Assuming user has been created then send
    JSON in following format to /api/auth/signin
    {
        "username": "username",
        "password": "password"
    }
    
    Response will be a JWT bearer token that must be included in the authorization header to access all secured
    endpoints listed below

    GET /api/persons 
    
    Returns list of all persons in the phonebook
    
    GET /api/persons/{personId}
    
    Returns person by id.
    
    GET /api/persons/phone-book/{phoneNumber}
    
    Returns list of all persons with partial phonenumber match
    
    POST /api/users
    
    Creates a new person in the phonebook/database.
    
    PUT /api/person/{personId}
    
    Updates a person's credentials in the phonebook/database
    
    DELETE /api/persons/{personId}
    
    Deletes a person from the phonebook/database
    
    


