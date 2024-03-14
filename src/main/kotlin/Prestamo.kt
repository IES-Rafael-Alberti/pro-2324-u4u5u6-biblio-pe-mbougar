package org.pebiblioteca

import java.util.*

/**
 * Data class que representa un préstamo de un libro.
 * @property id El identificador único del préstamo.
 * @property usuario El usuario que realiza el préstamo.
 * @property prestado Indica si el libro asociado al préstamo está prestado o devuelto.
 */
data class Prestamo(
    val id: UUID,
    val usuario: Usuario,
    val prestado: Boolean
)