# EduIT - Curso 2

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange" alt="Java 17" />
  <img src="https://img.shields.io/badge/Maven-Build-blue" alt="Maven" />
  <img src="https://img.shields.io/badge/Selenium-4.x-43B02A" alt="Selenium 4.x" />
  <img src="https://img.shields.io/badge/TestNG-6.x-FF6A00" alt="TestNG" />
  <img src="https://img.shields.io/badge/MySQL-JDBC-4479A1" alt="MySQL JDBC" />
</p>

## Descripción del proyecto

Este proyecto corresponde a la carpeta de práctica del curso de automatización de pruebas con Selenium, Java y TestNG. Su finalidad es consolidar conceptos de QA Automation a través de ejercicios de interacción con aplicaciones web, validación de formularios, ejecución en distintos navegadores y manejo de datos de prueba.

La carpeta `edu-it-curso2` reúne una serie de clases de prueba, suites de ejecución y utilidades desarrolladas para aprender y aplicar buenas prácticas en automatización de pruebas funcionales.

## Objetivo principal

El objetivo del proyecto es demostrar, en un contexto académico y práctico, cómo automatizar escenarios web mediante:

- Selenium WebDriver para interacción con la interfaz del navegador;
- TestNG como framework de ejecución de pruebas;
- Java como lenguaje principal para la lógica de automatización;
- Maven como herramienta de construcción y gestión de dependencias;
- utilidades complementarias para acceso a bases de datos y manejo de datos externos.

## Alcance técnico

El proyecto abarca varios aspectos clave del workflow de automatización:

- validación de formularios y flujos de usuario;
- navegación en páginas web mediante selectores y esperas;
- carga de archivos en formularios web;
- ejecución de pruebas en Chrome, Firefox y Edge;
- manejo de datos usando archivos y consultas JDBC;
- organización de suites de prueba con archivos XML de TestNG.

## Stack tecnológico

Las tecnologías y librerías que aparecen en este proyecto son las siguientes:

- Java 17
- Maven
- Selenium WebDriver 4.x
- TestNG 6.x
- JUnit 5
- Apache POI
- MySQL Connector/J
- ChromeDriver, GeckoDriver y EdgeDriver

## Estructura del proyecto

La organización del proyecto es la siguiente:

- `src/test/java/EducacionIT_75402/EduIT/`  
  Contiene ejercicios y prácticas del curso, con clases dedicadas a pruebas y validaciones de conceptos básicos.

- `src/test/java/pruebas/`  
  Incluye pruebas de automatización orientadas a flujos reales, cross-browser y casos de negocio.

- `src/test/java/paginas/`  
  Agrupa clases tipo Page Object para encapsular la interacción con la interfaz y mejorar la mantenibilidad.

- `src/test/java/utils/`  
  Contiene utilidades de apoyo, incluyendo acceso a base de datos y manejo de datos.

- `CrossBrowserTest.xml` y `OrangeHRMSuite.xml`  
  Archivos de configuración de TestNG para ejecutar suites de pruebas por navegador.

## Casos de prueba representativos

Dentro del proyecto se pueden identificar varios tipos de ejercicios y escenarios, entre ellos:

- registro de usuarios;
- navegación y validación de páginas de e-commerce y aplicaciones demo;
- automatización de formularios con selects, inputs y radios;
- carga de archivos en formularios web;
- comprobación de ejecución en múltiples navegadores;
- pruebas de conexión y consulta a base de datos MySQL.

## Cómo correr las pruebas

### 1. Requisitos previos

Asegurate de tener instalado lo siguiente:

- JDK 17 o superior
- Maven
- un navegador compatible con Selenium (Chrome, Firefox o Edge)
- los drivers correspondientes configurados correctamente en el entorno

### 2. Ejecutar toda la suite de pruebas

Desde la carpeta del proyecto, se puede ejecutar:

```bash
mvn clean test
```

Esto compilará el proyecto y ejecutará las pruebas del repositorio con Maven.

### 3. Ejecutar una clase específica

Si querés correr una clase determinada:

```bash
mvn -Dtest=pruebas.CrossBrowserTest test
```

### 4. Ejecutar desde archivos XML de TestNG

El repositorio incluye suites configuradas en XML para pruebas por navegador, por ejemplo:

```bash
mvn test -Dsurefire.suiteXmlFiles=CrossBrowserTest.xml
```

> El comando exacto puede variar según la configuración local del entorno y el runner que utilices, pero la idea general es ejecutar la suite desde Maven o desde el runner de TestNG.

## Buenas prácticas que refleja el proyecto

Este proyecto permite visualizar varias buenas prácticas de automatización, como:

- separar la lógica de prueba de la interacción con la interfaz;
- reutilizar utilidades comunes;
- estructurar escenarios de forma clara y mantenible;
- generar suites de ejecución por contexto o navegador;
- aplicar Selenium con una base sólida de conceptos de localización y espera.

## Observaciones finales

Este repositorio tiene un enfoque claramente formativo. Su valor no está solo en la automatización en sí, sino en la forma en que se utilizan herramientas como Selenium, Java, Maven y TestNG para construir una base sólida en QA Automation.

## Resumen

`edu-it-curso2` es un proyecto de práctica y aprendizaje enfocado en automatización de pruebas web con Selenium. A través de ejemplos reales, clases de prueba, utilidades y suites de ejecución, se consolida el uso de herramientas modernas para la validación funcional de aplicaciones web.
