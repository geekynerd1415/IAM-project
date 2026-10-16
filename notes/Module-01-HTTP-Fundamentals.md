# Module 01 — HTTP Fundamentals

## Standalone IAM Platform Lab

### Status
**Current module — Start here**

This is the first learning and implementation module of the Standalone IAM Platform Lab.

The objective is not to memorize HTTP. The objective is to understand HTTP well enough to build, inspect, troubleshoot, and eventually secure the services that will form our IAM platform.

---

# 1. How This Module Fits Into the Project

Our eventual architecture will grow toward:

```text
Browser / Client
       │
       │ HTTP / HTTPS
       ▼
 API Gateway / Edge
       │
       ▼
 IAM Services
       │
       ▼
 PostgreSQL
```

HTTP is the communication foundation underneath almost every part of this architecture.

Before we learn authentication, OAuth, OIDC, JWT, SSO, or IAM authorization, we need to understand how requests actually travel between components.

---

# 2. What You Need to Achieve

By the end of this module, you should be able to:

- Explain what HTTP is
- Explain client/server communication
- Read an HTTP request
- Read an HTTP response
- Understand HTTP methods
- Understand URLs
- Understand path parameters
- Understand query parameters
- Understand headers
- Understand request bodies
- Understand response bodies
- Understand JSON
- Understand important HTTP status codes
- Understand `Content-Type`
- Understand `Accept`
- Understand the `Authorization` header conceptually
- Understand the basic idea of stateless HTTP
- Understand how REST APIs use HTTP
- Inspect real HTTP traffic
- Build a simple Spring Boot API
- Send requests to it
- Interpret its responses
- Intentionally break requests and explain the failures

---

# 3. Learning Rule

Do not try to memorize this entire document first.

Use:

```text
Read a section
    ↓
Understand it
    ↓
Try the example
    ↓
Ask questions
    ↓
Implement it
    ↓
Break it
    ↓
Understand the failure
```

We will not move forward simply because the document has been read.

---

# 4. Topic 1 — What Is HTTP?

HTTP stands for **Hypertext Transfer Protocol**.

It is a protocol that defines how clients and servers communicate.

At the simplest level:

```text
Client
  │
  │ HTTP Request
  ▼
Server
  │
  │ HTTP Response
  ▼
Client
```

The client asks for something. The server processes the request and sends a response.

### What can be a client?

- Web browser
- Angular application
- Mobile application
- Postman
- `curl`
- Another backend service
- API Gateway
- CLI application

### What can be a server?

A server is software that receives requests and produces responses.

For our project:

```text
Angular
   │
   │ HTTP
   ▼
Spring Boot
```

Spring Boot will initially be our HTTP server/application.

---

# 5. Topic 2 — HTTP Request

An HTTP request is a message sent by a client to a server.

Conceptually:

```text
HTTP Request
├── Method
├── URL
├── Headers
└── Body
```

Example:

```http
GET /api/users HTTP/1.1
Host: localhost:8080
Accept: application/json
```

---

# 6. Topic 3 — HTTP Methods

The method tells the server what kind of operation the client wants.

| Method | Typical purpose |
|---|---|
| GET | Retrieve data |
| POST | Create/submit data |
| PUT | Replace/update a resource |
| PATCH | Partially update a resource |
| DELETE | Delete a resource |

## GET

```http
GET /api/users
```

Meaning: give me the users.

```http
GET /api/users/42
```

Meaning: give me user 42.

## POST

```http
POST /api/users
Content-Type: application/json

{
  "username": "alice",
  "email": "alice@example.com"
}
```

Meaning: create a user using this data.

## PUT

```http
PUT /api/users/42
Content-Type: application/json

{
  "username": "alice",
  "email": "new@example.com"
}
```

Conceptually: replace/update the representation of user 42.

## PATCH

```http
PATCH /api/users/42
Content-Type: application/json

{
  "email": "new@example.com"
}
```

Conceptually: change only this part of user 42.

## DELETE

```http
DELETE /api/users/42
```

Meaning: delete user 42.

---

# 7. Topic 4 — URL

Example:

```text
https://example.com/api/users/42?active=true
```

Understand these pieces:

- Scheme/protocol: `https`
- Host: `example.com`
- Path: `/api/users/42`
- Query string: `?active=true`

---

# 8. Topic 5 — Path

The path identifies the resource or operation being requested.

```text
/api/users
/api/users/42
```

A common REST pattern is:

```text
/api/users
/api/users/{id}
```

For our project:

