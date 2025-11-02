# Trello API Automation

This repository contains a **REST API automation framework** for Trello using **Java**, **RestAssured**, and **Cucumber**. The framework allows you to create, update, get, and delete boards, lists, cards, and labels, with automated test scenarios and reports.

---

## **Tech Stack**

* Java 17
* Maven
* RestAssured
* Cucumber (BDD)
* JUnit
* Masterthought Cucumber Reporting
* Git & GitHub

---

## Project Structure

Trello/

├── src/main/java/resourses/             # Utilities, API resources

├── src/main/java/pojo/                 # POJO classes for Trello API responses

├── src/test/java/stepDefinitionFile/   # Cucumber step definitions

├── src/test/java/features/             # Feature files (Gherkin syntax)

├── global.properties                   # Global config for API base URL and credentials

├── .env                                # Local environment variables (ignored in Git)

├── pom.xml                              # Maven configuration

└── README.md

---

## **Setup Instructions**

1. **Clone the repository**

```bash
git clone https://github.com/hammadalik01/RestAssured-Api-Testing-
cd Trello
```

2. **Create a `.env` file** in the project root with the following content:

```
BASE_URL=https://api.trello.com/1
API_KEY=your_trello_api_key
API_TOKEN=your_trello_api_token
```

3. **Install dependencies using Maven**

```bash
mvn clean install
```

4. **Running Tests**

Run all Cucumber scenarios using Maven:

```bash
mvn test
```

---

## **Test Scenarios**

The framework currently supports:

**Board Operations**

* Create Board
* Get Board
* Update Board
* Delete Board

**List Operations**

* Create List
* Update List
* Move List to another Board
* Get List details

**Card Operations**

* Create Card
* Update Card
* Delete Card

**Label Operations**

* Create Label
* Update Label
* Get Label
* Delete Label

---

## **Reporting**

The framework uses **Masterthought Cucumber Reporting** to generate HTML reports:

* Reports are located at: `target/cucumber-reports/`
* JSON reports are generated at: `target/cucumber.json`

---

## **Best Practices**

* Keep your API keys and tokens in `.env` and **do not commit them**.
* Clean and rebuild the project before running tests:

```bash
mvn clean install
```
