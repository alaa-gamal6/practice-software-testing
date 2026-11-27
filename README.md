# Tool Shop QA Automation Suite

Web UI automation template for [Practice Software Testing – Tool Shop](https://practicesoftwaretesting.com/).

This repository adapts the 02-Web-Automation track of the AOS QA Automation reference project to the Tool Shop application. It keeps the Java + Selenium + TestNG + Page Object Model approach and assigns six web components to six team members, plus an E2E scenario owned by the Leader.

## Team allocation

| Member | Component | Package (folder) | Test-case source |
|---|---|---|---|
| Member 1 (Leader) | Home Page | home | TestCasesForHomePage |
| Member 2 | Register | register | Register_All_Test_Cases.xlsx |
| Member 3 | Login | login | TestCasesForLogin |
| Member 4 | Checkout | checkout | CheckOut_All_Test_Cases.xlsx |
| Member 5 | My Account | myaccount | TestCasesForMyAccount.xlsx |
| Member 6 | Contact & Language Switching | contactlanguage | TestCasesForContact&LanguageSwitching.xlsx |
| Leader (Member 1) | E2E (Full Journey) | e2e | E2E scenario |

E2E tests cover one complete user journey (Register → Login → Add to cart → Checkout → My Account) and are written in tests/e2e/.

## Repository layout

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
│   │       │   ├── tests/
│   │       │   │   ├── component/
│   │       │   │   │   ├── home/
│   │       │   │   │   ├── register/
│   │       │   │   │   ├── login/
│   │       │   │   │   ├── checkout/
│   │       │   │   │   ├── myaccount/
│   │       │   │   │   └── contactlanguage/
│   │       │   │   ├── integration/
│   │       │   │   │   ├── home/
│   │       │   │   │   ├── register/
│   │       │   │   │   ├── login/
│   │       │   │   │   ├── checkout/
│   │       │   │   │   ├── myaccount/
│   │       │   │   │   └── contactlanguage/
│   │       │   │   └── e2e/
│   │       │   └── listeners/
│   │       └── resources/
│   ├── test-data/
│   │   ├── home/
│   │   ├── register/
│   │   ├── login/
│   │   ├── checkout/
│   │   ├── myaccount/
│   │   └── contactlanguage/
│   ├── screenshots/
│   ├── reports/
│   ├── pom.xml
│   └── testng.xml
└── README.md
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

From the 02-Web-Automation directory:

mvn clean test
Run headless:

mvn clean test -Dheadless=true
Run one component:

mvn clean test -Dtest=HomePageTest
mvn clean test -Dtest=RegisterTest
mvn clean test -Dtest=LoginTest
mvn clean test -Dtest=CheckoutTest
## Naming rules

- Packages and folders: lowercase, no spaces (for example myaccount).
- Class names: PascalCase (for example LoginTest, HomePageTest).
- Work only on your assigned branch. Never work directly on main.

## Component ownership

Each member owns their component's:
1. Page Object locators and reusable page actions.
2. Test class and assertions.
3. Test data and test-case traceability.
4. Component-level verification.
5. Pull Request and integration fixes for their component.

Shared framework code (BasePage, BaseTest, DriverFactory, configuration, listeners, pom.xml, testng.xml and the README) is maintained by the Leader. Contact the Leader before changing any shared file.

## Integration approach

- Keep component tests independent and repeatable.
- Reuse Page Objects in integration tests; do not duplicate locators.
- Add cross-component scenarios to tests/integration/ only after the relevant component tests are stable.
- - The Checkout component may depend on product/cart state. Make its test setup explicit; do not rely on execution order or a previous test's browser session.
- Use unique synthetic accounts for registration tests.
- Never commit real credentials, payment card details, tokens or private user data.

## Test-case traceability

The test-case sources for each component are listed in the Team allocation table. Test classes start as ready-to-fill scaffolds. Add the real test-case ID and expected result from each workbook to the corresponding @Test description and assertion. Do not treat placeholder tests as completed coverage.

## Important

The public Tool Shop application can change and is shared by many testers. Confirm locators and expected behaviour against the live site before implementing assertions. No live execution is claimed by this template.