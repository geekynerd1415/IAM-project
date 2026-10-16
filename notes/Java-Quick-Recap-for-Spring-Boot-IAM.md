# Java Quick Recap for Spring Boot & IAM Backend Development

> **Purpose:** A focused Java refresher before starting Spring Boot.
>
> This covers the Java language and core APIs you should be comfortable with for backend development, Spring Boot, REST APIs, security, databases, OAuth/OIDC, and IAM.
>
> **Java baseline:** Java 21 LTS

---

## 1. Basic Java Program

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

**Output**
```text
Hello, Java!
```

Know: class, `main`, statements, `System.out.println()`.

---

## 2. Variables

```java
int age = 25;
String name = "Alice";
double salary = 50000.50;
boolean active = true;

System.out.println(age);
System.out.println(name);
System.out.println(salary);
System.out.println(active);
```

**Output**
```text
25
Alice
50000.5
true
```

---

## 3. Primitive Data Types

| Type | Example |
|---|---|
| `byte` | `byte x = 10;` |
| `short` | `short x = 1000;` |
| `int` | `int x = 100000;` |
| `long` | `long x = 100000L;` |
| `float` | `float x = 10.5f;` |
| `double` | `double x = 10.5;` |
| `char` | `char c = 'A';` |
| `boolean` | `boolean ok = true;` |

Know the difference between primitive and reference types.

```java
int age = 25;
String name = "Alice";
```

---

## 4. Operators

### Arithmetic

```java
int a = 10;
int b = 3;

System.out.println(a + b);
System.out.println(a - b);
System.out.println(a * b);
System.out.println(a / b);
System.out.println(a % b);
```

**Output**
```text
13
7
30
3
1
```

### Comparison

```java
System.out.println(10 == 10);
System.out.println(10 != 5);
System.out.println(10 > 5);
System.out.println(10 <= 10);
```

### Logical

```java
boolean authenticated = true;
boolean active = true;

System.out.println(authenticated && active);
System.out.println(authenticated || active);
System.out.println(!authenticated);
```

**Output**
```text
true
true
false
```

Know: `+ - * / %`, `== != > < >= <=`, `&& || !`.

---

## 5. Type Casting

### Widening

```java
int number = 10;
double value = number;
System.out.println(value);
```

**Output**
```text
10.0
```

### Narrowing

```java
double value = 10.99;
int number = (int) value;
System.out.println(number);
```

**Output**
```text
10
```

---

## 6. Strings

`String` is a class, not a primitive.

```java
String name = "Alice";

System.out.println(name.length());
System.out.println(name.toUpperCase());
System.out.println(name.toLowerCase());
System.out.println(name.contains("lic"));
```

**Output**
```text
5
ALICE
alice
true
```

Know common methods: `length`, `isEmpty`, `isBlank`, `contains`, `startsWith`, `endsWith`, `substring`, `replace`, `toUpperCase`, `toLowerCase`.

### String comparison

Do not normally compare String content with `==`.

```java
if ("Alice".equals(name)) {
    System.out.println("Match");
}
```

Use `.equals()` for content comparison.

---

## 7. Conditions

```java
int age = 25;

if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

**Output**
```text
Adult
```

Also know `else if` and the ternary operator:

```java
String result = age >= 18 ? "Adult" : "Minor";
```

---

## 8. switch

```java
String role = "ADMIN";

switch (role) {
    case "ADMIN":
        System.out.println("Full access");
        break;
    case "USER":
        System.out.println("Normal access");
        break;
    default:
        System.out.println("Unknown role");
}
```

**Output**
```text
Full access
```

### Modern switch expression

```java
String permission = switch (role) {
    case "ADMIN" -> "FULL";
    case "USER" -> "LIMITED";
    default -> "NONE";
};
```

---

## 9. Loops

### for

```java
for (int i = 1; i <= 3; i++) {
    System.out.println(i);
}
```

**Output**
```text
1
2
3
```

### Enhanced for

```java
String[] roles = {"ADMIN", "USER", "GUEST"};

for (String role : roles) {
    System.out.println(role);
}
```

### while

```java
int count = 1;

