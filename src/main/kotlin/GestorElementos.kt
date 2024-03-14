package org.pebiblioteca
import java.util.*


class GestorElementos<T : ElementoBiblioteca>() {
    val elementos = Catalogo()

    fun agregarElemento(elemento: T) {
        elementos.agregar(elemento)
    }


    fun eliminarElemento(id: UUID) {
        elementos.eliminar(id)
    }


    fun obtenerElementos(): List<ElementoBiblioteca> {
        return elementos.devolverCatalogo()
    }

    //Lo he implementado lo bien que he podido pero esto me ha matado haha
    fun filtrarPorCriterio(criterio: (ElementoBiblioteca) -> Boolean): List<ElementoBiblioteca> {
        val catalogoActual = obtenerElementos()
        return catalogoActual.filter(criterio)
    }
}
