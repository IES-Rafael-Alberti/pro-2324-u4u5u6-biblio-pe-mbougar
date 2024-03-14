package org.pebiblioteca
import java.util.*

/**
 * Objeto que proporciona utilidades relacionadas con la biblioteca y sus usuarios.
 */
object UtilidadesBiblioteca { //Definido como singleton porque consideroq ue solo deberia haber una unica lista de UUID
    private val listaIdentidicadores: MutableList<UUID> = mutableListOf()

    /**
     * Genera un identificador único que no esté en uso para elementos de la biblioteca y sus usuarios.
     * @return Un identificador único generado.
     */
    fun generarIdentificadorUnico(): UUID {
        var identificador = UUID.randomUUID()
        while (identificador in listaIdentidicadores) {
            identificador = UUID.randomUUID()
        }
        return identificador
    }
}