```text
GET    /api/users
GET    /api/users/{id}
POST   /api/users
PUT    /api/users/{id}
DELETE /api/users/{id}
```

---

# 9. Topic 6 — Path Parameters

A path parameter identifies a specific resource.

```text
GET /api/users/42
```

Here `42` is the user ID.

Conceptually:

```text
/api/users/{id}
             ↑
          parameter
```

---

# 10. Topic 7 — Query Parameters

Query parameters provide additional information.

```text
GET /api/users?role=ADMIN
```

or:

```text
GET /api/users?page=1&size=20
```

They are commonly used for:

- Filtering
- Searching
- Sorting
- Pagination
- Optional behavior

---

# 11. Topic 8 — HTTP Headers

Headers provide metadata about the request or response.

Example:

```http
GET /api/users HTTP/1.1
Host: localhost:8080
Accept: application/json
```

Common headers we will encounter:

```text
Content-Type
Accept
Authorization
Cache-Control
User-Agent
Host
```

We do not need to learn every header now.

---

# 12. Topic 9 — Content-Type

`Content-Type` tells the receiver what format the body is in.

```http
Content-Type: application/json
```

This means the request body contains JSON.

Remember:

```text
Content-Type
    ↓
What format am I sending?
```

---

# 13. Topic 10 — Accept

`Accept` tells the server what response format the client prefers.

```http
Accept: application/json
```

Remember:

```text
Content-Type
    ↓
What format am I sending?

Accept
    ↓
What format do I want back?
```

---

# 14. Topic 11 — Request Body

Some requests contain a body.

```http
POST /api/users
Content-Type: application/json

{
  "username": "alice",
  "email": "alice@example.com"
}
```

The JSON object is the request body.

POST/PUT/PATCH commonly use request bodies.

---

# 15. Topic 12 — Response

The server responds to the request.

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": 1,
  "username": "alice",
  "email": "alice@example.com"
}
```

A response contains:

```text
HTTP Response
├── Status
├── Headers
└── Body
```

---

# 16. Topic 13 — HTTP Status Codes

Focus on these first:

| Status | Meaning |
|---|---|
| 200 | Request succeeded |
| 201 | Resource created |
| 204 | Succeeded, no response body |
| 400 | Bad request |
| 401 | Authentication required/failed |
| 403 | Authenticated but not allowed |
| 404 | Resource not found |
| 409 | Conflict |
| 500 | Server-side failure |

Understand the situations, not just the names.

### 200 OK

Successful request.

### 201 Created

A new resource was created.

### 204 No Content

Operation succeeded with no response body.

### 400 Bad Request

The request is invalid, for example malformed JSON or invalid input.

### 401 Unauthorized

For our IAM project, think:

> The request does not have valid authentication credentials.

Later:

```text
Request
   ↓
Authorization: Bearer <JWT>
   ↓
JWT invalid/expired
   ↓
401
```

### 403 Forbidden

Think:

> The request is authenticated, but the user is not allowed to perform the operation.

Later:

```text
Valid JWT
   ↓
User identified
   ↓
Required permission missing
   ↓
403
```

### 404 Not Found

The requested resource does not exist.

### 409 Conflict

The request conflicts with the current state of a resource, such as creating a duplicate unique user.

### 500 Internal Server Error

Something went wrong on the server.

Do not expose internal stack traces or implementation details to the client.

---

# 17. Topic 14 — JSON

JSON is a common format for API communication.

```json
{
  "id": 1,
  "username": "alice",
  "email": "alice@example.com"
}
```

Important JSON types:

- String
- Number
- Boolean
- Object
- Array
- null

Example:

```json
{
  "id": 1,
  "active": true,
  "roles": ["USER", "ADMIN"],
  "profile": {
    "displayName": "Alice"
  }
}
```

JSON will be the primary API data format in our project.

---

# 18. Topic 15 — Complete HTTP Request/Response

Request:

```http
POST /api/users HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

{
  "username": "alice",
  "email": "alice@example.com"
}
```

Breakdown:

```text
POST
 ↓
Operation

/api/users
 ↓
Resource

Content-Type: application/json
 ↓
Request body format

Accept: application/json
 ↓
Preferred response format

JSON body
 ↓
Data
```

Possible response:

```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": 1,
  "username": "alice",
  "email": "alice@example.com"
}
```

Flow:

```text
Client
  │
  │ POST /api/users
  │ JSON body
  ▼
Spring Boot
  │
  │ validate
  │ process
  │ store
  ▼
PostgreSQL
  │
  ▼
