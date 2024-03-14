package org.pebiblioteca

import java.util.*

data class Prestamo(val id: UUID, val usuario: Usuario, val prestado: Boolean)