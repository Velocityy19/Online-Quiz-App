#  Online Quiz WebApp

A full-stack **Spring Boot + Thymeleaf + PostgreSQL** web application for managing online quizzes.  
Built with Java 21, Spring Boot 3, and Maven.

---

##  Features
-  **Admin Dashboard** – Create, update, delete quizzes and questions
-  **Student Dashboard** – Attempt quizzes and view scores
-  Custom authentication and role-based access (Admin, Instructor, Student)
-  Real-time data with PostgreSQL hosted on Render
-  Deployed via Docker on Hostinger (or Render)

---

##  Tech Stack
| Layer | Technology |
|--------|-------------|
| Backend | Spring Boot 3, Java 21 |
| Frontend | Thymeleaf, Bootstrap 5 |
| Database | PostgreSQL (Render) |
| Build Tool | Maven |
| Deployment | Docker + Hostinger / Render |

-----

## ACCESS
## Default Admin Credentials

| Role | Email | Password |
|------|--------|-----------|
| Admin | admin@quizapp.com | admin123 |

> Participants have to register using `/register`

------

## Configuration
### Database (Render PostgreSQL)
Update the following in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://dpg-d46auseuk2gs73d22tv0-a.oregon-postgres.render.com/mydb_1nda
spring.datasource.username=mydb_1nda_user
spring.datasource.password=40Q5iXB9TvIRKVblKKo1WoeiweFGqKn4
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
