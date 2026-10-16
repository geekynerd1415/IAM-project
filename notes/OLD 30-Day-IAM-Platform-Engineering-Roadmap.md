# 30-Day IAM & Platform Engineering Preparation Roadmap

## 1. Vision

### Primary Vision

Become technically prepared to contribute to the design, development, automation, deployment, and operation of a **standalone enterprise IAM and API platform** from the ground up.

The goal is not to become an expert in every technology within one month.

The goal is to develop enough **depth, breadth, architectural understanding, and hands-on experience** to participate confidently in discussions and implementation involving:

- Identity and Access Management
- Authentication and Authorization
- OAuth 2.0
- OpenID Connect
- SAML
- JWT
- SCIM
- Backend services
- API design
- API gateways
- Kong
- Identity Providers
- AWS
- Docker
- Terraform
- CI/CD
- Observability
- Security
- Distributed systems
- System design
- Migration and modernization
- SSO automation
- Developer platforms

---

## 2. Mission

Build the knowledge and practical capability required to move from primarily **IAM support/troubleshooting experience** toward **IAM / Backend / Platform Engineering capability**.

The preparation should convert existing practical experience into the ability to:

> Understand the problem → design the solution → define contracts → implement services → integrate identity → secure the platform → deploy it → observe it → automate it → migrate existing consumers.

---

## 3. Strategic Context

The preparation is motivated by the expected creation of a standalone LS&H technology environment.

The technical discussions already indicate potential areas including:

- Standalone IAM services
- Identity Provider evaluation
- API gateway adoption, with Kong identified as a likely starting point
- Redevelopment of custom Kong plugins
- Developer Portal redevelopment
- Home-grown alerting
- Replacement of existing service-discovery/configuration technologies
- AWS ALB
- AWS Parameter Store
- Maintaining API compatibility with existing LS&H products
- Potential migration from existing Clarivate technology to independently operated technology

Therefore, the learning program will focus on **capabilities directly relevant to these areas** rather than attempting to learn the entire modern software-engineering ecosystem.

---

## 4. Primary Goals

### Goal 1 — Become strong in IAM fundamentals

Develop deep practical understanding of:

- Authentication
- Authorization
- Identity
- Sessions
- OAuth 2.0
- OpenID Connect
- JWT
- SAML 2.0
- SCIM
- RBAC
- Claims
- Scopes
- Roles
- Permissions
- Identity lifecycle
- Federation

**Desired capability:** Be able to explain and troubleshoot an enterprise authentication flow from:

```text
User
 ↓
Application
 ↓
Gateway
 ↓
Identity Provider
 ↓
Authorization
 ↓
Token
 ↓
Backend API
```

### Goal 2 — Strengthen backend engineering

Develop practical capability with:

- Java
- Spring Boot
- Spring Security
- REST APIs
- OpenAPI
- PostgreSQL
- Transactions
- Validation
- Error handling
- Authentication
- Authorization
- Testing

**Desired capability:** Be able to independently build a production-style IAM backend service rather than only troubleshoot existing services.

### Goal 3 — Become capable with API architecture

Focus on:

- REST
- HTTP
- API contracts
- OpenAPI
- Versioning
- Backward compatibility
- Pagination
- Idempotency
- Error handling
- Rate limiting
- Authentication
- Authorization
- API lifecycle

**Desired capability:** Be able to design an API while considering existing consumers and future compatibility.

### Goal 4 — Become practical with Kong

Develop enough Kong knowledge to participate in API gateway design and implementation.

Learn:

- Gateway architecture
- Services
- Routes
- Upstreams
- Targets
- Consumers
- Plugins
- Authentication
- OIDC
- JWT
- Rate limiting
- Request transformation
- TLS
- Custom plugins

**Desired capability:** Be able to build:

```text
Client
  ↓
Kong
  ↓
Authentication / Authorization
  ↓
Backend Service
```

and understand how custom gateway logic fits into the architecture.

### Goal 5 — Understand Identity Provider architecture

Learn how an enterprise Identity Provider fits into a larger IAM platform.

Understand:

