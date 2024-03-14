package org.pebiblioteca
import java.util.*


/**
 * Class que representa un libro de la biblioteca.
 * @property id El identificador único del libro. Por defecto se genera utilizando la utilidad [UtilidadesBiblioteca.generarIdentificadorUnico].
 * @property titulo El título del libro.
 * @property autor El autor del libro.
 * @property anioDePublicacion El año de publicación del libro.
 * @property tematica La temática del libro.
 * @property estado El estado actual del libro. Por defecto es DISPONIBLE. Utiliza la descripción del estado
 *                      desde el enum [EstadoLibro].
 */
class Libro(
    id: UUID = UtilidadesBiblioteca.generarIdentificadorUnico(),
    titulo: String,
    private val autor: String,
    private val anioDePublicacion: Int,
    private val tematica: String,
    estado: String = EstadoElemento.DISPONIBLE.desc): ElementoBiblioteca(id, titulo, estado), Prestable {

    fun obtenerAutor() = autor

    fun obtenerAnioPublicacion() = anioDePublicacion

    fun obtenerTematica() = tematica

    override fun prestar() {
        if (estado == EstadoElemento.DISPONIBLE.desc) {
            this.cambiarEstado()
        }
    }

    override fun devolver() {
        if (estado == EstadoElemento.PRESTADO.desc) {
            this.cambiarEstado()
        }
    }
}