// Archivo: Biblioteca.java
import java.io.*;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Libro> libros;
    private ArrayList<Usuario> usuarios;

    public Biblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
    }


    public void registrarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("✅ Libro registrado correctamente.");
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println(" Usuario registrado correctamente.");
    }

    public void prestarLibro(String idUsuario, String titulo) {
        Usuario usuario = buscarUsuario(idUsuario);
        Libro libro = buscarLibro(titulo);

        if (usuario == null) {
            System.out.println(" Usuario no encontrado.");
            return;
        }
        if (libro == null) {
            System.out.println(" Libro no encontrado.");
            return;
        }
        if (!libro.isDisponible()) {
            System.out.println(" El libro ya está prestado.");
            return;
        }

        libro.setDisponible(false);
        usuario.prestarLibro(titulo);
        System.out.println(" Préstamo realizado con éxito.");
    }

    public void devolverLibro(String idUsuario, String titulo) {
        Usuario usuario = buscarUsuario(idUsuario);
        Libro libro = buscarLibro(titulo);

        if (usuario == null || libro == null) {
            System.out.println(" Usuario o libro no encontrado.");
            return;
        }
        if (!usuario.getLibrosPrestados().contains(titulo)) {
            System.out.println("Este usuario no tiene prestado ese libro.");
            return;
        }

        libro.setDisponible(true);
        usuario.devolverLibro(titulo);
        System.out.println(" Devolución realizada con éxito.");
    }

    public void listarLibros(String filtro, String valor) {
        for (Libro libro : libros) {
            boolean mostrar = switch (filtro) {
                case "genero" -> libro.getGenero().equalsIgnoreCase(valor);
                case "autor" -> libro.getAutor().equalsIgnoreCase(valor);
                case "disponible" -> libro.isDisponible() == Boolean.parseBoolean(valor);
                default -> true;
            };
            if (mostrar) System.out.println(libro);
        }
    }


    private Libro buscarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) return libro;
        }
        return null;
    }

    private Usuario buscarUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) return usuario;
        }
        return null;
    }

    // ==================== PERSISTENCIA ====================
    public void guardarDatos() {
        try (BufferedWriter bwLibros = new BufferedWriter(new FileWriter("libros.txt"));
             BufferedWriter bwUsuarios = new BufferedWriter(new FileWriter("usuarios.txt"))) {

            for (Libro libro : libros) {
                bwLibros.write(libro.toFileString());
                bwLibros.newLine();
            }
            for (Usuario usuario : usuarios) {
                bwUsuarios.write(usuario.toFileString());
                bwUsuarios.newLine();
            }
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    public void cargarDatos() {
        libros.clear();
        usuarios.clear();

        try (BufferedReader brLibros = new BufferedReader(new FileReader("libros.txt"))) {
            String linea;
            while ((linea = brLibros.readLine()) != null) {
                libros.add(Libro.fromFileString(linea));
            }
        } catch (IOException ignored) {}

        try (BufferedReader brUsuarios = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = brUsuarios.readLine()) != null) {
                usuarios.add(Usuario.fromFileString(linea));
            }
        } catch (IOException ignored) {}
    }
}