- Authentication
- Federation
- OIDC
- OAuth
- SAML
- MFA
- User directory
- Token issuance
- Sessions
- Key management
- Provisioning
- Deprovisioning
- SCIM

**Desired capability:** Be able to participate in an evaluation of an Identity Provider based on technical and business requirements rather than simply knowing how to configure one.

### Goal 6 — Build cloud/platform engineering capability

Focus on the AWS components relevant to the anticipated architecture.

**AWS**

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

**Containers**

- Docker
- Dockerfiles
- Images
- Containers
- Networking
- Docker Compose
- Health checks

**Infrastructure as Code**

- Terraform
- Resources
- Variables
- Outputs
- Modules
- State
- Plan
- Apply

**CI/CD**

- GitHub Actions
- Build
- Test
- Security scanning
- Artifact creation
- Docker image creation
- Deployment
- Rollback

### Goal 7 — Develop system-design and architecture skills

Use **Hello Interview — System Design in a Hurry** as the primary system-design learning framework.

Focus on:

- System-design methodology
- Requirements gathering
- Capacity estimation
- Networking
- API design
- Data modeling
- Database indexing
- Caching
- Load balancing
- API gateways
- Scalability
- Reliability
- Distributed systems
- CAP theorem
- Common architectural patterns

**Desired capability:** Be able to take a requirement such as:

> “Build a standalone IAM platform for hundreds of applications.”

and turn it into:

```text
Requirements
      ↓
Constraints
      ↓
Architecture
      ↓
Components
      ↓
Data model
      ↓
APIs
      ↓
Security
      ↓
Scalability
      ↓
Deployment
      ↓
Observability
      ↓
Migration
```

### Goal 8 — Learn production engineering

A platform isn't complete when the code works.

**Observability**

- Logs
- Metrics
- Traces
- Dashboards
- Alerts

**Reliability**

- Health checks
- Timeouts
- Retries
- Circuit breakers
- Graceful shutdown
- Backups
- Disaster recovery

**Security**

- TLS
- Secrets
- Key management
- OWASP risks
- API security
- Token security
- Access control
- Tenant isolation

**Desired capability:** Be able to answer:

> “What happens when this component fails?”

for every major component in the architecture.

### Goal 9 — Understand migration and modernization

Learn:

- Legacy/new-system coexistence
- Migration strategies
- Backward compatibility
- Data migration
- User migration
- Dual-run approaches
- Phased migration
- Cutover
- Rollback
- Strangler pattern
- Dependency migration

**Desired capability:** Understand not only:

> “How do we build the new IAM?”

but:

> “How do we move existing applications from the old IAM to the new IAM without breaking them?”

### Goal 10 — Automate SSO onboarding

This is a potential specialization/differentiator.

Instead of:

```text
Customer request
      ↓
Engineer manually configures SSO
      ↓
Engineer tests
      ↓
Ticket closed
```

work toward:

```text
Administrator
      ↓
Developer Portal
      ↓
Configuration API
      ↓
Validation
      ↓
Automation
      ↓
IdP configuration
      ↓
Kong configuration
      ↓
Application configuration
      ↓
Automated testing
      ↓
Audit
```

**Desired capability:** Transform repetitive IAM/SSO operational work into a **self-service or highly automated platform capability**.

---

## 5. Learning Philosophy

### Principle 1 — Build, don't just watch

For every major concept:

```text
Learn
 ↓
Implement
 ↓
Break it
 ↓
Debug it
 ↓
Document it
```

Target approximately:

**30% theory / 70% hands-on implementation**

### Principle 2 — One integrated project

Do not create ten unrelated tutorial projects.

Build one continuously evolving project.

The project will become the practical representation of the roadmap.

### Principle 3 — Standards before vendors

Learn:

- OAuth
- OIDC
- SAML
- JWT
- SCIM
- REST
- HTTP

before becoming dependent on a particular vendor.

Then learn:

- Spring Security
- Kong
- AWS

as implementations/platforms of those concepts.

### Principle 4 — Architecture before implementation

Before writing a major feature:

