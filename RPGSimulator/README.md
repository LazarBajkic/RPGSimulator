# Java RPG Simulator

A console-based Role-Playing Game (RPG) simulator built in Java, designed to demonstrate core Object-Oriented Programming (OOP) principles, clean architecture, and robust state management.

## Features
- **OOP Architecture:** Abstract `Character` class with concrete implementations (`Mage`, `Warrior`) utilizing polymorphism.
- **Inventory System:** Dynamic item management (`addItem`, `removeItem`, `findItems`) with validation.
- **Equipment System:** Seamless gear swapping (`changeEquipment`) preventing item duplication using Java Streams and `Optional`.
- **Turn-Based Combat System:** Interactive console combat loop supporting basic attacks and mana-controlled abilities.
- **Trading System:** Secure gold and item exchange between characters and merchants.

## Technologies Used
- Java (JDK 17+)
- Collections Framework (`HashMap`, `ArrayList`)
- Java Streams & Lambdas
- `Optional` API for null-safety

## How to Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git](https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git)