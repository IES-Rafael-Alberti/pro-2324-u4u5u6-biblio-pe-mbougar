package org.pebiblioteca

/**
 * Enumeración que representa el estado de un libro.
 * @property desc La descripción del estado del libro.
 */
enum class EstadoLibro(val desc: String) {
    DISPONIBLE("**disponible**"),
    PRESTADO("**prestado**")
}