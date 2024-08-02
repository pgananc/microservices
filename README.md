# Microservices

# Ejecución Docker Compose con Microservices

Este proyecto usa Docker Compose para ejecutar RabbitMQ y Eureka Server para comunicacion de microservicios y descubrimeinto de servicios.

## Prerequisitos

Docker [Aqui](https://www.docker.com/get-started).

## Getting Started

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/pgananc/microservices.git

   ```

2. Ir al directorio:

   ```bash
   cd microservices

   ```

3. Ingresar a la carpeta `docker`

   ```bash
   cd docker
   ```

## Ejecución con Docker Compose

## Start

---

```bash
docker-compose up
```

Para ejecutar en background:

```bash
docker-compose up -d
```

## Stop

```bash
docker-compose down

```

## Acceso a servicios

- RabbitMQ:
  - Web UI: http://localhost:15672
  - AMQP port: localhost:5672
- Eureka Server:
  - Dashboard: http://localhost:8761

## EndPoints

- Client Service:
  - API: http://localhost:8080/clientes
- Account Service:
  - API: http://localhost:8081/cuentas
  - API: http://localhost:8081/movimientos

## Recursos para pruebas.

```bash
1. Script de base de datos: BaseDatos.sql
2. EndPoints: cuenta-bancaria.postman_collection.json

```

**DISFRUTA DEL BACK END!!!**

**DEVELOPER:** Pablo Ganan

**Correo:** pablos.ganan@gmail.com
