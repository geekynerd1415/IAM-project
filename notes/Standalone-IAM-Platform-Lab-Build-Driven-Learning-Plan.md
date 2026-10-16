# Standalone IAM Platform Lab — Build-Driven Learning Plan

## Purpose

This document is the **execution plan** for the hands-on project.

The 30-Day IAM & Platform Engineering Roadmap is the **master syllabus**.

This document is different:

> **It tells us what to learn and implement next.**

We will **not** wait until we have learned everything in the roadmap.

Instead, we will continuously use this loop:

```text
Learn
  ↓
Understand
  ↓
Implement
  ↓
Get stuck
  ↓
Investigate
  ↓
Fix
  ↓
Understand more deeply
  ↓
Expand the project
  ↓
Repeat
```

The project is a laboratory for understanding:

- How components communicate
- Why each component exists
- What constraints appear
- Where failures occur
- How authentication works
- How authorization works
- How APIs interact
- How data is stored
- How systems are deployed
- How security affects architecture
- How different environments behave
- How an IAM architecture can be built from the ground up

---

# 1. Our Learning Strategy

## Do NOT follow this model

```text
Learn IAM
      ↓
Learn Backend
      ↓
Learn AWS
      ↓
Learn Docker
      ↓
Learn Terraform
      ↓
Learn Everything
      ↓
Finally build the project
```

## Follow this model instead

```text
Build a small piece
      ↓
Discover what knowledge is required
      ↓
Learn that knowledge
      ↓
Implement it
      ↓
Break it
      ↓
Debug it
      ↓
Expand the system
```

This means the roadmap is **adaptive**.

If implementation reveals that we need another concept, we learn that concept at that point.

---

# 2. Current Objective

## We are starting with the foundation of the IAM platform.

Before implementing OAuth/OIDC, we need a small application that can:

1. Accept HTTP requests
2. Expose REST APIs
3. Store data
4. Retrieve data
5. Validate input
6. Return appropriate HTTP responses
7. Communicate between frontend and backend
8. Persist data in PostgreSQL

Once this foundation works, authentication becomes much easier to understand because we will have an actual application to secure.

---

# 3. CURRENT STEP — Build the Application Skeleton

## Learn NOW

### A. HTTP fundamentals

Understand:

- Client
- Server
- Request
- Response
- HTTP methods
- GET
- POST
- PUT
- PATCH
- DELETE
- HTTP headers
- Request body
- Response body
- HTTP status codes
- JSON
- Content-Type
- Authorization header
- URL
- Query parameters
- Path parameters

You do **not** need to memorize every HTTP status code.

Understand the important ones:

```text
200 OK
201 Created
204 No Content
400 Bad Request
401 Unauthorized
403 Forbidden
404 Not Found
409 Conflict
500 Internal Server Error
```

### B. REST API fundamentals

Understand:

- Resource
- Endpoint
- CRUD
- Stateless requests
- Resource-oriented API design
- Request validation
- Error responses
- API contracts

Example:

```text
GET    /api/users
GET    /api/users/{id}
POST   /api/users
PUT    /api/users/{id}
DELETE /api/users/{id}
```

### C. Spring Boot fundamentals

Understand:

- Application
- Controller
- Service
- Repository
- Entity
- Dependency Injection
- Configuration
- Profiles
- Exception handling
- Validation

The goal is not to memorize Spring annotations.

Understand how a request travels through the application:

```text
HTTP Request
     ↓
Controller
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
```

### D. PostgreSQL fundamentals

Understand:

- Database
- Table
- Row
- Column
- Primary key
- Foreign key
- Constraints
- Relationships
- INSERT
- SELECT
- UPDATE
- DELETE
- Basic indexes
- Transactions

Do not go deep into database optimization yet.

---

# 4. CURRENT IMPLEMENTATION

Create the first version of:

## Standalone IAM Platform Lab

Initial architecture:

```text
Angular Frontend
       │
       │ HTTP/JSON
       ▼
Spring Boot Backend
       │
       │ JPA/JDBC
       ▼
PostgreSQL
```

Do NOT add:

- OAuth yet
- OIDC yet
- SAML yet
- SCIM yet
- Kong yet
- AWS yet
- Terraform yet
- Kubernetes yet
- Complex microservices yet

We want to understand the simplest working system first.

---

# 5. First Feature — User Management

Build a basic user-management API.

## User model

Start with something similar to:

```text
User
----
id
username
email
displayName
createdAt
updatedAt
```

