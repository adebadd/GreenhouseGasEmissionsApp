# 🌍 Greenhouse Gas Emissions Web App

A full-stack Java Spring Boot application that displays Ireland’s greenhouse gas emissions data, parsed from multiple sources (XML, JSON, HTML). The app allows users to view, manage, and analyse emissions by category, with secure authentication and role-based access. Developed as a university assignment to demonstrate integration of multiple technologies in a real-world context.

---

## 🚀 Features

- User authentication and registration with Spring Security
- Parse and combine emissions data from XML, JSON, and HTML sources
- Store data in MySQL using JPA/Hibernate
- Filter emissions by category and view detailed data
- Full CRUD operations for emissions and users
- Thymeleaf-powered frontend for data visualisation
- Secure endpoints with session management
- Tested using **Postman** for all RESTful API routes

---

## 🧰 Tech Stack

- **Backend:** Java, Spring Boot, Spring MVC, Spring Security
- **Frontend:** Thymeleaf, HTML/CSS
- **Database:** MySQL
- **ORM:** Hibernate (JPA)
- **Data Parsing:** JAXB (XML), Jackson (JSON), Jsoup (HTML)
- **Build Tool:** Maven
- **Testing:** Postman
- **Version Control:** GitHub

---

## 📁 Project Structure

- `model/` – Entity classes (`User`, `Emission`)
- `controller/` – API endpoints and web controllers
- `service/` – Business logic and parsing services
- `repository/` – Spring Data JPA repositories
- `parser/` – Custom parsers for XML, JSON, and HTML
- `templates/` – Thymeleaf HTML views

---

## 🔐 User Authentication

- Registration with encrypted passwords (BCrypt)
- Login/Logout with session handling
- Protected routes accessible only to authenticated users

---

## 📡 API Endpoints

| Method | Endpoint               | Description                              |
|--------|------------------------|------------------------------------------|
| POST   | `/register`            | Register a new user                      |
| POST   | `/login`               | Log in with existing credentials         |
| GET    | `/logout`              | Log out current session                  |
| GET    | `/api/emissions`       | View all emissions                       |
| GET    | `/api/emissions/{id}`  | View single emission by ID              |
| GET    | `/home?category=X.Y.Z` | View emissions by category via UI        |
| GET    | `/parse`               | Parse emissions from XML & JSON sources |

> Example: `http://localhost:8080/home?category=2.D.`

---

## 📦 Sample Use Cases

1. **Register & Login**
   - Use `/register` to sign up
   - Log in via `/login` and test session persistence

2. **Parsing Data**
   - Visit `/parse` to trigger parsers for:
     - XML (Predicted values)
     - JSON (Actual values)
     - HTML (Category descriptions)

3. **View Emissions**
   - Access full emission data at `/api/emissions`
   - Filter by ID: `/api/emissions/1`
   - Filter by category in browser: `/home?category=1.A.1.A`

4. **Testing**
   - All API routes tested using **Postman**
   - Includes registration, login, emission CRUD, and parsing endpoints

---

## 📸 Demo

👉 [Watch the demo on YouTube](https://www.youtube.com/watch?v=hAQKmXQBbtQ)


## 📜 License

MIT License – feel free to fork and build on top of this for learning purposes.

