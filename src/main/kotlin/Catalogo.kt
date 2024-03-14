package org.pebiblioteca
import java.util.*

/**
 * Clase que representa el catálogo de libros de la biblioteca.
 */
class Catalogo: ICatalogo{
    private val elementosCatalogo: MutableList<ElementoBiblioteca> = mutableListOf()

    /**
     * Agrega un libro al catálogo.
     * @param elemento El libro que se va a agregar.
     */
    override fun agregar(elemento: ElementoBiblioteca) {
        elementosCatalogo.add(elemento)
    }

    /**
     * Elimina un elemento del catálogo dado su ID.
     * @param id El ID del elemento que se va a eliminar.
     */
    override fun eliminar(id: UUID) {
        elementosCatalogo.forEach { elemento -> if (elemento.obtnerId() == id) elementosCatalogo.remove(elemento) }
    }

    /**
     * Busca un elemento en el catálogo dado su ID.
     * @param id El ID del elemento que se va a buscar.
     * @return El elemento encontrado, o null si no se encuentra.
     */
    override fun buscarPorId(id: UUID): ElementoBiblioteca? {
        return elementosCatalogo.find { it.obtnerId() == id }
    }

    /**
     * Lista todos los elementos disponibles en el catálogo.
     * @return Una lista de elementos disponibles.
     */
    override fun listarDisponibles(): List<ElementoBiblioteca> {
        val elementosDisponibles: MutableList<ElementoBiblioteca> = mutableListOf()
        elementosCatalogo.forEach { elemento -> if (elemento.obtenerEstado() == EstadoElemento.DISPONIBLE.desc) elementosDisponibles.add(elemento) }

        return elementosDisponibles
    }
}