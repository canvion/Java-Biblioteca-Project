package Bibliotecas;

import java.util.ArrayList;
import java.util.Scanner;

public class Usuario {
	
    // Atributos
	
    private String nombre;
    private String apellidos;
    private String nif;
    private String contrasena;
    private ArrayList<Libro> reservas;

    // Constructores

    public Usuario(String nombre, String apellidos, String nif, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        setContraseña(contrasena);  
        this.reservas = new ArrayList<>();
    }

    public Usuario(Usuario otro) {
        this(otro.nombre, otro.apellidos, otro.nif, otro.contrasena);
        this.reservas = new ArrayList<>(otro.reservas);
    }

    // Métodos 

    @Override
    public String toString() {
        return "Usuario [Nombre=" + nombre + ", Apellidos=" + apellidos + ", NIF=" + nif + 
               ", Reservas=" + reservas.size() + "]";
    }
    
    public boolean reservarLibro(Libro libro) {
        if (reservas.size() >= 5) {
            System.out.println("❌ No puedes reservar más de 5 libros.");
            return false;
        }
        if (libro.getCopiasDisponibles() <= 0) { 
            System.out.println("❌ No hay copias disponibles de: " + libro.getTitulo());
            return false;
        }
        reservas.add(libro);
        libro.setCopiasDisponibles(libro.getCopiasDisponibles() - 1); 
        System.out.println("✅ Libro reservado: " + libro.getTitulo());
        return true;
    }
    
    public boolean devolverLibro(String isbn) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getIsbn().equalsIgnoreCase(isbn)) {
                Libro libroDevuelto = reservas.remove(i);
                libroDevuelto.setCopiasDisponibles(libroDevuelto.getCopiasDisponibles() + 1); 
                System.out.println("✅ Libro devuelto: " + libroDevuelto.getTitulo());
                return true;
            }
        }
        System.out.println("❌ No se encontró el libro con ISBN: " + isbn);
        return false;
    }

    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("❌ No tienes libros reservados.");
        } else {
            System.out.println("===== Tus Reservas =====");
            for (Libro libro : reservas) {
                System.out.println(libro);
            }
        }
    }

    public static void addUsuario(ArrayList<Usuario> lista) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce los apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("Introduce el NIF: ");
        String nif = sc.nextLine();
        
        String contrasena;
        do {
            System.out.print("Introduce la contraseña (mínimo 8 caracteres): ");
            contrasena = sc.nextLine();
            if (contrasena.length() < 8) {
                System.out.println("❌ La contraseña debe tener al menos 8 caracteres.");
            }
        } while (contrasena.length() < 8);
        
        lista.add(new Usuario(nombre, apellidos, nif, contrasena));
        System.out.println("✅ Usuario añadido correctamente.");
    }
    
    public static void deleteUsuario(ArrayList<Usuario> lista) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el NIF del usuario a eliminar: ");
        String nif = sc.nextLine();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNif().equalsIgnoreCase(nif)) {
                lista.remove(i);
                System.out.println("✅ Usuario eliminado correctamente.");
                return;
            }
        }
        System.out.println("❌ No se encontró ningún usuario con ese NIF.");
    }
    
    public static void mostrarUsuarios(ArrayList<Usuario> lista) {
        if (lista.isEmpty()) {
            System.out.println("❌ No hay usuarios registrados.");
        } else {
            System.out.println("===== Lista de Usuarios =====");
            for (Usuario usuario : lista) {
                System.out.println(usuario);
            }
        }
    }

    public static Usuario autenticarUsuario(ArrayList<Usuario> lista, String nif, String contrasena) {
        for (Usuario usuario : lista) {
            if (usuario.getNif().equalsIgnoreCase(nif) && usuario.getContraseña().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    
    public String getContraseña() {
        return contrasena;
    }
    public void setContraseña(String contrasena) {
        if (contrasena.length() >= 8) {
            this.contrasena = contrasena;
        } else {
            throw new IllegalArgumentException("❌ La contraseña debe tener al menos 8 caracteres.");
        }
    }
    
    public ArrayList<Libro> getReservas() {
        return reservas;
    }
    public void setReservas(ArrayList<Libro> reservas) {
        this.reservas = reservas;
    }
}
