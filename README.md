# Todo API Testing Framework

[![Java](https://img.shields.io/badge/Java-21-007396)](https://www.java.com/)
[![RestAssured](https://img.shields.io/badge/RestAssured-5.3.0-4bc51d)](https://rest-assured.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-ff6a00)](https://testng.org/)
[![Allure Report](https://img.shields.io/badge/Allure-Report-ff4e8a)](https://docs.qameta.io/allure/)

A Java-based API testing framework for Todo application endpoints using RestAssured and TestNG, designed for clear, maintainable automated tests.

---

## 🚀 Key Features
- **Full CRUD Operations**: Complete test coverage for Todo API endpoints
- **Dynamic Data Generation**: Randomized test data using JavaFaker
- **Model-Based Testing**: POJOs with Jackson annotations for request/response handling
- **Centralized Configuration**: Reusable specifications and error messages
- **Detailed Reporting**: Allure integration with step-by-step traces
- **CI/CD Ready**: GitHub Actions pipeline for automated testing
- **Token Authentication**: OAuth2 token handling for secure endpoints

---

## 🛠 Technology Stack

| Component          | Technology             |
|--------------------|------------------------|
| Language           | Java 21               |
| Testing Framework  | RestAssured 5.3.0     |
| Test Runner        | TestNG 7.8.0          |
| JSON Processing    | Jackson               |
| Test Data          | JavaFaker             |
| Reporting          | Allure                |
| CI/CD              | GitHub Actions        |
| Build Tool         | Maven                 |

---

## ⚙️ Project Structure

```text
src/
src/
├── main/java/com/qacart/todo/
│   ├── apis/         # API client implementations (UserApi, TodoApi)
│   ├── base/         # Base request specifications, common configurations
│   ├── data/         # API routes and error message constants
│   ├── models/       # Domain objects (User, Todo, Error)
│   └── steps/        # Test utilities and data generation helpers (UserSteps, TodoSteps)
├── test/java/com/qacart/todo/
│   └── testcases/    # Test suites (UserTest, TodoTest)
├── resources/        # Configuration files
└── pom.xml           # Dependency management
```
---

## 🚦 Getting Started

### Prerequisites
- Java 21+
- Maven 3.6+
- Allure CLI ([installation guide](https://docs.qameta.io/allure/#_installing_a_commandline))

### Installation
```bash
git clone https://github.com/your-username/todo-api-testing-framework.git
cd todo-api-testing-framework
mvn clean install
```

## Running Tests

### Run all test cases:

```bash
mvn test
```

Run specific test classes:
```bash
mvn test -Dtest=UserTest   # User management tests
mvn test -Dtest=TodoTest   # Todo operations tests
```

Generating Reports
After tests run, generate and view the Allure report:
```bash
allure serve target/allure-results
```

---

## 🔍 Test Coverage
| Module | Features Verified                             | Test Cases             |
| ------ | --------------------------------------------- | ---------------------- |
| User   | Registration, Login, and error handling tests | 3 functional API tests |
| Todo   | Full CRUD operations and validation scenarios | 6 functional API tests |



---

## 🧩 Framework Components
API Clients (apis/):

Abstract API interactions with reusable methods

Handle authentication and response parsing

Models (models/):

POJOs with Jackson annotations

Use builder pattern for data creation

Test Utilities (steps/):

Generate randomized data

Combine test steps for reuse

Config & Constants (base/, data/):

Base request spec

API route constants

Error messages

---

## 🔄 CI/CD Pipeline
> Continuous Integration and Continuous Deployment (CI/CD) pipeline using GitHub Actions
![image alt](https://github.com/OmarElsheikh1/todo-api-testing-framework/blob/ffd110256aa762df6594a1615bb2081b1020d75a/images/deepseek_mermaid_20250703_23e877.png)
Diagram showing the GitHub Actions workflow that runs automated tests and generates reports on every commit.
---

## 🛠️ Development Guide
Adding New Tests
Create a test class in testcases/

Use existing models and API clients

Generate test data via steps/ utilities

Annotate tests with Allure:
```bash
@Feature("New Feature")
@Story("User Story")
@Test(description = "Test case description")
```

Environment Configuration
Modify Specs.getRequestSpec() to support environment switching:
```bash
public class Specs {
    // Returns the request specification with a base URI.
    // The base URI can be overridden by setting the 'env' system property.
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
            .setBaseUri(System.getProperty("env", "https://todo.qacart.com"))
            .setContentType(ContentType.JSON)
            .build();
    }
}
```
Run tests with a custom environment URL:
```bash
mvn test -Denv=https://staging.todo-api.com
```

---

## 🤝 Contributing
Fork the repository

Create your branch: 
```bash
git checkout -b feature/your-feature
```

Commit your changes: 
```bash
git commit -m "Add new feature"
```

Push the branch: 
```bash
git push origin feature/your-feature
```
Open a pull request

Please follow the existing code style and include relevant test cases.

---

## 📄 License
Distributed under the MIT License. See the LICENSE file for more details.
