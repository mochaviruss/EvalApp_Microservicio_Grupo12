package cl.evalapp.microservicio.service

import cl.evalapp.microservicio.model.Alumno
import cl.evalapp.microservicio.repository.AlumnoRepository
import org.springframework.stereotype.Service

@Service
class AlumnoService(private val repository: AlumnoRepository) {

    fun listar(): List<Alumno> = repository.findAllByOrderByCursoAscNombreAsc()

    fun buscar(id: Long): Alumno? = repository.findById(id).orElse(null)

    fun crear(alumno: Alumno): Alumno {
        alumno.id = null
        return repository.save(alumno)
    }

    fun actualizar(id: Long, alumno: Alumno): Alumno? {
        if (!repository.existsById(id)) {
            return null
        }
        alumno.id = id
        return repository.save(alumno)
    }

    fun eliminar(id: Long): Boolean {
        if (!repository.existsById(id)) {
            return false
        }
        repository.deleteById(id)
        return true
    }
}
