# Selenium Módulo 5

<p align="center">
  <img src="https://img.shields.io/badge/Java-11-orange" alt="Java 11" />
  <img src="https://img.shields.io/badge/Maven-Build-blue" alt="Maven" />
  <img src="https://img.shields.io/badge/Selenium-4.18.1-43B02A" alt="Selenium 4.18.1" />
  <img src="https://img.shields.io/badge/TestNG-7.9.0-FF6A00" alt="TestNG 7.9.0" />
  <img src="https://img.shields.io/badge/WebDriverManager-5.7.0-8A2BE2" alt="WebDriverManager 5.7.0" />
</p>

## Descripción general

Este proyecto corresponde al quinto módulo de práctica en automatización de pruebas con Selenium, Java y TestNG. Su objetivo principal es consolidar habilidades de QA Automation mediante la ejecución de casos reales sobre aplicaciones web demo y sitios de práctica, aplicando automatización front-end, validaciones funcionales, manejo de alertas, cargas de archivos, lectura de tablas HTML y uso de datos desde Excel.

La carpeta `SeleniumModulo5` agrupa una colección de suites y pruebas enfocadas en escenarios de flujo de usuario, validación de formularios, autenticación, manejo de estados del navegador y ejecución de pruebas con parámetros.

## Objetivo del proyecto

El proyecto está orientado a demostrar la aplicación de Selenium en escenarios reales de automatización, incluyendo:

1. validación de login y flujo de compra;
2. interacción con formularios y páginas dinámicas;
3. lectura de datos desde archivos Excel;
4. ejecución de pruebas sobre diferentes aplicaciones de demostración;
5. manejo de desafíos avanzados como headless y modo incognito.

## Stack tecnológico

El proyecto utiliza las siguientes herramientas y dependencias:

- Java 11
- Maven
- Selenium WebDriver 4.18.1
- TestNG 7.9.0
- WebDriverManager 5.7.0
- Apache POI para lectura de Excel
- Commons IO
- ChromeDriver gestionado por WebDriverManager

## Alcance funcional

El módulo contiene pruebas sobre varias plataformas de práctica y demos, entre ellas:

- `saucedemo` para validar login, flujo de compra y checkout;
- `orangehrm` para administración de usuarios y empleados;
- `automationpractice` para pruebas de login, upload, alertas y lectura de tablas HTML;
- `pruebas` para ejercicios complementarios de búsqueda y navegación web.

## Estructura del proyecto

La organización del proyecto es la siguiente:

- `src/test/java/saucedemo/`  
  Contiene las pruebas del desafío de Sauce Demo, incluyendo flujo de compra y validaciones de orden.

- `src/test/java/orangehrm/`  
  Agrupa la automatización de pruebas sobre OrangeHRM con validaciones de usuarios y empleados.

- `src/test/java/automationpractice/`  
  Incluye escenarios de login inválido, carga de archivos, manejo de alertas y lectura de tablas HTML.

- `src/test/java/pruebas/`  
  Contiene ejercicios complementarios de navegación y búsqueda web.

- `src/test/java/utils/`  
  Incluye utilidades para trabajo con datos desde Excel.

- `testng.xml`  
  Suite principal del módulo, con varios escenarios de prueba coordinados por TestNG.

- `testng-desafio6.xml`  
  Suite específica para el desafío 6 donde se ejecuta la misma prueba con parámetros de headless e incognito.

## Casos de prueba incluidos

Dentro del proyecto se pueden identificar los siguientes tipos de automatización:

- login con datos desde Excel;
- flujo de compra completo en Sauce Demo;
- manejo de errores y validaciones de mensajes;
- carga de archivos en formularios web;
- manejo de alertas y pop-ups;
- lectura de contenido de tablas HTML;
- pruebas sobre gestión de usuarios en OrangeHRM;
- ejecución con parámetros de navegador y modo de ejecución.

## Principales clases de prueba

Algunas de las clases más relevantes son:

- `SauceDemoTest`  
  Automatiza el flujo de compra en Sauce Demo.

- `Desafio6SauceDemoTest`  
  Versión del desafío 6 con configuración headless/incognito.

- `Test_BonusTrack5b`  
  Pruebas de administración de usuarios y empleados sobre OrangeHRM.

- `Etapa51Test` a `Etapa54Test`  
  Ejercicios de Automation Practice para login, upload, alertas y tablas HTML.

## Cómo ejecutar las pruebas

### Requisitos previos

Antes de ejecutar el proyecto, asegurate de tener instalado:

- Java 11
- Maven
- Chrome disponible en el equipo
- conexión a internet para la descarga automática de drivers con WebDriverManager

### Ejecutar la suite principal

Desde la raíz de esta carpeta, podés ejecutar:

```bash
mvn test
```

Esto utilizará la configuración por defecto definida en `pom.xml` y la suite de TestNG `testng.xml`.

### Ejecutar una suite específica

Si querés correr una suite en particular, por ejemplo la del desafío 6:

```bash
mvn -DsuiteXmlFile=testng-desafio6.xml test
```

### Ejecutar con una suite personalizada

También podés cambiar la suite desde la línea de comando, por ejemplo:

```bash
mvn -DsuiteXmlFile=testng.xml test
```

## Características destacadas

Este proyecto incorpora varios elementos que son relevantes desde el punto de vista de QA Automation:

- uso de esperas explícitas para manejar elementos dinámicos;
- lectura parametrizada de datos desde Excel;
- manejo del navegador mediante WebDriverManager;
- ejecución en distintos modos de navegador;
- validaciones con `Assert` de TestNG;
- organización de pruebas en suites y clases temáticas.

## Observaciones

El proyecto tiene un enfoque claramente educativo y práctico. No se trata solo de ejecutar pruebas, sino de construir una base sólida en automatización web con Selenium, aprendiendo a manejar patrones de espera, localización de elementos, validación de resultados y estructura de suites.

## Resumen

`SeleniumModulo5` es un proyecto de automatización orientado a la práctica avanzada de Selenium con Java y TestNG. A través de escenarios reales de login, compra, uploads, alertas, tablas HTML y gestión de usuarios, se refleja un nivel sólido de aplicación de herramientas de QA Automation en entorno de entrenamiento y validación funcional.
