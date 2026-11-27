package cl.evalapp.microservicio.repository

import cl.evalapp.microservicio.model.Evaluacion
import org.springframework.data.jpa.repository.JpaRepository

interface EvaluacionRepository : JpaRepository<Evaluacion, Long> {

    fun findAllByOrderByFechaDesc(): List<Evaluacion>
}
