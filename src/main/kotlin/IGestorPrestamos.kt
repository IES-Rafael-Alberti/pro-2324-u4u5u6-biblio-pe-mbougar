package org.pebiblioteca
import java.util.*


/**
 * Interfaz para la gestion de préstamos de elementos.
 */
interface IGestorPrestamos {
    fun registrarPrestamo(elementoId: UUID, usuario: Usuario)
    fun registrarDevolucion(elementoId: UUID, usuario: Usuario)
    fun consultarHistorialElemento(elementoId: UUID): MutableList<Prestamo>
    fun consultarHistorialUsuario(usuario: Usuario): MutableList<Prestamo>
}