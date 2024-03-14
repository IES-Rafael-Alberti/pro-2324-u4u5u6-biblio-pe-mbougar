package org.pebiblioteca
import java.util.*


/**
 * Data class que representa un usuario de la biblioteca.
 * @property id El identificador único del usuario. Por defecto se genera utilizando la utilidad [UtilidadesBiblioteca.generarIdentificadorUnico].
 * @property nombre El nombre del usuario.
 * @property librosPrestados La lista de libros que el usuario tiene prestados. Por defecto es una lista vacía.
 */
data class Usuario(
    val id: UUID = UtilidadesBiblioteca.generarIdentificadorUnico(),
    val nombre: String,
    val librosPrestados: MutableList<Libro> = mutableListOf()
)