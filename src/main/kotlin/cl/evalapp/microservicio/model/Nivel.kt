package cl.evalapp.microservicio.model

import jakarta.persistence.Embeddable

@Embeddable
class Nivel(
    var nombre: String = "",
    var porcentaje: Int = 0
)
