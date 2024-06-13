# Catálogo de Libros

## Descripción del Proyecto

Es un Catálogo de Libros que permite la interacción textual (vía consola) con los usuarios, proporcionando, por ahora, 5 opciones de interacción. Los libros se buscan a través de la API de Gutendex y se almacenan en una base de datos PostgreSQL. Los usuarios pueden realizar consultas tanto a los libros como a los autores, permitiendo una gestión eficiente de la información.

## Funcionalidades

1. **Buscar libros por título**: Permite a los usuarios buscar libros ingresando el título del libro.
2. **Listar todos los libros registrados**: Muestra una lista completa de todos los libros almacenados en la base de datos.
3. **Listar los autores registrados**: Muestra una lista de todos los autores cuyos libros están en la base de datos.
4. **Listar los autores vivos entre determinados años**: Permite a los usuarios listar autores que estuvieron vivos entre dos años específicos.
5. **Listar los libros por idioma**: Permite a los usuarios listar los libros filtrados por el idioma en que están escritos.

## Tecnologías Utilizadas

- **Java**: Lenguaje principal para el desarrollo de la aplicación.
- **Spring Boot**: Framework para la creación de aplicaciones Java.
- **Hibernate**: Framework de mapeo objeto-relacional (ORM).
- **PostgreSQL**: Sistema de gestión de bases de datos utilizado para almacenar los libros y autores.
- **Gutendex API**: API utilizada para buscar y obtener información sobre los libros.
