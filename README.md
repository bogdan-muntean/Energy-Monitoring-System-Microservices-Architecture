# ⚡ Energy Monitoring & User–Device Management Platform

A full microservices-based system for managing users, devices, real-time energy consumption and live messaging.  
The platform includes multiple Spring Boot backend services, a React frontend, a RabbitMQ-based monitoring service, and a real-time chat microservice.

---

## 🚀 System Architecture Overview

This project is organized into several independent microservices:

---

## 🔹 1. User Service (Spring Boot, port 8080)

Handles all user-related operations, including authentication, authorization and CRUD management.

### Key Features

-   User registration & login
-   Role-based access (Admin / Client)
-   Full CRUD operations
-   REST API endpoints

### Main Files

-   `UserController.java`
-   `UserService.java`
-   `UserRepository.java`
-   `User.java`, `UserDTO.java`

---

## 🔹 2. Device Service (Spring Boot, port 8081)

Manages devices associated with users and stores device metadata and maximum hourly consumption.

### Features

-   Add, update, delete devices
-   Assign devices to users
-   Retrieve devices per user
-   REST API endpoints

### Main Files

-   `DeviceController.java`
-   `DeviceService.java`
-   `DeviceRepository.java`
-   `Device.java`

---

## 🔹 3. Frontend Application (React.js)

A React web interface that communicates with both backend services.

### Pages

-   Login
-   Register
-   Admin Dashboard (manage users & devices)
-   Client Dashboard (view assigned devices & consumption)

### Important Components

-   `ProtectedRoute` — restricts access based on user role
-   `UserService.js` / `DeviceService.js` — API communication
-   `UserListTable`, `DeviceListTable`

---

## 🔹 4. Energy Monitoring & Communication Microservice (Spring Boot + RabbitMQ)

Processes hourly energy consumption data sent by device simulators through RabbitMQ.  
Saves processed data and sends WebSocket notifications when consumption limits are exceeded.

### Core Components

-   `EnergyDataConsumer` — listens to the RabbitMQ queue
-   `RabbitMQConfig` — configures the `energy_data` queue
-   `HourlyConsumptionService` — stores consumption in the database
-   `WebSocketNotificationService` — real-time alerts to clients

### Features

-   Real-time energy data processing
-   Saves hourly consumption to `hourly_consumption` table
-   Detects limit exceedances
-   Sends live WebSocket alerts

---

## 🔹 5. Chat Microservice (Spring Boot + WebSocket)

Provides private real-time messaging between users with presence indicators.

### Features

-   Private messaging via WebSocket
-   "Online / Offline" status notifications
-   "Typing", "Sent", "Seen" indicators
-   Integrates with the User Service for fetching user data

### Main Components

-   `ChatController.java`
-   `ChatWebSocketConfig.java`
-   `UserService.java` (integration)
-   `UserRepository.java`

---

## 🗄️ Databases (PostgreSQL)

The system uses multiple databases to isolate responsibilities:

### **users-db**

Stores all user accounts and roles.  
Table:  
`users(id, username, password, role)`

### **devices-db**

Stores device information.  
Table:  
`devices(id, description, address, max_hourly_energy_consumption, user_id)`

### **hourly_consumption**

Stores processed energy readings.  
Table:  
`hourly_consumption(device_id, timestamp, total_consumption)`

---

## 🔄 Data Flow Overview

1. Users log in through the frontend → User Service validates credentials.
2. The frontend grants access based on user role.
3. Admins manage users & devices; clients view their devices.
4. Device simulators send energy readings to RabbitMQ.
5. The Monitoring Microservice:
    - consumes messages
    - stores hourly consumption
    - triggers WebSocket alerts
6. Clients see real-time consumption and notifications in the UI.

---

## 📸 UI Preview (Screenshots)

Below are several interface screenshots illustrating how the platform looks in action.

---

### 🔐 Login Page

The landing page where users can authenticate or navigate to registration.

<img src="./imagini-prezentare-documentatie/login-page.png" width="650"/>

---

### 📝 Register Page

New users can create an account and choose their role (Admin or Client).

<img src="./imagini-prezentare-documentatie/register-page.png" width="650"/>

---

### 🛠️ Administrator Dashboard – User & Device Management (1)

Admin users can add, update, delete and view all registered users and devices.

<img src="./imagini-prezentare-documentatie/administrator-page-1.png" width="650"/>

---

### 🛠️ Administrator Dashboard – User & Device Management (2)

Detailed lists for managing device associations and user roles.

<img src="./imagini-prezentare-documentatie/administrator-page-2.png" width="650"/>

---

### 📊 Client Dashboard – Assigned Devices

Clients can view their assigned devices, including address and maximum allowed hourly consumption.

<img src="./imagini-prezentare-documentatie/client-page.png" width="650"/>

---

## 🔧 Environment Variables

Each service requires a local .env file.
Do NOT commit your real .env file to GitHub.

Below is an example template.
Replace the values with your own local configuration:

# Example .env (template)

DB_IP=localhost
DB_PORT=5432
DB_USER=postgres
DB_PASSWORD=your_local_password_here

PORT=8083

REDIS_HOST=localhost
REDIS_PORT=6379

RABBITMQ_USERNAME=your_rabbitmq_user
RABBITMQ_PASSWORD=your_rabbitmq_password

## ▶️ Running the System (without Docker)

### **1. Start RabbitMQ manually**

RabbitMQ must be installed locally.

Start the RabbitMQ server using one of the following methods:

-   Windows Services
-   `rabbitmq-server` command
-   RabbitMQ installation UI

```sh
rabbitmq-server
```

RabbitMQ must be running on:
Port 5672 → AMQP (message queue)
Port 15672 → Management UI

You can access the management UI at:
http://localhost:15672
(default user: guest / guest)

### **2. Start each Spring Boot backend service**

From each backend folder, run:

```sh
mvn spring-boot:run
```

Services run on:
User Service → port 8080
Device Service → port 8081
Monitoring Service → custom port (defined in your config)
Chat Service → custom port

### **3. Start the React frontend**

From the frontend directory:

```sh
npm install
npm start
```

Frontend will run on: http://localhost:3000.

### **4. Start the device simulators**

Run the simulator with the proper arguments (as in documentation):

```sh
mvn spring-boot:run -Dspring-boot.run.arguments="--port=8084 start-device sensor.csv 1"
mvn spring-boot:run -Dspring-boot.run.arguments="--port=8085 start-device sensor.csv 6"
```
