# API REST - Challenge - Mutantes

### Descripción

Es un servicio REST que permite saber si un humano es un mutante basandose en su secuencia de ADN.

### Características
- Ver el listado de todos los ADN
- Ver las estadisticas de cuántos mutantes fueron verificados
- Poder enviar un ADN y confirmar si es un mutante
- Editar el ADN
- Borrarlo

### Cómo usar
Se admiten los métodos `GET`, `POST`, `PUT` y `DELETE`:
| Método | url | Descripción |
| ----- | --- | --- |
| `GET` | `/mutant` | Trae el listado de todos los ADN |
| `POST` | `/mutant` | Verifica un nuevo ADN |
| `GET` | `/mutant/{id}` | Trae un ADN específico |
| `PUT` | `/mutant/{id}` | Actualiza ADN específico |
| `DELETE` | `/mutant/{id}` | Borra ADN específico |
| `GET` | `/stats` | Trae las estadísticas de cuantos mutantes hay |

### Formato del ADN
El formato del ADN tiene que ser un array de String que represente una tabla NxN, donde los Strings pueden ser (A,T,C,G) las cuales representa cada base nitrogenada del ADN.
- Ejemplo: `{"adn": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}`
![Ejemplo](https://i.imgur.com/jyjMtZt.png)

### Códigos de Estado

- Recibiras el código `200` si el ADN verificado es un mutante.
- Recibiras el código `403` si el ADN verificado no es un mutante.

### Errores conocidos
- Faltan algunas validaciones al crear un nuevo ADN

### Tecnologías usadas
- Java 8
- Maven
- Spring Boot
- JPA
- H2 y PostgreSQL

