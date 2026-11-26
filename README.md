# Tool Shop QA Automation Suite

Web UI automation template for [Practice Software Testing – Tool Shop](https://practicesoftwaretesting.com/).

This repository adapts the `02-Web-Automation` track of the AOS QA Automation reference project to the Tool Shop application. It keeps the Java + Selenium + TestNG + Page Object Model approach and assigns exactly four web components to four team members.

## Team allocation

| Member | Component | Test-case source |
|---|---|---|
| Member 1 | Home Page | `TestCasesForHomePage` |
| Member 2 | Register | `Register_All_Test_Cases.xlsx` |
| Member 3 | Login | `TestCasesForLogin` |
| Member 4 | Checkout | `CheckOut_All_Test_Cases.xlsx` |

## Repository layout

```text
Tool-Shop-QA-Automation-Suite/
├── 02-Web-Automation/
│   ├── src/
│   │   ├── main/java/com/toolshop/automation/
│   │   │   ├── base/
│   │   │   ├── driver/
│   │   │   ├── pages/
│   │   │   └── utils/
│   │   └── test/
│   │       ├── java/com/toolshop/automation/
│   │       │   ├── tests/Component/
│   │       │   │   ├── home/
│   │       │   │   ├── register/
│   │       │   │   ├── login/
│   │       │   │   └── checkout/
│   │       │   ├── tests/Integration/
│   │       │   │   ├── home/
│   │       │   │   ├── register/
│   │       │   │   ├── login/
│   │       │   │   └── checkout/
│   │       │   └── listeners/
│   │       └── resources/
│   ├── test-data/
│   │   ├── home/
│   │   ├── register/
│   │   ├── login/
│   │   └── checkout/
│   ├── screenshots/
│   ├── reports/
│   ├── pom.xml
│   ├── testng.xml
│   └── README.md
└── README.md
```

## Technology stack

- Java 17
- Maven
- Selenium WebDriver 4
- TestNG
- WebDriverManager
- Page Object Model (POM)

## Requirements

- JDK 17+
- Maven 3.8+
- Google Chrome
- Internet access to reach the public test application

## Run

From the `02-Web-Automation` directory:

```bash
mvn clean test
```

Run headless:

```bash
mvn clean test -Dheadless=true
```

Run one component:

```bash
mvn clean test -Dtest=HomePageTest
mvn clean test -Dtest=RegisterTest
mvn clean test -Dtest=LoginTest
mvn clean test -Dtest=CheckoutTest
```

## Component ownership

Each member owns their component's:
1. Page Object locators and reusable page actions.
2. Test class and assertions.
3. Test data and test-case traceability.
4. Component-level verification.
5. Pull Request and integration fixes for their component.

Shared framework code (`BasePage`, `BaseTest`, `DriverFactory`, configuration, listeners and TestNG suite) is maintained collaboratively. Coordinate changes to shared files before merging.

## Integration approach

- Keep component tests independent and repeatable.
- Reuse Page Objects in integration tests; do not duplicate locators.
- Add cross-component scenarios to `tests/integration/` only after the relevant component tests are stable.
- The Checkout component may depend on product/cart state. Make its test setup explicit; do not rely on execution order or a previous test's browser session.
- Use unique synthetic accounts for registration tests.
- Never commit real credentials, payment card details, tokens or private user data.

## Test-case traceability

The four source workbooks were shown in the supplied screenshot, but the workbook contents were not provided. Therefore, test classes are created as ready-to-fill scaffolds. Add the real test-case ID and expected result from each workbook to the corresponding `@Test` description and assertion. Do not treat placeholder tests as completed coverage.

## Important

The public Tool Shop application can change and is shared by many testers. Confirm locators and expected behaviour against the live site before implementing assertions. No live execution is claimed by this template.