```text
Requirement
 ↓
Specification
 ↓
Architecture
 ↓
API Contract
 ↓
Implementation
 ↓
Testing
```

Spec-Driven Development and Spec Kit can be used to reinforce this approach.

### Principle 5 — Don't chase technology breadth unnecessarily

The objective is not:

> “Know every modern technology.”

The objective is:

> “Understand the technologies necessary to own an IAM platform end-to-end.”

---

## 6. 30-Day Learning Structure

### Week 1 — IAM Foundation

**Topics**

- Authentication vs authorization
- OAuth 2.0
- Authorization Code
- PKCE
- JWT
- JWK/JWKS
- OIDC
- SAML
- SCIM
- RBAC
- Claims
- Scopes
- Sessions

**Output:** A working understanding of enterprise authentication flows.

### Week 2 — Backend + System Design

**Topics**

- Spring Security
- Spring Boot
- REST
- OpenAPI
- PostgreSQL
- Data modeling
- Indexing
- Transactions
- API versioning
- Backward compatibility
- System-design fundamentals

**Hello Interview focus**

- Delivery framework
- Networking
- API design
- Data modeling
- Database indexing
- Caching
- CAP
- API gateways

**Output:** A functional IAM backend with users, organizations, roles and permissions.

### Week 3 — Kong + Cloud

**Topics**

- Kong Gateway
- Services
- Routes
- Consumers
- Plugins
- OIDC
- JWT
- Custom Kong plugins
- Docker
- AWS
- ALB
- VPC
- RDS
- Parameter Store
- Secrets Manager
- IAM

**Output:**

```text
Client
 ↓
Kong
 ↓
IAM Backend
 ↓
PostgreSQL
```

running in a containerized/cloud-oriented environment.

### Week 4 — Production + Platform Engineering

**Topics**

- Terraform
- CI/CD
- GitHub Actions
- Observability
- Security
- Reliability
- Rate limiting
- Notifications
- Metrics
- Migration architecture
- Developer Portal concepts
- SSO automation
- System architecture documentation

**Output:** A production-style miniature IAM platform.

---

## 7. Capstone Project

### Project Name

**Standalone IAM Platform Lab**

The name is intentionally generic and must not contain proprietary company code, configuration, secrets, customer information, or internal implementation details.

### Target Architecture

```text
                         ┌─────────────────────┐
                         │   Developer Portal   │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │ Configuration API   │
                         └──────────┬──────────┘
                                    │
                                    ▼
                         ┌─────────────────────┐
                         │    Kong Gateway     │
                         │                     │
                         │ OIDC / JWT / Plugin │
                         └──────────┬──────────┘
                                    │
                  ┌─────────────────┼─────────────────┐
                  ▼                 ▼                 ▼
             Auth Service      User Service      OAuth Service
                  │                 │                 │
                  └─────────────────┼─────────────────┘
                                    ▼
                              PostgreSQL
                                    │
                                    ▼
                                    IdP


             ┌──────────────────────────────────────┐
             │                 AWS                  │
             │                                      │
             │ VPC / ALB / Compute / RDS           │
             │ Parameter Store / Secrets / KMS      │
             └──────────────────────────────────────┘

             ┌──────────────────────────────────────┐
             │            Platform Operations       │
             │                                      │
             │ CI/CD / Logs / Metrics / Tracing     │
             │ Alerts / Security / Backups          │
             └──────────────────────────────────────┘
```

### Capstone Features

The project should progressively implement:

1. User management
2. Authentication
3. OAuth 2.0
4. OIDC
5. JWT validation
6. RBAC
7. Multi-tenancy
8. SAML understanding/integration
9. SCIM understanding/integration
10. Kong
11. Custom Kong plugin
12. Docker
13. AWS
14. Terraform
15. CI/CD
16. Observability
17. Security
18. SSO automation

---

## 8. Success Criteria

At the end of 30 days, success does **not** mean:

> “I finished every tutorial.”

Success means you can independently explain and discuss:

### IAM

- OAuth 2.0
- OIDC
- SAML
- Access token vs ID token
- JWT validation
- PKCE
- RBAC
- SCIM
- Identity lifecycle