while (count <= 3) {
    System.out.println(count);
    count++;
}
```

Also know `do-while`, `break`, `continue`, `++`, and `--`.

---

## 10. Arrays

```java
int[] numbers = {10, 20, 30};

System.out.println(numbers[0]);
System.out.println(numbers.length);
```

**Output**
```text
10
3
```

Indexes start at `0`.

```java
int[] numbers = new int[3];
numbers[0] = 10;
numbers[1] = 20;
numbers[2] = 30;
```

---

## 11. Methods

```java
public static int add(int a, int b) {
    return a + b;
}

int result = add(10, 20);
System.out.println(result);
```

**Output**
```text
30
```

Know:
- parameters
- return type
- `return`
- `void`
- method overloading

### Overloading

```java
static int add(int a, int b) {
    return a + b;
}

static int add(int a, int b, int c) {
    return a + b + c;
}
```

---

## 12. Classes and Objects

```java
class User {
    String name;
    int age;
}

User user = new User();
user.name = "Alice";
user.age = 25;

System.out.println(user.name);
System.out.println(user.age);
```

**Output**
```text
Alice
25
```

Mental model:

```text
Class  = blueprint
Object = instance
```

---

## 13. Constructors and `this`

```java
class User {
    String name;
    int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

User user = new User("Alice", 25);
```

`this` refers to the current object.

---

## 14. Access Modifiers

| Modifier | Meaning |
|---|---|
| `public` | Broadly accessible |
| `private` | Only inside the class |
| `protected` | Class/package/subclasses |
| no modifier | Package-private |

For backend code, understand why fields are commonly `private`.

---

## 15. Encapsulation

Prefer controlled access:

```java
class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

Encapsulation means keeping object state controlled behind an interface.

---

## 16. `static`

Belongs to the class rather than an individual object.

```java
class MathUtil {
    static int add(int a, int b) {
        return a + b;
    }
}

System.out.println(MathUtil.add(2, 3));
```

**Output**
```text
5
```

---

## 17. `final`

Prevents reassignment:

```java
final int MAX_RETRIES = 3;
```

This is invalid:

```java
MAX_RETRIES = 5;
```

---

# Object-Oriented Programming

## 18. The Four Core OOP Concepts

```text
Encapsulation
Inheritance
Polymorphism
Abstraction
```

---

## 19. Inheritance

```java
class Animal {
    void speak() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Woof");
    }
}

Dog dog = new Dog();
dog.speak();
dog.bark();
```

**Output**
```text
Animal sound
Woof
```

---

## 20. Method Overriding

```java
class Animal {
    void speak() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Woof");
    }
}

Animal animal = new Dog();
animal.speak();
```

**Output**
```text
Woof
```

This demonstrates runtime polymorphism.

---

## 21. Polymorphism

```java
Animal animal = new Dog();
```

Variable type:

```text
Animal
```

Actual object:

```text
Dog
```

The overridden implementation runs.

This is important for Spring interfaces and dependency injection.

---

## 22. Abstract Classes

```java
abstract class Animal {
    abstract void speak();

    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Woof");
    }
}
```

---

## 23. Interfaces

An interface defines a contract.

```java
interface PaymentService {
    void pay();
}

class CardPayment implements PaymentService {
    @Override
    public void pay() {
        System.out.println("Paid by card");
    }
}

PaymentService service = new CardPayment();
service.pay();
```

**Output**
```text
Paid by card
```

**Very important for Spring:** understand interface vs implementation.

---

## 24. Enum

```java
enum Role {
    ADMIN,
    USER,
    GUEST
}

Role role = Role.ADMIN;
System.out.println(role);
```

**Output**
```text
ADMIN
```

Useful for fixed states such as roles and statuses.

---

## 25. Packages and Imports

Package:

```java
package com.example.iam.user;
```

Import:

```java
import java.util.ArrayList;
import java.util.List;
```

Typical backend package structure:

```text
com.example.iam
├── controller
├── service
├── repository
├── model
├── dto
└── config
```

---

# Exceptions

## 26. Exceptions

```java
int result = 10 / 0;
```

Produces an `ArithmeticException`.

### try/catch

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

**Output**
```text
Cannot divide by zero
```

### finally

```java
try {
    System.out.println("Try");
} finally {
    System.out.println("Finally");
}
```

**Output**
```text
Try
Finally
```

### throw

```java
throw new IllegalArgumentException("Invalid age");
```

### throws

```java
public void readFile() throws IOException {
    // ...
}
```

Know the distinction between checked exceptions such as `IOException` and unchecked exceptions such as `NullPointerException` and `IllegalArgumentException`.

---

# Collections

## 27. Collections Overview

Know:

```text
List
Set
Map
Queue
Deque
```

---

## 28. List

Ordered and allows duplicates.

```java
List<String> names = new ArrayList<>();

names.add("Alice");
names.add("Bob");
names.add("Alice");

System.out.println(names);
System.out.println(names.get(0));
```

**Output**
```text
[Alice, Bob, Alice]
Alice
```

---

## 29. Set

Does not allow duplicate elements.

```java
Set<String> roles = new HashSet<>();

roles.add("ADMIN");
roles.add("USER");
roles.add("ADMIN");

System.out.println(roles);
```

The exact `HashSet` order is not guaranteed, but the set contains only `ADMIN` and `USER`.

---

## 30. Map

Key/value collection.

```java
Map<String, String> users = new HashMap<>();

users.put("1001", "Alice");
users.put("1002", "Bob");

System.out.println(users.get("1001"));
```

**Output**
```text
Alice
```

Know:

```text
put
get
remove
containsKey
containsValue
size
isEmpty
```

---

## 31. Queue

```java
Queue<String> queue = new LinkedList<>();

queue.add("A");
queue.add("B");

System.out.println(queue.poll());
System.out.println(queue.poll());
```

**Output**
```text
A
B
```

---

## 32. Deque / Stack Concept

```java
Deque<String> stack = new ArrayDeque<>();

stack.push("A");
stack.push("B");

System.out.println(stack.pop());
```

**Output**
```text
B
```

---

## 33. Generics

Generics provide type safety.

```java
List<String> names = new ArrayList<>();
names.add("Alice");
```

This is invalid:

```java
names.add(123);
```

Generic class:

```java
class Box<T> {
    private T value;

    Box(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }
}

Box<String> box = new Box<>("Hello");
System.out.println(box.getValue());
```

**Output**
```text
Hello
```

---

## 34. Wrapper Classes

| Primitive | Wrapper |
|---|---|
| `int` | `Integer` |
| `long` | `Long` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |

Collections use wrappers:

```java
List<Integer> numbers = new ArrayList<>();
```

not `List<int>`.

---

## 35. Autoboxing / Unboxing

```java
Integer number = 10;  // autoboxing
int value = number;   // unboxing
```

---

## 36. Comparable and Comparator

`Comparable` defines natural ordering.

`Comparator` defines custom/external ordering.

```java
List<String> names = new ArrayList<>(
        List.of("Charlie", "Alice", "Bob")
);

names.sort(Comparator.naturalOrder());

System.out.println(names);
```

**Output**
```text
[Alice, Bob, Charlie]
```

---

# Modern Java

## 37. Lambda Expressions

```java
List<String> names = List.of("Alice", "Bob");

names.forEach(name -> System.out.println(name));
```

**Output**
```text
Alice
Bob
```

Syntax:

```text
(parameters) -> expression
```

---

## 38. Functional Interfaces

```java
@FunctionalInterface
interface Greeting {
    void sayHello(String name);
}

Greeting greeting =
        name -> System.out.println("Hello " + name);

greeting.sayHello("Alice");
```

**Output**
```text
Hello Alice
```

---

## 39. Stream API

Streams process collections declaratively.

```java
List<String> names = List.of("Alice", "Bob", "Alex");

names.stream()
        .filter(name -> name.startsWith("A"))
        .forEach(System.out::println);
```

**Output**
```text
Alice
Alex
```

Know these operations:

```text
filter
map
sorted
distinct
limit
collect
toList
forEach
```

---

## 40. Stream `map`

```java
List<String> names = List.of("alice", "bob");

List<String> upper = names.stream()
        .map(String::toUpperCase)
        .toList();

System.out.println(upper);
```

**Output**
```text
[ALICE, BOB]
```

Mental model:

```text
Input
  ↓
filter
  ↓
map
  ↓
collect/toList
  ↓
Output
```

---

## 41. Optional

Represents a value that may or may not exist.

```java
Optional<String> name = Optional.ofNullable(null);

String result = name.orElse("Unknown");

System.out.println(result);
```

**Output**
```text
Unknown
```

Know:

```text
of
ofNullable
isPresent
ifPresent
orElse
orElseGet
orElseThrow
map
```

Do not treat `Optional` as a universal replacement for `null`.

---

## 42. `null`

```java
String name = null;
```

Calling:

```java
name.length();
```

can cause:

```text
NullPointerException
```

Safe pattern:

```java
if (name != null) {
    System.out.println(name.length());
}
```

Often safer:

```java
if ("ADMIN".equals(role)) {
}
```

---

## 43. Date and Time API

Prefer `java.time`.

```java
LocalDate date = LocalDate.of(2026, 9, 25);
System.out.println(date);
```

**Output**
```text
2026-09-25
```

Know:

```text
LocalDate
LocalDateTime
Instant
ZonedDateTime
Duration
Period
```

For distributed systems, understand timestamps and time zones.

---

## 44. Records

Records are useful immutable data carriers.

```java
record UserResponse(Long id, String name) {
}

UserResponse user = new UserResponse(1L, "Alice");

System.out.println(user.id());
System.out.println(user.name());
```

**Output**
```text
1
Alice
```

Records will be useful for API request/response DTOs.

---

## 45. `equals()` and `hashCode()`

These are important for object equality and collections such as `HashMap` and `HashSet`.

Conceptually:

```java
@Override
public boolean equals(Object o) {
    // compare logical identity/state
}

@Override
public int hashCode() {
    // consistent with equals
}
```

Rule:

> If two objects are equal according to `equals()`, they must have the same `hashCode()`.

---

## 46. `toString()`

Useful for debugging/logging.

```java
@Override
public String toString() {
    return "User{id=" + id + "}";
}
```

---

## 47. Immutability

An immutable object cannot change state after creation.

```java
public final class User {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

Immutability is useful for safer and easier-to-reason-about application code.

---

# Backend Foundations

## 48. Dependency Injection Concept

Problem:

```java
class UserService {
    private UserRepository repository = new UserRepository();
}
```

`UserService` creates its own dependency.

Better:

```java
class UserService {
    private final UserRepository repository;

