# Java Selenium Project

Proyecto de automatización de pruebas web desarrollado en Java con Selenium WebDriver y TestNG.

## Descripción

Este repositorio tiene como objetivo practicar y demostrar la automatización de flujos en navegadores web mediante pruebas funcionales. El proyecto incluye ejemplos de navegación, búsqueda de elementos, ejecución de suites de TestNG y una estructura inicial de Page Object Model.

## Tecnologías utilizadas

- Java 8
- Maven
- Selenium WebDriver 3.141.59
- TestNG 7.4.0
- WebDriverManager 5.9.2
- Commons IO 2.11.0

## Funcionalidades incluidas

- Apertura y control de Chrome y Firefox
- Navegación hacia la página de prueba `http://automationpractice.com/index.php`
- Ejemplos básicos de automatización con Selenium
- Ejecución de pruebas mediante archivos XML de TestNG
- Estructura base para aplicar Page Object Model

## Estructura del proyecto

```text
.
├── pom.xml
├── testng.xml
├── crossBrowser-testng.xml
├── Drivers/
├── src/
│   ├── main/java/
│   └── test/java/
└── README.md
```

## Requisitos previos

Antes de ejecutar la automatización, asegúrate de tener instalado:

- JDK 8 o superior
- Maven
- Google Chrome
- Mozilla Firefox
- Drivers compatibles en la carpeta `Drivers/`

## Instalación y configuración

1. Clona este repositorio.
2. Abre una terminal en la raíz del proyecto.
3. Descarga las dependencias con Maven:

```bash
mvn clean install
```

## Ejecución de pruebas

### Ejecutar todas las pruebas

```bash
mvn test
```

### Ejecutar una suite específica

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Ejecutar pruebas en Chrome y Firefox

```bash
mvn test -DsuiteXmlFile=crossBrowser-testng.xml
```

## Clases principales

- `Edit.EducacionIt.Laboratorio1` : ejemplos básicos de pruebas con Selenium
- `pruebas.CrossBrowserTest` : ejecución parametrizada por navegador
- `paginas.PaginaInicio` : ejemplo de Page Object Model

## Nota importante

Algunas clases hacen referencia a rutas de driver con formato Windows, por ejemplo `..\\EducacionIt\\Drivers\\...`. En sistemas Linux o macOS puede ser necesario ajustar esas rutas o utilizar WebDriverManager para que la resolución de drivers sea más portable.

## Licencia

Este proyecto es de uso académico y de práctica dentro del contexto de aprendizaje de automatización con Selenium.
