package org.pebiblioteca

fun main() {
    val listaLibros = listOf(
    Libro(titulo = "Kotlin 1", autor = "Diego Cano", anioDePublicacion = 2024, tematica = "Fantasía"),
    Libro(titulo = "Kotlin 2", autor = "Diego Cano", anioDePublicacion = 2023, tematica = "Realismo mágico"),
    Libro(titulo = "SQL 1", autor = "Tomas Coronado", anioDePublicacion = 2023, tematica = "Realismo mágico")
    )

    val usuario1 = Usuario(nombre = "Manuel")
    val usuario2 = Usuario(nombre = "Pedro")

    val biblioteca = GestorBiblioteca()

    for (libro in listaLibros) {
        biblioteca.agregarLibro(libro)
    }

    println(biblioteca.prestarLibro(listaLibros[1].id, usuario1))
    println(biblioteca.prestarLibro(listaLibros[1].id, usuario2))

    println(biblioteca.devolverLibro(listaLibros[1].id, usuario1))
    println(biblioteca.devolverLibro(listaLibros[2].id, usuario1))
}