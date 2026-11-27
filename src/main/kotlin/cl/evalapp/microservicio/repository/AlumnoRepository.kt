package cl.evalapp.microservicio.repository

import cl.evalapp.microservicio.model.Alumno
import org.springframework.data.jpa.repository.JpaRepository

interface AlumnoRepository : JpaRepository<Alumno, Long> {

    fun findAllByOrderByCursoAscNombreAsc(): List<Alumno>
}