    UserService(UserRepository repository) {
        this.repository = repository;
    }
}
```

Something else supplies the dependency.

Spring will eventually manage this for us.

Mental model:

```text
Without DI:

UserService
    |
    └── creates UserRepository


With DI:

UserRepository
       ↓
   injected into
       ↓
UserService
```

This is one of the most important Spring concepts.

---

## 49. Annotations

Annotations provide metadata/instructions to frameworks and tools.

Java:

```java
@Override
```

Spring examples you will learn later:

```java
@RestController
@GetMapping
@PostMapping
@Service
@Repository
@Configuration
@Bean
```

Do not try to memorize Spring annotations yet.

---

## 50. JSON

JSON is not a Java feature, but is fundamental to REST APIs.

```json
{
  "id": 1,
  "name": "Alice",
  "active": true
}
```

A Java record can represent the data:

```java
record UserResponse(
        long id,
        String name,
        boolean active
) {}
```

---

## 51. Serialization / Deserialization

Conceptually:

```text
Java object
    ↓
serialization
    ↓
JSON
```

and:

```text
JSON
    ↓
deserialization
    ↓
Java object
```

Spring Boot will later perform this conversion for HTTP APIs.

---

## 52. Maven Basics

Maven manages Java builds and dependencies.

Typical project:

```text
project/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   └── resources/
    └── test/
        └── java/
```

Common commands:

```bash
mvn clean
mvn compile
mvn test
mvn package
mvn spring-boot:run
```

You don't need to memorize every Maven command.

---

## 53. `pom.xml`

Dependencies are declared in `pom.xml`.

Example:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Maven downloads and manages the required dependency and its transitive dependencies.

---

## 54. Testing Basics

A unit test verifies behavior.

```java
@Test
void addsTwoNumbers() {
    assertEquals(5, add(2, 3));
}
```

Know the concepts of:

```text
JUnit
assertEquals
assertTrue
assertFalse
assertNull
assertNotNull
assertThrows
```

We will later use Spring Boot testing.

---

## 55. Debugging

Know how to:

- set a breakpoint
- start the debugger
- step over
- step into
- step out
- inspect variables
- read the call stack
- inspect exceptions

For backend development, debugging skills are more valuable than memorizing obscure Java syntax.

---

## 56. Logging

Backend applications normally use logging rather than `System.out.println()`.

Conceptually:

```java
logger.info("User created: {}", userId);
```

Common levels:

```text
TRACE
DEBUG
INFO
WARN
ERROR
```

Spring Boot will provide logging infrastructure.

---

# JVM Fundamentals

## 57. JDK, JVM, Bytecode

Mental model:

```text
Java source
    ↓
javac
    ↓
.class bytecode
    ↓
JVM
    ↓
Machine execution
```

Know these terms:

```text
JDK
JVM
Bytecode
Heap
Stack
Garbage Collection
```

---

## 58. Stack vs Heap

Simplified model:

```text
Stack
- method calls
- local variables/references
- per-thread

Heap
- objects
- shared application memory
- managed by garbage collection
```

Example:

```java
User user = new User();
```

The object is allocated on the heap.

---

## 59. Garbage Collection

Java automatically manages memory for objects that are no longer reachable.

Conceptually:

```text
Create object
    ↓
Object reachable
    ↓
Object no longer reachable
    ↓
Garbage collector may reclaim memory
```

---

## 60. Concurrency Basics

Know the concepts:

```text
Thread
Runnable
Executor
synchronized
volatile
Lock
CompletableFuture
```

Basic example:

```java
Thread thread = new Thread(() -> {
    System.out.println("Running");
});

thread.start();
```

**Output**
```text
Running
```

Advanced concurrency is not a first priority for our IAM lab.

---

# Security-Relevant Java Habits

## 61. Never Hard-Code Secrets

Bad:

```java
String password = "Admin123";
```

Use configuration/secrets management instead.

---

## 62. Never Log Secrets

Bad:

```java
logger.info("Password: {}", password);
```

Never casually log:

```text
passwords
access tokens
refresh tokens
API keys
client secrets
session identifiers
```

---

## 63. Validate Input

Never blindly trust request data.

Later our APIs will validate:

```text
required fields
lengths
formats
allowed values
authorization
```

---

## 64. Don't Expose Sensitive Data

For example, an API response should not casually return:

```json
{
  "passwordHash": "..."
}
```

Even if the hash is not the original password, it is sensitive internal data.

---

# Backend Architecture Connection

## 65. Typical Spring Boot Layers

A common project structure:

```text
controller/
    UserController

service/
    UserService

repository/
    UserRepository

model/
    User

dto/
    CreateUserRequest
    UserResponse

config/
    SecurityConfig
```

Responsibilities:

| Layer | Responsibility |
|---|---|
| Controller | HTTP/API boundary |
| Service | Business logic |
| Repository | Data access |
| Model/Entity | Domain/database representation |
| DTO | API data contract |
| Config | Application configuration |

---

## 66. HTTP + Java Mental Model

Eventually our API will look like:

```text
HTTP Request
       ↓
Spring MVC
       ↓
Controller
       ↓
Service
       ↓
Repository
       ↓
Database
```

Java implements the application logic at these layers.

---

## 67. Example Future IAM Endpoint

Eventually code may look like:

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(
            @RequestBody CreateUserRequest request) {

        return userService.createUser(request);
    }
}
```

To understand this, you need to recognize:

```text
class
constructor
private
final
reference types
method
return type
object
annotation
dependency injection
JSON → Java object
Java object → JSON
```

That is exactly why this Java recap focuses on these topics.

---

# 68. What You Should Know Well Before Spring Boot

## Must know

```text
1. Variables and types
2. Strings
3. Conditions
4. Loops
5. Methods
6. Classes and objects
7. Constructors
8. this
9. Access modifiers
10. Encapsulation
11. Interfaces
12. Inheritance
13. Polymorphism
14. Exceptions
15. List
16. Set
17. Map
18. Generics
19. Lambdas
20. Streams
21. Optional
22. Records
23. Packages/imports
24. Dependency injection concept
25. Maven basics
```

## Know conceptually

```text
26. JVM
27. Heap / Stack
28. Garbage Collection
29. Concurrency
30. Serialization / Deserialization
31. Logging
32. Testing
33. Environment variables
```

---

# 69. What You Do NOT Need to Master Yet

Do not spend weeks on:

```text
Advanced multithreading
JVM tuning
Custom garbage collectors
JNI
Advanced reflection
Bytecode manipulation
Advanced networking
Unsafe APIs
Advanced performance optimization
Complex design patterns
```

We can learn these only if the project requires them.

---

# 70. Recommended Recap Order

Use this order rather than trying to memorize the entire document:

```text
Phase 1 — Language
    ↓
Variables
Types
Operators
Strings
Conditions
Loops
Methods

Phase 2 — OOP
    ↓
Classes
Objects
Constructors
this
Encapsulation
Interfaces
Inheritance
Polymorphism
Abstract classes

Phase 3 — Core Java
    ↓
Exceptions
Collections
Generics
Enums
Optional
Date/Time

Phase 4 — Modern Java
    ↓
Lambdas
Functional interfaces
Streams
Records

Phase 5 — Backend foundations
    ↓
Packages
Annotations
JSON
Serialization
Dependency Injection
Maven
Testing
Logging

Phase 6 — Runtime
    ↓
JDK/JVM
Heap/Stack
Garbage Collection
Concurrency basics
```

---

# 71. Practical Checkpoint

Before starting Spring Boot, you should be able to explain:

### Language

- What is the difference between `int` and `Integer`?
- What is the difference between `==` and `.equals()`?
- What is `null`?
- What does `final` mean?
- What is a method?
- What is method overloading?

### OOP

- What is a class?
- What is an object?
- What is a constructor?
- What is encapsulation?
- What is inheritance?
- What is polymorphism?
- What is an interface?
- Why use an interface?

### Collections

- When would you use `List`?
- When would you use `Set`?
- When would you use `Map`?
- What does `List<String>` mean?

### Modern Java

- What is a lambda?
- What is a stream?
- What does `filter()` do?
- What does `map()` do?
- What is `Optional`?
- What is a record?

### Exceptions

- What is an exception?
- What do `try`, `catch`, and `finally` do?
- What is the difference between checked and unchecked exceptions?

### Backend

- What is JSON?
- What is serialization?
- What is deserialization?
- What is dependency injection?
- What is Maven?
- What is `pom.xml`?

If these concepts are comfortable, you are ready to begin Spring Boot.

---

# 72. Final Mental Model

Carry this into Spring Boot:

```text
                    CLIENT
                      │
                      │ HTTP
                      ▼
              ┌───────────────┐
              │   CONTROLLER  │
              │               │
              │ HTTP boundary │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │    SERVICE    │
              │               │
              │ Business      │
              │ logic         │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │  REPOSITORY   │
              │               │
              │ Data access   │
              └───────┬───────┘
                      │
                      ▼
                  DATABASE
```

The relationship is:

```text
HTTP
  +
Java
  +
Spring Boot
  +
Database
  +
Security
  =
Backend/IAM Platform
```

---

# Final Readiness Checklist

- [ ] I can write a basic Java class.
- [ ] I understand primitive vs reference types.
- [ ] I can write methods with parameters and return values.
- [ ] I understand classes and objects.
- [ ] I understand constructors and `this`.
- [ ] I understand encapsulation.
- [ ] I understand interfaces.
- [ ] I understand inheritance and polymorphism.
- [ ] I can use `List`, `Set`, and `Map`.
- [ ] I understand generics.
- [ ] I can read lambda expressions.
- [ ] I can read basic Stream API code.
- [ ] I understand exceptions.
- [ ] I understand `Optional`.
- [ ] I understand records.
- [ ] I understand JSON ↔ Java object conversion conceptually.
- [ ] I understand dependency injection conceptually.
- [ ] I understand basic Maven commands.
- [ ] I can read a `pom.xml`.
- [ ] I know the basics of debugging and logging.

> **Important:** You do not need expert-level knowledge of every topic before starting Spring Boot. The goal is to remove Java syntax and core-language friction so that your attention can stay on Spring, HTTP, API architecture, and eventually IAM.
