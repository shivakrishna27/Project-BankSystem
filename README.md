*** project using (SpringBoot + MySQL + thymeleaf/HTML)

- SpringBoot : CRUD operations,MVC as model, controller, view, repository
- MySQL : DataBase connection  with username, password, JDBC, Jpa
- thymeleaf : same as like HTML syntax for frontend development/ dynamically typed

Project Key Points

1. User Registration & Login (Authentication Module)

Users can sign up with auto-generated formNo, cardNumber, and PIN.
Login is handled using card number + PIN with session management.
Uses Spring MVC, HttpSession, Validation, and Exception Handling.

2. MVC Architecture with Spring Boot

Controllers handle HTTP requests (AuthController, BankController).
Service Layer manages business logic (AuthService, BankService).
Repositories use Spring Data JPA for DB operations.

3. Database Integration with JPA & JDBC

Entities: User and Transaction (with annotations & Lombok).
Uses JpaRepository for CRUD and JDBC for custom queries.
MySQL database can store users and transaction history.

4. Banking Operations
    Deposit → Adds entry to transactions table.
    Withdraw → Checks balance & updates DB.
    Balance Check → Calculates balance from all transaction records.

5. Mini Statement & Recent Transactions

Displays all transactions for a user (Mini Statement).
Tracks last 5 transaction types using an array (getRecentTransactionTypes()).

6. Session-Based Security

Users must login to access /dashboard, /deposit, /withdraw, /balance, /mini,... etc.
Session stores the PIN to identify account.

7. Exception Handling & Logging

Uses Logger (SLF4J) for debugging, info, and error logs.
Proper try-catch blocks in service and controller layers.

8. Frontend with Thymeleaf Templates

Clean UI using HTML + CSS
Pages included:login,signup....etc

This method demonstrates Java’s garbage collection.It creates many temporary objects to consume memory,
requests garbage collection with System.gc(),and logs memory usage before and after to show how GC frees unused objects

1.Java Basics: Uses variables (String, int, long), methods, and class declarations in User.java, Transaction.java, and services.
2.Control Statements: Implements if, else, for, and do-while in AuthService.java (unique ID generation) and BankService.java (e.g., balance checks).
3.Object-Oriented Programming (OOP): Demonstrates encapsulation (private fields with getters/setters in User.java), dependency injection (@Autowired), and class hierarchies.
4.Constructors and Overloading: Defines default and parameterized constructors in User.java and Transaction.java.
5.Arrays and Strings: Uses String for fields (cardNumber) and arrays in BankService.java (recentTransactionTypes).
6.Access Modifiers and Packages: Employs private, public, and package organization ( com.example.banksystem.controller).
7.Exception Handling: Uses try-catch in controllers (AuthController.java) and services, throwing RuntimeException for errors ( insufficient balance).
8.Collections Framework: Utilizes List<Transaction> in BankService.java for transaction history and balance calculation.
9.Java 8 Features: Includes Optional in UserRepository.java and forEach in AuthController.java for error handling.
10.Garbage Collection: Demonstrates memory management in AuthService.java via demonstrateGarbageCollection, triggered by /gc-demo.
11.JDBC basics to connect the MySQL DataBase connection........
