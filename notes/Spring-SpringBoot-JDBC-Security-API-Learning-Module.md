# Spring + Spring Boot + Spring JDBC + Spring Security — API Learning Module

> **Purpose:** The next step after the Java Quick Recap and Module 01 — HTTP Fundamentals.
>
> **Goal:** Learn what is required to create, understand, debug, extend, persist, and secure our first REST API for the Standalone IAM Platform Lab.
>
> **Java:** 21 LTS
>
> **Approach:** Concept → small example → understand request flow → implement → break/fix → continue.

---

# Table of Contents

1. [Where This Fits](#1-where-this-fits)
2. [The Learning Architecture](#2-the-learning-architecture)
3. [Spring Fundamentals](#3-spring-fundamentals)
4. [Dependency Injection and IoC](#4-dependency-injection-and-ioc)
5. [Spring Beans](#5-spring-beans)
6. [Important Spring Annotations](#6-important-spring-annotations)
7. [Constructor Injection](#7-constructor-injection)
8. [Spring Boot](#8-spring-boot)
9. [Spring Boot Project Structure](#9-spring-boot-project-structure)
10. [Maven and Starters](#10-maven-and-starters)
11. [Configuration](#11-configuration)
12. [Embedded Server](#12-embedded-server)
13. [Spring MVC Request Flow](#13-spring-mvc-request-flow)
14. [REST Controllers](#14-rest-controllers)
15. [Path Variables](#15-path-variables)
16. [Query Parameters](#16-query-parameters)
17. [Request Body and JSON](#17-request-body-and-json)
18. [Response Body and Status Codes](#18-response-body-and-status-codes)
19. [DTOs and Records](#19-dtos-and-records)
20. [Validation](#20-validation)
21. [Controller-Service-Repository Layers](#21-controller-service-repository-layers)
22. [Spring JDBC](#22-spring-jdbc)
23. [JDBC and JdbcTemplate](#23-jdbc-and-jdbctemplate)
24. [Queries and Row Mapping](#24-queries-and-row-mapping)
25. [Insert Update Delete](#25-insert-update-delete)
26. [Transactions](#26-transactions)
27. [JDBC vs JPA](#27-jdbc-vs-jpa)
28. [Exception Handling](#28-exception-handling)
29. [Spring Security](#29-spring-security)
30. [Security Filter Chain](#30-security-filter-chain)
31. [Authentication vs Authorization](#31-authentication-vs-authorization)
32. [SecurityContext](#32-securitycontext)
33. [Securing Endpoints](#33-securing-endpoints)
34. [Password Hashing](#34-password-hashing)
35. [CSRF and CORS](#35-csrf-and-cors)
36. [Sessions vs Bearer Tokens](#36-sessions-vs-bearer-tokens)
37. [JWT, OAuth 2.0 and OIDC](#37-jwt-oauth-20-and-oidc)
38. [Profiles, Secrets and Logging](#38-profiles-secrets-and-logging)
39. [Testing and Debugging](#39-testing-and-debugging)
40. [Our First API](#40-our-first-api)
41. [Break/Fix Exercises](#41-breakfix-exercises)
42. [Next: User API](#42-next-user-api)
43. [How This Becomes IAM](#43-how-this-becomes-iam)
44. [What Not to Learn Yet](#44-what-not-to-learn-yet)
45. [Checkpoint](#45-checkpoint)
46. [Exit Criteria and Next Step](#46-exit-criteria-and-next-step)

---

# 1. Where This Fits

Our learning sequence:

```text
HTTP Fundamentals
       ↓
Java Quick Recap
       ↓
THIS MODULE
       ↓
Spring
Spring Boot
Spring MVC
Spring JDBC
Spring Security
       ↓
First REST API
       ↓
PostgreSQL
       ↓
Authentication
       ↓
Authorization
       ↓
JWT
       ↓
OAuth 2.0
       ↓
OIDC / SSO
       ↓
IAM Platform
```

**Yes: this is the next step from Module 01.**

Module 01 taught HTTP. The Java recap refreshed the language. This module teaches the framework that receives HTTP requests and executes Java code.

---

# 2. The Learning Architecture

First:

```text
Client
  ↓ HTTP GET
Spring Boot
  ↓
Controller
  ↓
Java method
  ↓
JSON response
```

Later:

```text
Client
  ↓
Spring Security
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
JdbcTemplate
  ↓
PostgreSQL
```

Eventually:

```text
API Gateway
    ↓
Auth Service ───┐
User Service ───┼── PostgreSQL
OAuth Service ──┘
    ↓
IdP
```

The architecture is our teacher: every new component is introduced when we have a reason to need it.

---

# 3. Spring Fundamentals

Spring is a Java application framework. A central idea is that the framework can manage application components and their dependencies.

Without Spring:

```java
UserRepository repository = new UserRepository();
UserService service = new UserService(repository);
```

With Spring:

```text
Spring Container
   ↓
creates UserRepository
   ↓
creates UserService
   ↓
injects repository
```

Spring provides infrastructure for dependency injection, configuration, web applications, data access, security, testing, and more.

---

# 4. Dependency Injection and IoC

Problem:

```java
class UserService {
    private UserRepository repository = new UserRepository();
}
```

`UserService` controls construction of its dependency.

Better:

```java
class UserService {
    private final UserRepository repository;

    UserService(UserRepository repository) {
        this.repository = repository;
    }
}
```

Now another component supplies the dependency.

```text
Spring
  ↓
UserRepository
  ↓ injected into
UserService
```

**IoC (Inversion of Control)** means control over object creation/lifecycle is moved to the framework.

**DI (Dependency Injection)** is a way of supplying dependencies.

```text
IoC = who controls object creation?
DI  = how are dependencies supplied?
```

---

# 5. Spring Beans

A **bean** is an object managed by the Spring container.

```java
@Service
public class UserService {
}
```

Spring can create and manage a `UserService` bean.

Typical beans:

```text
Controller
Service
Repository
Configuration
Security components
```

The container creates objects, resolves dependencies, and manages their lifecycle.

---

# 6. Important Spring Annotations

| Annotation | Meaning |
|---|---|
| `@Component` | Generic managed component |
| `@Service` | Business/service component |
| `@Repository` | Data-access component |
| `@Controller` | MVC controller |
| `@RestController` | REST controller |
| `@Configuration` | Configuration class |
| `@Bean` | Explicitly registers returned object as a bean |
| `@RequestMapping` | Request mapping |
| `@GetMapping` | GET endpoint |
| `@PostMapping` | POST endpoint |
| `@PutMapping` | PUT endpoint |
| `@PatchMapping` | PATCH endpoint |
| `@DeleteMapping` | DELETE endpoint |

Don't memorize them blindly. Learn what role each plays in the request flow.

---

# 7. Constructor Injection

Preferred style:

```java
@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
```

With one constructor, Spring can inject it without `@Autowired`.

Benefits:

- dependencies are explicit
- fields can be `final`
- easier unit testing
- required dependencies cannot be silently missing

---

# 8. Spring Boot

Spring Boot makes Spring applications easier to create and run.

Important ideas:

```text
Auto-configuration
Starters
Embedded server
Sensible defaults
External configuration
Production tooling
```

Typical entry point:

```java
@SpringBootApplication
public class IamApplication {
    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }
}
```

Conceptually `@SpringBootApplication` combines configuration, component scanning, and auto-configuration.

---

# 9. Spring Boot Project Structure

```text
iam-platform/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/iam/
    │   │   ├── IamApplication.java
    │   │   ├── controller/
    │   │   ├── service/
    │   │   ├── repository/
    │   │   ├── model/
    │   │   ├── dto/
    │   │   └── config/
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/
```

Keep the main application class in a root package above the components so component scanning can discover them.

---

# 10. Maven and Starters

Maven manages dependencies, compilation, testing, packaging, and plugins.

Common commands:

```bash
mvn clean
mvn test
mvn package
mvn spring-boot:run
```

For a REST API, Spring Initializr will normally add Spring Web, represented by a starter such as:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Later we add dependencies for JDBC, PostgreSQL, validation, security, and testing when needed.

---

# 11. Configuration

`src/main/resources/application.properties` can contain configuration:

```properties
server.port=8080
```

Later:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/iam
spring.datasource.username=iam_user
spring.datasource.password=...
```

Do not put real production secrets in source control.

---

# 12. Embedded Server

A Spring Boot web application can include an embedded web server.

So:

```bash
mvn spring-boot:run
```

starts the application and its server.

Conceptually:

```text
Spring Boot
   ├── embedded server
   ├── Spring MVC
   └── your application
```

---

# 13. Spring MVC Request Flow

One of the most important mental models:

```text
HTTP Client
    ↓
Embedded Web Server
    ↓
Spring MVC
    ↓
DispatcherServlet
    ↓
Controller mapping
    ↓
Controller method
    ↓
Java return value
    ↓
JSON serialization
    ↓
HTTP response
```

With security:

```text
HTTP request
    ↓
Security Filter Chain
    ↓
Authentication
    ↓
Authorization
    ↓
Spring MVC
    ↓
Controller
```

Remember these flows instead of memorizing framework internals.

---

# 14. REST Controllers

```java
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
```

Request:

```text
GET /api/hello
```

Response:

```text
Hello
```

`@RestController` marks the class as a REST endpoint component and treats returned values as response bodies.

---

# 15. Path Variables

Request:

```text
GET /api/users/123
```

Code:

```java
@GetMapping("/api/users/{id}")
public String getUser(@PathVariable Long id) {
    return "User " + id;
}
```

Output:

```text
User 123
```

---

# 16. Query Parameters

Request:

```text
GET /api/users?role=ADMIN
```

Code:

```java
@GetMapping("/api/users")
public String getUsers(@RequestParam String role) {
    return "Role: " + role;
}
```

Output:

```text
Role: ADMIN
```

Optional/default:

```java
@RequestParam(required = false)
@RequestParam(defaultValue = "USER")
```

---

# 17. Request Body and JSON

Request:

```http
POST /api/users
Content-Type: application/json
```

```json
{
  "name": "Alice",
  "email": "alice@example.com"
}
```

Java:

```java
public record CreateUserRequest(
        String name,
        String email
) {}
```

Controller:

```java
@PostMapping("/api/users")
public CreateUserRequest create(
        @RequestBody CreateUserRequest request) {
    return request;
}
```

Conceptually:

```text
JSON → deserialization → Java object
```

---

# 18. Response Body and Status Codes

Returning a Java object:

```java
public UserResponse getUser() {
    return new UserResponse(1L, "Alice");
}
```

becomes JSON:

```json
{
  "id": 1,
  "name": "Alice"
}
```

Common API status codes:

| Code | Meaning |
|---|---|
| 200 | Success |
| 201 | Created |
| 204 | Success, no body |
| 400 | Bad request |
| 401 | Not authenticated |
| 403 | Not authorized |
| 404 | Not found |
| 409 | Conflict |
| 500 | Server error |

`ResponseEntity` gives explicit response control:

```java
return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(response);
```

---

# 19. DTOs and Records

DTO = Data Transfer Object.

Use DTOs for API contracts:

```java
public record CreateUserRequest(
        String name,
        String email
) {}

public record UserResponse(
        Long id,
        String name,
        String email
) {}
```

Mental model:

```text
HTTP request
    ↓
CreateUserRequest
    ↓
Service
    ↓
UserResponse
    ↓
HTTP response
```

Do not automatically expose database entities as API contracts.

---

# 20. Validation

API input is untrusted.

Later we can use Bean Validation:

```java
public record CreateUserRequest(
        @NotBlank String name,
        @Email String email
) {}
```

and:

```java
public UserResponse create(
        @Valid @RequestBody CreateUserRequest request) {
    ...
}
```

Validation belongs near the API boundary, while business rules belong in the service layer.

---

# 21. Controller-Service-Repository Layers

Typical flow:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Responsibilities:

| Layer | Responsibility |
|---|---|
| Controller | HTTP/API boundary |
| Service | Business logic |
| Repository | Persistence/data access |
| DTO | API data contract |
| Model/entity | Domain/database representation |
| Config | Application configuration/security |

Don't put SQL in controllers.

Don't put all business rules in controllers.

---

# 22. Spring JDBC

JDBC is Java's database connectivity API.

Spring JDBC provides a higher-level abstraction around JDBC.

```text
Java
  ↓
Spring JDBC
  ↓
JDBC
  ↓
PostgreSQL Driver
  ↓
PostgreSQL
```

The key class to learn initially is:

```text
JdbcTemplate
```

Spring JDBC still uses SQL. It removes much of the repetitive JDBC plumbing.

---

# 23. JDBC and JdbcTemplate

Repository:

```java
@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
```

`JdbcTemplate` can execute parameterized SQL.

Never concatenate user input into SQL.

Bad:

```java
"SELECT * FROM users WHERE id = " + id
```

Good:

```java
"SELECT * FROM users WHERE id = ?"
```

with `id` supplied separately.

This helps prevent SQL injection and keeps parameter handling correct.

---

# 24. Queries and Row Mapping

Query:

```java
String sql = """
        SELECT id, name, email
        FROM users
        WHERE id = ?
        """;
```

A `RowMapper` converts a database row into a Java object:

```java
RowMapper<User> mapper = (rs, rowNum) ->
        new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
        );
```

Mental model:

```text
Database row
     ↓
RowMapper
     ↓
Java User
```

---

# 25. Insert Update Delete

Insert:

```java
String sql = """
        INSERT INTO users(name, email)
        VALUES (?, ?)
        """;

jdbcTemplate.update(sql, name, email);
```

Update:

```java
jdbcTemplate.update(
        "UPDATE users SET name = ? WHERE id = ?",
        name, id
);
```

Delete:

```java
jdbcTemplate.update(
        "DELETE FROM users WHERE id = ?",
        id
);
```

`update()` is commonly used for `INSERT`, `UPDATE`, and `DELETE`.

---

# 26. Transactions

Suppose:

```text
Create user
   ↓
Assign role
   ↓
Create audit record
```

We may want all operations to succeed or all to roll back.

Spring can provide transaction management:

```java
@Transactional
public void createUser(...) {
    ...
}
```

Mental model:

```text
BEGIN
  ↓
operation 1
  ↓
operation 2
  ↓
operation 3
  ↓
COMMIT

failure → ROLLBACK
```

---

# 27. JDBC vs JPA

Spring JDBC:

```text
You write SQL
     ↓
JdbcTemplate
     ↓
PostgreSQL
```

JPA/Spring Data JPA:

```text
Java entities/repositories
     ↓
ORM
     ↓
SQL generated/executed
     ↓
Database
```

For this learning project, Spring JDBC is valuable because it teaches exactly what happens between Java and PostgreSQL.

We can introduce JPA later if useful. Do not learn both deeply at the same time.

---

# 28. Exception Handling

An internal exception should become an appropriate API response.

Example:

```text
UserNotFoundException
        ↓
404 Not Found
```

```text
DuplicateEmailException
        ↓
409 Conflict
```

Do not expose stack traces or database internals to clients.

A global handler can use:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handle(UserNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
```

---

# 29. Spring Security

Spring Security is the security framework for Spring applications.

It provides infrastructure for:

```text
Authentication
Authorization
Security filters
Password encoding
CSRF
Request authorization
OAuth2/OIDC integration
JWT/resource-server support
```

Important:

```text
Spring Security ≠ complete IAM platform
```

It is a framework component we will use inside our IAM services.

---

# 30. Security Filter Chain

A secured request flows approximately as:

```text
HTTP Request
      ↓
Security Filter Chain
      ↓
Authentication
      ↓
Authorization
      ↓
Controller
```

A request can be rejected before it reaches the controller.

This is fundamental for understanding IAM-related backend behavior.

---

# 31. Authentication vs Authorization

**Authentication:**

> Who are you?

Examples:

```text
username/password
session
access token
```

**Authorization:**

> What are you allowed to do?

Example:

```text
ADMIN → delete users
USER  → read own profile
```

Mental model:

```text
Authentication
      ↓
Identity
      ↓
Authorization
      ↓
Access decision
```

---

# 32. SecurityContext

After authentication, Spring Security maintains authentication information in the security context.

```text
Request
  ↓
Authentication
  ↓
SecurityContext
  ↓
Current identity/authorities
```

You may encounter:

```java
SecurityContextHolder.getContext()
```

but prefer suitable Spring abstractions where possible rather than coupling every class directly to the holder.

---

# 33. Securing Endpoints

Modern Spring Security configuration commonly uses `SecurityFilterChain`.

Conceptual example:

```java
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {

    http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/public/**").permitAll()
        .requestMatchers("/api/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated()
    );

    return http.build();
}
```

Mental model:

```text
/public/**       → public
/admin/**        → ADMIN
everything else → authenticated
```

We will add this only after the basic API works.

---

# 34. Password Hashing

Never store plaintext passwords.

Bad:

```text
password = "Admin123"
```

Instead:

```text
password
   ↓
PasswordEncoder
   ↓
hash
   ↓
database
```

Spring Security provides password encoders.

At login, the submitted password is checked against the stored hash.

Do not implement password hashing yourself.

---

# 35. CSRF and CORS

## CSRF

Cross-Site Request Forgery.

Its importance depends on authentication architecture, especially browser/session/cookie usage.

Do not memorize:

```text
"Disable CSRF for every API."
```

Instead ask:

```text
How is authentication represented?
Cookie/session?
Bearer token?
Browser?
```

## CORS

Cross-Origin Resource Sharing.

Example:

```text
Angular:    http://localhost:4200
Spring API: http://localhost:8080
```

A browser may enforce CORS rules between these origins.

CORS is a browser cross-origin policy.

It is **not authentication**.

---

# 36. Sessions vs Bearer Tokens

Session model:

```text
Login
 ↓
Server creates session
 ↓
Client sends cookie
 ↓
Server retrieves session
```

Bearer-token model:

```text
Client
 ↓
Authorization: Bearer <token>
 ↓
API validates token
 ↓
Authorize
```

Our IAM journey will eventually focus heavily on bearer tokens, OAuth 2.0, and OIDC.

---

# 37. JWT, OAuth 2.0 and OIDC

Do not treat these as synonyms.

```text
OAuth 2.0
  = authorization framework

OIDC
  = identity layer built on OAuth 2.0

JWT
  = token format

Spring Security
  = Java/Spring security framework
```

Typical resource-server flow:

```text
Client
  ↓
Bearer access token
  ↓
Spring Security
  ↓
Token validation
  ↓
Authorization
  ↓
Controller
```

We will learn OAuth/OIDC after basic Spring Security and JWT fundamentals.

---

# 38. Profiles, Secrets and Logging

Profiles:

```text
application.properties
application-dev.properties
application-test.properties
application-prod.properties
```

Use profiles for environment-specific behavior.

Keep secrets outside source code:

```text
database passwords
client secrets
API keys
private keys
tokens
```

Logging:

```java
logger.info("User created: {}", userId);
```

Common levels:

```text
DEBUG
INFO
WARN
ERROR
```

Never log passwords, access tokens, refresh tokens, or client secrets.

---

# 39. Testing and Debugging

Testing layers:

```text
Unit tests
   ↓
Controller tests
   ↓
Integration tests
   ↓
Database tests
   ↓
Security tests
```

When an API fails, debug from outside inward:

```text
1. Is server running?
2. Is port correct?
3. Did request reach Spring?
4. Did security reject it?
5. Did endpoint mapping match?
6. Did controller run?
7. Did service run?
8. Did repository run?
9. Did SQL execute?
10. Did database respond?
```

Use VS Code breakpoints and inspect the call stack.

---

# 40. Our First API

Target:

```text
GET /api/hello
```

Expected:

```json
{
  "message": "Hello from IAM Platform"
}
```

Create the project using Spring Initializr:

```text
Project: Maven
Language: Java
Java: 21
Packaging: Jar
Dependency: Spring Web
```

Do **not** add JDBC, PostgreSQL, Security, or JPA yet.

Main class:

```java
@SpringBootApplication
public class IamApplication {

    public static void main(String[] args) {
        SpringApplication.run(IamApplication.class, args);
    }
}
```

Response:

```java
public record HelloResponse(String message) {
}
```

Controller:

```java
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public HelloResponse hello() {
        return new HelloResponse(
                "Hello from IAM Platform"
        );
    }
}
```

Run:

```bash
mvn spring-boot:run
```

Test:

```bash
curl http://localhost:8080/api/hello
```

Expected:

```json
{"message":"Hello from IAM Platform"}
```

---

# 41. Break/Fix Exercises

After the endpoint works, deliberately break it.

## Exercise 1 — Change path

Change:

```java
@GetMapping("/hello")
```

to:

```java
@GetMapping("/greeting")
```

Call `/api/hello`.

Understand the `404`.

## Exercise 2 — Change method

Change:

```java
@GetMapping
```

to:

```java
@PostMapping
```

Call with GET.

Understand why the request no longer matches.

## Exercise 3 — Path variable

Implement:

```text
GET /api/hello/Alice
```

Response:

```json
{
  "message": "Hello Alice"
}
```

Use `@PathVariable`.

## Exercise 4 — Query parameter

Implement:

```text
GET /api/hello?name=Alice
```

Response:

```json
{
  "message": "Hello Alice"
}
```

Use `@RequestParam`.

## Exercise 5 — POST body

Implement:

```text
POST /api/hello
```

with:

```json
{
  "name": "Alice"
}
```

Use `@RequestBody`.

These exercises connect HTTP fundamentals directly to Spring MVC.

---

# 42. Next: User API

After `/api/hello`, build:

```text
POST /api/users
```

Request:

```json
{
  "name": "Alice",
  "email": "alice@example.com"
}
```

Response:

```json
{
  "id": 1,
  "name": "Alice",
  "email": "alice@example.com"
}
```

Initially use in-memory storage.

This teaches:

```text
DTO
Controller
Service
Dependency Injection
Validation
Error handling
HTTP status codes
```

Then add:

```text
GET /api/users
GET /api/users/{id}
PUT /api/users/{id}
DELETE /api/users/{id}
```

---

# 43. How This Becomes IAM

After the basic user API:

```text
POST /api/users
       ↓
UserController
       ↓
UserService
       ↓
UserRepository
       ↓
JdbcTemplate
       ↓
PostgreSQL
```

Then security:

```text
HTTP request
       ↓
Spring Security
       ↓
Authentication
       ↓
Authorization
       ↓
Controller
```

Then:

```text
Password hashing
       ↓
Login
       ↓
JWT
       ↓
Bearer token
       ↓
Protected API
       ↓
RBAC
```

Then:

```text
OAuth 2.0
       ↓
OIDC
       ↓
SSO
       ↓
External IdP
```

This is the bridge from ordinary backend development into IAM engineering.

---

# 44. What Not to Learn Yet

Do not spend time mastering these before building the first API:

```text
Spring WebFlux
Reactive programming
Spring Batch
Spring Integration
Spring Cloud
Advanced AOP
Advanced bean lifecycle customization
Advanced JPA/Hibernate internals
Kubernetes-specific Spring features
Advanced OAuth authorization-server implementation
Custom security framework internals
```

Also defer:

```text
Kong
AWS
Terraform
Docker
Kubernetes
Kafka
Redis
```

The principle:

> Learn deeply where you may own the problem; learn broadly where you only need to integrate with it.

---

# 45. Checkpoint

Before moving beyond this module, explain these in your own words.

## Spring

1. What is Spring?
2. What is IoC?
3. What is Dependency Injection?
4. What is a Spring bean?
5. What does the Spring container do?
6. Why prefer constructor injection?

## Spring Boot

7. What does Spring Boot add?
8. What does `@SpringBootApplication` do conceptually?
9. What is auto-configuration?
10. What is a starter?
11. What is an embedded server?
12. What is `application.properties`?

## Spring MVC

13. What is a controller?
14. What does `@RestController` do?
15. What are `@GetMapping` and `@PostMapping`?
16. What is `@PathVariable`?
17. What is `@RequestParam`?
18. What is `@RequestBody`?
19. How does JSON become a Java object?
20. How does a Java object become JSON?

## Architecture

21. What belongs in Controller?
22. What belongs in Service?
23. What belongs in Repository?
24. Why separate the layers?

## Spring JDBC

25. What is JDBC?
26. What does `JdbcTemplate` provide?
27. What is `RowMapper`?
28. Why use SQL parameters?
29. What is a transaction?
30. JDBC vs JPA: what is the conceptual difference?

## Security

31. What is Spring Security?
32. What is the Security Filter Chain?
33. Authentication vs authorization?
34. What is the SecurityContext?
35. What is password hashing?
36. What are CSRF and CORS?
37. Session vs bearer token?
38. Where does JWT fit?
39. Where do OAuth 2.0 and OIDC fit?

---

# 46. Exit Criteria and Next Step

You are ready to implement the first API when you can explain:

```text
HTTP request
    ↓
Security Filter Chain (once security is enabled)
    ↓
Spring MVC
    ↓
DispatcherServlet
    ↓
Controller
    ↓
Service
    ↓
Repository
    ↓
JdbcTemplate
    ↓
PostgreSQL
```

You do **not** need to memorize every annotation.

You need to understand the request flow and why each layer exists.

## Immediate implementation sequence

```text
1. Generate Spring Boot project
        ↓
2. Open in VS Code
        ↓
3. Understand project structure
        ↓
4. Run application
        ↓
5. Build GET /api/hello
        ↓
6. Test with curl
        ↓
7. Debug it
        ↓
8. Break it intentionally
        ↓
9. Fix it
        ↓
10. Add path/query parameters
        ↓
11. Add POST + JSON
        ↓
12. Build UserController
        ↓
13. Build UserService
        ↓
14. Add validation/error handling
        ↓
15. Add PostgreSQL
        ↓
16. Add Spring JDBC
        ↓
17. Add Spring Security
        ↓
18. Add authentication
        ↓
19. Add JWT
        ↓
20. Continue toward OAuth/OIDC
```

> **Core rule:** Do not learn Spring Boot by memorizing annotations. Learn it by understanding what happens to an HTTP request as it travels through the application.
