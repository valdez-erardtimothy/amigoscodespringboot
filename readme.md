# AmigosCode "*Getting Started With Spring Boot*" course

codes as I follow through the course.

DONE 2022-06-28 12pm

## Deviations from the tutorial

changes are listed on a per-chapter basis

### Spring Initializr

- used `MariaDB` instead of `PostgreSQL` Driver

I have MariaDB on my local dev environment already set up.

- Gave the `amigoscodespringboot` name instead of demo, group changed from `com.example` to `valdezet`

### Open Project with IntelliJ 

- following through with *Community Edition* instead of *Ultimate Edition*

### Starting the Server

- instead of the IntelliJ IDEA Ultimate Edition Spring Boot Application run feature, I made a maven configuration to run `spring-boot:run` via `mvnw`

### Properties file 

- adjusted JPA properties for MariaDB

### Creating and Connecting to Database

- used `amigoscodespringboot` as database name
- adjusted to use MariaDB

### Writing Some Business Logic

- unique email validation on `StudentService.addNewStudent` extracted to a new method in class

### Deleting Students

- used String.format on StudentService.deleteStudent exception throw

### Exercise and Exercise Solution

- validation methods of update fields are in separate method (still in the same class)