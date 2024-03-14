package org.pebiblioteca
import java.util.*


/**
 * Data class que representa un usuario de la biblioteca.
 * @property id El identificador único del usuario. Por defecto se genera utilizando la utilidad [UtilidadesBiblioteca.generarIdentificadorUnico].
 * @property nombre El nombre del usuario.
 * @property elementosPrestados La lista de elementos que el usuario tiene prestados. Por defecto es una lista vacía.
 */
data class Usuario(
    private val id: UUID = UtilidadesBiblioteca.generarIdentificadorUnico(),
    private val nombre: String,
    private val elementosPrestados: MutableList<ElementoBiblioteca> = mutableListOf()
) {
    fun obtenerId() = id

    fun obtenerNombre() = nombre

    fun agregarElemento(elemento: ElementoBiblioteca) {
        elementosPrestados.add(elemento)
    }

    fun eliminarElemento(elemento: ElementoBiblioteca) {
        elementosPrestados.remove(elemento)
    }

    fun buscarElementoPrestadoPorId(elementoId: UUID): ElementoBiblioteca? {
        val elemento = elementosPrestados.find { elemento -> elemento.obtnerId() == elementoId }
        return elemento
    }

    fun listarElementosPrestados(): List<ElementoBiblioteca> {
        val elementosPrestados: MutableList<ElementoBiblioteca> = mutableListOf()
        this.elementosPrestados.forEach { elemento -> elementosPrestados.add(elemento) }

        return elementosPrestados
    }
}