Do NOT add passwords yet.

Authentication comes later.

## APIs

Implement:

```text
POST   /api/users
GET    /api/users
GET    /api/users/{id}
PUT    /api/users/{id}
DELETE /api/users/{id}
```

## Required behavior

The API should:

- Validate input
- Return appropriate HTTP status codes
- Return JSON
- Handle invalid IDs
- Handle duplicate users appropriately
- Handle invalid request bodies
- Persist data
- Retrieve data from PostgreSQL

---

# 6. What We Should Understand Before Moving On

Do not move to authentication merely because the CRUD API works.

Make sure you can explain:

### Request flow

```text
Angular
   ↓
HTTP request
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
PostgreSQL
```

### And the reverse:

```text
PostgreSQL
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
JSON response
   ↓
Angular
```

You should understand:

- Where validation occurs
- Where business logic belongs
- Where database logic belongs
- How errors travel back to the client
- How HTTP status codes communicate outcomes
- How JSON is serialized/deserialized
- How a database transaction works at a basic level

---

# 7. FIRST HANDS-ON BREAK/FIX EXERCISES

Once the basic API works, deliberately break it.

## Exercise 1 — Wrong endpoint

Call:

```text
GET /api/user
```

instead of:

```text
GET /api/users
```

Understand why you receive:

```text
404
```

---

## Exercise 2 — Invalid request

Send incomplete JSON.

Understand:

```text
400 Bad Request
```

and where validation occurs.

---

## Exercise 3 — Duplicate user

Try creating the same user twice.

Decide how the API should respond.

For example:

```text
409 Conflict
```

Understand why a conflict is different from a server error.

---

## Exercise 4 — Database failure

Stop PostgreSQL.

Call the API.

Observe what happens.

Then determine:

- What error occurs?
- What should the client receive?
- What should be logged?
- Should database errors be exposed directly?

This is our first introduction to **failure handling**.

---

## Exercise 5 — Frontend/backend communication

Run the Angular frontend and Spring Boot backend separately.

Make them communicate.

If the browser blocks the request because of CORS:

> Investigate why.

Do not simply copy a CORS configuration from the internet.

Understand:

```text
Browser
   ↓
Origin A
   ↓
Request
   ↓
Origin B
```

and why the browser applies CORS rules.

---

# 8. CHECKPOINT 1

We are ready to move forward when you can explain:

### HTTP

- What is a request?
- What is a response?
- GET vs POST?
- PUT vs PATCH?
- 401 vs 403?
- 400 vs 409?
- What is a header?
- What is JSON?

### REST

- What is a resource?
- What makes an API REST-like?
- Why should APIs have contracts?
- Why should APIs return meaningful status codes?

### Spring Boot

- What does the controller do?
- What does the service do?
- What does the repository do?
- How does dependency injection help?
- Where should validation happen?
- How should exceptions be handled?

### PostgreSQL

- What is a primary key?
- What is a foreign key?
- What is a transaction?
- Why do we need constraints?
- Why do we use indexes?

### Architecture

You should be able to draw:

```text
Frontend
   ↓
HTTP
   ↓
Backend
   ↓
Database
```

and explain every arrow.

---

# 9. NEXT STEP — Authentication

Only after Checkpoint 1 will we introduce authentication.

We will then learn only the concepts needed for the next implementation.

Likely sequence:

```text
Authentication
      ↓
Password security
      ↓
Login
      ↓
Session / Token concepts
      ↓
JWT
      ↓
Protected APIs
      ↓
Authorization
      ↓
Roles / Permissions
```

At that point, OAuth/OIDC will have a real context.

---

# 10. NEXT STEP — JWT

When we reach JWT, learn:

- JWT structure
- Header
- Payload
- Signature
- Claims
- exp
- iss
- aud
- sub
- Signing
- Verification
- Symmetric vs asymmetric keys
- Public/private keys
- JWK
- JWKS

Then implement:

```text
Login
  ↓
JWT issued
  ↓
Client stores token appropriately
  ↓
Client calls protected API
  ↓
Backend validates JWT
  ↓
Request allowed/rejected
```

Then deliberately break:

- Expired token
- Invalid signature
- Wrong issuer
- Wrong audience
- Missing token
- Malformed token

This will make JWT/JWKS concepts concrete.

---

# 11. NEXT STEP — Authorization

After authentication works, ask:

> "The system knows who the user is. But what is the user allowed to do?"

