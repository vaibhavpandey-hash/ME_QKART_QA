🛒 QKart E-Commerce Automation (End-to-End)

This project automates end-to-end test scenarios for QKart, a sample e-commerce web application. It validates core user workflows such as login, product browsing, cart management, and checkout using Selenium WebDriver and TestNG.

🚀 Features

✔ End-to-end flow automation

✔ Page Object Model (POM) design

✔ TestNG assertions & reporting

✔ Reusable utilities for UI interactions

✔ Cross-browser compatible test setup

✔ Data-driven testing for user flows

🧰 Tech Stack

Java

Selenium WebDriver

TestNG

Gradle

Extent Reports (optional)

📂 Project Structure
src/
 ├── test/java/
 │      ├── testCases/        → Test classes (E2E, Login, Cart, Checkout)
 │      ├── pages/            → Page Object Model classes
 │      └── utils/            → WebDriver + helpers
 └── main/java/               → Core framework utilities

▶️ Running Tests

Build the project:

gradle clean build


Run all TestNG tests:

gradle test

🎯 Purpose

This project demonstrates end-to-end UI automation skills including:

UI interaction with Selenium

Test architecture using POM

End-to-end scenario validation

Assertions & reporting with TestNG

Running and maintaining scalable regression suites