Spring Boot
  │
  │ 201 Created
  │ JSON
  ▼
Client
```

---

# 19. Topic 16 — Statelessness

A basic property of HTTP is that requests can be treated independently.

Conceptually:

```text
Request 1
Request 2
Request 3
```

The server does not automatically remember everything about previous requests simply because they came from the same client.

This becomes extremely important for authentication.

Later we will ask:

> If HTTP requests are independent, how does the server know who the user is?

That leads toward:

- Sessions
- Cookies
- Tokens
- JWT
- OAuth
- OIDC

Do not jump ahead yet. Just understand the problem.

---

# 20. Topic 17 — REST

REST is an architectural style for designing networked applications.

For our project, focus on:

- Resources
- HTTP methods
- Stateless communication
- Meaningful URLs
- Appropriate status codes
- Consistent representations

Example:

```text
/api/users
/api/users/{id}
/api/organizations
/api/organizations/{id}
/api/roles
/api/roles/{id}
```

We will refine REST API design in Module 02.

---

# 21. Topic 18 — HTTP and IAM

Eventually:

```text
Browser
   │
   │ HTTPS
   ▼
Application
   │
   │ Authorization: Bearer <JWT>
   ▼
API Gateway
   │
   ▼
IAM Service
   │
   ▼
PostgreSQL
```

Every arrow involves communication.

Understanding HTTP allows us to reason about:

- Authentication requests
- Token requests
- API calls
- Error responses
- Headers
- Cookies
- CORS
- Content types
- Authorization
- API contracts

HTTP is foundational to the entire IAM platform.

---

# 22. Hands-On Setup

For this module we need:

- Java/JDK
- Maven or Gradle
- Spring Boot
- VS Code or IntelliJ
- Git
- A way to send HTTP requests

You can use:

- Browser
- `curl`
- Postman
- Bruno
- VS Code REST Client

Use `curl` or a REST client at least once so you understand the request directly rather than hiding everything behind Angular.

---

# 23. First Implementation

Create the smallest Spring Boot API possible.

Target:

```text
GET /api/hello
```

Expected response:

```json
{
  "message": "Hello from IAM Platform"
}
```

Run it locally and call:

```bash
curl http://localhost:8080/api/hello
```

Observe:

```text
Request
   ↓
Spring Boot
   ↓
Response
```

---

# 24. Second Implementation

Create:

```text
GET /api/hello/{name}
```

Example:

```text
GET /api/hello/Alice
```

Expected response:

```json
{
  "message": "Hello Alice"
}
```

This gives us practical experience with a path parameter.

---

# 25. Third Implementation

Create:

```text
GET /api/hello?name=Alice
```

Expected response:

```json
{
  "message": "Hello Alice"
}
```

Compare:

```text
Path parameter
/api/hello/Alice
```

with:

```text
Query parameter
/api/hello?name=Alice
```

Understand when each is appropriate.

---

# 26. Fourth Implementation — POST

Create:

```text
POST /api/users
```

with:

```json
{
  "username": "alice",
  "email": "alice@example.com"
}
```

Return:

```text
201 Created
```

with a JSON representation of the created user.

At this stage, an in-memory implementation is acceptable if PostgreSQL has not yet been introduced.

Do not over-engineer it.

---

# 27. Fifth Implementation — Error Handling

Test:

```text
GET /api/users/999
```

when the user doesn't exist.

Return:

```text
404 Not Found
```

Then send invalid input.

Return:

```text
400 Bad Request
```

The objective is to learn that the API communicates outcomes through HTTP.

---

# 28. Break/Fix Exercises

Do these intentionally.

## Exercise A — Wrong URL

Call:

```text
GET /api/unknown
```

Understand the response.

## Exercise B — Wrong method

Try:

```text
DELETE /api/hello
```

What happens? Why?

## Exercise C — Invalid JSON

Send malformed JSON to the POST endpoint.

Observe the result.

## Exercise D — Wrong Content-Type

Send JSON without:

```text
Content-Type: application/json
```

Observe what happens.

## Exercise E — Missing required field

Send:

```json
{
  "username": "alice"
}
```

without email.

Decide what response should be returned.

## Exercise F — Duplicate user

Create the same user twice.

Determine how your API should represent the conflict.

---

# 29. What You Should NOT Learn Yet

Do not spend time on these unless implementation requires them:

- HTTP/2 internals
- HTTP/3
- QUIC
- WebSockets
- Advanced caching
- ETags
- CDN architecture
- HTTP proxy internals
- Advanced TLS internals
- Every HTTP header
- Every HTTP status code

These are useful topics, but they are not required for the current milestone.

---

# 30. Checkpoint Questions

Before moving to the next module, answer these in your own words.

### HTTP basics

1. What is HTTP?
2. What is a client?
3. What is a server?
4. What is an HTTP request?
5. What is an HTTP response?

### Methods

6. What is GET used for?
7. What is POST used for?
8. PUT vs PATCH?
9. What is DELETE used for?

### URL

10. What is a path?
11. What is a path parameter?
12. What is a query parameter?

### Headers/body

13. What is a header?
14. What does `Content-Type` mean?
15. What does `Accept` mean?
16. What is a request body?
17. What is a response body?

### Status codes

18. When would you return 200?
19. When would you return 201?
20. What does 400 mean?
21. What does 401 mean?
22. What does 403 mean?
23. What does 404 mean?
24. What does 409 mean?
25. What does 500 mean?

### Architecture

26. What happens when Angular calls Spring Boot?
27. Where does PostgreSQL fit?
28. What is the difference between the client and backend?
29. Why does the API need a contract?
30. Why does HTTP matter to IAM?

---

# 31. Explain It Back

Explain this flow without looking at the notes:

```text
Angular
   ↓
