package org.pebiblioteca
import java.util.*

class Catalogo{
    private val elementosCatalogo: MutableList<Libro> = mutableListOf()

    fun agregar(libro: Libro) {
        elementosCatalogo.add(libro)
    }

    fun eliminar(id: UUID) {
        elementosCatalogo.forEach { elemento -> if (elemento.id == id) elementosCatalogo.remove(elemento) }
    }

    fun buscarPorId(id: UUID): Libro? {
        return elementosCatalogo.find { it.id == id }
    }

    fun listarDisponibles(): List<Libro> {
        val librosDisponibles: MutableList<Libro> = mutableListOf()
        elementosCatalogo.forEach { libro -> if (libro.estadoLibro == EstadoLibro.DISPONIBLE.desc) librosDisponibles.add(libro) }

        return librosDisponibles
    }
}