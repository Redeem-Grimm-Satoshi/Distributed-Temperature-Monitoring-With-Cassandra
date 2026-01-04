
# Distributed Systems Demo with Apache Cassandra & Raspberry Pi

## 📌 Overview
The goal of this project is to **demonstrate core distributed systems principles** -  
**replication, fault tolerance, eventual consistency, and observability** - using a **minimal but realistic setup**.

Rather than relying on large cloud infrastructure, this system uses **two Raspberry Pis**, a real sensor, and a production-grade distributed database to show how **real-world distributed systems behave under normal operation and failure conditions**.

---

## 🎯 Key Concepts Demonstrated
- **Replication** - Data is written to multiple nodes to ensure durability  
- **Fault Tolerance** - The system continues to operate when a node fails  
- **Eventual Consistency** - Replicas converge over time after failures  
- **Observability** - System behavior is visible through metrics and dashboards  

---

## 🏗️ System Architecture

### Components
- **Seed Node (Raspberry Pi 1)**  
  - Connected to a temperature sensor  
  - Acts as the initial Cassandra seed node  
  - Publishes sensor data to the backend  

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
- Apache Cassandra
- Java (Spring Boot)
- React + TypeScript
- Raspberry Pi
- Temperature Sensor
- REST APIs

---

## 🧠 Takeaway
> Distributed systems are not about avoiding failure;  
> they are about **continuing to function when failure is inevitable**.