Learn:

- Authentication vs authorization
- Roles
- Permissions
- Claims
- Scopes
- RBAC

Implement something like:

```text
ADMIN
  ↓
Can create/delete users

SUPPORT
  ↓
Can view users

USER
  ↓
Can view own profile
```

Then test unauthorized access.

---

# 12. NEXT STEP — OAuth 2.0 + OIDC

Only after we have a basic understanding of authentication and authorization will we introduce:

- OAuth 2.0
- Authorization Code
- PKCE
- OIDC
- Access tokens
- ID tokens
- Refresh tokens
- Authorization server
- Resource server
- Client
- Redirect URI
- State
- Nonce

Then integrate an Identity Provider.

The system evolves into:

```text
                Identity Provider
                       │
                       │ OIDC
                       ▼
Browser ─────────► Application
                       │
                       ▼
                    Backend
                       │
                       ▼
                  PostgreSQL
```

We will trace the actual requests rather than only reading about the protocol.

---

# 13. SSO

Add a second application:

```text
              Identity Provider
                     │
              ┌──────┴──────┐
              ▼             ▼
           App A          App B
```

Test:

1. Login to App A
2. Open App B
3. Observe authentication behavior
4. Understand why the user does not need to authenticate independently in every application

This is where SSO becomes practical.

---

# 14. SAML

SAML should be learned **after OIDC**, not before the project begins.

Learn:

- IdP
- SP
- Entity ID
- ACS
- Metadata
- Assertions
- Attributes
- Certificates
- Signing
- SP-initiated SSO
- IdP-initiated SSO

Then perform a small SAML integration/experiment.

The goal is understanding, not becoming a SAML specialist.

---

# 15. SCIM

After understanding identity and SSO, learn:

- User provisioning
- Deprovisioning
- Group provisioning
- Lifecycle management
- SCIM resources
- SCIM endpoints

Then implement or simulate:

```text
Admin System
     ↓
SCIM
     ↓
IAM Platform
     ↓
User created
```

and:

```text
User disabled
     ↓
SCIM
     ↓
IAM Platform
     ↓
Access revoked
```

---

# 16. SSO Automation

This becomes one of the major capstone capabilities.

Start with manual configuration.

Then turn it into:

```text
Configuration API
       ↓
Validation
       ↓
Store configuration
       ↓
Generate required configuration
       ↓
Apply configuration
       ↓
Test SSO
       ↓
Return status
       ↓
Audit
```

The purpose is to experience the transition from:

> IAM operations

to:

> IAM platform engineering.

---

# 17. API Gateway Awareness

Only once the backend APIs are meaningful should we introduce the gateway.

Learn:

- Why a gateway exists
- Routing
- Reverse proxy
- Authentication integration
- Rate limiting
- TLS
- CORS
- Request transformation
- Logging
- Health checks

Then place a gateway in front:

```text
Internet
   ↓
API Gateway
   ↓
IAM APIs
   ↓
PostgreSQL
```

Kong can be used for the lab if useful.

The goal is:

> Understand the gateway boundary.

The goal is NOT:

> Become a Kong engineer.

---

# 18. Docker

Once the application has multiple components, containerization becomes useful.

Containerize:

```text
Frontend
Backend
PostgreSQL
Identity Provider
Gateway
```

Then understand:

- Image
- Container
- Network
- Port
- Environment variable
- Volume
- Health check

The architecture becomes:

```text
Docker Network
│
├── frontend
├── backend
├── postgres
├── idp
└── gateway
```

---

# 19. Deployment

Only after the application works locally should we deploy it.

Possible learning deployment:

```text
Frontend
   ↓
Vercel or similar

Backend
   ↓
Render or similar

Database
   ↓
Hosted PostgreSQL

Identity Provider
   ↓
Hosted or appropriate environment
```

The exact hosting provider is secondary.

The objective is to learn:

- HTTPS
- Environment configuration
- Secrets
- CORS
- Public/private networking
- Deployment
- Logs
- Health checks

---

# 20. AWS

After we understand deployment, learn the AWS concepts needed to reproduce a more enterprise-like architecture.

Focus on:

- VPC
- Subnets
- Security Groups
- ALB
- Compute
- RDS
- IAM
- Parameter Store
- Secrets Manager
- KMS
- CloudWatch

We do NOT need to move the entire project to AWS immediately.

Use AWS when it teaches us something that local/managed hosting cannot.

---

# 21. Terraform

