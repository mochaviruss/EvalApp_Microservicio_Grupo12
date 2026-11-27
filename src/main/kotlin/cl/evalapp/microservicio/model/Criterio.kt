package cl.evalapp.microservicio.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Criterio(
    var nombre: String = "",

    @Column(length = 500)
    var descripcion: String = "",

    var puntajeMaximo: Int = 0
)
