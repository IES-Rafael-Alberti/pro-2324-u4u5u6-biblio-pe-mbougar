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
        return "Se ha agregado el libro ${libro.titulo}."
    }

    /**
     * Presta un libro a un usuario.
     * @param libroId El ID del libro que se va a prestar.
     * @param usuario El usuario que toma prestado el libro.
     * @return Un mensaje indicando si el préstamo se ha realizado correctamente o no.
     */
    fun prestarLibro(libroId: UUID, usuario: Usuario): String {
        val libro = catalogoLibros.buscarPorId(libroId)
        if (libro != null && libro.estadoLibro == EstadoLibro.DISPONIBLE.desc) {
            libro.estadoLibro = EstadoLibro.PRESTADO.desc
            usuario.librosPrestados.add(libro)
            registroPrestamos.registrarPrestamo(libroId, usuario)
            return("${usuario.nombre} ha tomado prestado el libro ${catalogoLibros.buscarPorId(libroId)!!.titulo}.")
        } else {
            return("${usuario.nombre} no ha tomado prestado el libro ${catalogoLibros.buscarPorId(libroId)!!.titulo}.")
        }
    }

    /**
     * Devuelve un libro prestado por un usuario.
     * @param libroId El ID del libro que se va a devolver.
     * @param usuario El usuario que devuelve el libro.
     * @return Un mensaje indicando si la devolución se ha realizado correctamente o no.
     */
    fun devolverLibro(libroId: UUID, usuario: Usuario): String {
        val libro = usuario.librosPrestados.find { it.id == libroId }
        if (libro != null && libro.estadoLibro == EstadoLibro.PRESTADO.desc) {
            libro.estadoLibro = EstadoLibro.DISPONIBLE.desc
            usuario.librosPrestados.remove(libro)
            registroPrestamos.registrarDevolucion(libroId, usuario)
            return("${usuario.nombre} ha devuelto el libro ${catalogoLibros.buscarPorId(libroId)!!.titulo}.")
        } else {
            return("${usuario.nombre} no tenía ${catalogoLibros.buscarPorId(libroId)!!.titulo} entre sus libros prestados.")
        }
    }

    /**
     * Lista todos los libros disponibles en la biblioteca.
     * @return Una lista de libros disponibles.
     */
    fun listarLibrosDisponibles(): List<Libro> {

        return catalogoLibros.listarDisponibles()
    }
}