Once we have infrastructure worth creating, learn Terraform.

Do not learn Terraform in isolation.

Instead:

```text
Manual AWS resource
       ↓
Understand it
       ↓
Terraform
       ↓
Create same resource as code
       ↓
Destroy
       ↓
Recreate
```

This makes Infrastructure as Code meaningful.

---

# 22. CI/CD

Once deployment exists:

```text
Git push
   ↓
CI
   ↓
Build
   ↓
Test
   ↓
Security checks
   ↓
Build artifact/image
   ↓
Deploy
   ↓
Health check
```

Then intentionally break a deployment and learn how to diagnose it.

---

# 23. Observability

Once the system is distributed, learn:

- Structured logging
- Metrics
- Tracing
- Correlation/request IDs
- Health checks
- Alerts

We should be able to answer:

> "A user says login failed. How do I determine what happened?"

Trace:

```text
Browser
 ↓
Gateway
 ↓
Backend
 ↓
IdP
 ↓
Database
```

---

# 24. System Design

System design should NOT be postponed until the end.

Whenever we add a major feature, ask:

- What are the requirements?
- What are the components?
- What data is needed?
- What happens when it fails?
- What happens under load?
- What are the security boundaries?
- What should be synchronous?
- What should be asynchronous?
- What needs to be cached?
- What needs to be persisted?
- How will this scale?
- How will this be monitored?

Use the project itself to learn system design.

---

# 25. Spec Kit / Spec-Driven Development

Use Spec Kit progressively.

Do not create massive specifications before we understand the system.

For each significant feature:

```text
Requirement
   ↓
Specification
   ↓
Clarification
   ↓
Technical Plan
   ↓
Tasks
   ↓
Implementation
   ↓
Tests
   ↓
Review
```

Example:

> "Add role-based authorization."

Before coding, define:

- Roles
- Permissions
- API behavior
- Data model
- Security rules
- Error behavior
- Tests

Then implement.

---

# 26. DSA

DSA is **not part of the critical path for this project**.

Do not stop the IAM project to study:

- Trees
- Graphs
- Dynamic programming
- Backtracking
- Sliding window
- Two pointers

You can learn DSA separately.

If there is spare time, DSA can be added as a secondary track.

The current primary objective is:

```text
IAM
+
Backend
+
Security
+
Architecture
+
Hands-on engineering
```

---

# 27. How We Decide What to Learn Next

At every stage, ask:

> **"What is the next thing the system needs?"**

Examples:

### We need user registration

Learn:

```text
REST
Spring Boot
PostgreSQL
Validation
```

### We need secure login

Learn:

```text
Authentication
Password security
Spring Security
```

### We need protected APIs

Learn:

```text
JWT
Authentication filters
Token validation
```

### We need permissions

Learn:

```text
RBAC
Roles
Permissions
Claims
```

### We need external application login

Learn:

```text
OAuth
OIDC
PKCE
```

### We need enterprise SSO

Learn:

```text
SAML
SCIM
Identity federation
```

### We need public deployment

Learn:

```text
Docker
HTTPS
Cloud
Secrets
Networking
```

### We need repeatable infrastructure

Learn:

```text
Terraform
```

### We need automated deployment

Learn:

```text
CI/CD
```

This is our **just-in-time learning mechanism**.

---

# 28. Definition of Done for the Project

The project is successful if, by the end, you can look at the architecture and explain:

```text
What is this component?
Why does it exist?
Who calls it?
What does it call?
What data does it own?
How is it secured?
What happens when it fails?
How is it deployed?
How is it monitored?
How would it scale?
How would we migrate it?
```

You do NOT need to create a production-ready enterprise IAM product.

You need to gain the experience necessary to reason about one.

---

# 29. The Core Rule

## Do not learn everything first.

## Build first.

## Learn what the build demands.

## Then go deeper.

The project should continuously drive the curriculum.

> **The architecture is our teacher.**

---

# 30. Immediate Next Action

Start with **CURRENT STEP — Build the Application Skeleton**.

The first target is:

```text
Angular
   ↓
HTTP/JSON
   ↓
Spring Boot
   ↓
PostgreSQL
```

with a working:

```text
User CRUD API
```

Do not add authentication yet.

First make the basic system work.

Then we will secure it.

Then we will add identity.

Then we will add authorization.

Then SSO.

Then enterprise identity protocols.

Then deployment.

Then cloud/platform engineering.

The system will grow naturally as our knowledge grows.
