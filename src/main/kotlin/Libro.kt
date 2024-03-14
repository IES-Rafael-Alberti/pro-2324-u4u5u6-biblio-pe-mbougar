package org.pebiblioteca
import java.util.*


/**
 * Data class que representa un libro de la biblioteca.
 * @property id El identificador único del libro. Por defecto se genera utilizando la utilidad [UtilidadesBiblioteca.generarIdentificadorUnico].
 * @property titulo El título del libro.
 * @property autor El autor del libro.
 * @property anioDePublicacion El año de publicación del libro.
 * @property tematica La temática del libro.
 * @property estadoLibro El estado actual del libro. Por defecto es DISPONIBLE. Utiliza la descripción del estado
 *                      desde el enum [EstadoLibro].
 */
data class Libro(
    val id: UUID = UtilidadesBiblioteca.generarIdentificadorUnico(),
    val titulo: String,
    val autor: String,
    val anioDePublicacion: Int,
    val tematica: String,
    var estadoLibro: String = EstadoLibro.DISPONIBLE.desc)