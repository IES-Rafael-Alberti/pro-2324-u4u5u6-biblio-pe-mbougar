package org.pebiblioteca

/**
 * Enumeración que representa el estado de un elemento.
 * @property desc La descripción del estado del elemento.
 */
enum class EstadoElemento(val desc: String) {
    DISPONIBLE("**disponible**"),
    PRESTADO("**prestado**")
}