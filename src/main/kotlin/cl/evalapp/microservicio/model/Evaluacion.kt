package cl.evalapp.microservicio.model

import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OrderColumn
import jakarta.persistence.Table
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "evaluacion")
class Evaluacion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var alumnoId: Long = 0,

    @field:NotBlank
    var alumno: String = "",

    @field:NotBlank
    var curso: String = "",

    var rubricaId: Long = 0,

    @field:NotBlank
    var rubrica: String = "",

    @field:NotBlank
    var fecha: String = "",

    @field:Min(0)
    var puntaje: Int = 0,

    @field:Min(1)
    var puntajeMaximo: Int = 1,

    var nota: Double = 1.0,

    @Column(length = 1000)
    var retroalimentacion: String = "",

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    var criterios: MutableList<CriterioEvaluado> = mutableListOf(),

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    var niveles: MutableList<Nivel> = mutableListOf(),

    @Column(length = 500)
    var grupo: String = "",

    var evaluador: String = ""
)
