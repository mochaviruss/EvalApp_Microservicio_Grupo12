package cl.evalapp.microservicio.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class CriterioEvaluado(
    var nombre: String = "",

    @Column(length = 500)
    var descripcion: String = "",

    var puntajeMaximo: Int = 0,

    var nivel: String = "",

    var puntaje: Int = 0,

    @Column(length = 1000)
    var comentario: String = ""
)
