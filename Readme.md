# Antes de comenzar

El siguiente documento es un instructivo básico para poder realizar las debidas validaciones para el proyecto de prueba técnica realizado.


## Descargar el proyecto.

Antes de comenzar debes descargar el proyecto /demoBank a tu repositorio local para poder importarlo a tu IDE preferido.

## Importar el proyecto

Una vez descargado el proyecto el siguiente paso es importarlo a nuestra herramienta de desarrollo preferida, en mi caso utilicé IntelliJ Idea.

## Archivos importantes

**DBConnection.java:** En este archivo se debe cambiar la url de conexión a la base de datos, en mi caso utilice Azure SQL.
**application.properties:** De igual manera en este archivo se debe cambiar la propiedad *spring.datasource.url*.

## Seguridad

Ambos microservicios imlementan un nivel de seguridad con JWT lo que básicamente hace es generar un token el cual va a ser necesario para realizar nuestras pruebas con nuestras APIs.
Este token se debe enviar como parte del header del HTTP request de nuestra API.

## Obtener token

Para obtener el token necesario, ambos microservicios incluyen un endpoint el cual recibe un usuario y una contraseña (para efectos de prueba no importan los valores que se utilicen siempre va a regresar el token) con los cuales vamos a obtener el token necesario para nuestras pruebas.

**Endpoint:** http://localhost:8081/user?user=fabio&password=123

**Importante**: para la comunicación entre microservicios no se incluyó el obtener el token de manera automática, así que se debe obtener el token mediante el endpoint indicado en el servicio de **Accounts** e ir a actualizarlo en el archivo **TransactionServiceImpl** del servicio Transaction en la línea 24.

## Export a file

You can export the current file by clicking **Export to disk** in the menu. You can choose to export the file as plain Markdown, as HTML using a Handlebars template or as a PDF.


# Documentación

Para un mejor manejo de la documentación de las APIs creadas, para cada servicio se implemento Swagger, esta documentación se puede visualizar en los siguientes enlaces:
Transaction service: http://localhost:8081/swagger-ui/index.html#
Account service: http://localhost:8082/swagger-ui/index.html#