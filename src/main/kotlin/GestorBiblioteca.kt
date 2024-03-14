package org.pebiblioteca
import java.util.*


class GestorBiblioteca {
    private val catalogoLibros = Catalogo()
    private val registroPrestamos = RegistroPrestamos()

    fun agregarLibro(libro: Libro): String {
        catalogoLibros.agregar(libro)
        return "Se ha agregado el libro ${libro.titulo}."
    }

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

    fun listarLibrosDisponibles(): List<Libro> {

        return catalogoLibros.listarDisponibles()
    }
}