# PrimeRide

PrimeRide is a Service-Oriented application designed for car rentals and sales. It provides citizens the ability to search for cars, book test drives, and purchase vehicles, while allowing dealerships to manage their car inventory efficiently.

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

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

## Contact
**Author**: Giannis Xoulidis  
**Email**: [mai25041@uom.edu.gr](mailto:mai25041@uom.edu.gr)
