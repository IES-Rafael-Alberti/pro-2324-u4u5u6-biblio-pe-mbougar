package org.pebiblioteca
import java.util.*

/**
 * Clase que gestiona el registro de préstamos y devoluciones de libros.
 */
class RegistroPrestamos {
    private val prestamosActuales: MutableMap<UUID, Usuario> = mutableMapOf()
    private val historialPrestamos: MutableList<Prestamo> = mutableListOf()

    /**
     * Registra un préstamo de libro.
     * @param id El identificador único (UUID) del libro prestado.
     * @param usuario El usuario que toma en préstamo el libro.
     */
    fun registrarPrestamo(id: UUID, usuario: Usuario) {
        prestamosActuales[id] = usuario
        historialPrestamos.add(Prestamo(id, usuario, true))
    }

    /**
     * Registra la devolución de un libro.
     * @param id El identificador único (UUID) del libro devuelto.
     * @param usuario El usuario que devuelve el libro.
     */
    fun registrarDevolucion(id: UUID, usuario: Usuario) {
        prestamosActuales.remove(id)
        historialPrestamos.add(Prestamo(id, usuario, false))
    }

    /**
     * Lista todos prestamos realizados para un libro dada su Id.
     * @return Una lista de prestamos.
     */
    fun consultarHistorialLibro(libroId: UUID): MutableList<Prestamo> {
        val historialPrestamosLibro: MutableList<Prestamo> = mutableListOf()
        historialPrestamos.forEach { prestamo -> if (prestamo.libroId == libroId) historialPrestamosLibro.add(prestamo) }

        return historialPrestamosLibro
    }

    /**
     * Lista todos prestamos realizados para un [Usuario].
     * @return Una lista de prestamos.
     */
    fun consultarHistorialUsuario(usuario: Usuario): MutableList<Prestamo> {
        val historialPrestamosUsuario: MutableList<Prestamo> = mutableListOf()
        historialPrestamos.forEach { prestamo -> if (prestamo.usuario == usuario) historialPrestamosUsuario.add(prestamo) }

        return historialPrestamosUsuario
    }
}