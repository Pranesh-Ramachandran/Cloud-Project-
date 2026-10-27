# 🚀 Cloud-Based Spring Boot Application with Automated CI/CD on Google Cloud Run

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Containerized-blue.svg)](https://www.docker.com/)
[![Google Cloud Run](https://img.shields.io/badge/Google%20Cloud-Cloud%20Run-4285F4.svg)](https://cloud.google.com/run)
[![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-2088FF.svg)](https://github.com/features/actions)

An interview-grade, production-ready DevOps demonstration showing how a modern Java / Spring Boot microservice is built, tested, packaged into a multi-stage Docker container, stored in Google Artifact Registry, and deployed serverless onto Google Cloud Run using fully automated GitHub Actions CI/CD pipelines.

---

## 🏗️ Architecture & Pipeline Flow

```text
[ Developer Machine ]
        │
        │ git push main
        ▼
[ GitHub Repository ]
        │
        │ Webhook triggers
        ▼
[ GitHub Actions Pipeline ]
  ├── 1. Checkout code
  ├── 2. Setup JDK 17 & cache Maven dependencies
  ├── 3. Execute unit tests (./mvnw clean test)
  ├── 4. Authenticate to GCP (IAM Service Account / Keyless OIDC)
  ├── 5. Build multi-stage Docker image
  ├── 6. Push image to Google Artifact Registry
  └── 7. Deploy revision to Google Cloud Run (Serverless HTTPS)
        │
        ▼
[ Google Cloud Run ] ───▶ Public HTTPS URL (Auto-scaling 0 to N)
```

---

## 🛠️ Tech Stack & Key Concepts Demonstrated

| Technology | Role | DevOps & Cloud Concept Demonstrated |
| :--- | :--- | :--- |
| **Java 17 & Spring Boot** | Backend REST API | Modern microservice development, 12-factor application config |
| **Maven & Wrapper (`mvnw`)** | Build & Dependency Management | Reproducible hermetic builds across development and CI environments |
| **Docker** | Containerization | Multi-stage build optimization (minimal attack surface, smaller image size) |
| **Google Artifact Registry** | Container Registry | Secure private Docker image repository with granular IAM permissions |
| **Google Cloud Run** | Serverless Compute | Containerized microservice deployment, automatic HTTPS, scale-to-zero |
| **Google Cloud IAM** | Security & Access Control | Principle of Least Privilege, Service Accounts, secure role delegation |
| **GitHub Actions** | CI/CD Automation | Continuous Integration, automated testing, Continuous Deployment |

---

## 📡 API Endpoints

| HTTP Method | Endpoint | Description | Sample Output |
| :--- | :--- | :--- | :--- |
| `GET` | `/` | Application index & welcome status | `{"application":"Cloud DevOps Demo","status":"running","message":"Hello from Pranesh's cloud project!"}` |
| `GET` | `/health` | Liveness & Readiness health probe | `{"status":"UP"}` |

---

## 💻 Local Development

### Prerequisites
- JDK 17+
- Git

### Run locally
```powershell
# Clone the repository
git clone https://github.com/Pranesh-Ramachandran/Cloud-Project-.git
cd Cloud-Project-

# Run tests
.\mvnw.cmd test

# Run application locally (port 8080)
.\mvnw.cmd spring-boot:run
```

Test endpoints:
- `http://localhost:8080/`
- `http://localhost:8080/health`

---

## 🔒 Security Best Practices Implemented
- **Dynamic Port Injection**: Application respects `PORT` environment variable via `server.port=${PORT:8080}`, complying with Cloud Run runtime contract.
- **Multi-Stage Dockerfile**: Uses Temurin JRE runtime in final stage to eliminate build tools and compilers from production runtime.
- **Automated Quality Gates**: The CI/CD pipeline runs unit tests before building or pushing the container image.

