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
    private val id: UUID = UtilidadesBiblioteca.generarIdentificadorUnico(),
    private val titulo: String,
    private val autor: String,
    private val anioDePublicacion: Int,
    private val tematica: String,
    private var estadoLibro: String = EstadoLibro.DISPONIBLE.desc) {

    fun obtnerId() = id

    fun obtenerTitulo() = titulo

    fun obtenerAutor() = autor

    fun obtenerAnioPublicacion() = anioDePublicacion

    fun obtenerTematica() = tematica

    fun obtenerEstadoLibro() = estadoLibro

    fun cambiarEstado() {
        estadoLibro = if (estadoLibro == EstadoLibro.DISPONIBLE.desc) EstadoLibro.PRESTADO.desc else EstadoLibro.DISPONIBLE.desc
    }
}