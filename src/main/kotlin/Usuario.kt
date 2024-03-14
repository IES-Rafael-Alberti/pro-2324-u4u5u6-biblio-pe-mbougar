package org.pebiblioteca
import java.util.*

data class Usuario(
    val id: UUID = UUID.randomUUID(),
    val nombre: String,
    val librosPrestados: MutableList<Libro> = mutableListOf()
)