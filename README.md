# Government Scheme Recommendation System

A full-stack application built with Spring Boot and React to help citizens find and track government schemes they are eligible for.

## Project Structure
- **backend/**: Spring Boot application (Java, Maven, H2/MySQL).
- **frontend/**: React + Vite application (JavaScript, Vanilla CSS).

## Key Features
- **Eligibility Finder**: Filter schemes based on age, income, category, state, caste, and gender.
- **Smart Search**: Integrated AI assistant for guided scheme discovery.
- **Persistent Bookmarks**: Save favorite schemes to your profile.
- **Admin Dashboard**: Comprehensive management of schemes (CRUD).
- **Multi-language Support**: English, Hindi, and Telugu.
- **Responsive Design**: Modern UI optimized for all devices.

## Major Refactor Highlights
- **Layered Architecture**: Controller → Service → Repository pattern.
- **Type Safety**: Enums for Category, Gender, Caste, and Role.
- **DTO Implementation**: Clean decoupling of API and Database layers.
- **Pagination & Sorting**: Efficient handling of large datasets.
- **Secure Configuration**: Environment variable support for sensitive settings.

## Getting Started

### Backend
1. Navigate to `backend/`.
2. Configure environment variables in `.env` (or set them in your system):
   - `SERVER_PORT`: Default 9090
   - `DB_URL`: JDBC URL
   - `DB_USERNAME`: Database username
   - `DB_PASSWORD`: Database password
   - `JWT_SECRET`: Secure key for token generation
3. Run `./mvnw spring-boot:run`.

### Frontend
1. Navigate to `frontend/`.
2. Run `npm install`.
3. Set `VITE_API_BASE_URL` in `.env`.
4. Run `npm run dev`.

## Technologies Used
- **Backend**: Spring Boot 3, Spring Security, Spring Data JPA, JWT, Lombok, H2 Database.
- **Frontend**: React 18, Vite, Axios, Lucide React, CSS3.
