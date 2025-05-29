# ⚙️ Configuración de la Base de Datos (MySQL)

Este repositorio contiene el script necesario para crear la base de datos utilizada por los microservicios de **Usuarios** y **Tareas**.

## 🧱 Paso 1: Crear la base de datos

1. Asegúrate de tener **MySQL Server** instalado y en ejecución. Puedes usar **MySQL Workbench** como herramienta gráfica.
2. Abre el archivo SQL incluido en este repositorio (`mi_proyecto_base_datos.sql`).
3. Ejecuta el script para crear las base de datos `tareas` y `usuarios` con sus respectivas tablas.

> ⚠️ Este script debe ejecutarse **una sola vez** antes de iniciar los microservicios.

---

## ⚙️ Paso 2: Configurar la conexión en los microservicios

En el `Config Server` en los archivos de propiedades de **cada microservicio** (`task-service` y `user-service`), asegúrate de tener configurada la conexión a MySQL:

Para el caso de `task-service`:
spring.datasource.url=jdbc:mysql://localhost:3306/tareas?useSSL=false&serverTimezone=UTC
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA

Para el caso de `user-service`:
spring.datasource.url=jdbc:mysql://localhost:3306/usuarios?useSSL=false&serverTimezone=UTC
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
