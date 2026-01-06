
<img width="1919" height="1079" alt="Screenshot 2025-09-08 125856" src="https://github.com/user-attachments/assets/81f3bbd2-b601-4c91-a6f3-9e7df63a3c7e" />

# Distributed Systems Demo with Apache Cassandra & Raspberry Pi

## 📌 Overview
The goal of this project is to **demonstrate core distributed systems principles** -  
**replication, fault tolerance, eventual consistency, and observability** - using a **minimal but realistic setup**.

Rather than relying on large cloud infrastructure, this system uses **two Raspberry Pis**, a real sensor, and a production-grade distributed database to show how **real-world distributed systems behave under normal operation and failure conditions**.

---

## 🎯 Key Concepts Demonstrated
- Horizontal scaling using multiple nodes
- Data replication with Apache Cassandra
- Fault tolerance and node failure simulation
- Heartbeat-based node liveness detection
- Eventual consistency
- Separation of data plane (Cassandra) and control plane (heartbeats)
- Observability through a real-time dashboard

---

## 🏗️ System Architecture

### Components
- **Seed Node (Raspberry Pi 1)**  
  - Connected to a temperature sensor  
  - Acts as the initial Cassandra seed node  
  - Publishes sensor data to the backend REST API  

- **Peer Node (Raspberry Pi 2)**  
  - Participates in Cassandra replication  
  - Maintains a replica of the dataset  
  - Demonstrates resilience under node failures  

- **Apache Cassandra**  
  - Distributed, peer-to-peer NoSQL database  
  - Handles data replication and fault tolerance  

- **Spring Boot Backend (Java)**  
  - Exposes REST APIs  
  - Writes sensor data to Cassandra  
  - Reads replicated data for visualization  

- **React + TypeScript Dashboard**  
  - Real-time data visualization  
  - Polls backend APIs  
  - Shows system behavior during failures  

---

## 🔄 Data Flow
1. Temperature sensor reads data on **Seed Node**
2. Spring Boot backend receives sensor data via REST
3. Data is written to **Apache Cassandra**
4. Cassandra replicates data to the **Peer Node**
5. Dashboard fetches and visualizes data in real time
6. When a node goes down, replication and recovery behavior can be observed

---

## 💥 Failure Scenarios Demonstrated
- Shutting down one Cassandra node
- Observing continued read/write availability
- Watching replicas converge after node recovery
- Monitoring latency and data consistency during failures

---

## 🎤 Live Presentations
This project was presented as a **live lightning talk/demo** at:
- **Community Over Code 2025**
- **PyData Boston 2025**

The demo showcased how **distributed systems behave under node failures**, using real hardware, real data, and real infrastructure components.

---

## 🚀 Why This Project Matters
Most distributed systems tutorials stop at “it works.”

This project goes further:
- It **expects failure**
- It **embraces imperfect networks**
- It shows how **production systems are designed to survive**, not avoid, failure

---

## 📚 Technologies Used
- Apache Cassandra (distributed database)
- Java + Spring Boot (REST APIs)
- React + TypeScript (frontend dashboard)
- Recharts (data visualization)
- Raspberry Pi 4 (2 nodes)
- DS18B20 temperature sensor
- Docker & Docker Compose
- Python (sensor reader & heartbeat scripts)
- Switch & Ethernet cables

---

## Diagram
<img width="1861" height="3052" alt="Untitled diagram-2026-01-04-022042" src="https://github.com/user-attachments/assets/5750e4dc-0bcf-4efd-9613-9abe371f8736" />

---

## 🧠 Takeaway
> Distributed systems are not about avoiding failure;  
> they are about **continuing to function when failure is inevitable**.

