package org.pebiblioteca
import java.util.*

data class Libro(
    val id: UUID = UUID.randomUUID(),
    val titulo: String,
    val autor: String,
    val anioDePublicacion: Int,
    val tematica: String,
    var estadoLibro: String = EstadoLibro.DISPONIBLE.desc)