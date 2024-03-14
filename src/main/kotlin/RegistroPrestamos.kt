package org.pebiblioteca
import java.util.*

/**
 * Clase que gestiona el registro de préstamos y devoluciones de libros.
 */
class RegistroPrestamos: IGestorPrestamos {
    private val prestamosActuales: MutableMap<UUID, Usuario> = mutableMapOf()
    private val historialPrestamos: MutableList<Prestamo> = mutableListOf()

    /**
     * Registra un préstamo de libro.
     * @param elementoId El identificador único (UUID) del libro prestado.
     * @param usuario El usuario que toma en préstamo el libro.
     */
    override fun registrarPrestamo(elementoId: UUID, usuario: Usuario) {
        prestamosActuales[elementoId] = usuario
        historialPrestamos.add(Prestamo(elementoId, usuario, true))
    }

    /**
     * Registra la devolución de un libro.
     * @param elementoId El identificador único (UUID) del libro devuelto.
     * @param usuario El usuario que devuelve el libro.
     */
    override fun registrarDevolucion(elementoId: UUID, usuario: Usuario) {
        prestamosActuales.remove(elementoId)
        historialPrestamos.add(Prestamo(elementoId, usuario, false))
    }

    /**
     * Lista todos prestamos realizados para un libro dada su Id.
     * @return Una lista de prestamos.
     */
    override fun consultarHistorialElemento(elementoId: UUID): MutableList<Prestamo> {
        val historialPrestamosElemento: MutableList<Prestamo> = mutableListOf()
        historialPrestamos.forEach { prestamo -> if (prestamo.elementoId == elementoId) historialPrestamosElemento.add(prestamo) }

        return historialPrestamosElemento
    }

    /**
     * Lista todos prestamos realizados para un [Usuario].
     * @return Una lista de prestamos.
     */
    override fun consultarHistorialUsuario(usuario: Usuario): MutableList<Prestamo> {
        val historialPrestamosUsuario: MutableList<Prestamo> = mutableListOf()
        historialPrestamos.forEach { prestamo -> if (prestamo.usuario == usuario) historialPrestamosUsuario.add(prestamo) }

        return historialPrestamosUsuario
    }
}