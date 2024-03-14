package org.pebiblioteca
import java.util.*


interface ICatalogo{

    fun agregar(elemento: ElementoBiblioteca)

    fun eliminar(id: UUID)

    fun buscarPorId(id: UUID): ElementoBiblioteca?

    fun listarDisponibles(): List<ElementoBiblioteca>
}