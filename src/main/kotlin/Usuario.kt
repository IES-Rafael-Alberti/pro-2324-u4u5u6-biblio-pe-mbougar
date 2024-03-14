package org.pebiblioteca
import java.util.*


/**
 * Data class que representa un usuario de la biblioteca.
 * @property id El identificador único del usuario. Por defecto se genera utilizando la utilidad [UtilidadesBiblioteca.generarIdentificadorUnico].
 * @property nombre El nombre del usuario.
 * @property librosPrestados La lista de libros que el usuario tiene prestados. Por defecto es una lista vacía.
 */
data class Usuario(
    private val id: UUID = UtilidadesBiblioteca.generarIdentificadorUnico(),
    private val nombre: String,
    private val librosPrestados: MutableList<Libro> = mutableListOf()
) {
    fun obtenerId() = id

    fun obtenerNombre() = nombre

    fun agregarLibro(libro: Libro) {
        librosPrestados.add(libro)
    }

    fun eliminarLibro(libro: Libro) {
        librosPrestados.remove(libro)
    }

    fun buscarLibroPrestadoPorId(libroId: UUID): Libro? {
        val libro = librosPrestados.find { libro -> libro.obtnerId() == libroId }
        return libro
    }

    fun listarLibrosPrestados(): List<Libro> {
        val librosPrestados: MutableList<Libro> = mutableListOf()
        this.librosPrestados.forEach { libro -> librosPrestados.add(libro) }

        return librosPrestados
    }
}