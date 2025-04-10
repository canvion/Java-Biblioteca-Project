package Bibliotecas;

import java.util.ArrayList;
import java.util.Scanner;

public class Libro {
	
    // Atributos
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private int copiasTotales;
    private int copiasDisponibles;
    
    private static int contadorTitulos = 0;
    
    // Constructores
    
    public Libro() {
        this("", "", "", "", 1, 1);
    }
    
    public Libro(String isbn, String titulo, String autor, String editorial, int copiasTotales, int copiasDisponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        setCopiasTotales(copiasTotales);     
        setCopiasDisponibles(copiasDisponibles);
        contadorTitulos++;
    }
    
    public Libro(Libro otro) {
        this(otro.isbn, otro.titulo, otro.autor, otro.editorial, otro.copiasTotales, otro.copiasDisponibles);
    }
    
    // Métodos
    
    @Override
    public String toString() {
        return "Libro [ISBN=" + isbn + ", Título=" + titulo + ", Autor=" + autor +
               ", Editorial=" + editorial + ", Copias Totales=" + copiasTotales +
               ", Copias Disponibles=" + copiasDisponibles + "]";
    }
    
    public static void añadirLibro(ArrayList<Libro> lista) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el ISBN: ");
        String isbn = sc.nextLine();
        
        System.out.print("Introduce el título: ");
        String titulo = sc.nextLine();
        
        System.out.print("Introduce el autor: ");
        String autor = sc.nextLine();
        
        System.out.print("Introduce la editorial: ");
        String editorial = sc.nextLine();
        
        System.out.print("Introduce el número de copias totales: ");
        int copiasTotales = sc.nextInt();
        sc.nextLine(); 
        
        Libro nuevoLibro = new Libro(isbn, titulo, autor, editorial, copiasTotales, copiasTotales);
        lista.add(nuevoLibro);
        System.out.println("✅ Libro añadido correctamente.");
    }
    
    public static void eliminarLibro(ArrayList<Libro> lista) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el ISBN del libro a eliminar: ");
        String isbnBuscar = sc.nextLine();
        
        int pos = buscarLibroPorISBN(lista, isbnBuscar);
        if (pos == -1) {
            System.out.println("❌ No se encontró ningún libro con ese ISBN.");
        } else {
            Libro libroAEliminar = lista.get(pos);
            if (libroAEliminar.getCopiasDisponibles() < libroAEliminar.getCopiasTotales()) {
                System.out.println("❌ No se puede eliminar el libro porque tiene reservas.");
            } else {
                lista.remove(pos);
                System.out.println("✅ Libro eliminado correctamente.");
            }
        }
    }
    
    public static int buscarLibroPorISBN(ArrayList<Libro> listaLibros, String isbn) {
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getIsbn().equals(isbn)) {
                return i; 
            }
        } return -1; 
    }

    public static void buscarLibroPorTitulo(ArrayList<Libro> listaLibros, String titulo) {
        for (Libro libro : listaLibros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                System.out.println(libro);
            }
        }
    }
    
    // Getters y Setters 
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    
    public int getCopiasTotales() {
        return copiasTotales;
    }
    public void setCopiasTotales(int copiasTotales) {
        this.copiasTotales = (copiasTotales < 1) ? 1 : copiasTotales;
    }
    
    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }
    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = (copiasDisponibles > this.copiasTotales) ? this.copiasTotales : copiasDisponibles;
    }
    
    public static int getContadorTitulos() {
        return contadorTitulos;
    }
}
