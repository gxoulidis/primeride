# PrimeRide

PrimeRide is a Service-Oriented application designed for car rentals and sales. It provides citizens the ability to search for cars, book test drives, and purchase vehicles, while allowing dealerships to manage their car inventory efficiently.

## Features and User Roles

### User Roles:
1. **Dealerships:**
   - Add and manage car inventory.
   - Confirm car sales.
   - Oversee test drive bookings.
2. **Citizens:**
   - Search for cars.
   - Book cars for test drives.
   - Cancel bookings.

### Core Features:
- **General:**
  - User registration.
  - User login.
- **For Dealerships:**
  - Add new cars.
  - Update car availability.
  - Confirm car purchases.
  - End test drive bookings.
- **For Citizens:**
  - Search cars by criteria.
  - Book cars for test drives.
  - Cancel bookings.

## Technologies Used

### Development Tools and Environment:
- **IDE:** IntelliJ IDEA (backend), Visual Studio Code (frontend).
- **Version Control:** Git and GitHub.
- **Backend Server:** XAMPP (Apache, PHP, MySQL).
- **Testing Tools:** Postman for API testing.
- **Dependency Management:** Maven.

### Frontend Technologies:
- PHP, CSS, JavaScript, jQuery, Ajax.

### Backend Framework:
- Spring Boot (Java-based).

### Database:
- MySQL.

## System Architecture

### Folder Structure:
```
primeride/
│
├── backend/         
│   ├── src/         
│   │   ├── java/      
│   │   │   ├──controller/   
│   │   │   ├──exception/    
│   │   │   ├──model/        
│   │   │   ├──repository/   
│   │   │   └──service/     
│   │   └── resources/      
│   │       ├──application.properties
│   │       └──schema.sql            
│   └── pom.xml               
├── frontend/          
│   ├── css/           
│   ├── js/            
│   └── php/          
└── index.php          
```

### Backend Configuration:
- **application.properties:**
  - Database URL: `jdbc:mysql://localhost:3306/primeride?createDatabaseIfNotExist=true`
  - Username: `root`, Password: ` ` (empty).
  - Auto schema creation disabled: `spring.jpa.hibernate.ddl-auto=none`.

### Backend Dependencies:
- `spring-boot-starter-data-jpa`, `spring-boot-starter-web`, `mysql-connector-j`, `hibernate-validator`, `spring-boot-maven-plugin`.


## Installation and Setup

### Prerequisites
- Java 17
- Maven
- MySQL Server
- XAMPP (Apache and PHP)

### Steps
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/gxoulidis/primeride.git
   ```

2. **Set Up the Frontend**:
   - Place the `primeride` folder in your XAMPP `htdocs` directory.
   - Start Apache using the XAMPP control panel.

3. **Set Up the Backend**:
   - Open the backend folder in IntelliJ IDEA.
   - Configure the database in `application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/primeride?createDatabaseIfNotExist=true
     spring.datasource.username=YOUR_USERNAME
     spring.datasource.password=YOUR_PASSWORD
     ```
   - Start MySQL using the XAMPP control panel.
   - Build and run the backend using Maven:
     ```bash
     mvn spring-boot:run
     ```

4. **Access the Application**:
   - Open your browser and navigate to:
     ```
     http://localhost/primeride
     ```

---

## Usage

### Registration and Login
#### Registration:
- **Citizens:**
  - Provide VAT number, first name, last name, email, and password.
  - Endpoint: `POST /api/users/citizen`
- **Dealerships:**
  - Provide VAT number, dealership name, owner name, email, and password.
  - Endpoint: `POST /api/users/dealership`

#### Login:
- **Endpoint:** `POST /api/users/login`
- **Required Fields:**
  - VAT number and password.
- **Response:**
  - Includes user type and name in headers for session management.

### Car Management for Dealerships
1. **Add Cars:**
   - Endpoint: `POST /api/cars`
   - **Details Required:** Brand, model, fuel type, engine type, seats, price, inventory count.
2. **Update Availability:**
   - Endpoint: `PATCH /api/cars/{id}`
   - **Details Required:** Inventory count for a specific car.
3. **View Inventory:**
   - Endpoint: `GET /api/cars/{dealershipAfm}`
   - Retrieves all cars for a dealership.

### Searching and Booking Cars for Citizens
1. **Search Cars:**
   - Endpoint: `GET /api/cars/search`
   - **Filter Criteria:**
     - Brand, model, fuel type, engine type, seats, price range, dealership.
2. **Book Cars:**
   - Endpoint: `POST /api/bookings`
   - **Details Required:** Booking date and duration.
3. **Cancel Bookings:**
   - Endpoint: `DELETE /api/bookings/{id}`

## Example API Usage

### Citizen Registration Example:
**Request:**
```
POST /api/users/citizen
Content-Type: application/json

{
  "afm": "123456789",
  "email": "citizen@example.com",
  "password": "pass123",
  "fname": "Giannis",
  "lname": "Xoulidis"
}
```

**Response:**
```
201 Created
{
  "afm": "123456789",
  "email": "citizen@example.com",
  "password": "pass123",
  "fname": "Giannis",
  "lname": "Xoulidis"
}
```

### Car Search Example:
**Request:**
```
GET /api/cars/search?brand=Toyota&fuel=PETROL&min-price=15000&max-price=30000
```

**Response:**
```
200 OK
[
  {
    "brand": "Toyota",
    "model": "Corolla",
    "count": 5,
    "fuel": "Petrol",
    "engine": "INLINE4",
    "price": 20000.0,
    "dealership": {
      "afm": "123456789",
      "name": "The Best Deal"
    }
  }
]
```

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

## Contact
**Author**: Giannis Xoulidis  
**Email**: [giannis.xoulidis@example.com](mailto:giannis.xoulidis@example.com)
