package org.pebiblioteca

import java.util.*

/**
 * Data class que representa un préstamo de un libro.
 * @property elementoId El identificador único del libro.
 * @property usuario El usuario que realiza el préstamo.
 * @property prestado Indica si el libro asociado al préstamo está prestado o devuelto.
 */
data class Prestamo(
    val elementoId: UUID,
    val usuario: Usuario,
    val prestado: Boolean
) {
    override fun toString(): String {
        return " (Prestamo: Id Libro: $elementoId, Nombre usuario: ${usuario.obtenerNombre()}, Id Usuario ${usuario.obtenerId()}, Tipo Prestamo: ${if (prestado) "Prestamo" else "Devolución"})"
    }
}