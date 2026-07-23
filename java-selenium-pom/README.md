# Selenium Java Test Project

Proyecto de automatización con Selenium, Java, Maven y TestNG para ejecutar pruebas funcionales en navegadores Chrome.

## Descripción

Este repositorio contiene una colección de pruebas automatizadas para distintos escenarios, incluyendo:

- Sauce Demo
- OrangeHRM
- Automation Practice
- Wikipedia
- Desafío 6 con Selenium

## Tecnologías

- Java 11
- Maven
- TestNG
- Selenium WebDriver 4
- WebDriverManager
- Apache POI para lectura de Excel

## Requisitos previos

Antes de ejecutar las pruebas, asegurate de tener instalado:

- Java JDK 11+
- Maven
- Google Chrome

## Estructura principal

- `pom.xml`: configuración de Maven y dependencias
- `testng.xml`: suite principal de pruebas
- `testng-desafio6.xml`: suite específica para el desafío 6
- `src/test/java`: clases de pruebas
- `src/test/resources`: datos de prueba y recursos

## Ejecutar todas las pruebas

Desde la raíz del proyecto:

```bash
mvn test
```

## Ejecutar una suite específica

Para ejecutar la suite del desafío 6:

```bash
mvn -DsuiteXmlFile=testng-desafio6.xml test
```

## Ejecutar con limpieza previa

```bash
mvn clean test
```

## Resultado esperado

Las ejecuciones generan reportes de TestNG y dejan evidencia en la carpeta de salida de Maven.

## Nota

Las pruebas usan `WebDriverManager` para resolver el driver de Chrome automáticamente, por lo que no es necesario descargarlo manualmente.