### Backend

- Spring Security
- REST APIs
- PostgreSQL
- Transactions
- Authorization
- API contracts

### Gateway

- Why an API gateway exists
- Kong Services
- Routes
- Plugins
- OIDC
- Custom plugins

### Cloud

- VPC
- ALB
- Compute
- RDS
- IAM
- Secrets
- Configuration

### Architecture

- Scalability
- Reliability
- Security
- Observability
- Migration
- Backward compatibility

### Engineering

- Docker
- Terraform
- CI/CD
- Testing
- Debugging

---

## 9. Final Capability Target

The ultimate target is to be able to participate in a technical discussion like this:

> **Requirement:**  
> “We need an independent IAM platform that supports existing applications and future products.”

You should be able to respond by discussing:

```text
1. Requirements
       ↓
2. Identity model
       ↓
3. IdP strategy
       ↓
4. OAuth/OIDC/SAML
       ↓
5. API contracts
       ↓
6. Kong architecture
       ↓
7. IAM services
       ↓
8. Data model
       ↓
9. Authorization
       ↓
10. AWS architecture
       ↓
11. Security
       ↓
12. Observability
       ↓
13. CI/CD
       ↓
14. Migration
       ↓
15. Operations
```

You don't need to have every answer.

You need to know **what questions need to be answered, what trade-offs exist, and where to find the technical details.**

---

## 10. Priority Matrix

| Area | Priority | Target Depth |
|---|---|---|
| OAuth 2.0 | 🔴 Critical | Deep |
| OIDC | 🔴 Critical | Deep |
| JWT/JWKS | 🔴 Critical | Deep |
| Spring Security | 🔴 Critical | Deep |
| API Design | 🔴 Critical | Deep |
| Kong | 🔴 Critical | Deep |
| Authorization/RBAC | 🔴 Critical | Deep |
| System Design | 🔴 Critical | Deep |
| SAML | 🟠 High | Strong |
| AWS | 🟠 High | Practical |
| PostgreSQL | 🟠 High | Practical |
| Docker | 🟠 High | Practical |
| Custom Kong Plugins | 🟠 High | Practical |
| Terraform | 🟠 High | Basic/Practical |
| CI/CD | 🟠 High | Practical |
| Observability | 🟠 High | Practical |
| Security | 🟠 High | Practical |
| SCIM | 🟡 Medium | Conceptual/Practical |
| Distributed Systems | 🟡 Medium | Conceptual |
| Kubernetes | 🟢 Low | Conceptual |
| Kafka | 🟢 Low | Conceptual |
| Redis | 🟢 Low | Conceptual |

---

## 11. What this roadmap is NOT

This is **not**:

- A certification roadmap
- A list of technologies to memorize
- A generic software-engineering curriculum
- A promise that these exact technologies will be selected for the standalone company
- A replacement for actual architecture decisions made by the future engineering organization

It is a **preparation strategy** designed around the technical direction currently visible to us.

---

## 12. End State

At the end of this preparation, the intended progression is:

```text
Current capability
       │
       ▼
IAM Support / Troubleshooting
       │
       ▼
IAM + Backend Engineering
       │
       ▼
IAM Platform Engineering
       │
       ▼
IAM Architecture / Platform Ownership
```

The long-term objective is not simply to become better at handling IAM tickets.

It is to become someone who can say:

> **“Give me the IAM/platform problem. I can understand the requirements, design the architecture, implement the backend, integrate identity, build the gateway layer, automate the workflow, deploy it, secure it, monitor it, and help migrate consumers to it.”**

That is the capability this 30-day roadmap is designed to build.

---

## 13. Operating Rule for the Next 30 Days

**Learn → Build → Break → Debug → Document → Explain.**

For every major topic, follow this cycle.

Maintain a running list of:

- Concepts learned
- Things implemented
- Questions
- Architecture decisions
- Mistakes
- Unknowns
- System-design questions
- Areas requiring deeper study

This document is the **master plan**. Each subsequent learning session should focus on one section without losing sight of the overall objective.
