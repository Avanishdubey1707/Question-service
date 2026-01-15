# ❓ Question Service – Quiz Application (Microservice)

## 📌 Overview
The **Question Service** is a **Spring Boot microservice** responsible for managing quiz questions.  
It handles creation, retrieval, generation , adding and evaluation  of questions and exposes REST APIs that are consumed by other services such as the **Quiz Service**.

This service was **extracted from a monolithic quiz application** as part of a migration to a **microservices-based architecture**.

---

## 🏗️ Architecture
- **Architecture Type:** Microservice
- **Framework:** Spring Boot
- **Build Tool:** Maven
- **Database:** MySQL (or H2 – update as applicable)
- **Communication:** REST APIs
- **Service Discovery:** Eureka Client (optional / future scope)

Each service is **independently deployable** and maintains its own database.

---

## 📂 Project Structure