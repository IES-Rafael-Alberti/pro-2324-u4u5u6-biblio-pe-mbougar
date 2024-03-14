package org.pebiblioteca
import java.util.*

/**
 * Clase que representa el catálogo de libros de la biblioteca.
 */
class Catalogo{
    private val elementosCatalogo: MutableList<Libro> = mutableListOf()

    /**
     * Agrega un libro al catálogo.
     * @param libro El libro que se va a agregar.
     */
    fun agregar(libro: Libro) {
        elementosCatalogo.add(libro)
    }

    /**
     * Elimina un libro del catálogo dado su ID.
     * @param id El ID del libro que se va a eliminar.
     */
    fun eliminar(id: UUID) {
        elementosCatalogo.forEach { elemento -> if (elemento.id == id) elementosCatalogo.remove(elemento) }
    }

    /**
     * Busca un libro en el catálogo dado su ID.
     * @param id El ID del libro que se va a buscar.
     * @return El libro encontrado, o null si no se encuentra.
     */
    fun buscarPorId(id: UUID): Libro? {
        return elementosCatalogo.find { it.id == id }
    }

    /**
     * Lista todos los libros disponibles en el catálogo.
     * @return Una lista de libros disponibles.
     */
    fun listarDisponibles(): List<Libro> {
        val librosDisponibles: MutableList<Libro> = mutableListOf()
        elementosCatalogo.forEach { libro -> if (libro.estadoLibro == EstadoLibro.DISPONIBLE.desc) librosDisponibles.add(libro) }

        return librosDisponibles
    }
}