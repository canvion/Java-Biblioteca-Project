package Bibliotecas;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        Scanner teclat = new Scanner(System.in); 
        Biblioteca biblioteca = new Biblioteca("Biblioteca CIFP FBMoll", new ArrayList<>(), new ArrayList<>());
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n📚 Menú Principal 📚");
            System.out.println("1. Información de libros");
            System.out.println("2. Gestión de personal");
            System.out.println("3. Gestionar libros");
            System.out.println("4. Gestión de Usuarios");     
            System.out.println("5. Reservar libros");         
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                switch (opcion) {
                    case 1:
                        menuLibros(biblioteca.getListaLibros(), scanner);
                        break;
                    case 2:
                        menuPersonal(biblioteca, scanner);
                        break;
                    case 3:
                        GestionarLibros(biblioteca, scanner);
                        break;
                    case 4: 
                        menuUsuarios(listaUsuarios, scanner);
                        break;
                    case 5: 
                        menuReservas(listaUsuarios, biblioteca.getListaLibros(), scanner);
                        break;
                    case 6:
                        salir = true;
                        System.out.println("👋 Saliendo del programa...");
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Introduce un número válido.");
            }
        }
        scanner.close();
    }

    public static void menuLibros(ArrayList<Libro> listaLibros, Scanner scanner) {
        int opcion;
        do {
            System.out.println("📚 Menú de Información de Libros 📚");
            System.out.println("1. Mostrar todos los libros");
            System.out.println("2. Mostrar libros disponibles");
            System.out.println("3. Buscar libro por ISBN");
            System.out.println("4. Buscar libro por título");
            System.out.println("5. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    for (Libro libro : listaLibros) {
                        System.out.println(libro);
                    }
                    break;
                case 2:
                    for (Libro libro : listaLibros) {
                        if (libro.getCopiasDisponibles() > 0) {
                            System.out.println(libro);
                        }
                    }
                    break;
                case 3:
                    System.out.print("Introduce el ISBN del libro: ");
                    String isbn = scanner.nextLine();
                    int indice = Libro.buscarLibroPorISBN(listaLibros, isbn);
                    if (indice != -1) {
                        System.out.println("Libro encontrado: " + listaLibros.get(indice));
                    } else {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Introduce el título o parte de él: ");
                    String titulo = scanner.nextLine();
                    Libro.buscarLibroPorTitulo(listaLibros, titulo);
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
        } while (opcion != 5);
    }


    public static void menuPersonal(Biblioteca biblioteca, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n👥 Gestión de Personal 👥");
            System.out.println("1. Añadir Persona");
            System.out.println("2. Eliminar Persona");
            System.out.println("3. Mostrar Personal");
            System.out.println("4. Volver al menú principal");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
            	case 1:
            		System.out.print("Nombre: ");
            		String nombre = scanner.nextLine();
            		System.out.print("Apellidos: ");
            		String apellidos = scanner.nextLine();
            		System.out.print("NIF: ");
            		String nif = scanner.nextLine();
                
            		String contraseña;
            		do {
            			System.out.print("Contraseña (mínimo 8 caracteres): ");
            			contraseña = scanner.nextLine();
            			if (contraseña.length() < 8) {
            				System.out.println("⚠ La contraseña debe tener al menos 8 caracteres.");
            			}
            		} while (contraseña.length() < 8);

                Persona.addPersona(biblioteca.getListaPersonal(), nombre, apellidos, nif, contraseña);
                break;

                case 2:
                    System.out.print("Ingrese el NIF de la persona a eliminar: ");
                    String nifEliminar = scanner.nextLine();
                    Persona.deletePersona(biblioteca.getListaPersonal(), nifEliminar);
                    System.out.println("✔ Persona eliminada si existía.");
                    break;

                case 3:
                    System.out.println("\n📜 Lista de Personal 📜");
                    Persona.mostrarPersona(biblioteca.getListaPersonal());
                    break;

                case 4:
                    System.out.println("🔙 Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("⚠ Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 4);
    }


    public static void GestionarLibros(Biblioteca biblioteca, Scanner teclat) {
        boolean autenticado = false;
        Persona personaAutenticada = null;

        while (!autenticado) {
            System.out.print("Introduce tu NIF: ");
            String nif = teclat.nextLine().trim();
            System.out.print("Introduce tu contraseña: ");
            String contrasena = teclat.nextLine().trim();

            personaAutenticada = Persona.autenticarPersona(biblioteca.getListaPersonal(), nif, contrasena);
            if (personaAutenticada != null) {
                autenticado = true;
                System.out.println("¡Bienvenido, " + personaAutenticada.getNombre() + "!");
                menuGestionLibros(biblioteca, teclat);
            } else {
                System.out.println("❌ NIF o contraseña incorrectos. Inténtalo de nuevo.");
            }
        }
    }

    public static void menuGestionLibros(Biblioteca biblioteca, Scanner teclat) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n📖 Menú de Gestión de Libros 📖");
            System.out.println("1. Añadir libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Volver al menú principal");
            System.out.print("Elige una opción: ");

            try {
                int opcion = Integer.parseInt(teclat.nextLine().trim());
                switch (opcion) {
                	case 1:	
                		Libro.añadirLibro(biblioteca.getListaLibros());  
                		break;
                    case 2:
                    	Libro.eliminarLibro(biblioteca.getListaLibros());
                        break;
                    case 3: 
                    	biblioteca.mostrarLibros();
                    	break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Inténtalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Introduce un número válido.");
            }
        }
    }
    public static void menuUsuarios(ArrayList<Usuario> listaUsuarios, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n👤 Gestión de Usuarios 👤");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Mostrar usuarios");
            System.out.println("4. Volver al menú principal");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(scanner.nextLine().trim());
            
            switch (opcion) {
                case 1:
                    Usuario.addUsuario(listaUsuarios);
                    break;
                case 2:
                    Usuario.deleteUsuario(listaUsuarios);
                    break;
                case 3:
                    Usuario.mostrarUsuarios(listaUsuarios);
                    break;
                case 4:
                    System.out.println("🔙 Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 4);
    }

    public static void menuReservas(ArrayList<Usuario> listaUsuarios, ArrayList<Libro> listaLibros, Scanner scanner) {
        System.out.println("\n🔐 Autenticación de Usuario 🔐");
        System.out.print("Introduce tu NIF: ");
        String nif = scanner.nextLine().trim();
        System.out.print("Introduce tu contraseña: ");
        String contrasena = scanner.nextLine().trim();

        Usuario usuario = Usuario.autenticarUsuario(listaUsuarios, nif, contrasena);
        
        if (usuario == null) {
            System.out.println("❌ Autenticación fallida. Verifica tus credenciales.");
            return;
        }

        System.out.println("\n¡Bienvenido, " + usuario.getNombre() + "!");
        int opcion;
        do {
            System.out.println("\n📘 Menú de Reservas 📘");
            System.out.println("1. Reservar libro");
            System.out.println("2. Devolver libro");
            System.out.println("3. Listar mis reservas");
            System.out.println("4. Volver al menú principal");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(scanner.nextLine().trim());
            
            switch (opcion) {
                case 1:
                	System.out.print("Introduce el ISBN del libro a reservar: ");
                	String isbn = scanner.nextLine();
                	int indiceLibro = Libro.buscarLibroPorISBN(listaLibros, isbn);

                	if (indiceLibro != -1) {
                	    Libro libro = listaLibros.get(indiceLibro);
                	    if (libro.getCopiasDisponibles() > 0) { 
                	        usuario.reservarLibro(libro);
                	    } else {
                	        System.out.println("❌ No hay copias disponibles de este libro.");
                	    }
                	} else {
                	    System.out.println("❌ Libro no encontrado.");
                	}
                    break;
                case 2:
                    System.out.print("Introduce el ISBN del libro a devolver: ");
                    usuario.devolverLibro(scanner.nextLine());
                    break;
                case 3:
                    usuario.listarReservas();
                    break;
                case 4:
                    System.out.println("🔙 Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 4);
    }
}