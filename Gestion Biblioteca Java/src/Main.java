// Archivo: Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.cargarDatos();

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("SISTEMA DE BIBLIOTECA");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Listar libros");
            System.out.println("6. Guardar y salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Año de publicación: ");
                    int anio = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Género: ");
                    String genero = sc.nextLine();
                    biblioteca.registrarLibro(new Libro(titulo, autor, anio, genero));
                }
                case 2 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Número de identificación: ");
                    String id = sc.nextLine();
                    biblioteca.registrarUsuario(new Usuario(nombre, id));
                }
                case 3 -> {
                    System.out.print("ID del usuario: ");
                    String id = sc.nextLine();
                    System.out.print("Título del libro: ");
                    String titulo = sc.nextLine();
                    biblioteca.prestarLibro(id, titulo);
                }
                case 4 -> {
                    System.out.print("ID del usuario: ");
                    String id = sc.nextLine();
                    System.out.print("Título del libro: ");
                    String titulo = sc.nextLine();
                    biblioteca.devolverLibro(id, titulo);
                }
                case 5 -> {
                    System.out.print("Filtrar por (genero/autor/disponible/todos): ");
                    String filtro = sc.nextLine();
                    String valor = "";
                    if (!filtro.equalsIgnoreCase("todos")) {
                        System.out.print("Valor del filtro: ");
                        valor = sc.nextLine();
                    }
                    biblioteca.listarLibros(filtro.equalsIgnoreCase("todos") ? "" : filtro, valor);
                }
                case 6 -> biblioteca.guardarDatos();
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 6);

        sc.close();
    }
}
