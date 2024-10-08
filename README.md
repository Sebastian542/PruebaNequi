# Franquicias API

## Descripción
API para gestionar franquicias, sucursales y productos.

## Requisitos
- Java
- MySQL

## Ejecución Local
1. Clonar el repositorio.
2. Crear una base de datos en MySQL llamada `franquicias_db`.
3. Ejecutar `mvn spring-boot:run` para iniciar la aplicación.

## Endpoints
- POST /api/franquicias
- POST /api/franquicias/{franquiciaId}/sucursales
- POST /api/franquicias/{franquiciaId}/sucursales/{sucursalId}/productos
