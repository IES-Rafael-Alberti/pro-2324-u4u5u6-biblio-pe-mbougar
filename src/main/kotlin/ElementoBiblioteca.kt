package org.pebiblioteca

import java.util.*

abstract class ElementoBiblioteca(
    val id: UUID,
    val titulo: String,
    var estado: String) {

    fun obtnerId() = id

    fun obtenerTitulo() = titulo

    fun obtenerEstado() = estado

    fun cambiarEstado() {
        estado = if (estado == EstadoElemento.DISPONIBLE.desc) EstadoElemento.PRESTADO.desc else EstadoElemento.DISPONIBLE.desc
    }
}