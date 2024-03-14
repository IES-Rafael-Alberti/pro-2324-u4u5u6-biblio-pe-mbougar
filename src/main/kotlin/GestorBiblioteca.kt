package org.pebiblioteca
import java.util.*


/**
 * Clase que gestiona las operaciones relacionadas con la biblioteca.
 * @property gestorPrestamos El registro de préstamos de libros.
 * @property gestorElementos El catálogo de libros de la biblioteca.
 */
class GestorBiblioteca<T : ElementoBiblioteca>(
    private val gestorPrestamos: IGestorPrestamos,
    private val gestorElementos: GestorElementos<T>
) {

    /**
     * Agrega un libro al catálogo de la biblioteca.
     * @param elemento El elemento que se va a agregar.
     * @return Un mensaje indicando que el libro se ha agregado correctamente.
     */
    fun agregarLibro(elemento: T): String {
        gestorElementos.agregarElemento(elemento)
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
        return if (elemento != null && elemento.obtenerEstado() == EstadoElemento.DISPONIBLE.desc && elemento is Prestable) {
            elemento.prestar()
            usuario.agregarElemento(elemento)
            registrarPrestamo(elementoId, usuario)
            "${usuario.obtenerNombre()} ha tomado prestado el libro ${elemento.obtenerTitulo()}."
        } else {
            "${usuario.obtenerNombre()} no ha tomado prestado el libro ${elemento?.obtenerTitulo()}."
        }
    }

    private fun obtenerElemento(elementoId: UUID): T? {
        return gestorElementos.filtrarPorCriterio(gestorElementos.elementos.)
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
        return if (elemento != null && elemento.obtenerEstado() == EstadoElemento.PRESTADO.desc && elemento is Prestable) {
            elemento.devolver()
            usuario.eliminarElemento(elemento)
            registrarDevolucion(elementoId, usuario)
            "${usuario.obtenerNombre()} ha devuelto el libro ${gestorElementos.buscarPorId(elementoId)!!.obtenerTitulo()}."
        } else {
            "${usuario.obtenerNombre()} no tenía ${gestorElementos.buscarPorId(elementoId)!!.obtenerTitulo()} entre sus libros prestados."
        }
    }

    private fun registrarDevolucion(elementoId: UUID, usuario: Usuario) {
        gestorPrestamos.registrarDevolucion(elementoId, usuario)
    }

    /**
     * Lista todos los libros disponibles en la biblioteca.
     * @return Una lista de libros disponibles.
     */
    fun listarLibrosDisponibles(): List<T> {

        return gestorElementos.listarDisponibles()
    }

    /**
     * Lista todos los préstamos realizados para un libro dado su Id.
     * @return Una lista de préstamos.
     */
    fun consultarHistorialPrestamoLibro(elementoId: UUID): MutableList<Prestamo> {
        return gestorPrestamos.consultarHistorialElemento(elementoId)
    }

    /**
     * Lista todos los préstamos realizados para un [Usuario].
     * @return Una lista de préstamos.
     */
    fun consultarHistorialPrestamoUsuario(usuario: Usuario): MutableList<Prestamo> {
        return gestorPrestamos.consultarHistorialUsuario(usuario)
    }
}