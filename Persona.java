package Bibliotecas;

import java.util.ArrayList;

public class Persona {
    
    // Atributos
	
    private String nombre;
    private String apellidos;
    private String nif;
    private String contraseña;

    // Constructores
    
    public Persona(String nombre, String apellidos, String nif, String contraseña) {
        this.setNombre(nombre);
        this.setApellidos(apellidos);
        this.setNif(nif);
        this.setContraseña(contraseña);
    }

    public Persona(Persona otra) {
        this(otra.getNombre(), otra.getApellidos(), otra.getNif(), otra.getContraseña());
    }

    // Métodos

    @Override
    public String toString() {
        return "👤 Persona [Nombre=" + nombre + ", Apellidos=" + apellidos + ", NIF=" + nif + "]";
    }

    public static void addPersona(ArrayList<Persona> lista, String nombre, String apellidos, String nif, String contraseña) {
        if (contraseña.length() < 8) {
            System.out.println("❌ La contraseña debe tener al menos 8 caracteres. Inténtalo de nuevo.");
            return; 
        }
        
        lista.add(new Persona(nombre, apellidos, nif, contraseña));
        System.out.println("✔ Persona añadida con éxito.");
    }

    public static void deletePersona(ArrayList<Persona> lista, String nif) {
        lista.removeIf(persona -> persona.getNif().equals(nif));
    }

    public static void mostrarPersona(ArrayList<Persona> lista) {
        for (Persona p : lista) {
            System.out.println(p);
        }
    }
    
    public static Persona autenticarPersona(ArrayList<Persona> listaPersonal, String nif, String contrasena) {
        for (Persona p : listaPersonal) {
            if (p.getNif().equals(nif) && p.getContraseña().equals(contrasena)) {
                return p; 
            }
        }
        return null; 
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.trim();
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos.trim();
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif.trim().toLowerCase();
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        if (contraseña.length() >= 8) {
            this.contraseña = contraseña;
        } else {
            throw new IllegalArgumentException("❌ La contraseña debe tener al menos 8 caracteres.");
        }
    }
}