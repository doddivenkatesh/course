1.DEPENDENCY INJECTION

Dependency Injection is a design pattern used to achieve loose coupling between classes by injecting dependencies (like services, repositories, etc.) from the outside, rather than creating them inside the class.

Benefits of Dependency Injection:
✔ Loose coupling

✔ Easy to test (mock dependencies)

✔ Cleaner code, separation of concerns

✔ Better for large, scalable applications


Constructor Injection – Recommended; injected via constructor

Setter Injection – Injected using a setter method

Field Injection – Uses @Autowired directly on fields (less testable)



2.What is @Autowired in Spring?
@Autowired is a Spring annotation used to automatically inject dependencies into a class from the Spring container.


Scans your project for components (@Component, @Service, @Repository, etc.)

Finds the right bean (CourseService in this case)

Injects it wherever @Autowired is used


INVERSION OF CONTROL

Inversion of Control (IoC) is a design principle where the control of creating objects and managing their dependencies is inverted—instead of the class creating its own dependencies, a container or framework (like Spring) provides them.



Concept	Meaning
IoC	Principle: Control is inverted, framework manages object creation
DI	Pattern: Dependencies are passed from outside
@Autowired	Annotation: Tells Spring to inject dependencies automatically


🔁 What is @Transactional in Spring (JPA + Hibernate)?
@Transactional is an annotation provided by Spring to manage database transactions automatically. It ensures that a block of code (usually a method) is run within a database transaction — meaning all database operations inside it will be:

✅ Committed if everything succeeds

❌ Rolled back if any exception occurs

 Where to Use @Transactional
On service layer methods (recommended)

Where you perform multiple DB operations that must succeed together

Point	Description
Rollback on RuntimeException	By default, only unchecked exceptions (like RuntimeException) trigger rollback
Checked Exception?	Use @Transactional(rollbackFor = Exception.class) if you want to rollback on checked exceptions too
Propagation	Controls how transactions behave when calling another transactional method (e.g., REQUIRED, REQUIRES_NEW)
Isolation	Controls how transaction is isolated from others (e.g., READ_COMMITTED, SERIALIZABLE)

 Behind the Scenes (Hibernate + Spring)
Spring creates a proxy for your service class.

When a method marked @Transactional is called, Spring:

Opens a transaction (begin)

Runs the method

If everything is fine → commit

If any exception → rollback

Hibernate participates as the ORM provider to map objects to the database.


constructor 

A constructor is a special method in a class that is called automatically when an object is created. It’s used to initialize the object.

Type	Description
Default Constructor	No arguments; provided automatically if no constructor is defined
Parameterized Constructor	Accepts arguments to initialize variables
No-arg Constructor	Explicit constructor with no parameters (used in JPA, Hibernate, etc.)

🛑 Key Points:
Constructor name = class name

No return type (not even void)

Called only once per object — when it's created

Can be overloaded (multiple constructors with different parameters)



🧩 How Kafka Works (Simple Terms)
Producer – Sends (publishes) data to a Kafka topic

Topic – A category/feed name where messages are stored

Broker – Kafka server that stores messages

Consumer – Reads data from the topic

Zookeeper (or KRaft) – Manages Kafka cluster metadata (Kafka now supports Zookeeper-less mode too)

📦 Use Cases
Use Case	Example
Log aggregation	Centralize logs from different services
Real-time analytics	Streaming data to dashboards
Order processing	E-commerce orders processed in real time
Activity tracking	Track user actions (clicks, page views, etc.)
Data pipeline	Move data between systems (DB to ML system, etc.)

🔁 Kafka vs REST API
Kafka	REST API
Async, event-based	Sync, request-response
Scalable for high-volume	Suitable for low/moderate traffic
Real-time stream	Static calls
Durable messaging	No message queue by default

🛠 Example Flow:
text
Copy
Edit
User clicks "Buy" ➜ Event sent to Kafka topic ➜ Order Service consumes the event ➜ Payment Service is notified ➜ Inventory updates
Would you like a diagram, Kafka setup guide, or how to use Kafka with Spring Boot or Node.js?