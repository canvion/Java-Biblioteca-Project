package Bibliotecas;

import java.util.ArrayList;

public class Biblioteca {
	
    // Atributos
    
	private String nombre;
    private ArrayList<Libro> listaLibros;
    private ArrayList<Persona> listaPersonal;
    
    // Constructores

    public Biblioteca() {
        this.nombre = "Biblioteca sin nombre";
        this.listaLibros = new ArrayList<>();
        this.listaPersonal = new ArrayList<>();
    }
    
    public Biblioteca(String nombre, ArrayList<Libro> listaLibros, ArrayList<Persona> listaPersonal) {
        setNombre(nombre);
        this.listaLibros = listaLibros;
        this.listaPersonal = listaPersonal;
    }
    
    public Biblioteca(Biblioteca otra) {
        this(otra.nombre, new ArrayList<>(otra.listaLibros), new ArrayList<>(otra.listaPersonal));
    }
    
    // Métodos 

    public void mostrarLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("❌ No hay libros en la biblioteca.");
        } else {
            System.out.println("===== Lista de Libros =====");
            for (Libro libro : listaLibros) {
                System.out.println(libro);
            }
        }
    }

    public void mostrarLibrosDisponibles() {
        boolean hayDisponibles = false;
        System.out.println("===== Libros Disponibles =====");
        for (Libro libro : listaLibros) {
            if (libro.getCopiasDisponibles() > 0) {
                System.out.println(libro);
                hayDisponibles = true;
            }
        }
        if (!hayDisponibles) {
            System.out.println("❌ No hay libros disponibles.");
        }
    }
    
    @Override
    public String toString() {
        return "Biblioteca [Nombre=" + nombre + ", Libros=" + listaLibros.size() + 
               ", Personal=" + listaPersonal.size() + "]";
    }
    
    // Getters y Setters
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = (nombre != null && !nombre.isEmpty()) ? nombre : "Biblioteca sin nombre";
    }
    
    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }
    
    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
    
    public ArrayList<Persona> getListaPersonal() {
        return listaPersonal;
    }
    
    public void setListaPersonal(ArrayList<Persona> listaPersonal) {
        this.listaPersonal = listaPersonal;
    }
}