HTTP Request
   ↓
Spring Boot Controller
   ↓
Service
   ↓
Repository
   ↓
PostgreSQL
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
HTTP Response
   ↓
Angular
```

You should be able to explain:

- What each layer does
- What travels between layers
- Where data is validated
- Where data is persisted
- How success is communicated
- How failure is communicated

---

# 32. Module 01 Exit Criteria

We can consider HTTP Fundamentals sufficiently understood when you can:

- Read an HTTP request without confusion
- Read an HTTP response without confusion
- Choose an appropriate HTTP method for basic operations
- Explain path vs query parameters
- Explain request/response headers
- Explain JSON request/response bodies
- Distinguish the major status codes
- Build a simple Spring Boot REST endpoint
- Call it using a real HTTP client
- Understand the response
- Intentionally cause common errors
- Explain why those errors occurred

You do NOT need to know every detail of HTTP before moving forward.

---

# 33. After This Module

The next module will be:

# Module 02 — REST API Fundamentals

We will build on the HTTP foundation and learn:

- Resource-oriented API design
- CRUD
- API naming
- Request/response DTOs
- Validation
- Error response structure
- Pagination
- Filtering
- Sorting
- Idempotency
- PUT vs PATCH in practice
- API versioning
- Backward compatibility
- API contracts
- OpenAPI

Then we will strengthen the actual user API.

---

# 34. Project Principle

We are not learning HTTP because HTTP is on a syllabus.

We are learning HTTP because:

```text
IAM
 ↓
APIs
 ↓
HTTP
 ↓
Communication
```

and we need to understand that communication deeply before we add:

```text
Authentication
 ↓
JWT
 ↓
OAuth
 ↓
OIDC
 ↓
SSO
 ↓
Authorization
```

The project will determine what we learn next.

---

# 35. Immediate Action List

## Your actions

### Step 1
Read sections 1–20.

Do not try to memorize them.

### Step 2
Set up the development environment if it isn't already ready.

### Step 3
Create the Spring Boot project.

### Step 4
Implement:

```text
GET /api/hello
GET /api/hello/{name}
GET /api/hello?name=...
```

### Step 5
Test them using `curl`, Postman, Bruno, or another HTTP client.

### Step 6
Implement:

```text
POST /api/users
```

### Step 7
Implement basic error handling.

### Step 8
Perform the break/fix exercises.

### Step 9
Answer the checkpoint questions in your own words.

### Step 10
When something doesn't work, do not immediately search for the solution.

Bring the error/request/response here. We will debug it together.

---

# 36. What I Will Do After You Start

When you come back with your implementation, error, question, or explanation, I will:

1. Review what you built
2. Identify gaps in understanding
3. Explain the underlying concept
4. Help debug the problem
5. Add the useful lesson to our notes
6. Decide whether we need another HTTP concept
7. Move us toward the next implementation
8. Only then introduce the next module

This makes the notes a living record of what you actually learned, rather than a static textbook.

---

# 37. First Target

Our first concrete target is very small:

```text
Spring Boot
    │
    └── GET /api/hello
             │
             ▼
       JSON response
```

Once this works, we build outward.

**Do not add authentication, OAuth, PostgreSQL, Docker, AWS, Kong, or anything else yet.**

First:

> **Make one HTTP request. Understand exactly what happened.**
