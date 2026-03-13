Spring Boot CI/CD Pipeline

Este repositorio contiene una API web sencilla desarrollada en Java utilizando Spring Boot, junto con un pipeline de Integración Continua y Entrega Continua (CI/CD) configurado mediante GitHub Actions.
El objetivo del proyecto es demostrar cómo automatizar el proceso de compilación, pruebas y preparación del despliegue de una aplicación.


Estructura del Proyecto

HelloController.java
Contiene el código principal de la API. Define dos endpoints básicos (/ y /health) que devuelven respuestas simples.

HelloControllerTest.java
Archivo de pruebas unitarias que utiliza JUnit para verificar que el sistema de testing funciona correctamente.

pom.xml
Archivo de configuración de Maven, donde se definen las dependencias del proyecto y la configuración de compilación.

Dockerfile
Archivo que define cómo empaquetar la aplicación en una imagen utilizando Docker.

.github/workflows/pipeline.yml
Archivo de configuración de GitHub Actions que define el pipeline de CI/CD.


Funcionamiento de la API

El archivo HelloController.java define los endpoints de la aplicación.
Un endpoint es una dirección web a la cual un cliente puede enviar una petición para obtener una respuesta.

Ejemplo de código:

@RestController
public class HelloController {
    @GetMapping("/")
    public String hola() {
        return "Hola desde la API";
    }
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}

Explicación

@RestController
Indica que la clase es un controlador web, es decir, un componente que recibe peticiones HTTP y devuelve respuestas.

@GetMapping("/")
Define un endpoint accesible desde:
http://localhost:8080/
Cuando se accede a esa dirección, se ejecuta el método hola() y devuelve el texto:
Hola desde la API

@GetMapping("/health")
Define otro endpoint accesible desde:
http://localhost:8080/health
Este endpoint devuelve "OK" y normalmente se utiliza para verificar que el servicio está funcionando correctamente.


Pruebas Automatizadas

El proyecto incluye pruebas unitarias utilizando JUnit.

Archivo:
HelloControllerTest.java

Código:
@Test
void testSimple() {
    assertTrue(true);
}

Explicación

@Test
Indica que el método es una prueba automática.
assertTrue(true)
Verifica que la condición sea verdadera.
Aunque este test es simple, permite demostrar que el pipeline puede ejecutar pruebas automáticamente.
Las pruebas son importantes porque permiten detectar errores en el código antes de desplegar la aplicación.


Flujo de CI/CD (GitHub Actions)

El pipeline de CI/CD está definido en el archivo:
.github/workflows/pipeline.yml
Este archivo describe las tareas automáticas que GitHub ejecutará cada vez que se suba código al repositorio.

Cuándo se Ejecuta (Triggers)
El pipeline se activa cuando ocurre el siguiente evento:

Push al repositorio
Cuando el desarrollador ejecuta:
git push
El código se envía a GitHub, y automáticamente se inicia el pipeline de GitHub Actions.

Fases del Pipeline (Jobs)
El pipeline incluye procesos de Integración Continua (CI) y preparación para Entrega Continua (CD).

1. Continuous Integration (CI)
Esta fase verifica que el código funciona correctamente.
Pasos del proceso:

Checkout code
GitHub descarga el código del repositorio.
Set up Java
Se instala Java 17 en el entorno de ejecución.
Build with Maven
Se ejecuta Maven, que compila el proyecto y genera el archivo .jar.
Run tests
Se ejecutan las pruebas unitarias definidas en HelloControllerTest.java.
Si alguna prueba falla, el pipeline se detiene.
Esto permite detectar errores automáticamente antes de que el software sea desplegado.

2. Continuous Deployment (CD)
La segunda parte prepara la aplicación para su despliegue utilizando Docker.

Archivo utilizado:
Dockerfile

Código:
FROM openjdk:17
COPY target/cicd-demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

Explicación
FROM openjdk:17
Utiliza una imagen base que ya tiene Java 17 instalado.

COPY target/...jar app.jar
Copia el archivo .jar generado por Maven dentro del contenedor.
Ese archivo contiene toda la aplicación.

ENTRYPOINT ["java","-jar","/app.jar"]
Define el comando que se ejecutará cuando el contenedor inicie.
En este caso, ejecuta la aplicación Java.


Para ejecutar la aplicación de forma local se puede utilizar Maven.

Ejecutar la aplicación
mvn spring-boot:run

Luego abrir en el navegador:
http://localhost:8080

Endpoint principal:
http://localhost:8080/

Endpoint de salud del sistema:
http://localhost:8080/health

Conclusión

Este proyecto demuestra un flujo básico de CI/CD aplicado a una aplicación Java.
El proceso incluye:
Desarrollo de una API utilizando Spring Boot
Control de versiones con Git
Almacenamiento del código en GitHub
Ejecución automática de pruebas mediante GitHub Actions

Preparación del despliegue mediante Docker

Este tipo de automatización permite mejorar la calidad del software y reducir errores durante el proceso de desarrollo y despliegue.
