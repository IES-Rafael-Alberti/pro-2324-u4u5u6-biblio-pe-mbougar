package org.pebiblioteca
import java.util.*


/**
 * Clase que gestiona las operaciones relacionadas con la biblioteca.
 * @property catalogoElementos El catálogo de libros de la biblioteca.
 * @property gestorPrestamos El registro de préstamos de libros.
 */
class GestorBiblioteca(private val gestorPrestamos: IGestorPrestamos, private val catalogoElementos: ICatalogo) {

    /**
     * Agrega un libro al catálogo de la biblioteca.
     * @param elemento El elemento que se va a agregar.
     * @return Un mensaje indicando que el libro se ha agregado correctamente.
     */
    fun agregarLibro(elemento: ElementoBiblioteca): String {
        catalogoElementos.agregar(elemento)
        return "Se ha agregado el libro ${elemento.obtenerTitulo()}."
    }

    /**
     * Presta un libro a un usuario.
     * @param elementoId El ID del libro que se va a prestar.
     * @param usuario El usuario que toma prestado el libro.
     * @return Un mensaje indicando si el préstamo se ha realizado correctamente o no.
     */
    fun prestar(elementoId: UUID, usuario: Usuario): String {
        val elemento = obtenerElemento(elementoId)
        if (elemento != null && elemento.obtenerEstado() == EstadoElemento.DISPONIBLE.desc && elemento is Prestable) {
            elemento.prestar()
            usuario.agregarElemento(elemento)
            registrarPrestamo(elementoId, usuario)
            return "${usuario.obtenerNombre()} ha tomado prestado el libro ${elemento.obtenerTitulo()}."
        } else {
            return "${usuario.obtenerNombre()} no ha tomado prestado el libro ${elemento?.obtenerTitulo()}."
        }
    }

    private fun obtenerElemento(elementoId: UUID): ElementoBiblioteca? {
        return catalogoElementos.buscarPorId(elementoId)
    }

    private fun registrarPrestamo(elementoId: UUID, usuario: Usuario) {
        gestorPrestamos.registrarPrestamo(elementoId, usuario)
    }

    /**
     * Devuelve un libro prestado por un usuario.
     * @param elementoId El ID del elemento que se va a devolver.
     * @param usuario El usuario que devuelve el libro.
     * @return Un mensaje indicando si la devolución se ha realizado correctamente o no.
     */
    fun devolver(elementoId: UUID, usuario: Usuario): String {
        val elemento = obtenerElemento(elementoId)
        if (elemento != null && elemento.obtenerEstado() == EstadoElemento.PRESTADO.desc && elemento is Prestable) {
            elemento.devolver()
            usuario.eliminarElemento(elemento)
            registrarDevolucion(elementoId, usuario)
            return("${usuario.obtenerNombre()} ha devuelto el libro ${catalogoElementos.buscarPorId(elementoId)!!.obtenerTitulo()}.")
        } else {
            return("${usuario.obtenerNombre()} no tenía ${catalogoElementos.buscarPorId(elementoId)!!.obtenerTitulo()} entre sus libros prestados.")
        }
    }

    private fun registrarDevolucion(elementoId: UUID, usuario: Usuario) {
        gestorPrestamos.registrarDevolucion(elementoId, usuario)
    }

    /**
     * Lista todos los libros disponibles en la biblioteca.
     * @return Una lista de libros disponibles.
     */
    fun listarLibrosDisponibles(): List<ElementoBiblioteca> {

        return catalogoElementos.listarDisponibles()
    }

    /**
     * Lista todos prestamos realizados para un libro dada su Id.
     * @return Una lista de prestamos.
     */
    fun consultarHistorialPrestamoLibro(elementoId: UUID): MutableList<Prestamo> {
        val historialPrestamosLibro = gestorPrestamos.consultarHistorialElemento(elementoId)

        return historialPrestamosLibro
    }

    /**
     * Lista todos prestamos realizados para un [Usuario].
     * @return Una lista de prestamos.
     */
    fun consultarHistorialPrestamoUsuario(usuario: Usuario): MutableList<Prestamo> {
        val historialPrestamosUsuario = gestorPrestamos.consultarHistorialUsuario(usuario)

        return historialPrestamosUsuario
    }
}