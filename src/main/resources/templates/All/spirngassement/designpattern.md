✅ 1. Singleton Pattern
Usage: All Spring beans by default are Singleton-scoped.

Purpose: Ensures a single shared instance of a bean across the application.

Example:

java
Copy
Edit
@Service
public class UserService {
    // Only one instance created and managed by Spring
}
✅ 2. Factory Pattern
Usage: Spring uses factories to create and manage beans.

Framework: BeanFactory, ApplicationContext

Purpose: Decouples object creation from the application logic.

Example:

java
Copy
Edit
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
UserService userService = context.getBean(UserService.class);

✅ 3. Dependency Injection (DI) / Inversion of Control (IoC)
Core pattern in Spring Framework.

Usage: Dependencies are injected via constructor, setter, or field.

Purpose: Promotes loose coupling.

Example:

java
Copy
Edit
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
✅ 4. Proxy Pattern
Usage: Used extensively in AOP (Aspect-Oriented Programming).

Purpose: Adds extra behavior (e.g., logging, transactions) without modifying code.

Example: @Transactional, @Cacheable

java
Copy
Edit
@Transactional
public void updateUser(User user) {
    // Spring creates a proxy to manage the transaction
}
✅ 5. Template Method Pattern
Usage: Provided in helper classes like JdbcTemplate, RestTemplate, JpaTemplate

Purpose: Define a skeleton of an algorithm, deferring steps to subclasses or parameters.

Example:

java
Copy
Edit
jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
✅ 6. Builder Pattern
Usage: Often used in DTOs or ResponseEntity constructions.

Example:

java
Copy
Edit
return ResponseEntity.ok()
                     .header("Custom-Header", "value")
                     .body(user);
✅ 7. Observer Pattern
Usage: Spring Event System.

Purpose: Notify listeners when an event occurs.

Example:

java
Copy
Edit
@EventListener
public void handleUserCreatedEvent(UserCreatedEvent event) {
    // handle the event
}
✅ 8. Strategy Pattern
Usage: Replaces if-else logic by injecting different strategy implementations.

Example:

java
Copy
Edit
public interface PaymentStrategy {
    void pay();
}

@Component("creditCardPayment")
public class CreditCardPayment implements PaymentStrategy { ... }

@Component("paypalPayment")
public class PaypalPayment implements PaymentStrategy { ... }

@Autowired
@Qualifier("creditCardPayment")
private PaymentStrategy paymentStrategy;
✅ 9. Adapter Pattern
Usage: Convert incompatible interfaces to work with Spring (e.g., WebMvcConfigurer).

Example:

java
Copy
Edit
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // adapts Spring MVC behavior
}
✅ 10. Decorator Pattern
Usage: For extending functionalities (e.g., adding filters, interceptors).

Example: Logging filters, request decorators in Spring Web.

✅ 11. Command Pattern
Usage: Often seen in Spring Batch, or @Scheduled tasks, or CommandHandlers in CQRS.

Purpose: Encapsulate a request as an object.

If you're building a custom architecture (like microservices, clean architecture, hexagonal), you may combine these patterns manually as well.

