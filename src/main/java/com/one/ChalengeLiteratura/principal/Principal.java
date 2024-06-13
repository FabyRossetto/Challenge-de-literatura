/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.one.ChalengeLiteratura.principal;

import com.one.ChalengeLiteratura.model.Autor;
import com.one.ChalengeLiteratura.model.DatosAutor;
import com.one.ChalengeLiteratura.model.DatosLibros;
import com.one.ChalengeLiteratura.model.Libro;
import com.one.ChalengeLiteratura.repository.AutorRepository;
import com.one.ChalengeLiteratura.repository.LibrosRepository;
import com.one.ChalengeLiteratura.service.ConsumoAPI;
import com.one.ChalengeLiteratura.service.ConvierteDatos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Faby
 */

@Component
public class Principal {
      private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibrosRepository repositorio;
    private AutorRepository autoresRepo;
    private List<Libro> libros;
     private Optional<Libro> libroBuscado;

     
  
   public Principal(LibrosRepository repo, AutorRepository autoresRepo) {
        this.repositorio = repo;
        this.autoresRepo = autoresRepo;
        
        // Verificación de depuración
    if (this.repositorio == null || this.autoresRepo == null) {
        throw new IllegalStateException("Los repositorios no están siendo inyectados correctamente.");
    }
   }

    public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            String menu = """
                    1 - Buscar libros por titulo
                    2 - Listar todos los libros registrados en la base de datos
                    3 - Listar los autores registrados en la base de datos
                    4 - Listar los autores vivos entre determinados años
                    5 - Listar los libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibrosPorTitulo();
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> buscarAutoresVivosEntreAnios();
                case 5 -> buscarLibroPorIdioma();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    //este metodo va a la api busca el libro que le estamos pidiendo y lo inserta en nuestra DB
   private void buscarLibrosPorTitulo() {
    System.out.println("Escribe el nombre del libro que deseas buscar:");
    String nombreLibro = teclado.nextLine();
    String url = URL_BASE + "?search=" + nombreLibro.replace(" ", "+");

    // Obtener datos de la API
    String json = consumoAPI.obtenerDatos(url);
    DatosLibros datos = conversor.obtenerDatos(json, DatosLibros.class);

    if (datos != null && datos.resultados() != null && !datos.resultados().isEmpty()) {
        List<Libro> librosGuardados = new ArrayList<>();

        // Iterar sobre los resultados de la búsqueda
        for (DatosLibros libroData : datos.resultados()) {
            // Crear el libro y su autor asociado
            Libro libro = new Libro(libroData);

            // Guardar el autor si no existe en la base de datos
            for (DatosAutor datosAutor : libroData.autores()) {
                Autor autor = new Autor(datosAutor);

                Optional<Autor> autorExistente = autoresRepo.findByNombre(autor.getNombre());
                if (autorExistente.isEmpty()) {
                    autoresRepo.save(autor);
                } else {
                    autor = autorExistente.get();
                }

                libro.setAutor(autor);
                repositorio.save(libro); // Guardar el libro en la base de datos

                // Añadir el libro guardado a la lista de libros guardados
                librosGuardados.add(libro);
                break; // Salir después de guardar el primer autor
            }
        }

        // Mostrar la lista de libros guardados
        if (!librosGuardados.isEmpty()) {
            System.out.println("Se encontraron los siguientes libros con el título \"" + nombreLibro + "\":");
            for (Libro libro : librosGuardados) {
                System.out.println(libro);
            }
        } else {
            System.out.println("No se encontraron libros con ese título en la base de datos.");
        }
    } else {
        System.out.println("No se encontraron libros con ese título en la búsqueda.");
    }
}


    private void listarLibros() {
        libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            libros.stream()
                  .sorted(Comparator.comparing(Libro::getTitulo))
                  .forEach(libro -> {
                  String autorNombre = libro.getAutor() != null ? libro.getAutor().getNombre() : "Autor desconocido";
                  System.out.println("Título: " + libro.getTitulo() +
                                     ", Autor: " + autorNombre +
                                     ", Idioma: " + libro.getIdioma() +
                                     ", Número de Descargas: " + libro.getNumeroDeDescarga());
              });
        }
    }

    //busca los que estan en la base de datos.
    public void buscarLibroPorIdioma() {
        System.out.println("Indica el idioma en el que estás buscando los libros:");
        String idiomaSeleccionado = teclado.nextLine().trim().toLowerCase();

        switch (idiomaSeleccionado) {
            case "english", "ingles" -> libros = repositorio.findByIdioma("en");
            case "spanish", "español" -> libros = repositorio.findByIdioma("es");
            case "french", "frances" -> libros = repositorio.findByIdioma("fr");
            
           
            default -> {
                System.out.println("Idioma no soportado. Intenta con 'english', 'spanish', o 'french'.");
                return;
            }
        }

        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles en el idioma seleccionado.");
        } else {
            System.out.println(" Los libros en " + idiomaSeleccionado + " que se encuentran en la base de datos son " + libros.size() + " : ");
            libros.stream()
                  .sorted(Comparator.comparing(Libro::getTitulo))
                  .forEach(System.out::println);
            
        }
    }

    private void listarAutores() {
        List<Autor> autores = autoresRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
        } else {
            autores.stream()
                   .sorted(Comparator.comparing(Autor::getNombre))
                   .forEach(System.out::println);
        }
    }

    private void buscarAutoresVivosEntreAnios() {
        System.out.println("Introduce el año de inicio:");
        int inicio = teclado.nextInt();
        System.out.println("Introduce el año de fin:");
        int fin = teclado.nextInt();
        teclado.nextLine(); // Consumir nueva línea

        List<Autor> autores = autoresRepo.findAutoresVivosEntreAnios(inicio, fin);
        if (autores.isEmpty()) {
            System.out.println("No hay autores vivos entre los años especificados.");
        } else {
            autores.stream()
                   .sorted(Comparator.comparing(Autor::getNombre))
                   .forEach(System.out::println);
        }
    }
}
