# 1. Descripción General del Proyecto

**Nombre del Sistema:**  
**Grupo Alimentos Perú S.A.C. – Sistema Centralizado de Gestión de Tiendas**

**Tipo de Sistema:**  
ERP liviano (Enterprise Resource Planning simplificado)

**Descripción Breve:**  
El sistema está orientado a gestionar de manera centralizada las operaciones de una cadena de minimarkets ubicados en Chosica y Chaclacayo, con el objetivo de controlar productos, ventas y usuarios según su rol dentro de la organización.

---

# 2. Objetivo General

Desarrollar un sistema ERP liviano que permita la gestión centralizada de múltiples tiendas, optimizando la administración de productos, ventas y usuarios con distintos niveles de acceso.

---

# 3. Objetivos Específicos

* Controlar los productos de cada tienda, incluyendo nombre, precio, stock, categoría y ubicación.
* Gestionar usuarios con roles diferenciados (Gerente de Operaciones, Gerente de Tienda, Operador de Caja).
* Registrar y consultar ventas filtradas por tienda, fecha y rango de días.
* Brindar al Gerente de Operaciones una visión global del desempeño de todas las tiendas.
* Mejorar la eficiencia administrativa mediante el uso de una base de datos centralizada y un backend seguro.
* Escalar el sistema en el futuro hacia una arquitectura moderna con microservicios y autenticación JWT.

---

# 4. Problemática a Resolver

Actualmente, la empresa gestiona productos y ventas de forma manual y descentralizada, lo cual genera:

* Errores humanos en el control de inventarios.
* Duplicidad de datos entre tiendas.
* Falta de trazabilidad de las ventas.
* Dificultad para analizar métricas globales.

Con este sistema, toda la información se centraliza en una única plataforma, lo que permite:

* Mejorar la precisión de los registros.
* Reducir tiempos administrativos.
* Facilitar la toma de decisiones estratégicas.

---

# 5. Roles del Sistema

## 🔹 Gerente de Operaciones

* Acceso total al sistema.
* Puede visualizar, crear, modificar y eliminar datos de todas las tiendas.
* Administra usuarios, roles, categorías y reportes globales.
* Supervisa el rendimiento general y analiza estadísticas de ventas.

**Nombre alternativo profesional:** Administrador General o Director de Operaciones.

## 🔹 Gerente de Tienda

* Tiene acceso únicamente a su tienda asignada.
* Puede gestionar productos, precios, categorías y ver las ventas locales.
* No puede modificar datos de otras tiendas.
* Puede registrar nuevos operadores de caja para su tienda.

**Nombre alternativo profesional:** Supervisor de Sucursal o Administrador de Tienda.

## 🔹 Operador de Caja

* Encargado de registrar las ventas del día.
* Puede crear nuevas ventas y visualizar los productos disponibles.
* No puede eliminar ni actualizar productos ni usuarios.
* Acceso limitado a reportes básicos de ventas diarias.

**Nombre alternativo profesional:** Cajero/a Operativo o Asistente de Punto de Venta.

---

# 6. Tecnologías a Utilizar

### **Backend (Java Spring Boot)**

* Spring Boot 3.5.7
* Spring Data JPA
* Spring Web
* Spring Security (modo básico inicialmente)
* PostgreSQL (base de datos relacional)
* Flyway (migraciones de base de datos)
* Lombok (reducción de código repetitivo)
* ModelMapper (mapeo de DTOs a Entities)

### **Frontend**

* React.js 18+
* HTML5, CSS3, JavaScript (ES6+)
* Axios o Fetch API para la comunicación con el backend
* Vite como build tool

### **Otras Herramientas**

* Postman / Insomnia (para probar endpoints)
* GitHub / Git (control de versiones)
* Visual Studio Code (desarrollo frontend)
* IntelliJ IDEA / VS Code (desarrollo backend)

---

# 7. Dependencias del Proyecto (Backend - Maven)

| Dependencia                                       | Descripción                                |
| ------------------------------------------------- | ------------------------------------------ |
| spring-boot-starter-web                           | Permite exponer controladores REST         |
| spring-boot-starter-data-jpa                      | Acceso y persistencia de datos con JPA     |
| postgresql                                        | Controlador JDBC para la base de datos     |
| flyway-core                                       | Gestión de versiones de la base de datos   |
| lombok                                            | Anotaciones para reducir código repetitivo |
| modelmapper                                       | Conversión entre entidades y DTOs          |
| spring-boot-devtools                              | Recarga automática en desarrollo           |
| spring-boot-starter-security (opcional por ahora) | Autenticación y autorización básica        |

---

# 8. Plan de Evolución Tecnológica

| Actual                       | Futuro                                                  |
| ---------------------------- | ------------------------------------------------------- |
| ModelMapper                  | Migrar a MapStruct para mejor rendimiento            |
| React.js                     | Migrar a Angular para entornos empresariales grandes |
| Spring Security (Basic Auth) | Implementar JWT (JSON Web Token)                     |
| Monolito                     | Escalar a microservicios (Spring Cloud)              |
| PostgreSQL                   | Añadir Redis o MongoDB para caching y analítica      |

---

# 9. Diseño de Base de Datos

Se plantea un desarrollo por fases incrementales para facilitar el aprendizaje y la escalabilidad.

## **Fase 1 – Estructura Base**

### **1. categoria**

* id_categoria (PK, serial)
* nombre (varchar 100)
* descripcion (varchar 255)

### **2. producto**

* id_producto (PK, serial)
* nombre (varchar 150)
* precio (decimal 10,2)
* stock (int)
* id_categoria (FK → categoria)
* id_tienda (FK → tienda)

### **3. tienda**

* id_tienda (PK, serial)
* nombre (varchar 100)
* direccion (varchar 200)
* distrito (varchar 100)

---

## **Fase 2 – Módulo de Ventas**

### **4. venta**

* id_venta (PK, serial)
* fecha_venta (timestamp)
* total (decimal 10,2)
* id_usuario (FK → usuario)
* id_tienda (FK → tienda)

### **5. detalle_venta**

* id_detalle (PK, serial)
* id_venta (FK → venta)
* id_producto (FK → producto)
* cantidad (int)
* subtotal (decimal 10,2)

---

## **Fase 3 – Módulo de Usuarios (Auth clásico)**

### **6. rol**

* id_rol (PK, serial)
* nombre_rol (varchar 50)

### **7. usuario**

* id_usuario (PK, serial)
* nombre_usuario (varchar 100)
* contraseña (varchar 255)
* id_rol (FK → rol)
* id_tienda (FK → tienda)

**Relación:**

* Un rol puede tener muchos usuarios.
* Un usuario pertenece a un solo rol.
* Varios usuarios (por ejemplo cajeros o gerentes de tienda) pueden compartir el mismo rol.

---

# 10. Alcance del Proyecto

## **Alcance inicial**

* Desarrollo del backend en Spring Boot.
* Conexión con base de datos PostgreSQL.
* API REST para CRUD de productos, categorías y tiendas.
* Módulo básico de autenticación (Spring Security básico).
* Interfaz web en React para el manejo de inventarios y consultas.
* Migraciones de base de datos con Flyway.

## **Futuras extensiones**

* Reportes analíticos de ventas por rango de fechas.
* Dashboard centralizado para el gerente general.
* Implementación de JWT, auditoría y microservicios.

---
