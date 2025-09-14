// Archivo: Usuario.java
import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String id;
    private ArrayList<String> librosPrestados;

    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.librosPrestados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getLibrosPrestados() {
        return librosPrestados;
    }

    public void prestarLibro(String titulo) {
        librosPrestados.add(titulo);
    }

    public void devolverLibro(String titulo) {
        librosPrestados.remove(titulo);
    }

    @Override
    public String toString() {
        return nombre + " | " + id + " | Libros prestados: " + librosPrestados;
    }

    public String toFileString() {
        return nombre + ";" + id + ";" + String.join(",", librosPrestados);
    }

    public static Usuario fromFileString(String linea) {
        String[] datos = linea.split(";");
        Usuario usuario = new Usuario(datos[0], datos[1]);
        if (datos.length > 2 && !datos[2].isEmpty()) {
            for (String titulo : datos[2].split(",")) {
                usuario.prestarLibro(titulo);
            }
        }
        return usuario;
    }
}