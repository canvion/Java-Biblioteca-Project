# Java-Biblioteca-Project
# 📘 Práctica 1 Java – Introducción a la POO

El objetivo de esta práctica es consolidar los conocimientos adquiridos relacionados con la **POO (Programación Orientada a Objetos)**.  
Para ello, se propone lo siguiente:

## 🛠 Proyecto

Crea un proyecto Java llamado **`Biblioteca`**.

La Biblioteca del CIFP FBMoll necesita un programa para gestionar sus operaciones. Para poder llevarlo a cabo, se solicita desarrollar las siguientes clases:

---

## 📚 Clase `Libro`

Debe almacenar:

- ISBN
- Título
- Autor
- Editorial
- Número de copias
- Número de copias disponibles

### Métodos y requisitos:

- Constructor vacío
- Constructor con todos los parámetros
- Constructor copia
- `toString`
- Getters y Setters (DTO)
- **Contador de libros** (controla títulos distintos, no copias)
- **Control en el setter**: el número de copias nunca debe ser menor a 1

### Métodos estáticos necesarios:

- **Añadir libro**  
  Solicita datos y añade un libro a una lista de libros recibida como parámetro.

- **Eliminar libro**  
  Solicita ISBN, busca y elimina el libro de la lista (solo si no tiene reservas).

- **Buscar libro por ISBN**  
  Solicita ISBN, lo busca y devuelve su posición o -1 si no se encuentra.

- **Buscar libro por título**  
  Solicita un título y muestra todos los libros que contienen dicha cadena.

---

## 🏛 Clase `Biblioteca`

Debe almacenar:

- Nombre de la biblioteca
- Lista de libros
- Lista de personal que gestiona la biblioteca

### Métodos y requisitos:

- Constructor vacío
- Constructor con todos los parámetros
- Constructor copia
- `toString`
- Getters y Setters (DTO)
- **Control**: el nombre de la biblioteca debe empezar con mayúscula

### Métodos de instancia:

- **Mostrar libros**  
  Imprime la lista completa de libros

- **Mostrar libros disponibles**  
  Imprime solo los libros con copias disponibles

---

## 👤 Clase `Persona`

Debe almacenar:

- Nombre
- Apellidos
- NIF
- Contraseña

### Métodos y requisitos:

- Constructor con todos los parámetros
- Constructor copia
- `toString`
- Getters y Setters (DTO)
- **Control de contraseña**: al menos 8 caracteres
- Métodos para añadir/eliminar personal que gestiona la biblioteca (bibliotecarios)

---

## 📋 Menú Principal

Debe usarse un menú principal con `switch`, que permita:

- Gestionar la biblioteca
- Acceder a todos los métodos solicitados
- Separar el menú del personal y el de reservas si es necesario

---

## 🧪 OPCIONAL

### Clase `Usuario`

Debe almacenar:

- Nombre
- Apellidos
- NIF
- Contraseña

### Métodos y requisitos:

- Constructor con todos los parámetros
- Constructor copia
- `toString`
- Getters y Setters (DTO)
- **Control de contraseña**: mínimo 8 caracteres
- Métodos para añadir/eliminar usuarios de la biblioteca

### Gestión de reservas

- Solo los usuarios pueden reservar libros
- Un usuario **no puede reservar más de 5 libros a la vez**
- Debe devolver libros antes de seguir reservando
- El programa debe permitir:
  - Reservar libros
  - Devolver libros
  - Mostrar listado de reservas por usuario

### 🧠 Análisis

Se debe analizar cómo controlar las reservas realizadas por cada usuario, y adaptar las clases y el programa principal para dar soporte a este sistema.

---
