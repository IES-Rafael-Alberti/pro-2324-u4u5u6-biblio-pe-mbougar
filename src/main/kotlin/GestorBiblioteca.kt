package org.pebiblioteca
import java.util.*


/**
 * Clase que gestiona las operaciones relacionadas con la biblioteca.
 * @property catalogoLibros El catálogo de libros de la biblioteca.
 * @property registroPrestamos El registro de préstamos de libros.
 */
class GestorBiblioteca {
    private val catalogoLibros = Catalogo()
    private val registroPrestamos = RegistroPrestamos()

    /**
     * Agrega un libro al catálogo de la biblioteca.
     * @param libro El libro que se va a agregar.
     * @return Un mensaje indicando que el libro se ha agregado correctamente.
     */
    fun agregarLibro(libro: Libro): String {
        catalogoLibros.agregar(libro)
        return "Se ha agregado el libro ${libro.obtenerTitulo()}."
    }

    /**
     * Presta un libro a un usuario.
     * @param libroId El ID del libro que se va a prestar.
     * @param usuario El usuario que toma prestado el libro.
     * @return Un mensaje indicando si el préstamo se ha realizado correctamente o no.
     */
    fun prestarLibro(libroId: UUID, usuario: Usuario): String {
        val libro = catalogoLibros.buscarPorId(libroId)
        if (libro != null && libro.obtenerEstadoLibro() == EstadoLibro.DISPONIBLE.desc) {
            libro.cambiarEstado()
            usuario.agregarLibro(libro)
            registroPrestamos.registrarPrestamo(libroId, usuario)
            return("${usuario.obtenerNombre()} ha tomado prestado el libro ${catalogoLibros.buscarPorId(libroId)!!.obtenerTitulo()}.")
        } else {
            return("${usuario.obtenerNombre()} no ha tomado prestado el libro ${catalogoLibros.buscarPorId(libroId)!!.obtenerTitulo()}.")
        }
    }

    /**
     * Devuelve un libro prestado por un usuario.
     * @param libroId El ID del libro que se va a devolver.
     * @param usuario El usuario que devuelve el libro.
     * @return Un mensaje indicando si la devolución se ha realizado correctamente o no.
     */
    fun devolverLibro(libroId: UUID, usuario: Usuario): String {
        val libro = usuario.buscarLibroPrestadoPorId(libroId)
        if (libro != null && libro.obtenerEstadoLibro() == EstadoLibro.PRESTADO.desc) {
            libro.cambiarEstado()
            usuario.eliminarLibro(libro)
            registroPrestamos.registrarDevolucion(libroId, usuario)
            return("${usuario.obtenerNombre()} ha devuelto el libro ${catalogoLibros.buscarPorId(libroId)!!.obtenerTitulo()}.")
        } else {
            return("${usuario.obtenerNombre()} no tenía ${catalogoLibros.buscarPorId(libroId)!!.obtenerTitulo()} entre sus libros prestados.")
        }
    }

    /**
     * Lista todos los libros disponibles en la biblioteca.
     * @return Una lista de libros disponibles.
     */
    fun listarLibrosDisponibles(): List<Libro> {

        return catalogoLibros.listarDisponibles()
    }

    /**
     * Lista todos prestamos realizados para un libro dada su Id.
     * @return Una lista de prestamos.
     */
    fun consultarHistorialPrestamoLibro(libroId: UUID): MutableList<Prestamo> {
        val historialPrestamosLibro = registroPrestamos.consultarHistorialLibro(libroId)

        return historialPrestamosLibro
    }

    /**
     * Lista todos prestamos realizados para un [Usuario].
     * @return Una lista de prestamos.
     */
    fun consultarHistorialPrestamoUsuario(usuario: Usuario): MutableList<Prestamo> {
        val historialPrestamosUsuario = registroPrestamos.consultarHistorialUsuario(usuario)

        return historialPrestamosUsuario
    }
}