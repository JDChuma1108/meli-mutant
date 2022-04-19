# Meli Mutant

Proyecto para detectar si un humano es **Mutante** basandose en su secuencia de **ADN**.

### Pre-requisitos 📋

_Herramientas necesarias para correr el proyecto:_

```
1. JDK 11
2. IntelliJ
3. Postman
4. Maven (Recomendado)
5. Git (Recomendado)
```
## Corriendo el proyecto 🚀

_Para correr el proyecto seguir las siguientes instrucciones:_

```
1. Clonar este repositorio en la rama develop
2. Importar proyecto desde IntelliJ 
3. En la clase MutantApplication dar click derecho y elegir opción Run
```

### Consumo de la API 🔧

**URL:** https://mutant-api-mutant.azuremicroservices.io/api

**Servicios:**

- **Mutant:** Retorna **TRUE** si es un mutante y **FALSE** en caso contrario. Guarda un registro de cada **ADN** verificado (Si la matriz tiene dimensiones diferentes a **NXN** o contiene elementos diferentes a **A, T, G** ó **C** retorna **FALSE** pero no guarda el registro.)

```bash
POST -> /mutant
{
    “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```

- **Stats:** Retorna las estadisticas de cuantos **ADN** de humano, cuantos de mutante y cual es la proporción de mutantes con respecto a humanos que se han registrado.

```bash
GET -> /stats
```

- **Register:** Retorna todos los registros que se han guardado.
```bash
GET -> /register
```

## Construido con 🛠️

_Herramientas utilizadas_

* [Spring Boot](https://spring.io/projects/spring-boot) - Framework
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Junit](https://junit.org/junit5/) - Framework de pruebas unitarias
* [Jacoco](https://www.jacoco.org/jacoco/trunk/doc/) - Reporte de cobertura de pruebas unitarias
* [Sonarlint](https://www.sonarlint.org/) - Analisis de código estático



## Autor
* **Julian David Quiroz** 🤓- [JDChuma1108](https://github.com/JDChuma1108)
