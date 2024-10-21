
# DDoS Incident Response Simulation 🚀

This project simulates a **Distributed Denial of Service (DDoS) Attack** and demonstrates how to detect and respond to it using **Java**. The goal is to build an incident response mechanism that monitors traffic, detects abnormal behavior, and recovers from the attack.

## Project Structure 📂

- **ServerWithRecovery.java**:  
  The main server that processes client requests and detects DDoS attacks. Upon detecting a high volume of requests, it triggers a recovery mechanism by temporarily halting request processing.

- **DDoSAttackSimulation.java**:  
  Simulates a DDoS attack by sending a rapid sequence of requests to the server, mimicking a malicious actor.

- **ServerSimulation.java**:  
  A basic server to handle requests (optional for testing).  

- **ServerWithDetection.java**:  
  A simpler version of the main server focusing on DDoS detection.

## Technologies Used 🛠️

- **Java**:  
  The core programming language used for both the server and client logic in this project.

- **Sockets**:  
  Utilized for client-server communication, allowing the server to accept incoming requests from clients.

- **Concurrency**:  
  Implemented using threads and `AtomicInteger` to handle multi-threaded request counting, ensuring accurate counting of incoming requests without data corruption.


---

