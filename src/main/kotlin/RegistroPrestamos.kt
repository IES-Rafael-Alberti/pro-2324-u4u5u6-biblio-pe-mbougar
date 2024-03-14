package org.pebiblioteca
import java.util.*

class RegistroPrestamos {
    private val prestamosActuales: MutableMap<UUID, Usuario> = mutableMapOf()
    private val historialPrestamos: MutableList<Prestamo> = mutableListOf()

    fun registrarPrestamo(id: UUID, usuario: Usuario) {
        prestamosActuales[id] = usuario
        historialPrestamos.add(Prestamo(id, usuario, true))
    }

    fun registrarDevolucion(id: UUID, usuario: Usuario) {
        prestamosActuales.remove(id)
        historialPrestamos.add(Prestamo(id, usuario, false))
    }
}