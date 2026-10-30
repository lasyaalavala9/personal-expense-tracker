# Personal Expense Tracker

A console-based expense management application developed using Core Java.

## Features

- Add new expenses
- View all recorded expenses
- Search expenses by category
- Delete expenses by ID
- Calculate total expenses
- Generate category-wise summaries
- Generate monthly expense summaries
- Save expense records using file handling

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList and Collections
- File Handling
- Exception Handling
- Git and GitHub

## Project Structure

```text
PersonalExpenseTracker/
├── src/
│   ├── Main.java
│   ├── Expense.java
│   ├── ExpenseManager.java
│   └── FileManager.java
├── data/
│   └── expenses.txt
├── screenshots/
├── .gitignore
└── README.md
```

## How to Run

Make sure Java is installed on your system.

Compile the application from the project root:

```bash
javac -d out src/*.java
```

Run the application:

```bash
java -cp out Main
```

The application stores expense records in `data/expenses.txt`.

## Learning Outcomes

This project demonstrates object-oriented programming, collections, file handling, exception handling, and basic version control using Git