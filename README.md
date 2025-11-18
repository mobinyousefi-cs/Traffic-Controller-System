# Traffic Controller System

Java JSP & MySQL-based web application to monitor and control traffic signals and routes. The system provides a minimal dashboard where traffic officers and control-center operators can inspect the current state of intersections and update signal phases or route status in real time.

---

## Features

- View all configured **traffic signals** with intersection, direction, state and last update time.
- View all configured **routes** with from/to intersections and status.
- Update a signal state (RED / YELLOW / GREEN / OFFLINE) directly from the dashboard.
- Update a route status (OPEN / CLOSED / CONGESTED).
- Layered architecture: `dao` → `service` → `web` (servlets + JSP).
- Simple JDBC-based persistence using MySQL.

---

## Tech Stack

- **Language:** Java 11
- **Web:** JSP, JSTL, Servlets (Jakarta/Java EE)
- **Database:** MySQL 8
- **Build Tool:** Maven (WAR packaging)

---

## Project Structure

```text
traffic-controller-system/
├── pom.xml
├── README.md
├── sql/
│   └── schema.sql
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── mobinyousefi/
        │           └── traffic/
        │               ├── config/
        │               │   └── DatabaseConfig.java
        │               ├── dao/
        │               │   ├── DaoException.java
        │               │   ├── TrafficSignalDao.java
        │               │   └── RouteDao.java
        │               ├── dao/jdbc/
        │               │   ├── JdbcTrafficSignalDao.java
        │               │   └── JdbcRouteDao.java
        │               ├── model/
        │               │   ├── TrafficSignal.java
        │               │   └── Route.java
        │               ├── service/
        │               │   └── TrafficNetworkService.java
        │               └── web/
        │                   └── TrafficDashboardServlet.java
        └── webapp/
            ├── index.jsp
            ├── WEB-INF/
            │   ├── web.xml
            │   └── views/
            │       └── dashboard.jsp
            └── assets/
                └── styles.css
```

---

## Database Setup (MySQL)

1. Create the schema and seed data:

   ```bash
   mysql -u root -p < sql/schema.sql
   ```

   This creates a `traffic_controller` database with tables `traffic_signal` and `route` and inserts sample data.

2. Optionally create a dedicated DB user (recommended):

   ```sql
   CREATE USER 'traffic_user'@'localhost' IDENTIFIED BY 'traffic_password';
   GRANT ALL PRIVILEGES ON traffic_controller.* TO 'traffic_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. Ensure the credentials in `DatabaseConfig.java` match your local configuration:

   ```java
   private static final String JDBC_URL = "jdbc:mysql://localhost:3306/traffic_controller?useSSL=false&serverTimezone=UTC";
   private static final String JDBC_USER = "traffic_user";
   private static final String JDBC_PASSWORD = "traffic_password";
   ```

---

## Build & Run

1. **Build the WAR**

   ```bash
   mvn clean package
   ```

   This produces `target/traffic-controller-system.war`.

2. **Deploy to Tomcat (or any Servlet 4.0+ container)**

   - Copy the WAR file into the `webapps/` directory of your Tomcat installation.
   - Start Tomcat and wait for the deployment to finish.

3. **Access the application**

   - Open your browser and navigate to:

     ```
     http://localhost:8080/traffic-controller-system/
     ```

   - You will be redirected to the `/dashboard` view.

---

## Extending the System

Some ideas to extend this project:

- Add entities for **diversions**, **incidents**, and **traffic officers**.
- Integrate a **map view** (e.g. OpenStreetMap) showing intersections and route status visually.
- Add authentication/authorization (e.g. operator vs supervisor roles).
- Schedule **automatic signal plans** based on time-of-day.
- Expose a REST API for integration with external ITS components.

---

## License

This project can be released under the MIT License (matching your GitHub defaults). Update this section and add a `LICENSE` file before publishing the repository on GitHub.
