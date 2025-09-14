// Archivo: Libro.java
public class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String genero;
    private boolean disponible;

    public Libro(String titulo, String autor, int anioPublicacion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.disponible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return titulo + " | " + autor + " | " + anioPublicacion + " | " + genero + " | " + (disponible ? "Disponible" : "Prestado");
    }

    public String toFileString() {
        return titulo + ";" + autor + ";" + anioPublicacion + ";" + genero + ";" + disponible;
    }

    public static Libro fromFileString(String linea) {
        String[] datos = linea.split(";");
        Libro libro = new Libro(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3]);
        libro.setDisponible(Boolean.parseBoolean(datos[4]));
        return libro;
    }
}
