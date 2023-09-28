import java.util.Scanner; // Importa la clase Scanner para leer la entrada del usuario.

// Define una clase llamada "Libro" para representar los libros en la biblioteca.
class Libro {
    String titulo; // Título del libro.
    String autor; // Nombre del autor del libro.
    String isbn; // Número ISBN (identificador único) del libro.
    boolean disponible; // Indica si el libro está disponible para préstamo.

    // Constructor de la clase Libro para crear objetos Libro con información inicial.
    public Libro(String titulo, String autor, String isbn, boolean disponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = disponible;
    }

    // Método para prestar un libro.
    public void prestar() {
        if (disponible) { // Si el libro está disponible para préstamo:
            disponible = false; // Cambia el estado del libro a no disponible.
            System.out.println("Libro prestado con éxito.");
        } else {
            System.out.println("El libro no está disponible para préstamo.");
        }
    }

    // Método para devolver un libro.
    public void devolver() {
        disponible = true; // Cambia el estado del libro a disponible.
        System.out.println("Libro devuelto con éxito.");
    }

    // Método para mostrar la información del libro.
    public void mostrarInformacion() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Disponible: " + (disponible ? "Sí" : "No")); // Imprime "Sí" si está disponible, "No" si no lo está.
    }
}

// Define una clase llamada "Usuario" para representar a los usuarios de la biblioteca.
class Usuario {
    String nombre; // Nombre del usuario.
    String carnet; // Carnet de identificación del usuario.
    String tipo; // Tipo de usuario (Estudiante o Profesor).

    // Constructor de la clase Usuario para crear objetos Usuario con información inicial.
    public Usuario(String nombre, String carnet, String tipo) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.tipo = tipo;
    }

    // Método para mostrar la información del usuario.
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Carnet: " + carnet);
        System.out.println("Tipo: " + tipo);
    }
}

// Clase principal llamada "Biblioteca" que contiene el método principal (main) del programa.
public class Biblioteca {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crea un objeto Scanner para leer la entrada del usuario.

        Libro[] biblioteca = new Libro[2]; // Arreglo para almacenar libros en la biblioteca.
        Usuario[] usuarios = new Usuario[2]; // Arreglo para almacenar usuarios registrados.

        // Creación de objetos Libro y Usuario y almacenamiento en los arreglos.
        biblioteca[0] = new Libro("La Odisea", "Homero", "123456", true);
        biblioteca[1] = new Libro("Cien años de soledad", "Gabriel García Márquez", "789012", true);

        usuarios[0] = new Usuario("Juan", "E12345", "Estudiante");
        usuarios[1] = new Usuario("Maria", "P98765", "Profesor");

        int opcion; // Variable para almacenar la opción seleccionada por el usuario.
        do {
            // Menú de opciones que se muestra al usuario.
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Realizar préstamo");
            System.out.println("2. Realizar devolución");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.next()); // Lee la opción ingresada por el usuario.
            } catch (NumberFormatException e) {
                opcion = 0; // Maneja errores de entrada no válida.
            }

            switch (opcion) {
                case 1: // Opción 1: Realizar préstamo de un libro.
                    System.out.print("Ingrese su carnet: ");
                    String carnet = scanner.next(); // Lee el carnet del usuario.
                    System.out.print("Ingrese el ISBN del libro que desea prestar: ");
                    String isbn = scanner.next(); // Lee el ISBN del libro.

                    Usuario usuarioEncontrado = null;
                    // Busca al usuario en el arreglo de usuarios por su carnet.
                    for (Usuario usuario : usuarios) {
                        if (usuario.carnet.equals(carnet)) {
                            usuarioEncontrado = usuario;
                            break;
                        }
                    }

                    if (usuarioEncontrado != null) {
                        // Si se encuentra el usuario, busca el libro en la biblioteca por su ISBN.
                        for (Libro libro : biblioteca) {
                            if (libro.isbn.equals(isbn)) {
                                libro.prestar(); // Realiza el préstamo del libro.
                                break;
                            }
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 2: // Opción 2: Realizar devolución de un libro.
                    System.out.print("Ingrese el ISBN del libro que desea devolver: ");
                    isbn = scanner.next(); // Lee el ISBN del libro.

                    // Busca el libro en la biblioteca por su ISBN.
                    for (Libro libro : biblioteca) {
                        if (libro.isbn.equals(isbn)) {
                            libro.devolver(); // Realiza la devolución del libro.
                            break;
                        }
                    }
                    break;

                case 3: // Opción 3: Salir del programa.
                    System.out.println("Saliendo del programa.");
                    break;

                default: // Opción inválida: Cuando el usuario ingresa una opción incorrecta.
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3); // El programa se ejecuta hasta que el usuario elija la opción 3 (Salir).

        scanner.close(); // Cierra el objeto Scanner.
    }
}
