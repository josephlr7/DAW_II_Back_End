# 🚀 Backend E-Commerce - Arquitectura de Microservicios

Este repositorio contiene la arquitectura backend de microservicios distribuida para la plataforma de comercio electrónico empresarial desarrollada con **Spring Boot 3**, **Spring Cloud**, **REST**, **SOAP (JAX-WS)** y **MySQL**.

> **Nota:** La capa de presentación (Frontend SPA en React 19 + Vite) se encuentra alojada en su propio repositorio independiente: [DAW_II-Front_End](https://github.com/josephlr7/DAW_II-Front_End.git).

---

## 🏛️ Topología de Servicios

| Servicio | Puerto | Descripción | Protocolo / Tecnología |
| :--- | :---: | :--- | :--- |
| **EUREKA-SERVER** | `8761` | Servidor de Registro y Descubrimiento dinámico | Netflix Eureka |
| **GATEWAY-Service** | `8080` | Punto de entrada perimetral, CORS y enrutamiento dinámico (`lb://`) | Spring Cloud Gateway |
| **Config-Service** | `8888` | Servidor de configuración centralizada de propiedades | Spring Cloud Config |
| **Auth-Service** | `8085` | Autenticación, generación de tokens JWT y control de roles (RBAC) | REST / Spring Security |
| **Product-Service** | `8096` | Catálogo de productos, control de precios y multimedia Cloudinary | REST / Spring Data JPA |
| **Identity-Service** | `8091` | Consulta en tiempo real de DNI (RENIEC) y RUC (SUNAT) | REST / HTTPS API Externa |
| **Order-Service** | `8090` | Orquestador transaccional del flujo de compras | REST / OpenFeign |
| **Payment-Service** | `8092` | Procesamiento y simulación de pagos con tarjeta | REST / JSON |
| **Billing-Service** | `8084` | Facturación electrónica: emisión de Boletas y Facturas | **SOAP / JAX-WS (WSDL)** |
| **Shipping-Service** | `8097` | Logística, gestión de despacho domiciliario y guías | REST / JSON |
| **Inventory-Service** | `9099` | Gestión de stock y existencias físicas en almacén | REST / JSON |
| **Notification-service** | `9098` | Registro y emisión de alertas y confirmaciones de compra | REST / JSON |

---

## 🛠️ Tecnologías Empleadas

* **Lenguaje:** Java 17 / 21
* **Framework:** Spring Boot 3.x, Spring Cloud 2023+
* **Interoperabilidad:** RESTful APIs (JSON) + SOAP Web Service (XML/WSDL)
* **Bases de Datos:** MySQL 8.0 (Patrón *Database-per-Service*)
* **Seguridad:** JWT (JSON Web Tokens) con cifrado simétrico
* **Orquestación:** Docker & Docker Compose
* **Despliegue en la Nube:** AWS EC2

---

## ⚡ Requisitos y Ejecución

### Prerrequisitos
* Java Development Kit (JDK) 17 o superior
* Apache Maven 3.8+
* Docker & Docker Compose
* MySQL 8.0+

### Levantar con Docker Compose
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/josephlr7/DAW_II_Back_End.git
   cd DAW_II_Back_End
   ```

2. Ejecutar la orquestación de contenedores:
   ```bash
   cd mi-proyecto-floci
   docker-compose up -d --build
   ```

3. Verificar estado de los servicios en Eureka Dashboard:
   * URL: `http://localhost:8761`

4. Acceso al API Gateway:
   * URL: `http://localhost:8080`
