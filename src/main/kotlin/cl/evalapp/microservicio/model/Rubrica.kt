package cl.evalapp.microservicio.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OrderColumn
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "rubrica")
class Rubrica(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotBlank
    var nombre: String = "",

    @field:NotBlank
    var asignatura: String = "",

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    var criterios: MutableList<Criterio> = mutableListOf(),

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    var niveles: MutableList<Nivel> = mutableListOf()
)
