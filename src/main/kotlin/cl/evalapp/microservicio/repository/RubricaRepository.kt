package cl.evalapp.microservicio.repository

import cl.evalapp.microservicio.model.Rubrica
import org.springframework.data.jpa.repository.JpaRepository

interface RubricaRepository : JpaRepository<Rubrica, Long> {

    fun findAllByOrderByNombreAsc(): List<Rubrica>
}
